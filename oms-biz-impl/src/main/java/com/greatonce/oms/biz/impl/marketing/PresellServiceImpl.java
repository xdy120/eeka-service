package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.marketing.PresellDetailService;
import com.greatonce.oms.biz.marketing.PresellService;
import com.greatonce.oms.biz.marketing.PresellStoreDetailService;
import com.greatonce.oms.biz.marketing.PresellStoreService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.stock.StockUploadService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.SyncStockUploadBO;
import com.greatonce.oms.bo.stock.SyncStockUploadUploadSkuBO;
import com.greatonce.oms.dao.marketing.PresellDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.MarketingType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.marketing.PresellDetailStatus;
import com.greatonce.oms.domain.enums.marketing.PresellStatus;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.marketing.PresellDetail;
import com.greatonce.oms.domain.marketing.PresellStore;
import com.greatonce.oms.domain.marketing.PresellStoreDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.marketing.PresellQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预售.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class PresellServiceImpl extends AbstractVersionService<Presell, PresellQuery> implements
    PresellService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PresellServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.MARKETING_PRESELL);
  @Autowired
  private PresellDao dao;
  @Resource
  private IdGenerator presellIdGenerator;
  @Resource
  private CodeGenerator presellCodeGenerator;
  @Autowired
  private PresellDetailService presellDetailService;
  @Autowired
  private PresellStoreService presellStoreService;
  @Autowired
  private PresellStoreDetailService presellStoreDetailService;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private StockUploadService stockUploadService;

  @Override
  protected QueryDao<Presell, PresellQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return presellIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public int create(Presell record) {
    initDefaultValue(record);
    //编程式事务
    int count = getTransactionTemplate().executeWithResult(() -> {
      presellStoreService.batchCreate(record.getStores());
      presellDetailService.batchCreate(record.getDetails());
      return insert(record);
    });
    BIZ_LOGGER.log(record.getPresellId(), BizLogger.ADD);
    return count;
  }

  @Override
  protected void initDefaultValue(Presell record) {
    super.initDefaultValue(record);
    if (record.isDynamic() == null) {
      record.setDynamic(false);
    }
    record.setStatus(PresellStatus.CREATED);
    record.setPresellCode(presellCodeGenerator.next());
    record.setCreator(BizContext.getNickname());
    record.getDetails().forEach(detail -> detail.setPresellId(record.getPresellId()));
    record.getStores().forEach(store -> store.setPresellId(record.getPresellId()));
  }


  /**
   * 开始预售活动.
   */
  @Override
  public void begin(Presell presell, VersionBO bo) {
    if (presell.getStatus() != PresellStatus.AUDITED) {
      throw new OmsException("已审核的预售才能开始");
    }

    presell.setStatus(PresellStatus.BEGIN);
    presell.setVersion(bo.getVersion());

    List<PresellStore> stores = presellStoreService.listStores(presell.getPresellId());
    List<PresellStoreDetail> details = new ArrayList<>();
    for (PresellStore store : stores) {
      details.addAll(beginStore(presell, store));
    }
    try {
      getTransactionTemplate().execute(() -> {
        presellStoreDetailService.batchModify(details);
        update(presell);
      });
    } catch (Exception e) {
      LOGGER.error("开始预售失败，预售信息:{}", JsonUtil.toJson(presell));
      LOGGER.error("开始预售失败，堆栈信息:", e);
      throw new OmsException("开始预售失败");
    }
    BIZ_LOGGER.log(presell.getPresellId(), BizLogger.BEGIN);
  }

  private List<PresellStoreDetail> beginStore(Presell presell, PresellStore store) {
    List<PresellStoreDetail> details = presellStoreDetailService
        .listNotStart(presell.getPresellId(), store.getStoreId());
    Assert.notEmpty(details, "所有明细已开始");
    SyncStockUploadBO syncStockUploadBo = new SyncStockUploadBO();
    syncStockUploadBo.setStoreId(store.getStoreId());
    syncStockUploadBo.setListing(true);
    List<SyncStockUploadUploadSkuBO> itemBOS = new ArrayList<>(details.size());
    for (PresellStoreDetail detail : details) {
      productMallMappingService
          .beginMarketing(detail.getStoreId(), presell.getPresellId(), MarketingType.PRE_SELL,
              presell.isDynamic(), detail.getPresellDetail().getSkuId(),
              detail.getPresellDetail().getMallProductId());

      SyncStockUploadUploadSkuBO itemBO = new SyncStockUploadUploadSkuBO();
      itemBO.setMallProductId(detail.getPresellDetail().getMallProductId());
      itemBO.setProductCode(detail.getPresellDetail().getProductCode());
      itemBO.setSkuCode(detail.getPresellDetail().getSkuCode());
      itemBO.setSkuId(detail.getPresellDetail().getSkuId());
      itemBO
          .setQuantity((int) (detail.getPresellDetail().getQuantity() * store.getRate() / 100.00));
      itemBO.setUploadType(StockUploadType.COVER);
      itemBOS.add(itemBO);
    }
    syncStockUploadBo.setSkus(itemBOS);
    syncStockUploadBo.setBatchNo(presell.getPresellCode());

    final Map<Long, SyncStockUploadUploadSkuBO> uploadMap = stockUploadService
        .syncManualUpload(syncStockUploadBo, presell.getPresellCode(), BizContext.getNickname(),
            "预售计划开始");
    for (PresellStoreDetail detail : details) {
      SyncStockUploadUploadSkuBO itemBO = uploadMap.get(detail.getPresellDetail().getSkuId());
      if (itemBO.isSuccess()) {
        detail.setStatus(PresellDetailStatus.STARTED);
        detail.setRemark("已开始");
      } else {
        detail.setRemark(itemBO.getMsg());
      }
    }
    return details;
  }

  /**
   * 结束预售活动.
   */
  @Override
  public void end(Presell presell, VersionBO bo) {
    if (presell.getStatus() != PresellStatus.BEGIN) {
      throw new OmsException("已开始的预售才能结束");
    }
    List<PresellStore> stores = presellStoreService.listStores(presell.getPresellId());
    List<PresellDetail> details = presellDetailService.listDetails(presell.getPresellId());
    List<PresellStoreDetail> allStoreDetails = new ArrayList<>(details.size() * stores.size());
    List<StockChangedMessage> messages = new ArrayList<>(details.size());
    for (PresellDetail detail : details) {
      messages.add(new StockChangedMessage(presell.getPresellCode(), detail.getSkuId(),
          BizContext.getNickname(), "预售结束"));
    }
    for (PresellStore store : stores) {
      List<PresellStoreDetail> storeDetails = presellStoreDetailService
          .listStarted(presell.getPresellId(), store.getStoreId());
      for (PresellStoreDetail storeDetail : storeDetails) {
        storeDetail.setStatus(PresellDetailStatus.FINISHED);
        storeDetail.setRemark("已结束");
      }
      allStoreDetails.addAll(storeDetails);
    }
    presell.setStatus(PresellStatus.END);
    presell.setVersion(bo.getVersion());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        for (PresellStoreDetail detail : allStoreDetails) {
          productMallMappingService
              .endMarketing(detail.getStoreId(), true, detail.getPresellDetail().getSkuId(), null);
        }
        presellStoreDetailService.batchModify(allStoreDetails);
        update(presell);
      });
      getMqProducer().send(messages);
    } catch (Exception e) {
      LOGGER.error("结束预售失败，预售信息:{}", JsonUtil.toJson(presell));
      LOGGER.error("结束预售失败，堆栈信息:", e);
      throw new OmsException("结束预售失败");
    }
    BIZ_LOGGER.log(presell.getPresellId(), BizLogger.END);
  }

  /**
   * 预售审核.
   */
  @Override
  public void audit(Presell presell, VersionBO bo) {
    if (presell.getStatus() != PresellStatus.CREATED) {
      throw new OmsException("新建的预售才能审核");
    }

    List<PresellStore> stores = presellStoreService.listStores(presell.getPresellId());
    List<PresellDetail> details = presellDetailService.listDetails(presell.getPresellId());
    List<PresellStoreDetail> storeDetails = new ArrayList<>(stores.size() * details.size());
    stores.forEach(store -> details.forEach(detail -> {
      if (detail.getQuantity() == null || detail.getQuantity().equals(0)) {
        throw new OmsException(detail.getSkuCode() + "没有预售数量");
      }
      PresellStoreDetail presellStoreDetail = new PresellStoreDetail();
      presellStoreDetail.setPresellDetailId(detail.getPresellDetailId());
      presellStoreDetail.setPresellId(presell.getPresellId());
      presellStoreDetail.setBeginTime(presell.getBeginTime());
      presellStoreDetail.setEndTime(presell.getEndTime());
      presellStoreDetail.setStoreId(store.getStoreId());
      presellStoreDetail.setStatus(PresellDetailStatus.NOT_STARTED);
      storeDetails.add(presellStoreDetail);
    }));

    Presell updateInfo = new Presell();
    updateInfo.setPresellId(presell.getPresellId());
    updateInfo.setStatus(PresellStatus.AUDITED);
    updateInfo.setAuditedTime(LocalDateTime.now());
    updateInfo.setAuditor(BizContext.getNickname());
    updateInfo.setVersion(bo.getVersion());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        presellStoreDetailService.batchCreate(storeDetails);
        modify(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("预售审核失败，预售信息:{}", JsonUtil.toJson(presell));
      LOGGER.error("预售审核失败，堆栈信息:", e);
      throw new OmsException("预售审核失败");
    }
    BIZ_LOGGER.log(presell.getPresellId(), BizLogger.AUDIT);
  }

  @Override
  public void retry(Presell presell, PresellStore presellStore) {
    if (presell.getStatus() != PresellStatus.BEGIN) {
      throw new OmsException("预售还未开始");
    }
    List<PresellStoreDetail> details = beginStore(presell, presellStore);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        for (PresellStoreDetail detail : details) {
          if (detail.getStatus() == PresellDetailStatus.STARTED) {
            productMallMappingService
                .beginMarketing(detail.getStoreId(), presell.getPresellId(), MarketingType.PRE_SELL,
                    presell.isDynamic(), detail.getPresellDetail().getSkuId(), null);
          }
        }
        presellStoreDetailService.batchModify(details);
      });
    } catch (Exception e) {
      LOGGER.error("预售重传失败，预售信息:{}", JsonUtil.toJson(presell));
      LOGGER.error("预售重传失败，堆栈信息:", e);
      throw new OmsException("预售重传失败！");
    }
    BIZ_LOGGER.log(presell.getPresellId(), "失败重传", "店铺：{}", presellStore.getStoreName());
  }

  /**
   * 作废预售.
   */
  @Override
  public void invalid(Presell presell, VersionBO bo) {
    if (presell.getStatus() == PresellStatus.BEGIN
        || presell.getStatus() == PresellStatus.INVALID) {
      throw new OmsException("预售已开始或已作废");
    }
    Presell updateInfo = new Presell();
    updateInfo.setPresellId(presell.getPresellId());
    updateInfo.setStatus(PresellStatus.INVALID);
    updateInfo.setVersion(bo.getVersion());
    update(updateInfo);
    BIZ_LOGGER.log(presell.getPresellId(), BizLogger.INVALID);
  }

  /**
   * 获取店铺上传比例.
   */
  @Override
  public Integer getStoreRate(Long presellId, Long storeId) {
    PresellStore eg = new PresellStore();
    eg.setStoreId(storeId);
    eg.setPresellId(presellId);
    PresellStore store = presellStoreService.getByExample(eg);
    return Assert.isNull(store) ? null : store.getRate();
  }
}
package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.RabbitMqProducer;
import com.greatonce.oms.biz.marketing.ActivityDetailService;
import com.greatonce.oms.biz.marketing.ActivityService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.stock.StockUploadService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.marketing.BeginActivityDetailBO;
import com.greatonce.oms.bo.marketing.EndActivityDetailBO;
import com.greatonce.oms.bo.stock.SyncStockUploadBO;
import com.greatonce.oms.bo.stock.SyncStockUploadUploadSkuBO;
import com.greatonce.oms.dao.marketing.ActivityDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.MarketingType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.marketing.ActivityDetailStatus;
import com.greatonce.oms.domain.enums.marketing.ActivityStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.domain.marketing.ActivityDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.marketing.ActivityQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动报名.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class ActivityServiceImpl extends AbstractVersionService<Activity, ActivityQuery> implements
    ActivityService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.MARKETING_ACTIVITY);
  @Autowired
  private ActivityDao dao;
  @Autowired
  private ActivityDetailService activityDetailService;
  @Resource
  private IdGenerator activityIdGenerator;
  @Resource
  private CodeGenerator activityCodeGenerator;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private StockUploadService stockUploadService;
  @Autowired
  private RabbitMqProducer mqProducer;

  @Override
  protected QueryDao<Activity, ActivityQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return activityIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public int create(Activity record) {
    initDefaultValue(record);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        activityDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getActivityId(), BizLogger.ADD);
      return count;
    } catch (Exception e) {
      LOGGER.error("活动报名新增失败，活动信息：{}", JsonUtil.toJson(record));
      LOGGER.error("活动报名新增失败，堆栈信息：", e);
      throw new OmsException("活动报名新增失败");
    }
  }

  @Override
  protected void initDefaultValue(Activity activity) {
    super.initDefaultValue(activity);
    activity.setStatus(ActivityStatus.CREATED);
    activity.setActivityCode(activityCodeGenerator.next());
    activity.setCreator(BizContext.getNickname());
    activity.setGoodsValue(0.00D);
    activity.getDetails().forEach(item -> {
      item.setActivityId(activity.getActivityId());
      activity.setGoodsValue(activity.getGoodsValue() + item.getPrice() * item.getPlanQuantity());
    });
  }

  @Override
  public int modify(Activity record) {
    return update(record);
  }

  @Override
  public int remove(Activity entity) {
    return delete(entity.getActivityId());
  }

  @Override
  public void begin(Activity activity, VersionBO bo) {
    if (activity.getStatus() != ActivityStatus.AUDITED) {
      throw new OmsException("只有已审核的活动才能开始");
    }
    List<ActivityDetail> details = activityDetailService.listByActivityId(activity.getActivityId());

    SyncStockUploadBO syncStockUploadBo = new SyncStockUploadBO();
    syncStockUploadBo.setStoreId(activity.getStoreId());
    syncStockUploadBo.setListing(true);
    final List<SyncStockUploadUploadSkuBO> itemBOS = details.stream().map(x -> {
      SyncStockUploadUploadSkuBO itemBO = new SyncStockUploadUploadSkuBO();
      itemBO.setMallProductId(x.getMallProductId());
      itemBO.setProductCode(x.getProductCode());
      itemBO.setSkuCode(x.getSkuCode());
      itemBO.setSkuId(x.getSkuId());
      itemBO.setQuantity(x.getLockQuantity());
      itemBO.setUploadType(StockUploadType.COVER);
      return itemBO;
    }).collect(Collectors.toList());
    syncStockUploadBo.setSkus(itemBOS);
    syncStockUploadBo.setBatchNo(activity.getActivityCode());

    final Map<Long, SyncStockUploadUploadSkuBO> uploadMap = stockUploadService
        .syncManualUpload(syncStockUploadBo, activity.getActivityCode(), BizContext.getNickname(),
            "活动报名开始");
    for (ActivityDetail detail : details) {
      SyncStockUploadUploadSkuBO itemBO = uploadMap.get(detail.getSkuId());
      if (itemBO.isSuccess()) {
        detail.setStatus(ActivityDetailStatus.STARTED);
      } else {
        detail.setRemark(itemBO.getMsg());
      }
    }
    //上传库存
    Activity updateInfo = new Activity();
    updateInfo.setActivityId(activity.getActivityId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(ActivityStatus.BEGIN);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        for (ActivityDetail detail : details) {
          productMallMappingService
              .beginMarketing(activity.getStoreId(), activity.getActivityId(),
                  MarketingType.ACTIVITY,
                  false, detail.getSkuId(), detail.getMallProductId());
          activityDetailService.modify(detail);
        }
        update(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("开始活动报名失败，活动信息：{}", JsonUtil.toJson(activity));
      LOGGER.error("开始活动报名失败，堆栈信息：", e);
      throw new OmsException("开始活动报名失败");
    }
    BIZ_LOGGER.log(activity.getActivityId(), BizLogger.BEGIN);
  }

  @Override
  public void end(Activity activity, VersionBO bo) {
    if (activity.getStatus() != ActivityStatus.BEGIN) {
      throw new OmsException("只有进行中的活动才能结束");
    }
    List<ActivityDetail> details = activityDetailService.listByActivityId(activity.getActivityId());
    Activity updateInfo = new Activity();
    updateInfo.setActivityId(activity.getActivityId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(ActivityStatus.END);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        for (ActivityDetail detail : details) {
          productMallMappingService
              .endMarketing(activity.getStoreId(), true, detail.getSkuId(),
                  detail.getMallProductId());
          activityDetailService.end(activity, detail);
        }
        stockOccupancyService
            .deleteOccupancy(activity.getActivityId(), StockOccupancyType.ACTIVITY);
        update(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("结束活动报名失败，活动信息：{}", JsonUtil.toJson(activity));
      LOGGER.error("结束活动报名失败，堆栈信息：", e);
      throw new OmsException("结束活动报名失败");
    }

    details.stream().map(x -> new StockChangedMessage(activity.getActivityCode(), x.getSkuId(),
        BizContext.getNickname(), "活动报名结束")).forEach(msg -> mqProducer.send(msg));

    BIZ_LOGGER.log(activity.getActivityId(), BizLogger.END);
  }

  @Override
  public void beginDetail(Activity activity, BeginActivityDetailBO bo) {

  }

  @Override
  public void endDetail(Activity activity, EndActivityDetailBO bo) {

  }

  @Override
  public void audit(Activity activity, VersionBO bo) {
    if (activity.getStatus() != ActivityStatus.CREATED) {
      throw new OmsException("只有新建的活动才能审核");
    }
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(activity.getVirtualWarehouseId());
    List<ActivityDetail> details = activityDetailService.listAvailable(activity);
    Activity updateInfo = new Activity();
    updateInfo.setActivityId(activity.getActivityId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(ActivityStatus.AUDITED);
    updateInfo.setAuditor(BizContext.getNickname());
    updateInfo.setAuditedTime(LocalDateTime.now());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        for (ActivityDetail detail : details) {
          int count = Math.min(detail.getPlanQuantity(), detail.getLockQuantity());
          count = count < 0 ? 0 : count;
          stockOccupancyService.create(DomainUtil
              .createStockOccupancy(virtualWarehouse.getWarehouseId(),
                  virtualWarehouse.getWarehouseName(),
                  virtualWarehouse.getVirtualWarehouseId(),
                  virtualWarehouse.getVirtualWarehouseName(),
                  StockOccupancyType.ACTIVITY,
                  detail.getSkuId(), detail.getSkuCode(), count, activity.getActivityId(),
                  detail.getActivityDetailId(), StockOccupancyStatus.LOCK));
          detail.setLockQuantity(count);
          activityDetailService.updateLockQuantity(detail);
        }
        update(updateInfo);
      });
      mqProducer.send(details.stream()
          .map(x -> new StockChangedMessage(activity.getActivityCode(), x.getSkuId(),
              BizContext.getNickname(), "活动报名审核")).collect(Collectors.toList()));
      BIZ_LOGGER.log(activity.getActivityId(), BizLogger.AUDIT);
    } catch (Exception e) {
      LOGGER.error("审核活动报名失败，活动信息：{}", JsonUtil.toJson(activity));
      LOGGER.error("审核活动报名失败，堆栈信息：", e);
      throw new OmsException("审核活动报名失败");
    }
  }

  @Override
  public void occupancy(Activity activity, VersionBO bo) {
    if (activity.getStatus() != ActivityStatus.AUDITED) {
      throw new OmsException("只有已审核的活动才能补占库存");
    }
    List<ActivityDetail> details = activityDetailService.listAvailable(activity);
    Activity updateInfo = new Activity();
    updateInfo.setActivityId(activity.getActivityId());
    updateInfo.setVersion(bo.getVersion());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        for (ActivityDetail detail : details) {
          int count = Math.min(detail.getPlanQuantity(), detail.getLockQuantity());
          count = count < 0 ? 0 : count;
          stockOccupancyService
              .adjustQuantity(activity.getActivityId(), detail.getActivityDetailId(),
                  StockOccupancyType.ACTIVITY, count);
          activityDetailService.updateLockQuantity(detail);
        }
        update(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("补充活动占用失败，活动信息：{}", JsonUtil.toJson(activity));
      LOGGER.error("补充活动占用失败，堆栈信息：", e);
      throw new OmsException("补充活动占用失败");
    }
    mqProducer.send(
        details.stream().map(x -> new StockChangedMessage(activity.getActivityCode(), x.getSkuId(),
            BizContext.getNickname(), "活动报名补充占用")).collect(Collectors.toList()));
    BIZ_LOGGER.log(activity.getActivityId(), "补充占用");
  }

  @Override
  public void invalid(Activity activity, VersionBO bo) {
    if (activity.getStatus() == ActivityStatus.BEGIN
        || activity.getStatus() == ActivityStatus.INVALID) {
      throw new OmsException("活动已开始或作废");
    }

    Activity updateInfo = new Activity();
    updateInfo.setActivityId(activity.getActivityId());
    updateInfo.setStatus(ActivityStatus.INVALID);
    updateInfo.setVersion(bo.getVersion());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        if (activity.getStatus() == ActivityStatus.AUDITED) {
          stockOccupancyService
              .deleteOccupancy(activity.getActivityId(), StockOccupancyType.ACTIVITY);
        }
        update(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("作废活动报名失败，活动信息：{}", JsonUtil.toJson(activity));
      LOGGER.error("作废活动报名失败，堆栈信息：", e);
      throw new OmsException("作废活动报名失败");
    }

    if (activity.getStatus() == ActivityStatus.AUDITED) {
      List<ActivityDetail> details = activityDetailService
          .listByActivityId(activity.getActivityId());
      details.stream().map(x -> new StockChangedMessage(activity.getActivityCode(), x.getSkuId(),
          BizContext.getNickname(), "活动报名作废")).forEach(msg -> mqProducer.send(msg));
    }
    BIZ_LOGGER.log(activity.getActivityId(), BizLogger.INVALID);
  }
}

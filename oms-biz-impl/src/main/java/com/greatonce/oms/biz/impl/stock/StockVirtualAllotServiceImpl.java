package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.stock.StockVirtualAllotDetailService;
import com.greatonce.oms.biz.stock.StockVirtualAllotService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.dao.stock.StockVirtualAllotDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.stock.StockVirtualAllotStatus;
import com.greatonce.oms.domain.stock.StockVirtualAllot;
import com.greatonce.oms.domain.stock.StockVirtualAllotDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.stock.StockVirtualAllotDetailQuery;
import com.greatonce.oms.query.stock.StockVirtualAllotQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 虚拟调拨. StockVirtualAllot <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-01-17
 */

@Service
public class StockVirtualAllotServiceImpl extends
    AbstractVersionService<StockVirtualAllot, StockVirtualAllotQuery> implements
    StockVirtualAllotService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.STOCK_VIRTUAL_ALLOT);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockVirtualAllotServiceImpl.class);

  @Autowired
  private StockVirtualAllotDao dao;
  @Autowired
  private StockVirtualAllotDetailService stockVirtualAllotDetailService;
  @Autowired
  private StockService stockService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Resource
  private IdGenerator stockVirtualAllotIdGenerator;
  @Resource
  private CodeGenerator stockVirtualAllotCodeGenerator;
  @Autowired
  private SettingService settingService;
  @Autowired
  private MessageExporter messageExporter;


  @Override
  protected QueryDao<StockVirtualAllot, StockVirtualAllotQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.stockVirtualAllotIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(StockVirtualAllot entity) {
    super.initDefaultValue(entity);
    entity.setCreator(BizContext.getNickname());
    entity.setStatus(StockVirtualAllotStatus.CREATED);
    entity.setStockVirtualAllotId(stockVirtualAllotIdGenerator.next());
    entity.setStockVirtualAllotCode(stockVirtualAllotCodeGenerator.next());
    entity.setCreator(BizContext.getNickname());
    int planQuantity = 0;
    entity.setQuantity(0);
    for (StockVirtualAllotDetail item : entity.getDetails()) {
      item.setStockVirtualAllotId(entity.getStockVirtualAllotId());
      planQuantity += item.getPlanQuantity();
    }
    entity.setPlanQuantity(planQuantity);
  }

  @Override
  public int create(StockVirtualAllot record) {
    try {
      initDefaultValue(record);
      int result = getTransactionTemplate().executeWithResult(() -> {
        stockVirtualAllotDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getStockVirtualAllotId(), BizLogger.ADD);
      return result;
    } catch (Exception e) {
      LOGGER.error("插入虚拟调拨失败，虚拟调拨：{}", JsonUtil.toJson(record));
      LOGGER.error("插入虚拟调拨失败，堆栈信息：", e);
      throw new OmsException("插入虚拟调拨失败！");
    }
  }


  private void allot(StockVirtualAllot allot, VirtualWarehouse outWarehouse,
      VirtualWarehouse inWarehouse, List<StockVirtualAllotDetail> list) {
    int quantity = 0;
    for (StockVirtualAllotDetail detail : list) {
      if (detail.getQuantity() > 0) {
        if (detail.getQuantity() > detail.getPlanQuantity()) {
          detail.setQuantity(detail.getPlanQuantity());
        }
        stockService.adjustQuantity(allot.getStockVirtualAllotCode(), OrderType.VIRTUAL_ALLOW,
            detail.getSkuId(), detail.getSkuCode(), outWarehouse, -detail.getQuantity());
        stockService.adjustQuantity(allot.getStockVirtualAllotCode(), OrderType.VIRTUAL_ALLOW,
            detail.getSkuId(), detail.getSkuCode(), inWarehouse, detail.getQuantity());
        quantity += detail.getQuantity();
      } else {
        detail.setQuantity(0);
      }
    }
    allot.setQuantity(quantity);
  }


  @Override
  public void audit(StockVirtualAllot allot, VersionBO bo) {
    if (allot.getStatus() != StockVirtualAllotStatus.CREATED) {
      throw new OmsException("调拨已经审核或作废！");
    }
    StockSetting stockSetting = settingService.getInventorySetting();
    VirtualWarehouse inVirtualWarehouse = virtualWarehouseService
        .getByKey(allot.getInVirtualWarehouseId());
    VirtualWarehouse outVirtualWarehouse = virtualWarehouseService
        .getByKey(allot.getOutVirtualWarehouseId());
    List<StockVirtualAllotDetail> details;
    if (stockSetting.getVirtualAllocationType() == StockSetting.AllocationType.AVAILABLE_BINS) {
      details = stockVirtualAllotDetailService.listAvailable(allot.getStockVirtualAllotId());
    } else {
      details = stockVirtualAllotDetailService.listSaleable(allot.getStockVirtualAllotId());
    }

    StockVirtualAllot updateInfo = new StockVirtualAllot();
    updateInfo.setStockVirtualAllotId(allot.getStockVirtualAllotId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(StockVirtualAllotStatus.AUDITED);
    updateInfo.setAuditedTime(LocalDateTime.now());
    updateInfo.setAuditor(BizContext.getNickname());

    try {
      getTransactionTemplate().execute(() -> {
        allot(allot, outVirtualWarehouse, inVirtualWarehouse, details);
        stockVirtualAllotDetailService.batchModify(details);
        updateInfo.setQuantity(allot.getQuantity());
        update(updateInfo);
      });
      getMqProducer().send(details.stream().map(
          x -> new StockChangedMessage(allot.getStockVirtualAllotCode(), x.getSkuId(),
              BizContext.getNickname(), "虚拟调拨审核")).collect(Collectors.toList()));
      BIZ_LOGGER.log(allot.getStockVirtualAllotId(), BizLogger.AUDIT);
    } catch (Exception e) {
      LOGGER.error("审核虚拟调拨失败，虚拟调拨：{}", JsonUtil.toJson(allot));
      LOGGER.error("审核虚拟调拨失败，堆栈信息：", e);
      throw new OmsException("审核虚拟调拨失败！");
    }
  }

  @Override
  public void invalid(StockVirtualAllot allot, VersionBO bo) {
    if (allot.getStatus() != StockVirtualAllotStatus.CREATED) {
      throw new OmsException("调拨已经审核或作废！");
    }
    StockVirtualAllot updateInfo = new StockVirtualAllot();
    updateInfo.setStockVirtualAllotId(allot.getStockVirtualAllotId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(StockVirtualAllotStatus.INVALID);
    modify(updateInfo);
    BIZ_LOGGER.log(allot.getStockVirtualAllotId(), BizLogger.INVALID);
  }

  @Override
  public void exportAllot(Long allotId, String fileName) {
    StockVirtualAllotDetailQuery query = new StockVirtualAllotDetailQuery();
    query.setStockVirtualAllotId(allotId);
    final List<StockVirtualAllotDetail> details = stockVirtualAllotDetailService.list(query);
    ExcelHeaderCollection<StockVirtualAllotDetail> headers = new ExcelHeaderCollection<>();
    headers.add("商品编码", StockVirtualAllotDetail::getProductCode);
    headers.add("商品名称", StockVirtualAllotDetail::getProductName);
    headers.add("规格编码", StockVirtualAllotDetail::getSkuCode);
    headers.add("规格名称", StockVirtualAllotDetail::getSkuName);
    headers.add("计划数量", x -> FormatUtil.formatInteger(x.getPlanQuantity()));
    headers.add("实调数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    messageExporter.exportExcel(headers, details, fileName);
  }
}
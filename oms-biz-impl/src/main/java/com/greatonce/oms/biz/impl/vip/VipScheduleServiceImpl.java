package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.vip.VipScheduleDetailService;
import com.greatonce.oms.biz.vip.VipScheduleService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.dao.vip.VipScheduleDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.base.setting.StockSetting.AllocationType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.vip.VipScheduleStatus;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.vip.VipSchedule;
import com.greatonce.oms.domain.vip.VipScheduleDetail;
import com.greatonce.oms.query.vip.VipScheduleQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 唯品档期. VipSchedule <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipScheduleServiceImpl extends
    AbstractVersionService<VipSchedule, VipScheduleQuery> implements
    VipScheduleService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.VIP_SCHEDULE);
  private static final Logger LOGGER = LoggerFactory.getLogger(VipScheduleServiceImpl.class);
  @Autowired
  private IdGenerator vipScheduleIdGenerator;
  @Autowired
  private CodeGenerator vipScheduleCodeGenerator;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private VipScheduleDetailService vipScheduleDetailService;
  @Autowired
  private VipScheduleDao dao;
  @Autowired
  private SettingService settingService;


  @Override
  protected QueryDao<VipSchedule, VipScheduleQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipScheduleIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(VipSchedule entity) {
    super.initDefaultValue(entity);
    entity.setVipScheduleCode(vipScheduleCodeGenerator.next());
    entity.setStatus(VipScheduleStatus.CREATED);
    entity.setCreator(BizContext.getNickname());
    entity.setVersion(0);
    entity.setGoodsValue(0D);

    if (entity.getDetails() != null && entity.getDetails().size() > 0) {
      Double goodsValueSum = 0.00;
      for (VipScheduleDetail detail : entity.getDetails()) {
        detail.setVipScheduleId(entity.getVipScheduleId());
        goodsValueSum += detail.getGoodsValue();
      }
      entity.setGoodsValue(goodsValueSum);
    }
  }

  @Override
  public int create(VipSchedule record) {
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        vipScheduleDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getVipScheduleId(), "唯品档期新增成功", BizLogger.ADD);
      return count;
    } catch (Exception e) {
      LOGGER.error("唯品档期新增失败,档期信息: {}", JsonUtil.toJson(record));
      LOGGER.error("唯品档期新增失败,堆栈信息: ", e);
      throw new OmsException("唯品档期新增失败");
    }
  }

  @Override
  public void audit(VipSchedule schedule, VersionBO bo) {
    if (schedule.getStatus() != VipScheduleStatus.CREATED) {
      throw new OmsException("只有新建的唯品档期才能审核");
    }
    StockSetting stockSetting = settingService.getInventorySetting();
    List<VipScheduleDetail> lockedDetails;
    if (stockSetting.getDeliveryAllocationType() == AllocationType.SOLD_OUT) {
      lockedDetails = vipScheduleDetailService.listSaleable(schedule.getVipScheduleId());
    } else {
      lockedDetails = vipScheduleDetailService.listAvailable(schedule.getVipScheduleId());
    }
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(schedule.getVirtualWarehouseId());
    List<StockOccupancy> stockOccupancies = new ArrayList<>();
    schedule.getDetails().forEach(detail -> {
      if (detail.getLockQuantity() > 0 && detail.getLockQuantity() > detail.getPlanQuantity()) {
        detail.setLockQuantity(detail.getPlanQuantity());
        StockOccupancy stockOccupancy = DomainUtil
            .createStockOccupancy(virtualWarehouse.getWarehouseId(),
                virtualWarehouse.getWarehouseName(),
                virtualWarehouse.getVirtualWarehouseId(),
                virtualWarehouse.getVirtualWarehouseName(),
                StockOccupancyType.VIP_SCHEDULE, detail.getSkuId(), detail.getSkuCode(),
                detail.getLockQuantity(), schedule.getVipScheduleId(),
                detail.getVipScheduleDetailId(), StockOccupancyStatus.LOCK);
        stockOccupancies.add(stockOccupancy);
      } else {
        detail.setLockQuantity(0);
      }
    });

    schedule.setStatus(VipScheduleStatus.AUDITED);
    schedule.setAuditor(BizContext.getNickname());
    schedule.setAuditedTime(LocalDateTime.now());
    schedule.setVersion(bo.getVersion());
    try {
      getTransactionTemplate().execute(() -> {
        vipScheduleDetailService.batchModify(lockedDetails);
        stockOccupancyService.batchCreate(stockOccupancies);
        update(schedule);
      });
      BIZ_LOGGER.log(schedule.getVipScheduleId(), "唯品档期审核成功", BizLogger.AUDIT);
    } catch (Exception e) {
      LOGGER.error("审核唯品档期失败,档期信息: {}", JsonUtil.toJson(schedule));
      LOGGER.error("审核唯品档期失败,堆栈信息: ", e);
      throw new OmsException("审核唯品档期失败");
    }
  }

  @Override
  public void upload(VipSchedule entity) {
    entity.setStatus(VipScheduleStatus.UPLOADED);
    modify(entity);
    BIZ_LOGGER.log(entity.getVipScheduleId(), "开始上传");
  }

  @Override
  public void end(VipSchedule entity) {
    entity.setStatus(VipScheduleStatus.FINISHED);
    modify(entity);
    BIZ_LOGGER.log(entity.getVipScheduleId(), "结束上传");
  }

}
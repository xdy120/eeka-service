package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.vip.VipDispatchDetailService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.dao.vip.VipDispatchDetailDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.vip.VipDispatchStatus;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.query.vip.VipDispatchDetailQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 唯品配货单明细. VipDispatchDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipDispatchDetailServiceImpl extends
    AbstractDetailService<VipDispatch, VipDispatchDetail, VipDispatchDetailQuery> implements
    VipDispatchDetailService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.VIP_DISPATCH);
  private static final Logger LOGGER = LoggerFactory.getLogger(VipDispatchDetailServiceImpl.class);
  @Resource
  private IdGenerator vipDispatchDetailIdGenerator;
  @Resource
  private VipDispatchDetailDao dao;
  @Resource
  private VipDispatchService vipDispatchService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private ProductSkuService productSkuService;

  @Override
  protected BizService<VipDispatch, ? extends Query> getMainService() {
    return vipDispatchService;
  }

  @Override
  protected QueryDao<VipDispatchDetail, VipDispatchDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<VipDispatchDetail> getDetails(VipDispatch vipDispatch) {
    return vipDispatch.getDetails();
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipDispatchDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(VipDispatchDetail detail) {
    super.initDefaultValue(detail);
    detail.setOutQuantity(0);
    detail.setVipAmount(0D);
    if (Assert.isNull(detail.getVipPrice()) || detail.getVipPrice() <= 0) {
      detail.setVipPrice(0D);
      detail.setVipPriceAbnormal(true);
    } else {
      detail.setVipPriceAbnormal(false);
    }
    if (Assert.isNull(detail.getVipBarcode())) {
      detail.setVipBarcode(detail.getSkuCode());
    }
    if (Assert.isNull(detail.isAbnormal())) {
      detail.setAbnormal(false);
    }
  }

  /**
   * 唯品拣货单明细新增.
   *
   * @param list not null
   */
  @Override
  public int batchCreate(Collection<? extends VipDispatchDetail> list) {
    list.forEach(this::initDefaultValue);
    return super.insertBatch(list);
  }

  @Override
  public List<VipDispatchDetail> listDetails(Long vipDispatchId) {
    VipDispatchDetailQuery vipDispatchDetailFilter = new VipDispatchDetailQuery();
    vipDispatchDetailFilter.setVipDispatchId(vipDispatchId);
    return list(vipDispatchDetailFilter);
  }

  @Override
  public int createDetail(VipDispatch record) {
    if (VipDispatchStatus.CREATED == record.getStatus()) {
      try {
        int count = getTransactionTemplate().executeWithResult(() -> {
          batchCreate(record.getDetails());
          adjustNoticeQuantity(record);
          return getMainService().modify(record);
        });
        BIZ_LOGGER.log(record.getVipDispatchId(), BizLogger.ADD, "新增唯品拣货单明细成功");
        return count;
      } catch (Exception e) {
        LOGGER.error("新增唯品拣货单明细失败,拣货单明细信息: {}", JsonUtil.toJson(record.getDetails()));
        LOGGER.error("新增唯品拣货单明细失败,堆栈信息: ", e);
        throw new OmsException("新增唯品拣货单明细失败");
      }
    } else {
      throw new OmsException("只有新建状态的拣货单才能增加明细");
    }
  }

  @Override
  public int modifyDetail(VipDispatch record) {
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        updateBatch(record.getDetails());
        adjustNoticeQuantity(record);
        return getMainService().modify(record);
      });
      BIZ_LOGGER.log(record.getVipDispatchId(), BizLogger.UPDATE, "修改唯品拣货单明细成功");
      return count;
    } catch (Exception e) {
      LOGGER.error("修改唯品拣货单明细失败,拣货单明细信息: {}", JsonUtil.toJson(record.getDetails()));
      LOGGER.error("修改唯品拣货单明细失败,堆栈信息: ", e);
      throw new OmsException("修改唯品拣货单明细失败");
    }
  }

  @Override
  public int removeDetail(VipDispatch record) {
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        record.getDetails().forEach(x -> delete(x.getVipDispatchDetailId()));
        adjustNoticeQuantity(record);
        return getMainService().modify(record);
      });
      BIZ_LOGGER.log(record.getVipDispatchId(), BizLogger.DELETE, "删除唯品拣货单成功");
      return count;
    } catch (Exception e) {
      LOGGER.error("删除唯品拣货单失败,拣货单明细信息: {}", JsonUtil.toJson(record.getDetails()));
      LOGGER.error("删除唯品拣货单失败,堆栈信息: ", e);
      throw new OmsException("删除唯品拣货单失败");
    }
  }

  public void adjustNoticeQuantity(VipDispatch vipDispatch) {
    List<VipDispatchDetail> details = listDetails(vipDispatch.getVipDispatchId());
    int noticeQuantity = 0;
    for (VipDispatchDetail detail : details) {
      noticeQuantity += detail.getNoticeQuantity();
      if (detail.isVipPriceAbnormal()) {
        vipDispatch.setVipPriceAbnormal(true);
      }
    }
    vipDispatch.setNoticeQuantity(noticeQuantity);
  }

}
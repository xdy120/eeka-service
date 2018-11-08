package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.dao.trade.SalesOrderDetailDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.query.trade.SalesOrderDetailQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 销售订单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class SalesOrderDetailServiceImpl extends
    AbstractDetailService<SalesOrder, SalesOrderDetail, SalesOrderDetailQuery> implements
    SalesOrderDetailService {

  @Autowired
  private SalesOrderDetailDao dao;
  @Autowired
  private SalesOrderService salesOrderService;
  @Resource
  private IdGenerator salesOrderDetailIdGenerator;

  @Override
  protected BizService<SalesOrder, ? extends Query> getMainService() {
    return salesOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return salesOrderDetailIdGenerator;
  }

  @Override
  protected QueryDao<SalesOrderDetail, SalesOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected void initDefaultValue(SalesOrderDetail entity) {
    super.initDefaultValue(entity);
    entity.setDropShipping(Assert.isTrue(entity.isDropShipping()));
    entity.setPrepackage(Assert.isTrue(entity.isPrepackage()));
    entity.setCombination(Assert.isTrue(entity.isCombination()));
    entity.setCombinationDetail(Assert.isTrue(entity.isCombinationDetail()));
    entity.setLockStock(Assert.isTrue(entity.isLockStock()));
    entity.setManualAdd(Assert.isTrue(entity.isManualAdd()));
    entity.setSeparate(Assert.isTrue(entity.isSeparate()));
    entity.setOos(Assert.isTrue(entity.isOos()));
    entity.setGift(Assert.isTrue(entity.isGift()));
    entity.setNeedReturnGoods(Assert.isTrue(entity.isNeedReturnGoods()));
    entity.setProductAbnormal(Assert.isTrue(entity.isProductAbnormal()));
    entity.setDeleted(false);
  }

  @Override
  protected List<SalesOrderDetail> getDetails(SalesOrder salesOrder) {
    return salesOrder.getDetails();
  }

  @Override
  public List<SalesOrderDetail> listBySalesOrderId(Long salesOrderId) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrderId);
    return dao.listExample(eg);
  }

  @Override
  public List<SalesOrderDetail> listSimpleBySalesOrderId(Long salesOrderId) {
    SalesOrderDetailQuery eg = new SalesOrderDetailQuery();
    eg.setSalesOrderId(salesOrderId);
    return dao.listSimple(eg);
  }

  @Override
  public List<SalesOrderDetail> listNormalBySalesOrderId(Long salesOrderId) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrderId);
    eg.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    return dao.listExample(eg);
  }

  @Override
  public List<SalesOrderDetail> listSimpleNormal(Long salesOrderId) {
    return dao.listSimpleNormal(salesOrderId);
  }

  @Override
  public List<SalesOrderDetail> listByMallDetailId(Long salesOrderId, String mallDetailId) {
    SalesOrderDetail detail = new SalesOrderDetail();
    detail.setSalesOrderId(salesOrderId);
    detail.setMallDetailId(mallDetailId);
    return dao.listExample(detail);
  }

  @Override
  public void refund(SalesOrder salesOrder, SalesOrderDetail detail) {
    SalesOrderDetail updateInfo = new SalesOrderDetail();
    updateInfo.setSalesOrderDetailId(detail.getSalesOrderDetailId());
    updateInfo.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    updateInfo.setOos(false);
    update(updateInfo);
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
  }

  @Override
  public void refund(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details) {
    List<SalesOrderDetail> updateList = new ArrayList<>(details.size());
    for (SalesOrderDetail detail : details) {
      SalesOrderDetail updateInfo = new SalesOrderDetail();
      updateInfo.setSalesOrderDetailId(detail.getSalesOrderDetailId());
      updateInfo.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
      updateInfo.setOos(false);
      updateList.add(updateInfo);
    }
    updateBatch(updateList);
    for (SalesOrderDetail detail : details) {
      detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    }
  }

  @Override
  public void refund(SalesOrder salesOrder) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrder.getSalesOrderId());
    SalesOrderDetail detail = new SalesOrderDetail();
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    detail.setOos(false);
    updateByExample(detail, eg);
  }

  @Override
  public void applyRefund(SalesOrder salesOrder, SalesOrderDetail detail) {
    SalesOrderDetail updateInfo = new SalesOrderDetail();
    updateInfo.setSalesOrderDetailId(detail.getSalesOrderDetailId());
    updateInfo.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.APPLY);
    update(updateInfo);
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.APPLY);
  }

  @Override
  public void applyRefund(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details) {
    List<SalesOrderDetail> updateList = new ArrayList<>(details.size());
    for (SalesOrderDetail detail : details) {
      SalesOrderDetail updateInfo = new SalesOrderDetail();
      updateInfo.setSalesOrderDetailId(detail.getSalesOrderDetailId());
      updateInfo.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.APPLY);
      updateList.add(updateInfo);
    }
    updateBatch(updateList);
    for (SalesOrderDetail detail : details) {
      detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.APPLY);
    }
  }

  @Override
  public void cancelRefund(SalesOrder salesOrder, SalesOrderDetail detail) {

    SalesOrderDetail updateInfo = new SalesOrderDetail();
    updateInfo.setSalesOrderDetailId(detail.getSalesOrderDetailId());
    updateInfo.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    update(updateInfo);
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
  }

  @Override
  public void cancelRefund(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details) {
    List<SalesOrderDetail> updateList = new ArrayList<>(details.size());
    for (SalesOrderDetail detail : details) {
      SalesOrderDetail updateInfo = new SalesOrderDetail();
      updateInfo.setSalesOrderDetailId(detail.getSalesOrderDetailId());
      updateInfo.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
      updateList.add(updateInfo);
    }
    updateBatch(updateList);
    for (SalesOrderDetail detail : details) {
      detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    }
  }

  @Override
  public void cancelRefund(SalesOrder salesOrder) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrder.getSalesOrderId());
    SalesOrderDetail detail = new SalesOrderDetail();
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    updateByExample(detail, eg);
  }

  @Override
  public void invalid(SalesOrder salesOrder, SalesOrderDetail detail) {
    SalesOrderDetail updateInfo = new SalesOrderDetail();
    updateInfo.setSalesOrderDetailId(detail.getSalesOrderDetailId());
    updateInfo.setStatus(SalesOrderDetailStatus.INVALID);
    updateInfo.setOos(false);
    update(updateInfo);
    detail.setStatus(SalesOrderDetailStatus.INVALID);
  }

  @Override
  public void invalid(SalesOrder salesOrder) {
    SalesOrderDetail value = new SalesOrderDetail();
    value.setStatus(SalesOrderDetailStatus.INVALID);
    value.setOos(false);
    SalesOrderDetail filter = new SalesOrderDetail();
    filter.setSalesOrderId(salesOrder.getSalesOrderId());
    filter.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    updateByExample(value, filter);
  }

  @Override
  public void replace(SalesOrder salesOrder, SalesOrderDetail source, SalesOrderDetail target) {
    Map<String, SalesOrderDetail> map = new HashMap<>(2);
    map.put("source", source);
    map.put("target", target);
    //批量替换时的判断
    if (target.getQuantity() == null && source.getQuantity() != null) {
      target.setQuantity(source.getQuantity());
    }
    dao.replace(map);
  }

  @Override
  public void replace(SalesOrder salesOrder, SalesOrderDetail source,
      List<SalesOrderDetail> targets) {
    getTransactionTemplate().execute(() -> {
      delete(salesOrder, source);
      batchCreate(targets);
    });
  }

  @Override
  public void delete(SalesOrder salesOrder, SalesOrderDetail source) {
    if (source.getSalesOrderDetailType() == SalesOrderDetailType.ORIGINAL) {
      throw new OmsException("原始明细不允许删除");
    }
    SalesOrderDetail update = new SalesOrderDetail();
    update.setSalesOrderDetailId(source.getSalesOrderDetailId());
    update.setDeleted(true);
    update(update);
  }

  @Override
  public void reset(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details) {
    updateStatus(details, SalesOrderDetailStatus.WAITING);
  }

  @Override
  public List<SalesOrderDetail> getSalesOrderDetails(Long salesOrderId) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrderId);
    eg.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    eg.setStatus(SalesOrderDetailStatus.WAITING);
    eg.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    return dao.listExample(eg);
  }

  private void updateStatus(Collection<? extends SalesOrderDetail> details,
      SalesOrderDetailStatus status) {
    List<SalesOrderDetail> updateInfo = new ArrayList<>(details.size());
    for (SalesOrderDetail detail : details) {
      SalesOrderDetail update = new SalesOrderDetail();
      update.setSalesOrderDetailId(detail.getSalesOrderDetailId());
      update.setStatus(status);
      updateInfo.add(update);
    }
    super.updateBatch(updateInfo);
  }

  @Override
  public void dispatch(Collection<? extends SalesOrderDetail> details) {
    List<SalesOrderDetail> updateInfo = new ArrayList<>(details.size());
    for (SalesOrderDetail detail : details) {
      SalesOrderDetail update = new SalesOrderDetail();
      update.setSalesOrderDetailId(detail.getSalesOrderDetailId());
      update.setStatus(SalesOrderDetailStatus.DISPATCHED);
      update.setOos(false);
      updateInfo.add(update);
    }
    super.updateBatch(updateInfo);
  }

  @Override
  public void cancelDispatch(SalesOrder salesOrder,
      Collection<? extends SalesOrderDetail> details) {
    updateStatus(details, SalesOrderDetailStatus.WAITING);
  }

  /**
   * 获取平台发货明细
   */
  @Override
  public List<SalesOrderDetail> listToMallDeliveryDetailsInfo(Long salesOrderId) {
    return dao.listToMallDeliveryDetailsInfo(salesOrderId);
  }

  @Override
  public void clearOos() {
    dao.clearOos();
  }

  @Override
  public List<SalesOrderDetail> listDispatchableBySalesOrderId(Long salesOrderId) {
    return dao.listDispatchableBySalesOrderId(salesOrderId);
  }

  @Override
  public void wmsDelivery(SalesOrder salesOrder, Collection<? extends Long> detailIds) {
    List<SalesOrderDetail> details = detailIds.stream().map(x -> {
      SalesOrderDetail detail = new SalesOrderDetail();
      detail.setSalesOrderDetailId(x);
      return detail;
    }).collect(Collectors.toList());
    updateStatus(details, SalesOrderDetailStatus.DELIVERED);
  }
}
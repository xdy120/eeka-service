package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.dao.trade.DispatchOrderDetailDao;
import com.greatonce.oms.domain.enums.trade.DispatchOrderDetailStatus;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.query.trade.DispatchOrderDetailQuery;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配货单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-08
 */
@Service
public class DispatchOrderDetailServiceImpl extends
    AbstractService<DispatchOrderDetail, DispatchOrderDetailQuery> implements
    DispatchOrderDetailService {

  @Autowired
  private DispatchOrderDetailDao dao;
  @Resource
  private IdGenerator dispatchOrderDetailIdGenerator;

  @Override
  protected QueryDao<DispatchOrderDetail, DispatchOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return dispatchOrderDetailIdGenerator;
  }

  @Override
  public void cancel(DispatchOrder dispatchOrder, DispatchOrderDetail detail) {
    DispatchOrderUtil.isBeforeDelivery(detail);
    detail.setStatus(DispatchOrderDetailStatus.CANCELED);
    modify(detail);
  }

  @Override
  public void cancel(DispatchOrder dispatchOrder) {
    DispatchOrderDetail value = new DispatchOrderDetail();
    value.setStatus(DispatchOrderDetailStatus.CANCELED);
    DispatchOrderDetail filter = new DispatchOrderDetail();
    filter.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    updateByExample(value, filter);
  }

  @Override
  public List<DispatchOrderDetail> listSimple(Long dispatchOrderId) {
    return dao.listSimple(dispatchOrderId);
  }

  @Override
  public List<DispatchOrderDetail> listByDispatchOrderId(Long dispatchOrderId) {
    DispatchOrderDetail example = new DispatchOrderDetail();
    example.setDispatchOrderId(dispatchOrderId);
    return listExample(example);
  }

  @Override
  public void delivery(DispatchOrder dispatchOrder,
      Collection<? extends DispatchOrderDetail> details) {
    List<DispatchOrderDetail> updates = details.stream().map(x -> {
      DispatchOrderDetail update = new DispatchOrderDetail();
      update.setDispatchOrderDetailId(x.getDispatchOrderDetailId());
      update.setOutQuantity(x.getOutQuantity());
      update.setStatus(DispatchOrderDetailStatus.DELIVERED);
      if (dispatchOrder.isOfflineDelivery()) {
        update.setVirtualWarehouseId(x.getVirtualWarehouseId());
        update.setVirtualWarehouseName(x.getVirtualWarehouseName());
      }
      return update;
    }).collect(Collectors.toList());
    updateBatch(updates);
  }

  @Override
  public int batchCreate(Collection<? extends DispatchOrderDetail> collection) {
    collection.forEach(x -> initDefaultValue(x));
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends DispatchOrderDetail> collection) {
    return updateBatch(collection);
  }
}
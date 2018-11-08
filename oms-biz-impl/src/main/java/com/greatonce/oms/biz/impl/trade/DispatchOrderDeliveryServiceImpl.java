package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.dao.trade.DispatchOrderDeliveryDao;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.query.trade.DispatchOrderDeliveryQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配货单发货信息.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-08
 */
@Service
public class DispatchOrderDeliveryServiceImpl extends
    AbstractService<DispatchOrderDelivery, DispatchOrderDeliveryQuery> implements
    DispatchOrderDeliveryService {

  @Autowired
  private DispatchOrderDeliveryDao dao;
  @Resource
  private IdGenerator dispatchOrderDeliveryIdGenerator;

  @Override
  protected QueryDao<DispatchOrderDelivery, DispatchOrderDeliveryQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return dispatchOrderDeliveryIdGenerator;
  }

  @Override
  public List<DispatchOrderDelivery> listByDispatchOrderId(Long dispatchOrderId) {
    DispatchOrderDelivery eg = new DispatchOrderDelivery();
    eg.setDispatchOrderId(dispatchOrderId);
    return listExample(eg);
  }

  @Override
  public int batchCreate(Collection<? extends DispatchOrderDelivery> collection) {
    collection.forEach(x -> initDefaultValue(x));
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends DispatchOrderDelivery> collection) {
    return updateBatch(collection);
  }
}
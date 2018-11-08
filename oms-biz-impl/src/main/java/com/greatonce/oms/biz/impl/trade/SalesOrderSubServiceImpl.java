package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.trade.SalesOrderSubService;
import com.greatonce.oms.dao.trade.SalesOrderSubDao;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.query.trade.SalesOrderSubQuery;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单附属信息.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class SalesOrderSubServiceImpl extends
    AbstractService<SalesOrderSub, SalesOrderSubQuery> implements SalesOrderSubService {

  @Autowired
  private SalesOrderSubDao dao;

  @Override
  protected QueryDao<SalesOrderSub, SalesOrderSubQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected void initDefaultValue(SalesOrderSub entity) {
    super.initDefaultValue(entity);
    if (Assert.isEmpty(entity.getSellerMemo())) {
      entity.setSellerMemo(null);
    }
    if (Assert.isEmpty(entity.getBuyerMemo())) {
      entity.setBuyerMemo(null);
    }
    entity.setBlacklist(Assert.isTrue(entity.isBlacklist()));
    entity.setHasInvoice(Assert.isTrue(entity.isHasInvoice()));
    entity.setNewMember(Assert.isTrue(entity.isNewMember()));
    entity.setPrepay(Assert.isTrue(entity.isPrepay()));
    entity.setThirdDelivery(Assert.isTrue(entity.isThirdDelivery()));
    entity.setCod(Assert.isTrue(entity.isCod()));
  }

  @Override
  public int batchCreate(Collection<? extends SalesOrderSub> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends SalesOrderSub> collection) {
    return updateBatch(collection);
  }
}
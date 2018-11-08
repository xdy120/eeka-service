package com.greatonce.oms.biz.impl.mall;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.mall.MallRefundOrderService;
import com.greatonce.oms.dao.mall.MallRefundOrderDao;
import com.greatonce.oms.domain.mall.MallRefundOrder;
import com.greatonce.oms.message.trade.MallRefundOrderDownloadMessage;
import com.greatonce.oms.query.mall.MallRefundOrderQuery;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * MallRefundOrder <br/> 商城退单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-19
 */

@Service
public class MallRefundOrderServiceImpl extends
    AbstractService<MallRefundOrder, MallRefundOrderQuery> implements MallRefundOrderService {

  @Autowired
  private MallRefundOrderDao dao;
  @Resource
  private IdGenerator mallRefundOrderIdGenerator;

  @Override
  protected QueryDao<MallRefundOrder, MallRefundOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return mallRefundOrderIdGenerator;
  }


  @Override
  public int batchCreate(Collection<? extends MallRefundOrder> collection) {
    collection.forEach(this::initDefaultValue);
    int count = getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
    collection.forEach(
        x -> getMqProducer().send(
            new MallRefundOrderDownloadMessage(x.getMallRefundOrderId(), x.getStoreId(),
                x.getTradeId(), x.getMallRefundId())));
    return count;
  }

  @Override
  public int batchModify(Collection<? extends MallRefundOrder> collection) {
    return updateBatch(collection);
  }
}
package com.greatonce.oms.biz.impl.mall;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.mall.MallExchangeOrderService;
import com.greatonce.oms.dao.mall.MallExchangeOrderDao;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.message.trade.MallExchangeOrderDownloadMessage;
import com.greatonce.oms.query.mall.MallExchangeOrderQuery;
import java.util.Collection;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * MallExchangeOrder <br/> 商城换货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-30
 */

@Service
public class MallExchangeOrderServiceImpl extends
    AbstractService<MallExchangeOrder, MallExchangeOrderQuery> implements
    MallExchangeOrderService {

  @Autowired
  private MallExchangeOrderDao dao;
  @Resource
  private IdGenerator mallExchangeOrderIdGenerator;

  @Override
  protected QueryDao<MallExchangeOrder, MallExchangeOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return mallExchangeOrderIdGenerator;
  }


  @Override
  public int batchCreate(Collection<? extends MallExchangeOrder> collection) {
    collection.forEach(this::initDefaultValue);
    int count = getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
    collection.forEach(x -> getMqProducer()
        .send(new MallExchangeOrderDownloadMessage(x.getMallExchangeOrderId(), x.getStoreId(),
            x.getTradeId(), x.getMallExchangeId())));
    return count;
  }

  @Override
  public int batchModify(Collection<? extends MallExchangeOrder> collection) {
    return getTransactionTemplate().executeWithResult(() -> updateBatch(collection));
  }
}
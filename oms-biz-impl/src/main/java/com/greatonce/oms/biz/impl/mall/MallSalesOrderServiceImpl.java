package com.greatonce.oms.biz.impl.mall;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.dao.mall.MallSalesOrderDao;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.message.trade.MallSalesOrderDownloadMessage;
import com.greatonce.oms.message.trade.MallSalesOrderImportedMessage;
import com.greatonce.oms.query.mall.MallSalesOrderQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * MallSalesOrder <br/> 商城订单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-01-15
 */
@Service
public class MallSalesOrderServiceImpl extends
    AbstractService<MallSalesOrder, MallSalesOrderQuery> implements MallSalesOrderService {

  @Autowired
  private MallSalesOrderDao dao;
  @Resource
  private IdGenerator mallSalesOrderIdGenerator;

  @Override
  protected QueryDao<MallSalesOrder, MallSalesOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return mallSalesOrderIdGenerator;
  }

  @Override
  public int create(MallSalesOrder record) {
    initDefaultValue(record);
    int count = insert(record);
    getMqProducer().send(new MallSalesOrderDownloadMessage(record.getMallSalesOrderId(),
        record.getStoreId(), record.getTradeId()));
    return count;
  }

  @Override
  public void importSalesOrder(Store store, Collection<MallSalesOrderInfo> mallSalesOrderInfos) {
    List<MallSalesOrder> list = new ArrayList<>();
    for (MallSalesOrderInfo orderInfo : mallSalesOrderInfos) {
      MallSalesOrder order = new MallSalesOrder();
      order.setMallSalesOrderId(mallSalesOrderIdGenerator.next());
      order.setStoreId(store.getStoreId());
      order.setStoreName(store.getStoreName());
      order.setStatus(MallDataProcessStatus.WAITING);
      order.setTradeId(orderInfo.getTradeId());
      order.setMallSalesOrderStatus(orderInfo.getStatus());
      order.setOrderJson(JsonUtil.toJson(orderInfo));
      list.add(order);
    }
    getTransactionTemplate().execute(() -> insertBatch(list));
    list.forEach(x -> {
      MallSalesOrderImportedMessage importedMessage = new
          MallSalesOrderImportedMessage(x.getMallSalesOrderId(), x.getStoreId(), x.getTradeId());
      getMqProducer().send(importedMessage);
    });
  }

  @Override
  public int batchCreate(Collection<? extends MallSalesOrder> collection) {
    collection.forEach(this::initDefaultValue);
    int count = insertBatch(collection);
    for (MallSalesOrder mallSalesOrder : collection) {
      getMqProducer().send(new MallSalesOrderDownloadMessage(mallSalesOrder.getMallSalesOrderId(),
          mallSalesOrder.getStoreId(), mallSalesOrder.getTradeId()));
    }
    return count;
  }

  @Override
  public int batchModify(Collection<? extends MallSalesOrder> collection) {
    return updateBatch(collection);
  }
}
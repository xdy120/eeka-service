package com.greatonce.oms.biz.mall;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.query.mall.MallSalesOrderQuery;
import java.util.Collection;

/**
 * MallSalesOrder <br/>
 * 商城订单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface MallSalesOrderService extends
    BatchBizService<MallSalesOrder, MallSalesOrderQuery> {

  void importSalesOrder(Store store, Collection<MallSalesOrderInfo> values);
}
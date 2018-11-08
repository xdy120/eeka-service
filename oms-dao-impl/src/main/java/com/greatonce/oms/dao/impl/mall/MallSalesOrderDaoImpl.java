package com.greatonce.oms.dao.impl.mall;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.mall.MallSalesOrderDao;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.query.mall.MallSalesOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * MallSalesOrder <br/>
 * 商城订单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class MallSalesOrderDaoImpl extends AbstractOmsDao<MallSalesOrder,MallSalesOrderQuery> implements MallSalesOrderDao {

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.mall.MallSalesOrderMapper";
    }

}
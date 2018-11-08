package com.greatonce.oms.dao.impl.purchase;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.purchase.PurchaseOrderDao;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.query.purchase.PurchaseOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * PurchaseOrder <br/>
 * 采购订单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class PurchaseOrderDaoImpl extends AbstractOmsDao<PurchaseOrder,PurchaseOrderQuery> implements PurchaseOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.purchase.PurchaseOrderMapper";
    }

    @Override
    public PageList<PurchaseOrder> listPage(PurchaseOrderQuery purchaseOrderQuery, int page,
        int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("advanceQuery"),purchaseOrderQuery,page,pageSize);
    }
}
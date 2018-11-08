package com.greatonce.oms.dao.impl.purchase;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.purchase.PurchaseNoticeOrderDao;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * PurchaseNoticeOrder <br/>
 * 采购入库通知单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class PurchaseNoticeOrderDaoImpl extends AbstractOmsDao<PurchaseNoticeOrder,PurchaseNoticeOrderQuery> implements PurchaseNoticeOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.purchase.PurchaseNoticeOrderMapper";
    }
}
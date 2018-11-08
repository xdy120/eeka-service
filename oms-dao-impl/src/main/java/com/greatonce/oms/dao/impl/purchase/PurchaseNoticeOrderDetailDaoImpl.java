package com.greatonce.oms.dao.impl.purchase;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.purchase.PurchaseNoticeOrderDetailDao;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderDetailQuery;
import org.springframework.stereotype.Repository;

/**
 * PurchaseNoticeOrderDetail <br/>
 * 采购入库通知单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class PurchaseNoticeOrderDetailDaoImpl extends AbstractOmsDao<PurchaseNoticeOrderDetail,PurchaseNoticeOrderDetailQuery> implements PurchaseNoticeOrderDetailDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.purchase.PurchaseNoticeOrderDetailMapper";
    }
    
}
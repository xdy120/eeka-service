package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.ReturnOrderOutDetailDao;
import com.greatonce.oms.domain.trade.ReturnOrderOutDetail;
import com.greatonce.oms.query.trade.ReturnOrderOutDetailQuery;
import org.springframework.stereotype.Repository;

/**
 * ReturnOrderOutDetail <br/>
 * 退换货单换出商品
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ReturnOrderOutDetailDaoImpl extends AbstractOmsDao<ReturnOrderOutDetail,ReturnOrderOutDetailQuery> implements ReturnOrderOutDetailDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.ReturnOrderOutDetailMapper";
    }
    
}
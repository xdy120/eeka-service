package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.PresellDetailDao;
import com.greatonce.oms.domain.marketing.PresellDetail;
import com.greatonce.oms.query.marketing.PresellDetailQuery;
import org.springframework.stereotype.Repository;

/**
 * PresellDetail <br/>
 * 预售商品信息
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class PresellDetailDaoImpl extends AbstractOmsDao<PresellDetail,PresellDetailQuery> implements PresellDetailDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.PresellDetailMapper";
    }

    @Override
    public PageList<PresellDetail> listPage(PresellDetailQuery presellDetailQuery, int page, int pageSize) {
        return getSqlSessionDecorator()
            .selectList(getStatement("listPageFullInfo"), presellDetailQuery, page, pageSize);
    }
}
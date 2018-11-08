package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.PresellStoreDetailDao;
import com.greatonce.oms.domain.marketing.PresellStoreDetail;
import com.greatonce.oms.query.marketing.PresellStoreDetailQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PresellStoreDetail <br/>
 * 预售店铺明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class PresellStoreDetailDaoImpl extends AbstractOmsDao<PresellStoreDetail, PresellStoreDetailQuery> implements PresellStoreDetailDao {

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.PresellStoreDetailMapper";
    }

    @Override
    public List<PresellStoreDetail> list(PresellStoreDetailQuery presellStoreDetailQuery) {
        return getSqlSessionDecorator().selectList(getStatement("listDetails"), presellStoreDetailQuery);
    }

    @Override
    public PageList<PresellStoreDetail> listPage(PresellStoreDetailQuery presellStoreDetailQuery, int page, int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("listPageDetails"), presellStoreDetailQuery, page, pageSize);
    }
}
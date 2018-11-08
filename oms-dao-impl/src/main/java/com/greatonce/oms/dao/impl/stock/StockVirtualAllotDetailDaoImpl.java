package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockVirtualAllotDetailDao;
import com.greatonce.oms.domain.stock.StockVirtualAllotDetail;
import com.greatonce.oms.query.stock.StockVirtualAllotDetailQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StockVirtualAllotDetail <br/>
 * 虚拟调拨明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockVirtualAllotDetailDaoImpl extends AbstractOmsDao<StockVirtualAllotDetail, StockVirtualAllotDetailQuery> implements StockVirtualAllotDetailDao {

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.stock.StockVirtualAllotDetailMapper";
    }

    @Override
    public List<StockVirtualAllotDetail> listAvailable(Long stockVirtualAllotId) {
        return getSqlSessionDecorator().selectList(getStatement("listAvailable"), stockVirtualAllotId);
    }

    @Override
    public List<StockVirtualAllotDetail> listSaleable(Long stockVirtualAllotId) {
        return getSqlSessionDecorator().selectList(getStatement("listSaleable"), stockVirtualAllotId);
    }
}
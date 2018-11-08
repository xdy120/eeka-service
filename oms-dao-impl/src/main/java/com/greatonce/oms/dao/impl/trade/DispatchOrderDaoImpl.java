package com.greatonce.oms.dao.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.DispatchOrderDao;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.query.trade.DispatchOrderQuery;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DispatchOrder <br/>
 * 配货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class DispatchOrderDaoImpl extends AbstractOmsDao<DispatchOrder, DispatchOrderQuery> implements DispatchOrderDao {

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.DispatchOrderMapper";
    }

    @Override
    public List<DispatchOrder> listBySalesOrderId(Long salesOrderId) {
        return getSqlSessionDecorator().selectList(getStatement("listBySalesOrderId"), salesOrderId);
    }

    @Override
    public DispatchOrder getSimpleInfo(Long dispatchOrderId) {
        return getSqlSessionDecorator().selectOne(getStatement("getSimpleInfo"), dispatchOrderId);
    }

    @Override
    public PageList<DispatchOrder> listPageByConditions(DispatchOrderQuery filter, int page, int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("listPageByConditions"), filter, page, pageSize);
    }

    @Override
    public List<DispatchOrder> listBySalesOrderDetailId(Long salesOrderId, Collection<Long> salesOrderDetailIds) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("salesOrderId", salesOrderId);
        map.put("salesOrderDetailIds", salesOrderDetailIds);
        return  getSqlSessionDecorator().selectList(getStatement("listBySalesOrderDetailId"), map);
    }
}
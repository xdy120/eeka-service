package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.DispatchOrderDetailDao;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.query.trade.DispatchOrderDetailQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DispatchOrderDetail <br/>
 * 配货单明细
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class DispatchOrderDetailDaoImpl extends AbstractOmsDao<DispatchOrderDetail,DispatchOrderDetailQuery> implements DispatchOrderDetailDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.trade.DispatchOrderDetailMapper";
    }

    @Override
    public List<DispatchOrderDetail> listSimple(Long dispatchOrderId){
        return getSqlSessionDecorator().selectList(getStatement("listSimple"), dispatchOrderId);
    }
}
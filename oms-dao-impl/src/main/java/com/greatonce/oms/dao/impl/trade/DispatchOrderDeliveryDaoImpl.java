package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.DispatchOrderDeliveryDao;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.query.trade.DispatchOrderDeliveryQuery;
import org.springframework.stereotype.Repository;

/**
 * DispatchOrderDelivery <br/>
 * 配货单发货信息
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class DispatchOrderDeliveryDaoImpl extends AbstractOmsDao<DispatchOrderDelivery,DispatchOrderDeliveryQuery> implements DispatchOrderDeliveryDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.DispatchOrderDeliveryMapper";
    }

}
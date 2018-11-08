package com.greatonce.oms.dao.impl.mall;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.mall.MallExchangeOrderDao;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.query.mall.MallExchangeOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * MallExchangeOrder <br/>
 * 商城换货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class MallExchangeOrderDaoImpl extends AbstractOmsDao<MallExchangeOrder,MallExchangeOrderQuery> implements MallExchangeOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.mall.MallExchangeOrderMapper";
    }

}
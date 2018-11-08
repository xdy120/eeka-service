package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockTransitDao;
import com.greatonce.oms.domain.stock.StockTransit;
import com.greatonce.oms.query.stock.StockTransitQuery;
import org.springframework.stereotype.Repository;

/**
 * StockTransit <br/>
 * 在途库存
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class StockTransitDaoImpl extends AbstractOmsDao<StockTransit,StockTransitQuery> implements StockTransitDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.stock.StockTransitMapper";
    }
    
}
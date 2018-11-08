package com.greatonce.oms.dao.impl.purchase;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.purchase.SupplierDao;
import com.greatonce.oms.domain.purchase.Supplier;
import com.greatonce.oms.query.purchase.SupplierQuery;
import org.springframework.stereotype.Repository;

/**
 * Supplier <br/>
 * 供应商
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SupplierDaoImpl extends AbstractOmsDao<Supplier,SupplierQuery> implements SupplierDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.purchase.SupplierMapper";
    }
    
}
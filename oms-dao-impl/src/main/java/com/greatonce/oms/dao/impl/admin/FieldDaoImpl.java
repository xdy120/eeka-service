package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.FieldDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.Field;
import com.greatonce.oms.query.admin.FieldQuery;
import org.springframework.stereotype.Repository;

/**
 * Field <br/>
 * 字段
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class FieldDaoImpl extends AbstractAdminDao<Field,FieldQuery> implements FieldDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.admin.FieldMapper";
    }

}
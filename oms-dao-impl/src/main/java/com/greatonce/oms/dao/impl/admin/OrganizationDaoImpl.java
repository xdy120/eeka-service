package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.OrganizationDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.Organization;
import com.greatonce.oms.query.admin.OrganizationQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Organization <br/>
 * 组织
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class OrganizationDaoImpl extends AbstractAdminDao<Organization,OrganizationQuery> implements OrganizationDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.admin.OrganizationMapper";
    }

    @Override
    public Organization getSimpleByExample(Organization organization){
        List<Organization> list = this.listSimpleByExample(organization);
        return list != null && !list.isEmpty() ? list.get(0) : null;
    }

    @Override
    public List<Organization> listSimpleByExample(Organization organization){
        return getSqlSessionDecorator().selectList(getStatement("listSimpleByExample"), organization);
    }
}
package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.PrivilegeDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Privilege;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.query.base.PrivilegeQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Privilege <br/>
 * 权限
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class PrivilegeDaoImpl extends AbstractOmsDao<Privilege,PrivilegeQuery> implements PrivilegeDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.base.PrivilegeMapper";
    }

    @Override
    public List<Privilege> listUserPrivilege(Long userId, PrivilegeType type){
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("privilegeType", type);
        return getSqlSessionDecorator().selectList(getStatement("listUserPrivilege"), map);
    }
}
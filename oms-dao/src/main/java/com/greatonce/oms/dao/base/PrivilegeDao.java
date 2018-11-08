package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.Privilege;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.query.base.PrivilegeQuery;

import java.util.List;

/**
 * Privilege <br/>
 * 权限
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface PrivilegeDao extends QueryDao<Privilege,PrivilegeQuery>{

    List<Privilege> listUserPrivilege(Long userId, PrivilegeType type);
}
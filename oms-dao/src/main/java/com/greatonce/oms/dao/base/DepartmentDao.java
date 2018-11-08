package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.Department;
import com.greatonce.oms.query.base.DepartmentQuery;

/**
 * Department <br/>
 * 部门
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface DepartmentDao extends QueryDao<Department, DepartmentQuery> {

  int countDepartments(Long companyId);

  int updateChildrenQuantity(Long id, int quantity);
}
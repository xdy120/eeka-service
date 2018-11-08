package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.DepartmentDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Department;
import com.greatonce.oms.query.base.DepartmentQuery;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * Department <br/>
 * 部门
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class DepartmentDaoImpl extends AbstractOmsDao<Department, DepartmentQuery> implements
    DepartmentDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.base.DepartmentMapper";
  }

  @Override
  public int countDepartments(Long companyId) {
    return this.getSqlSessionDecorator().selectOne(getStatement("countDepartments"), companyId);
  }

  @Override
  public int updateChildrenQuantity(Long id, int quantity) {
    Map<String, Object> map = new HashMap<>(3);
    map.put("departmentId", id);
    map.put("quantity", quantity);
    return getSqlSessionDecorator().update(getStatement("updateChildrenQuantity"), map);
  }
}
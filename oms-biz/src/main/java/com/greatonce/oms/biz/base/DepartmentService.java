package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.Department;
import com.greatonce.oms.query.base.DepartmentQuery;

import java.util.List;

/**
 * Department <br/>
 * ??
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-10-30
 */
public interface DepartmentService extends BizService<Department,DepartmentQuery>{
    int countDepartments(Long companyId);

    List<Department> listChildrenByParentId(Long id);
}
package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.DepartmentService;
import com.greatonce.oms.domain.base.Department;
import com.greatonce.oms.query.base.DepartmentQuery;
import com.greatonce.oms.web.controller.FullListController;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("base/department")
@CrossOrigin
public class DepartmentController implements FullListController<Department, DepartmentQuery> {

  @Resource
  DepartmentService departmentService;

  @Override
  public BizService<Department, DepartmentQuery> getBizService() {
    return departmentService;
  }

  @GetMapping(path = "/company/{companyId}")
  public int getByCompanyId(@PathVariable("companyId") Long companyId) {
    return departmentService.countDepartments(companyId);
  }

  /**
   * 根据公司id获取父id为0的第一级机构
   */
  @GetMapping(path = "/listByCompany/{companyId}")
  public List<Department> listDepartmentsByCompanyId(@PathVariable("companyId") Long companyId) {
    Department example = new Department() {{
      setCompanyId(companyId);
      setParentId(0L);
    }};
    return departmentService.listExample(example);
  }

  /**
   * 根据父id获取组织机构的子节点
   */
  @GetMapping(path = "/children/{parentId}")
  public List<Department> listChildren(@PathVariable("parentId") Long parentId) {
    return departmentService.listChildrenByParentId(parentId);
  }

  /**
   * 删除并减少父机构的子节点数量
   *
   * @param id 主键ID
   */
  @Override
  @DeleteMapping(path = "{id}")
  public void delete(@PathVariable("id") Long id) {
    departmentService.remove(departmentService.getByKey(id));
  }
}

package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.DepartmentService;
import com.greatonce.oms.biz.base.RoleService;
import com.greatonce.oms.biz.base.RoleUserService;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaUserQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaUserQueryResponse;
import com.greatonce.oms.domain.base.Department;
import com.greatonce.oms.domain.base.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
@RestController
@EekaApiCondition
public class EekaUserController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaUserController.class);

  @Autowired
  private UserService userService;
  @Autowired
  private DepartmentService departmentService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private RoleUserService roleUserService;
  @Resource
  private IdGenerator apiIdGenerator;

  /**
   * 用户查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.user.list")
  public EekaUserQueryResponse userQuery(HttpServletRequest servletRequest) {
    EekaUserQueryRequest request = checkSign(servletRequest,
        EekaUserQueryRequest.class);
    EekaUserQueryResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getUserName())) {
        response = new EekaUserQueryResponse(apiIdGenerator.next(), "请求参数不能为空");
        return response;
      }
      User userEg = new User();
      userEg.setLoginName(request.getUserName());
      User user = userService.getByExample(userEg);
      List<User> users = new ArrayList<>();
      if (!Assert.isNull(user)) {
        Department departmentEg = new Department();
        departmentEg.setDepartmentId(user.getDepartmentId());
        departmentEg.setDepartmentName(user.getDepartmentName());
        Department department = departmentService.getByExample(departmentEg);
        if (!Assert.isNull(department)) {
          users = userService.listExample(new User() {
            {
              setDepartmentId(department.getDepartmentId());
            }
          });
        }
      }
      if (!Assert.isEmpty(users)) {
        response = new EekaUserQueryResponse(apiIdGenerator.next());
        response.setUsers(users);
      } else {
        response = new EekaUserQueryResponse(apiIdGenerator.next(), "查询信息为空");
        return response;
      }
    }
    if (Assert.isNull(response)) {
      response = new EekaUserQueryResponse(apiIdGenerator.next());
    }
    return response;
  }


}

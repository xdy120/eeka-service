package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVirtualWarehouseQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVirtualWarehouseQueryResponse;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import java.util.List;
import java.util.stream.Collectors;
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
public class EekaVirtualWarehouseController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaVirtualWarehouseController.class);

  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private UserService userService;
  @Resource
  IdGenerator apiIdGenerator;

  /**
   * 用户权限的虚拟仓查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.user.virtual.warehouse.list")
  public EekaVirtualWarehouseQueryResponse virtualWarehouseQuery(
      HttpServletRequest servletRequest) {
    EekaVirtualWarehouseQueryRequest request = checkSign(servletRequest,
        EekaVirtualWarehouseQueryRequest.class);
    EekaVirtualWarehouseQueryResponse response = new EekaVirtualWarehouseQueryResponse(
        apiIdGenerator.next());
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getLoginName())) {
        response = new EekaVirtualWarehouseQueryResponse(apiIdGenerator.next(), "用户不能为空");
        return response;
      }
      User userEg = new User();
      userEg.setLoginName(request.getLoginName());
      userEg.setEnable(true);
      User user = userService.getByExample(userEg);
      if (!Assert.isNull(user)) {
        List<VirtualWarehouse> virtualWarehouses = virtualWarehouseService
            .listUserVirtualWarehouse(user.getUserId());
        response = new EekaVirtualWarehouseQueryResponse(apiIdGenerator.next());
        if(Assert.isEmpty(request.getVirtualWarehouseType())){
          response.setVirtualWarehouses(virtualWarehouses);
        }else if ("1".equals(request.getVirtualWarehouseType())){
          virtualWarehouses = virtualWarehouses.stream().filter(x->x.getVirtualWarehouseName().contains("B2C退货仓")).collect(
              Collectors.toList());
          response.setVirtualWarehouses(virtualWarehouses);
        }else if ("2".equals(request.getVirtualWarehouseType())){
          virtualWarehouses = virtualWarehouses.stream().filter(x->x.getVirtualWarehouseName().contains("唯品退货仓")).collect(
              Collectors.toList());
          response.setVirtualWarehouses(virtualWarehouses);
        }
      } else {
        response = new EekaVirtualWarehouseQueryResponse(apiIdGenerator.next(), "用户不存在或已禁用");
        return response;
      }
    }
    return response;
  }

}

package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.bo.trade.CreateReturnNoticeOrderBO;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaReturnOrderNoticeRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaReturnOrderNoticeResponse;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;
import com.greatonce.oms.domain.trade.ReturnOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EekaApiCondition
public class EekaReturnOrderNoticeController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaReturnOrderNoticeController.class);

  @Autowired
  private ReturnOrderService returnOrderService;
  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private UserService userService;
  @Autowired
  private DataDictItemService dataDictItemService;

  /**
   * 退换货单通知单创建
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.return.notice.order.create")
  public EekaReturnOrderNoticeResponse notice(HttpServletRequest servletRequest) {
    EekaReturnOrderNoticeRequest request = checkSign(servletRequest,
        EekaReturnOrderNoticeRequest.class);
    EekaReturnOrderNoticeResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getVirtualWarehouseId())) {
        response = new EekaReturnOrderNoticeResponse(apiIdGenerator.next(), "退入仓库不能为空");
        return response;
      }
      if (Assert.isNull(request.getOperator())) {
        response = new EekaReturnOrderNoticeResponse(apiIdGenerator.next(), "用户不能为空");
        return response;
      }
      CreateReturnNoticeOrderBO createReturnNoticeOrderBO = new CreateReturnNoticeOrderBO();
      if (!Assert.isNull(request.getUnpackedTimeBegin())) {
        createReturnNoticeOrderBO
            .setBeginTime(DateTimeUtil.parserLocalDateTime(request.getUnpackedTimeBegin()));
      }
      if (!Assert.isNull(request.getUnpackedTimeEnd())) {
        createReturnNoticeOrderBO
            .setEndTime(DateTimeUtil.parserLocalDateTime(request.getUnpackedTimeEnd()));
      }
      createReturnNoticeOrderBO.setBoxNos(request.getBoxNos());
      createReturnNoticeOrderBO.setBrandCodes(request.getBrandCodes());
      if(Assert.isEmpty(request.getBoxNos())){
        if (!Assert.isEmpty(request.getUnpackers())) {
          User user = getUser(request.getUnpackers().get(0));
          if (!Assert.isNull(user)) {
            createReturnNoticeOrderBO.setNickname(user.getNickname());
          }
        }
      }
      if (!Assert.isEmpty(request.getOperator())) {
        User user = getUser(request.getOperator());
        if (!Assert.isNull(user)) {
          createReturnNoticeOrderBO.setOperator(user.getNickname());
        }
      }
      createReturnNoticeOrderBO.setSkuCodes(request.getSkuCodes());
      createReturnNoticeOrderBO.setVirtualWarehouseId(request.getVirtualWarehouseId());
      List<ReturnOrderStatus> statuses = new ArrayList<>(2);
      //statuses.add(ReturnOrderStatus.AUDITED);
      statuses.add(ReturnOrderStatus.REVIEWED);
      createReturnNoticeOrderBO.setStatuses(statuses);
      final Map<String,String> returnTypeMap = dataDictItemService.listMapByDictId(10301L);
      createReturnNoticeOrderBO.setReturnType(returnTypeMap.get("001"));
      try {
        returnOrderService.createNotice(createReturnNoticeOrderBO);
      } catch (Exception e) {
        LOGGER.error("生成退换货单通知单失败,堆栈信息:", e);
        return new EekaReturnOrderNoticeResponse(apiIdGenerator.next(), e.getMessage());
      }
      response = new EekaReturnOrderNoticeResponse(apiIdGenerator.next());
    }
    if (Assert.isNull(response)) {
      response = new EekaReturnOrderNoticeResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 退换货单查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.return.order.list")
  public EekaReturnOrderNoticeResponse query(HttpServletRequest servletRequest) {
    EekaReturnOrderNoticeRequest request = checkSign(servletRequest,
        EekaReturnOrderNoticeRequest.class);
    EekaReturnOrderNoticeResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getVirtualWarehouseId())) {
        response = new EekaReturnOrderNoticeResponse(apiIdGenerator.next(), "退入仓库不能为空");
        return response;
      }
      CreateReturnNoticeOrderBO createReturnNoticeOrderBO = new CreateReturnNoticeOrderBO();
      if (!Assert.isNull(request.getUnpackedTimeBegin())) {
        createReturnNoticeOrderBO
            .setBeginTime(DateTimeUtil.parserLocalDateTime(request.getUnpackedTimeBegin()));
      }
      if (!Assert.isNull(request.getUnpackedTimeEnd())) {
        createReturnNoticeOrderBO
            .setEndTime(DateTimeUtil.parserLocalDateTime(request.getUnpackedTimeEnd()));
      }
      createReturnNoticeOrderBO.setBoxNos(request.getBoxNos());
      createReturnNoticeOrderBO.setBrandCodes(request.getBrandCodes());
      if(Assert.isEmpty(request.getBoxNos())){
        if (!Assert.isEmpty(request.getUnpackers())) {
          User user = getUser(request.getUnpackers().get(0));
          if (!Assert.isNull(user)) {
            createReturnNoticeOrderBO.setNickname(user.getNickname());
          }
        }
      }
      final Map<String,String> returnTypeMap = dataDictItemService.listMapByDictId(10301L);
      createReturnNoticeOrderBO.setReturnType(returnTypeMap.get("001"));
      createReturnNoticeOrderBO.setSkuCodes(request.getSkuCodes());
      createReturnNoticeOrderBO.setVirtualWarehouseId(request.getVirtualWarehouseId());
      List<ReturnOrder> returnOrders = returnOrderService
          .listReturnOrder(createReturnNoticeOrderBO);
      response = new EekaReturnOrderNoticeResponse(apiIdGenerator.next());
      if (!Assert.isEmpty(returnOrders)) {
        response.setReturnOrders(returnOrders);
        Integer skuQuantity = 0;
        for (int i = 0; i < returnOrders.size(); i++) {
          skuQuantity += returnOrders.get(i).getQuantity();
        }
        List<Long> salesOrders = returnOrders.stream()
            .map(x -> x.getSalesOrderId()).distinct().collect(Collectors.toList());
        response.setSkuQuantity(skuQuantity);
        response.setOrderQuantity(Assert.isEmpty(salesOrders) ? 0 : salesOrders.size());
      }
    }
    if (Assert.isNull(response)) {
      response = new EekaReturnOrderNoticeResponse(apiIdGenerator.next());
    }
    return response;
  }

  private User getUser(String loginName) {
    User userEg = new User();
    userEg.setLoginName(loginName);
    userEg.setEnable(true);
    return userService.getByExample(userEg);
  }

}

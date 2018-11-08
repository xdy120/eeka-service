package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.trade.ScanExpressBO;
import com.greatonce.oms.bo.trade.SourceOrderFilterBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaSalesOrderQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaSalesOrderQueryResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EekaApiCondition
public class EekaSalesOrderController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaSalesOrderController.class);

  @Autowired
  private ReturnOrderService returnOrderService;
  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private ReturnOrderDetailService returnOrderDetailService;

  /**
   * 查询原单
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.sales.order.list")
  public EekaSalesOrderQueryResponse SalesOrderQuery(HttpServletRequest servletRequest) {
    EekaSalesOrderQueryRequest request = checkSign(servletRequest,
        EekaSalesOrderQueryRequest.class);
    EekaSalesOrderQueryResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getDispatchOrderCode()) && Assert.isNull(request.getExpressNo())
          && Assert.isNull(request.getContact()) && Assert.isNull(request.getTelephone())) {
        response = new EekaSalesOrderQueryResponse(apiIdGenerator.next(), "请求参数不能为空");
        return response;
      }
      SourceOrderFilterBO filterBO = new SourceOrderFilterBO();
      SalesOrderQuery filter = new SalesOrderQuery();
      ScanExpressBO scanExpressBO = null;
      if (Pattern
          .matches(TradeConstants.DISPATCH_ORDER_CODE_PATTERN, request.getDispatchOrderCode())) {
        ArrayList<String> dispatchCode = new ArrayList<>();
        dispatchCode.add(request.getDispatchOrderCode());
        filter.setDispatchOrderCodes(dispatchCode);
        filterBO.setSalesOrderQuery(filter);
        scanExpressBO = returnOrderService.getSourceOrder(filterBO);
      } else if (Pattern.matches(TradeConstants.MOBILE_PATTERN, request.getDispatchOrderCode())) {
        //这里应该拿有效店铺
        //拿店铺
        List<Store> stores = storeService.listEnableStore();
        List<Long> storeIds = stores.stream().map(x -> x.getStoreId()).collect(Collectors.toList());
        //用拿到的店铺解密手机
        List<String> mobiles = encryptKeyByStoreId(request.getDispatchOrderCode(), storeIds,
            DataType.MOBILE);
        mobiles.add(request.getDispatchOrderCode());
        filter.setMobiles(mobiles);
        filterBO.setSalesOrderQuery(filter);
        scanExpressBO = returnOrderService.getSourceOrder(filterBO);
      } else {
        //增加重复验证
        ReturnOrder returnOrder = new ReturnOrder();
        returnOrder.setExpressNo(request.getDispatchOrderCode());
        List<ReturnOrder> returnOrders = returnOrderService.listExample(returnOrder);
        response = new EekaSalesOrderQueryResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(returnOrders)){
          Iterator<ReturnOrder> it = returnOrders.iterator();
          while (it.hasNext()) {
            ReturnOrder rOrder = it.next();
            if (ReturnOrderStatus.INVALID.equals(rOrder.getStatus())) {
              it.remove();
            }
          }
          if(!Assert.isEmpty(returnOrders)){
            response.setResultFlag(true);
          }
        }
        filterBO.setSalesOrderQuery(filter);
        filterBO.setExpressNo(request.getDispatchOrderCode());
        scanExpressBO = returnOrderService.getSourceOrder(filterBO);
      }
      if (!Assert.isNull(scanExpressBO)) {
        BeanUtils.copyProperties(scanExpressBO, response);
      }
    }
    if (Assert.isNull(response)) {
      response = new EekaSalesOrderQueryResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 根据店铺对某一字段加密.
   * <p/>
   * 根据传入的每个店铺id查找店铺，且仅当appkey存在时对key进行加密，每个appkey只加密一次，返回集合不包括传入字段本身。
   *
   * @param key 被加密字段
   * @param storeIds 店铺id集合
   * @param dataType 加密字段类型
   */
  @SuppressWarnings("Duplicates")
  public List<String> encryptKeyByStoreId(String key, List<Long> storeIds, DataType dataType) {
    Map<String, String> encryptMap = new HashMap<>(storeIds.size());
    for (Long storeId : storeIds) {
      Store storeFullInfo = storeService.getByKey(storeId);
      if (Assert.isNull(storeFullInfo.getMallApp())) {
        continue;
      }
      //相同appkey不需重复加密
      if (!encryptMap.containsKey(storeFullInfo.getMallApp().getAppKey())) {
        SecurityBridge securityBridge = mallBridgeFactory
            .getSecurityBridge(storeFullInfo.getMallType());
        String encryptResult = securityBridge.encrypt(storeFullInfo, key, dataType);
        encryptMap.put(storeFullInfo.getMallApp().getAppKey(), encryptResult);
      }
    }
    return encryptMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
  }



}

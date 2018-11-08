package com.greatonce.oms.consumer.trade.prerefund;

import com.alibaba.fastjson.annotation.JSONField;
import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.admin.MallAppService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.trade.DispatchOrderCancelBO;
import com.greatonce.oms.bridge.mall.impl.taobao.TaobaoMall;
import com.greatonce.oms.domain.admin.MallApp;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.util.logging.BizLogger;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.api.internal.toplink.LinkException;
import com.taobao.api.request.RdcAligeniusOrdermsgUpdateRequest;
import com.taobao.api.response.RdcAligeniusOrdermsgUpdateResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/9/29
 */
@Component
@ConditionalOnProperty(name = "oms.consumer.prerefund.enabled", havingValue = "true")
public class MessageListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

  @Value("${oms.consumer.prerefund.mallAppId}")
  private Long mallAppId;
  @Value("${oms.consumer.prerefund.messagegroup}")
  private String messageGroup;
  @Autowired
  private MallAppService mallAppService;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private DispatchOrderDetailService dispatchOrderDetailService;
  @Autowired
  private TaobaoMall mall;
  @Autowired
  private ManualTransactionTemplate transactionTemplate;

  @PostConstruct
  public void init() {
    MallApp mallApp = mallAppService.getByKey(mallAppId);
    TmcClient tmcClient =
        new TmcClient(mallApp.getAppKey(), mallApp.getAppSecret(), messageGroup);
    tmcClient.setMessageHandler((message, messageStatus) -> {
      LOGGER.info(">>>{}收到预退款消息 --- {}", message.getUserNick(), JsonUtil.toJson(message));
      try {
        handlePrerefund(message, messageStatus, mallApp);
      } catch (Exception e) {
        messageStatus.fail("消息处理异常");
        LOGGER.error("消息处理异常", e);
      }
    });
    try {
      tmcClient.connect("ws://mc.api.taobao.com");
      LOGGER.info(">>>预退款消息监听连接是否成功:{}", tmcClient.isOnline() ? "是" : "否");
    } catch (LinkException e) {
      LOGGER.error(">>>连接异常", e);
    }
  }

  public void handlePrerefund(Message message, MessageStatus messageStatus, MallApp mallApp) {

    if (messageStatus.isFail()) {
      LOGGER.info(">>> Message ={};MessageStatus={}", JsonUtil.toJson(message),
          JsonUtil.toJson(messageStatus));
      return;
    }
    PrerefundMessage preRefundMessage = JsonUtil
        .toObject(message.getContent(), PrerefundMessage.class);
    if (preRefundMessage == null) {
      LOGGER.info(">>> 预退款消息内容为空");
      messageStatus.fail("content为空");
      return;
    }
    Long tid = preRefundMessage.getTid();
    Long oid = preRefundMessage.getOid();
    if (tid == null || oid == null) {
      LOGGER.info(">>> 交易号或子订单号异常 => tid ={},oid={}", tid, oid);
      return;
    }
    String seller_nick = Assert.isEmpty(preRefundMessage.getSellerNick()) ?
        message.getUserNick() : preRefundMessage.getSellerNick();
    Store store = new Store();
    store.setNickname(seller_nick);
    store = storeService.getByExample(store);
    if (store == null || !Assert.isTrue(store.isEnable())) {
      LOGGER.info("店铺异常，店铺名：{}", seller_nick);
      messageStatus.fail();
      return;
    }
    store.setMallApp(mallApp);
    SalesOrder salesOrder = salesOrderService
        .getByTradeId(store.getStoreId(), String.valueOf(tid));
    if (salesOrder == null) {
      messageStatus.fail("订单未抓取或无此订单");
      return;
    }
    RdcAligeniusOrdermsgUpdateRequest req = new RdcAligeniusOrdermsgUpdateRequest();
    req.setOid(oid);
    req.setTid(tid);
    boolean result = prerefund(salesOrder);
    req.setStatus(result ? 1L : 2L);
    LOGGER.info("订单{}预退款，交易号：{}，子订单号{}", salesOrder.getSalesOrderCode(), tid, oid);

    // 发送回传结果请求
    RdcAligeniusOrdermsgUpdateResponse resp = mall.call(store, req);
    if (!resp.isSuccess()) {
      if (!"isv.invalid-parameter".equals(resp.getSubCode().toLowerCase())) {
        int retryTimes = 0;
        while (retryTimes < 3) {
          LOGGER.info("订单{}预退款处理结果回传，淘宝错误重试：第{}次,{},{}",
              salesOrder.getSalesOrderCode(), retryTimes, resp.getSubCode(), resp.getSubMsg());
          resp = mall.call(store, req);
          retryTimes++;
          if (resp.isSuccess()) {
            return;
          }
        }
      }
    }
  }

  private boolean prerefund(SalesOrder salesOrder) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrder.getSalesOrderId());
    eg.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    List<SalesOrderDetail> details = salesOrderDetailService.listExample(eg);

    if (salesOrder.getDispatchStatus() == DispatchStatus.NONE ||
        details.stream().allMatch(x -> x.getStatus() == SalesOrderDetailStatus.WAITING)) {
      salesOrderService.prerefund(salesOrder);
      salesOrderService.getBizLogger()
          .log(salesOrder.getSalesOrderId(), BizLogger.UPDATE, "预退款拦截，订单未配货");
      return true;
    } else {
      List<DispatchOrder> dispatchOrders = details.stream().map(x -> {
        DispatchOrderDetail detailEg = new DispatchOrderDetail();
        detailEg.setSalesOrderDetailId(x.getSalesOrderDetailId());
        DispatchOrderDetail detail = dispatchOrderDetailService.getByExample(detailEg);
        return detail.getDispatchOrderId();
      }).distinct().map(x -> dispatchOrderService.getByKey(x)).collect(Collectors.toList());

      try {
        transactionTemplate.execute(() -> {
          dispatchOrders.forEach(x -> {
            DispatchOrderCancelBO bo = new DispatchOrderCancelBO();
            bo.setVersion(x.getVersion());
            bo.setReason("订单预退款");
            dispatchOrderService.cancel(x, bo);
          });
          salesOrderService
              .prerefund(salesOrderService.getSimpleInfo(salesOrder.getSalesOrderId()));
        });
        salesOrderService.getBizLogger().log(salesOrder.getSalesOrderId(), BizLogger.UPDATE,
            "预退款拦截，订单取消配货");
        return true;
      } catch (Exception e) {
        LOGGER.info("订单{}预退款拦截，取消配货失败", salesOrder.getSalesOrderCode());
        LOGGER.error("订单预退款拦截，取消配货失败，异常：", e);
        return false;
      }
    }
  }

  private static class PrerefundMessage {

    @JSONField(name = "action_type")
    private long actionType;
    private Long tid;
    private Long oid;
    @JSONField(name = "seller_nick")
    private String sellerNick;

    public long getActionType() {
      return actionType;
    }

    public void setActionType(long actionType) {
      this.actionType = actionType;
    }

    public Long getTid() {
      return tid;
    }

    public void setTid(Long tid) {
      this.tid = tid;
    }

    public Long getOid() {
      return oid;
    }

    public void setOid(Long oid) {
      this.oid = oid;
    }

    public String getSellerNick() {
      return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
      this.sellerNick = sellerNick;
    }
  }
}

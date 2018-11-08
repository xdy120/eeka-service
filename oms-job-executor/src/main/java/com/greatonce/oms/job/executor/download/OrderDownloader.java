package com.greatonce.oms.job.executor.download;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.OrderBridge;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.bridge.mall.response.OrderQueryResponse;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.job.executor.AbstractDownloader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 订单下载.
 */
@DisallowConcurrentExecution
public class OrderDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderDownloader.class);
  @Autowired
  private MallSalesOrderService mallSalesOrderService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    OrderQueryRequest request = new OrderQueryRequest(store);
    request.setPage(1);
    request.setBeginTime(config.getBeginTime());
    request.setEndTime(endTime);
    request.setStoreDownloadConfig(config);
    OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
    if (Assert.isTrue(store.getSetting().isEnablePrepayOrder())) {
      download(store, orderBridge, request, MallSalesOrderStatus.WAIT_BUYER_PAY);
      download(store, orderBridge, request, MallSalesOrderStatus.TRADE_CLOSE);
    }
    download(store, orderBridge, request, MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS);
    download(store, orderBridge, request, MallSalesOrderStatus.TRADE_FINISHED);
  }

  private void download(Store store, OrderBridge orderBridge, OrderQueryRequest request,
      MallSalesOrderStatus status) {
    request.setStatus(status);
    OrderQueryResponse response = orderBridge.queryOrder(request);
    if (!response.isSuccess()) {
      throw new OmsException(StringUtil.format("【{0}】下载{1}~{2}第{3}页数据失败：{4}",
          store.getStoreName(), DateTimeUtil.format(request.getBeginTime()),
          DateTimeUtil.format(request.getEndTime()),
          request.getPage(), response.getResult()));
    }
    LOGGER.info("【{}】{},{}~{}第{}页共{}条订单，下一页：{}...",
        store.getStoreName(), status.caption(),
        DateTimeUtil.format(request.getBeginTime()),
        DateTimeUtil.format(request.getEndTime()),
        request.getPage(), response.getCount(), response.isHasNext());
    saveOrder(store, request, response);
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      download(store, orderBridge, request, status);
    }
  }

  private void saveOrder(Store store, OrderQueryRequest request, OrderQueryResponse response) {
    if (response.isSuccess()) {
      if (response.getOrders() != null && response.getOrders().size() > 0) {
        List<MallSalesOrder> mallSalesOrderList = new ArrayList<>(response.getOrders().size());
        response.getOrders().forEach(x -> {
          MallSalesOrder order = new MallSalesOrder();
          order.setStoreId(store.getStoreId());
          order.setMallSalesOrderStatus(x.getStatus());
          order.setStatus(MallDataProcessStatus.WAITING);
          order.setTradeId(x.getTradeId());
          order.setOrderJson(JsonUtil.toJson(x));
          order.setStoreName(store.getStoreName());
          mallSalesOrderList.add(order);
        });
        mallSalesOrderService.batchCreate(mallSalesOrderList);
      }
    } else {
      LOGGER.info("店铺【{}】第{}页获取下载数据异常！{}", store.getStoreName(), request.getPage(),
          response.getResult());
    }
  }
}



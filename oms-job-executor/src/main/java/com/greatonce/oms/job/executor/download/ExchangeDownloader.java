package com.greatonce.oms.job.executor.download;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.mall.MallExchangeOrderService;
import com.greatonce.oms.biz.mall.MallRefundOrderService;
import com.greatonce.oms.bridge.mall.ExchangeBridge;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.request.ExchangeQueryRequest;
import com.greatonce.oms.bridge.mall.response.ExchangeQueryResponse;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import com.greatonce.oms.domain.enums.mall.MallExchangeStatus;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.job.executor.AbstractDownloader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class ExchangeDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeDownloader.class);
  @Autowired
  private StoreService storeService;
  @Resource
  private IdGenerator idGenerator;

  @Autowired
  private MallRefundOrderService mallRefundOrderService;
  @Autowired
  private MallExchangeOrderService mallExchangeOrderService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private StoreDownloadConfigService storeDownloadConfigService;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    ExchangeQueryRequest request = new ExchangeQueryRequest(store);
    request.setPage(1);
    request.setBeginTime(config.getBeginTime());
    request.setEndTime(endTime);
    request.setStoreDownloadConfig(config);

    ExchangeBridge exchangeBridge = mallBridgeFactory.getExchangeBridge(store.getMallType());
    download(store, exchangeBridge, request);
  }


  private void download(Store store, ExchangeBridge orderBridge, ExchangeQueryRequest request) {
    ExchangeQueryResponse response = orderBridge.queryExchange(request);
    if (!response.isSuccess()) {
      throw new OmsException(StringUtil.format("【{0}】下载{1}~{2}第{3}页数据失败：{4}",
          store.getStoreName(), DateTimeUtil.format(request.getBeginTime()),
          DateTimeUtil.format(request.getEndTime()),
          request.getPage(), response.getResult()));
    }
    LOGGER.info("【{}】,{}~{}第{}页共{}条订单...",
        store.getStoreName(),
        DateTimeUtil.format(request.getBeginTime()),
        DateTimeUtil.format(request.getEndTime()),
        request.getPage(), response.getCount());
    saveOrder(store, request, response);
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      download(store, orderBridge, request);
    }
  }

  private void saveOrder(Store store, ExchangeQueryRequest request,
      ExchangeQueryResponse response) {
    if (response.isSuccess()) {
      if (response.getOrders() != null && response.getOrders().size() > 0) {
        List<MallExchangeOrder> mallSalesOrder = new ArrayList<>(response.getOrders().size());
        response.getOrders().forEach(x -> {
          MallExchangeOrder order = new MallExchangeOrder();
          order.setMallExchangeOrderId(idGenerator.next());
          order.setStoreId(store.getStoreId());
          order.setStatus(MallDataProcessStatus.WAITING);
          order.setTradeId(x.getOid());
          order.setMallDetailId(x.getOid());
          order.setMallExchangeId(x.getExchangeId());
          order.setOrderJson(JsonUtil.toJson(x));
          order.setStoreName(store.getStoreName());
          order.setMallExchangeStatus(toMallExchangeStatus(x.getExchangeStatus()));
          mallSalesOrder.add(order);
        });
        mallExchangeOrderService.batchCreate(mallSalesOrder);
      }
    } else {
      LOGGER.info("店铺【{}】第{}页获取下载数据异常！{}", store.getStoreName(), request.getPage(),
          response.getResult());
    }
  }


  private MallExchangeStatus toMallExchangeStatus(String status) {
    switch (status) {
      case "换货待处理":
        return MallExchangeStatus.WAIT_DEAL;
      case "待买家退货":
        return MallExchangeStatus.WAIT_BUYER_RETURN;
      case "买家已退货，待收货":
        return MallExchangeStatus.BUYER_RETURNED;
      case "换货关闭":
        return MallExchangeStatus.EXCHANGE_CLOSE;
      case "换货成功":
        return MallExchangeStatus.EXCHANGE_SUCCESS;
      case "待买家修改":
        return MallExchangeStatus.WAIT_BUYER_MODIFY;
      case "待发出换货商品":
        return MallExchangeStatus.WAIT_SEND_EXCHANGE_GOODS;
      case "待买家收货":
        return MallExchangeStatus.WAIT_BUYER_RECEIVE;
      case "请退款":
        return MallExchangeStatus.REFUND;
      default:
        return MallExchangeStatus.WAIT_DEAL;
    }
  }
}



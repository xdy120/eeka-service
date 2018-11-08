package com.greatonce.oms.job.executor.download;

import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.mall.MallRefundOrderService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.RefundBridge;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.bridge.mall.response.RefundQueryResponse;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import com.greatonce.oms.domain.mall.MallRefundOrder;
import com.greatonce.oms.job.executor.AbstractDownloader;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 退货单下载.
 */
@DisallowConcurrentExecution
public class RefundDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(RefundDownloader.class);
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallRefundOrderService mallRefundOrderService;
  @Autowired
  private StoreDownloadConfigService storeDownloadConfigService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    RefundQueryRequest request = new RefundQueryRequest(store);
    request.setPage(1);
    request.setBeginTime(config.getBeginTime());
    request.setEndTime(endTime);
    request.setStoreDownloadConfig(config);

    RefundBridge refundBridge = mallBridgeFactory.getRefundBridge(store.getMallType());
    download(store, refundBridge, request);
  }

  private void download(Store store, RefundBridge orderBridge, RefundQueryRequest request) {
    RefundQueryResponse response = orderBridge.queryRefund(request);
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

  private void saveOrder(Store store, RefundQueryRequest request, RefundQueryResponse response) {
    if (response.isSuccess()) {
      if (response.getOrders() != null && response.getOrders().size() > 0) {
        List<MallRefundOrder> mallSalesOrder = new ArrayList<>(response.getOrders().size());
        response.getOrders().forEach(x -> {
          MallRefundOrder order = new MallRefundOrder();
          order.setStoreId(store.getStoreId());
          order.setStatus(MallDataProcessStatus.WAITING);
          order.setCreatedTime(LocalDateTime.now());
          order.setTradeId(x.getTradeId());
          order.setMallRefundId(x.getRefundId());
          order.setOrderJson(JsonUtil.toJson(x));
          order.setStoreName(store.getStoreName());
          order.setMallRefundStatus(x.getApplyStatus());  //售后申请状态   todo
          mallSalesOrder.add(order);
        });
        mallRefundOrderService.batchCreate(mallSalesOrder);
      }
    } else {
      LOGGER.info("店铺【{}】第{}页获取下载数据异常！{}", store.getStoreName(), request.getPage(),
          response.getResult());
    }
  }
}



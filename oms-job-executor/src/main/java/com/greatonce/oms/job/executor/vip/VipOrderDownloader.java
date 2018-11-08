package com.greatonce.oms.job.executor.vip;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.vip.VipSalesOrderDetailService;
import com.greatonce.oms.bridge.mall.impl.vip.VipOrderBridge;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipOrderQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse.VipOrder;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse.VipOrderDetail;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.vip.VipSalesOrderDetail;
import com.greatonce.oms.job.executor.AbstractDownloader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 唯品单据下载.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/8
 */
@Component
@DisallowConcurrentExecution
public class VipOrderDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(VipOrderDownloader.class);
  @Autowired
  private VipOrderBridge vipOrderBridge;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private VipSalesOrderDetailService vipSalesOrderDetailService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;


  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    StockDispatchStrategy dispatchStrategy = stockDispatchStrategyService
        .getByKey(store.getSetting().getStockStrategy());
    Assert.notNull(dispatchStrategy, "店铺配货策略为空");
    Assert.notEmpty(dispatchStrategy.getRule().getWarehouses(), "店铺配货仓库为空");
    VipOrderQueryRequest request = new VipOrderQueryRequest(store);
    request.setBeginTime(config.getBeginTime());
    request.setEndTime(endTime);
    request.setStoreDownloadConfig(config);
    request.setPage(1);
    download(store, request, dispatchStrategy);
  }


  private void download(Store store, VipOrderQueryRequest request,
      StockDispatchStrategy setting) {
    VipOrderQueryResponse response = vipOrderBridge.queryOrder(request);
    LOGGER.info("下载【{}】{}~{}第{}页订单共{}条数据...", store.getStoreName(), request.getBeginTime(),
        request.getEndTime(), request.getPage(), response.getCount());
    saveOrder(store, response, setting);
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      download(store, request, setting);
    }
  }

  private void saveOrder(Store store, VipOrderQueryResponse response,
      StockDispatchStrategy dispatchStrategy) {
    if (response.isSuccess() && !Assert.isEmpty(response.getOrders())) {
      vipSalesOrderDetailService.filterExistingOrder(store.getStoreId(), response.getOrders());
      StockDispatchWarehouse warehouse = dispatchStrategy.getRule().getWarehouses().get(0);
      List<VipSalesOrderDetail> detailList = new ArrayList<>(response.getOrders().size());
      ProductSku sku;
      ProductMallMapping productMallMapping;
      for (VipOrder vipOrder : response.getOrders()) {
        if (!Assert.isEmpty(vipOrder.getDetails())) {
          for (VipOrderDetail vipOrderDetail : vipOrder.getDetails()) {
            VipSalesOrderDetail detail = new VipSalesOrderDetail();
            productMallMapping = productMallMappingService
                .getByMallSkuOutCode(vipOrderDetail.getBarcode(), store.getStoreId());
            if (productMallMapping != null && productMallMapping.isMatched()) {
              detail.setSkuId(productMallMapping.getSkuId());
              detail.setSkuCode(productMallMapping.getSkuCode());
            } else {
              sku = productSkuService.getEffectiveByCode(vipOrderDetail.getBarcode());
              if (sku != null) {
                detail.setSkuId(sku.getSkuId());
                detail.setSkuCode(sku.getSkuCode());
              }
            }
            if (Assert.isNull(detail.getSkuId())) {
              LOGGER.info("【{}】店铺销售单：{}，barcode：{}未找到系统商品", store.getStoreName(),
                  vipOrder.getOrderNo(), vipOrderDetail.getBarcode());
              continue;
            }

            detail.setTradeId(vipOrder.getOrderNo());
            detail.setQuantity(vipOrderDetail.getQuantity());
            detail.setVipBarcode(vipOrderDetail.getBarcode());
            detail.setMallPaidTime(vipOrderDetail.getPayTime());
            detail.setSalesNo(vipOrderDetail.getSalesNo());
            detail.setStoreName(store.getStoreName());
            detail.setStoreId(store.getStoreId());
            detail.setVirtualWarehouseId(warehouse.getVirtualWarehouseId());
            detail.setVirtualWarehouseName(warehouse.getVirtualWarehouseName());
            detail.setWarehouseName(warehouse.getWarehouseName());
            detail.setWarehouseId(warehouse.getWarehouseId());
            detailList.add(detail);
          }
        }
      }
      vipSalesOrderDetailService.autoCreate(detailList);
    }
  }
}

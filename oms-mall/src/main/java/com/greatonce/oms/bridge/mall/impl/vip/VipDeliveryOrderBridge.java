package com.greatonce.oms.bridge.mall.impl.vip;

import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryCreateRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryImportRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryImportRequest.VipDeliveryDetail;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryModifyRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipDeliveryCreateResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipDeliveryImportResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipDeliveryModifyResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipDeliveryQueryResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.vip.VipJitType;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.vip.osp.sdk.exception.OspException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vipapis.common.Warehouse;
import vipapis.delivery.CreateMultiPoDeliveryRequest;
import vipapis.delivery.Delivery;
import vipapis.delivery.EditMultiPoDeliveryRequest;
import vipapis.delivery.GetDeliveryListResponse;

/**
 * 唯品发货单接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
@Component
public class VipDeliveryOrderBridge extends AbstractBridge {

  protected static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  private static final vipapis.delivery.JitDeliveryServiceHelper.JitDeliveryServiceClient DELIVERY_SERVICE_CLIENT = new vipapis.delivery.JitDeliveryServiceHelper.JitDeliveryServiceClient();

  @Autowired
  private VipMall vipMall;
  @Autowired
  private VipDeliveryOrderBridge vipDeliveryOrderBridge;

  private String buildVipTime(String date, String time) {
    return date.concat(" ").concat(time).concat(":00");
  }

  /**
   * 创建发货单.
   */
  public VipDeliveryCreateResponse createDelivery(VipDeliveryCreateRequest request) {
    if (isTesting()) {
      return new VipDeliveryCreateResponse(request, "vip-test");
    }

    try {
      CreateMultiPoDeliveryRequest deliveryRequest = new CreateMultiPoDeliveryRequest();
      deliveryRequest.setWarehouse(Warehouse.valueOf(request.getVipWarehouseCode()));
      deliveryRequest.setPo_no(request.getPoCode());
      deliveryRequest.setVendor_id(request.getStore().getSetting().getVipVendorId());
      deliveryRequest.setCarrier_code(request.getCarrierCode());
      deliveryRequest.setDelivery_no(request.getWaybillNumber());
      deliveryRequest.setDelivery_method(request.getDeliveryMethod());
      deliveryRequest
          .setArrival_time(buildVipTime(request.getArrivalDate(), request.getArrivalTime()));
      deliveryRequest
          .setDelivery_time(buildVipTime(request.getDeliveryDate(), request.getDeliveryTime()));
      if (request.getJitType() != VipJitType.NORMAL) {
        deliveryRequest.setJit_type(request.getJitType().value());
      }
      vipMall.initContext(request.getStore());
      MALL_LOGGER.info(request.getStore(), "唯品创建发货单：{}", JsonUtil.toJson(deliveryRequest));
      String deliveryNo = DELIVERY_SERVICE_CLIENT.createMultiPoDelivery(deliveryRequest);
      return new VipDeliveryCreateResponse(request, deliveryNo);
    } catch (Exception e) {
      MALL_LOGGER.error(request.getStore(), "唯品创建发货单失败！", e);
      return new VipDeliveryCreateResponse(request, false, e.getMessage());
    }
  }

  /**
   * 修改发货单.
   */
  public VipDeliveryModifyResponse modifyDelivery(VipDeliveryModifyRequest request) {
    if (isTesting()) {
      return new VipDeliveryModifyResponse(request);
    }

    try {
      EditMultiPoDeliveryRequest deliveryRequest = new EditMultiPoDeliveryRequest();
      deliveryRequest.setWarehouse(Warehouse.valueOf(request.getVipWarehouseCode()));
      deliveryRequest.setStorage_no(request.getStorageNo());
      deliveryRequest.setVendor_id(request.getStore().getSetting().getVipVendorId());
      deliveryRequest.setCarrier_code(request.getCarrierCode());
      deliveryRequest.setDelivery_no(request.getWaybillNumber());
      deliveryRequest.setDelivery_method(request.getDeliveryMethod());

      deliveryRequest
          .setDelivery_time(buildVipTime(request.getDeliveryDate(), request.getDeliveryTime()));
      deliveryRequest
          .setArrival_time(buildVipTime(request.getArrivalDate(), request.getArrivalTime()));
      vipMall.initContext(request.getStore());
      MALL_LOGGER.info(request.getStore(), "唯品修改发货单：{}", JsonUtil.toJson(deliveryRequest));
      DELIVERY_SERVICE_CLIENT.editMultiPoDelivery(deliveryRequest);
      return new VipDeliveryModifyResponse(request);
    } catch (OspException e) {
      MALL_LOGGER.error(request.getStore(), "唯品修改发货单失败！", e);
      if (e.getReturnMessage().contains("已出仓")) {
        return new VipDeliveryModifyResponse(request);
      }
      return new VipDeliveryModifyResponse(request, false, e.getMessage());
    }
  }

  /**
   * 唯品发货.
   */
  public VipDeliveryImportResponse delivery(VipDeliveryImportRequest request) {
    if (isTesting()) {
      return new VipDeliveryImportResponse(request);
    }

    try {
      Map<String, List<VipDeliveryDetail>> detailMap = CollectionUtil
          .listToMapList(request.getDetails(), VipDeliveryDetail::getPoCode);
      vipMall.initContext(request.getStore());
      detailMap.forEach((k, v) -> {
        int pageSize = 1000;
        request.setPoCode(k);
        if (v.size() > pageSize) {
          int totalPage = MallUtil.calcTotalPage(pageSize, v.size());
          for (int i = 0; i < totalPage; i++) {
            List<Delivery> deliveries = v.stream().skip(i * pageSize)
                .limit(pageSize)
                .map(this::buildVipDeliveryDetail).collect(Collectors.toList());
            delivery(request, deliveries);
          }
        } else {
          List<Delivery> deliveries = v.stream()
              .map(this::buildVipDeliveryDetail).collect(Collectors.toList());
          delivery(request, deliveries);
        }
      });
      DELIVERY_SERVICE_CLIENT
          .confirmDelivery(request.getStore().getSetting().getVipVendorId(), request.getStorageNo(),
              request.getPoCode(), null);
      return new VipDeliveryImportResponse(request);
    } catch (MallException e) {
      return new VipDeliveryImportResponse(request, false, e.getMessage());
    } catch (OspException e) {
      MALL_LOGGER
          .error(request.getStore(), "唯品发货单{}，确认失败：{}", request.getStorageNo(), e.getMessage());
      return new VipDeliveryImportResponse(request, false, "出仓单确认失败！" + e.getMessage());
    }
  }

  private Delivery buildVipDeliveryDetail(VipDeliveryDetail detail) {
    Delivery delivery = new Delivery();
    delivery.setBarcode(detail.getVipSkuCode());
    delivery.setBox_no("001");
    delivery.setPick_no(detail.getPickNo());
    delivery.setVendor_type("COMMON");
    delivery.setPo_no(detail.getPoCode());
    delivery.setAmount(detail.getQuantity());
    return delivery;
  }

  private void delivery(VipDeliveryImportRequest request, List<Delivery> deliveries) {
    int retry = 1;
    while (true) {
      try {
        DELIVERY_SERVICE_CLIENT
            .importMultiPoDeliveryDetail(request.getStore().getSetting().getVipVendorId(),
                request.getPoCode(), request.getStorageNo(), null, deliveries);
        break;
      } catch (OspException e) {
        MALL_LOGGER
            .error(request.getStore(), "唯品导入发货单{}明细失败！重试次数：{}", request.getStorageNo(), retry);
        if (retry++ > 3) {
          MALL_LOGGER
              .error(request.getStore(), "唯品导入发货单{}明细失败！重试失败！明细数据：{}", request.getStorageNo(),
                  JsonUtil.toJson(deliveries));
          throw new MallException("唯品导入发货单明细失败，请联系售后客服处理。".concat(e.getMessage()));
        }
      }
    }
  }

  /**
   * 查询发货单.
   */
  public VipDeliveryQueryResponse queryDelivery(VipDeliveryQueryRequest request) {
    try {
      int pageSize = 50;
      Store store = request.getStore();
      VipDeliveryQueryResponse response = new VipDeliveryQueryResponse(request);
      final GetDeliveryListResponse deliveryList = DELIVERY_SERVICE_CLIENT
          .getDeliveryList(String.valueOf(store.getSetting().getVipVendorId()), null, null, null,
              null,
              DateTimeUtil.format(request.getBeginTime()),
              DateTimeUtil.format(request.getEndTime()), null, null, null, null, null, null, null,
              null);
      response.setOrders(deliveryList.getDelivery_list());
      response
          .setHasNext(MallUtil.calcHasNext(pageSize, request.getPage(), deliveryList.getTotal()));
      return response;
    } catch (OspException e) {
      return new VipDeliveryQueryResponse(request, false, e.getMessage());
    }
  }
}

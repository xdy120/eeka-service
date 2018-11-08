package com.greatonce.oms.bridge.mall.impl.vip;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipOrderInPickQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderDetailQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderInPickQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipPickOrderDetailQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipPickOrderQueryResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.vip.osp.sdk.exception.OspException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vipapis.delivery.GetMultiPoPickDetailRequest;
import vipapis.delivery.GetMultiPoPickDetailResponse;
import vipapis.delivery.GetPickListResponse;
import vipapis.delivery.JitDeliveryServiceHelper;
import vipapis.delivery.MultiPickDetailInfo;
import vipapis.delivery.Pick;
import vipapis.inventory.InventoryDeductOrdersResponse;
import vipapis.inventory.InventoryServiceHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangc on 2017/6/26.
 */
@Component
public class VipPickOrderBridge {

  private static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  static final int PAGE_SIZE = 50;
  private static final JitDeliveryServiceHelper.JitDeliveryServiceClient DELIVERY_SERVICE_CLIENT = new JitDeliveryServiceHelper.JitDeliveryServiceClient();
  private static final InventoryServiceHelper.InventoryServiceClient INVENTORY_SERVICE_CLIENT = new InventoryServiceHelper.InventoryServiceClient();
  @Autowired
  private VipMall mall;

  public VipPickOrderQueryResponse queryPickOrder(VipPickOrderQueryRequest request) {
    try {
      Store store = request.getStore();
      mall.initContext(store);
      GetPickListResponse getPickListResponse;
      if (Assert.isNull(request.getPickNos())) {
        //获取拣货单列表响应对象，AccessToken：访问指令
        getPickListResponse = DELIVERY_SERVICE_CLIENT
            .getPickList(store.getSetting().getVipVendorId(), null, null, null, null, null,
                DateTimeUtil.format(request.getBeginTime()),
                DateTimeUtil.format(request.getEndTime()), null, null, null, null, null,
                request.getPage(), PAGE_SIZE, null);
      } else {
        getPickListResponse = DELIVERY_SERVICE_CLIENT
            .getPickList(store.getSetting().getVipVendorId(), null, request.getPickNos(), null,
                null, null,
                null, null, null, null, null, null, null, request.getPage(), PAGE_SIZE, null);
      }
      MALL_LOGGER.info(store,"api:vipPickOrderQuery,request:{},response:{}",
          JsonUtil.toJson(request),JsonUtil.toJson(getPickListResponse));
      VipPickOrderQueryResponse response = new VipPickOrderQueryResponse(request);
      if (getPickListResponse.getTotal() > 0) {
        //设置请求的下一步
        response.setHasNext(
            request.getPage() < MallUtil.calcTotalPage(PAGE_SIZE, getPickListResponse.getTotal()));
        //创建唯品会拣货单集合
        List<VipPickOrderQueryResponse.VipPickOrder> pickOrders = new ArrayList<>(
            getPickListResponse.getPicks().size());
        //从拣货列表中获取拣货信息
        for (Pick pick : getPickListResponse.getPicks()) {
          VipPickOrderQueryResponse.VipPickOrder pickOrder = new VipPickOrderQueryResponse.VipPickOrder();
          pickOrder.setPickNo(pick.getPick_no());
          pickOrder.setPo(pick.getPo_no());
          pickOrder.setCreateTime(DateTimeUtil.parserLocalDateTime(pick.getCreate_time()));
          pickOrder.setWarehouse(pick.getSell_site());
          pickOrders.add(pickOrder);
        }
        response.setOrders(pickOrders);
      }
      return response;
    } catch (OspException e) {
      return new VipPickOrderQueryResponse(request, false, e.getReturnMessage());
    }
  }

  //查询拣货单明细
  public VipPickOrderDetailQueryResponse queryPickOrderDetail(
      VipPickOrderDetailQueryRequest request) {
    try {
      Store store = request.getStore();
      mall.initContext(store);
      GetMultiPoPickDetailRequest getMultiPoPickDetailRequest = new GetMultiPoPickDetailRequest();
      getMultiPoPickDetailRequest.setVendor_id(request.getStore().getSetting().getVipVendorId());
      getMultiPoPickDetailRequest.setVendor_id(store.getSetting().getVipVendorId());
      getMultiPoPickDetailRequest.setPick_no(request.getPickNo());
      getMultiPoPickDetailRequest.setPage(request.getPage());
      getMultiPoPickDetailRequest.setLimit(PAGE_SIZE);
      GetMultiPoPickDetailResponse getMultiPoPickDetailResponse = DELIVERY_SERVICE_CLIENT
          .getMultiPoPickDetail(getMultiPoPickDetailRequest);
      MALL_LOGGER.info(store,"api:vipPickOrderDetailQuery,request:{},response:{}",JsonUtil.toJson(request),JsonUtil.toJson(getMultiPoPickDetailResponse));
      VipPickOrderDetailQueryResponse response = new VipPickOrderDetailQueryResponse(request);
      if (getMultiPoPickDetailResponse.getTotal() > 0) {
        response.setHasNext(request.getPage() < MallUtil
            .calcTotalPage(PAGE_SIZE, getMultiPoPickDetailResponse.getTotal()));
        List<VipPickOrderDetailQueryResponse.VipPickOrderDetail> details = new ArrayList<>(
            getMultiPoPickDetailResponse.getPick_detail_list().size());
        for (MultiPickDetailInfo multiPickDetailInfo : getMultiPoPickDetailResponse
            .getPick_detail_list()) {
          VipPickOrderDetailQueryResponse.VipPickOrderDetail vipPickOrderDetail = new VipPickOrderDetailQueryResponse.VipPickOrderDetail();
          vipPickOrderDetail.setBarcode(multiPickDetailInfo.getBarcode());
          vipPickOrderDetail.setNoDeliveryQuantity(multiPickDetailInfo.getNot_delivery_num());
          vipPickOrderDetail.setQuantity(multiPickDetailInfo.getPick_num());
          vipPickOrderDetail.setSn(multiPickDetailInfo.getSn());
          vipPickOrderDetail.setPo(multiPickDetailInfo.getPo_no());
          details.add(vipPickOrderDetail);
        }
        response.setDetails(details);
      }
      return response;
    } catch (OspException e) {
      return new VipPickOrderDetailQueryResponse(request, false, e.getMessage());
    }
  }

  //获取拣货单中的订单
  public VipOrderInPickQueryResponse queryOrderInPick(VipOrderInPickQueryRequest request) {
    try {
      Store store = request.getStore();
      mall.initContext(store);
      //库存扣除订单要求
      vipapis.inventory.InventoryDeductOrdersRequest deductOrdersRequest = new vipapis.inventory.InventoryDeductOrdersRequest();
      //设置供应商ID
      deductOrdersRequest.setVendor_id(store.getSetting().getVipVendorId().longValue());
      //设置拣货单ID
      deductOrdersRequest.setPick_no(request.getPickNo());
      //设置订单号
      deductOrdersRequest.setPo_no(request.getPo());
      //设置页数
      deductOrdersRequest.setPage(request.getPage());
      //通过库存客户的库存扣除单来获取库存扣除订单响应
      InventoryDeductOrdersResponse inventoryDeductOrdersResponse = INVENTORY_SERVICE_CLIENT
          .getInventoryDeductOrders(deductOrdersRequest);
      MALL_LOGGER.info(store,"api:queryOrderInPick,request:{},response:{}",
          JsonUtil.toJson(request),JsonUtil.toJson(inventoryDeductOrdersResponse));
      //唯品会订单拣货查找响应
      VipOrderInPickQueryResponse response = new VipOrderInPickQueryResponse(request);
      //设置下一步
      response.setHasNext(inventoryDeductOrdersResponse.getHas_next());
      //设置订单号
      response.setOrderNos(inventoryDeductOrdersResponse.getOrders());
      return response;
    } catch (OspException e) {
      return new VipOrderInPickQueryResponse(request, false, e.getReturnMessage());
    }
  }
}

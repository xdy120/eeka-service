package com.greatonce.oms.bridge.mall.impl.vip;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.bridge.mall.impl.AbstractProductBridge;
import com.greatonce.oms.bridge.mall.impl.UniqueCodeWorker;
import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.ProductQueryResponse;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.vip.osp.sdk.exception.OspException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vipapis.inventory.GetScheduleSkuListCriteria;
import vipapis.inventory.GetScheduleSkuListResult;
import vipapis.inventory.GetSkuInventoryResult;
import vipapis.inventory.InventoryServiceHelper;
import vipapis.inventory.ScheduleSku;
import vipapis.inventory.UpdateSkuInventoryRequest;
import vipapis.product.SpuWithSkusBaseInfo;
import vipapis.product.VendorProductQueryServiceHelper;

/**
 * VipProductBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/28
 */
@Component
public class VipProductBridge extends AbstractProductBridge {

  private static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  private static final Set<String> BREAK_ERROR_CODES = new HashSet<>(1);
  private static final Map<String, String> INVENTORY_ERROR_DESC_MAP = new HashMap<>();
  private static final String VIP_GLOBAL_WAREHOUSE_CODE = "VIPCNLGC01";

  static {
    BREAK_ERROR_CODES.add("vipapis.InventoryService.circuit_break_hint");

    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.repeated_batch_no", "批次号重复");
    INVENTORY_ERROR_DESC_MAP
        .put("vipapis.InventoryService.initial_quantity_not_imported", "未导入初始库存");
    INVENTORY_ERROR_DESC_MAP
        .put("vipapis.InventoryService.quantity_lt_cart_quantity", "库存数小于购物车库存和未支付订单库存");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.no_ack_for_circuit_break", "未响应熔断警告");
    INVENTORY_ERROR_DESC_MAP
        .put("vipapis.InventoryService.repeated_ack_for_circuit_break", "重复响应熔断警告");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.circuit_break_hint", "熔断响应提示");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.invalid_setting_flag", "无效的flag设置");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.sku_maybe_offline", "商品可能已经下线");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.quantity_calc_error", "计算库存错误");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.other_error", "其他错误");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.InventoryService.quantity_lt_leavings", "其他错误");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.comm.invalid-parameter", "无效参数");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.comm.vendorId-notExsist", "无效参数");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.comm.base-api-error", "上游内部接口错误");
    INVENTORY_ERROR_DESC_MAP.put("vipapis.comm.inner-error", "系统内部错误");
    INVENTORY_ERROR_DESC_MAP.put("vippias.comm.item-size-exceed", "无效参数");
  }

  @Autowired
  private VipMall mall;
  @Autowired
  private UniqueCodeWorker uniqueCodeWorker;

  private String convertErrorMessage(OspException e) {
    return INVENTORY_ERROR_DESC_MAP.containsKey(e.getReturnCode()) ? INVENTORY_ERROR_DESC_MAP
        .get(e.getReturnCode()) : e.getReturnMessage();
  }

  @Override
  public SkuQuantityUploadResponse doUploadQuantity(SkuQuantityUploadRequest request) {
    InventoryServiceHelper.InventoryServiceClient client = new InventoryServiceHelper.InventoryServiceClient();
    if (Assert.isNull(request.getStore().getSetting().getVipCooperationNo())) {
      return new SkuQuantityUploadResponse(request, false, "请填写常态合作编码！");
    }
    mall.initContext(request.getStore());
    //更新商品库存请求
    UpdateSkuInventoryRequest inventoryRequest = new UpdateSkuInventoryRequest();
    //通过店铺来设置库存请求的供应商ID
    inventoryRequest.setVendor_id(request.getStore().getSetting().getVipVendorId());
    inventoryRequest.setCooperation_no(request.getStore().getSetting().getVipCooperationNo());
    //通过产品商城映射类的唯一码来设置库存请求的条形码
    inventoryRequest.setBarcode(request.getMapping().getMallSkuId());
    //通过代码工人类提供文本来设置批号
    inventoryRequest.setBatch_no(String.valueOf(uniqueCodeWorker.next()));
    //设置数量
    inventoryRequest.setQuantity(request.getQuantity());
    //根据上传类型来设置同步模式（如果是全量为0，增量为1）
    inventoryRequest.setSync_mode(request.getUploadType() == StockUploadType.COVER ? 0 : 1);
    //设置上传仓库
    inventoryRequest.setWarehouse(request.getUploadConfig().getMallWarehouse());
    if (Assert.isEmpty(inventoryRequest.getWarehouse())) {
      inventoryRequest.setWarehouse(VIP_GLOBAL_WAREHOUSE_CODE);
    }
    try {
      //如果是增量模式并且库存数量小于0（数量为负数时，证明有人在下单）
      if (inventoryRequest.getSync_mode() == 1 && inventoryRequest.getQuantity() < 0) {
        //通过商店和条形码来获取SKU库存的库存结果
        GetSkuInventoryResult stockResult = getStock(request.getStore(),
            inventoryRequest.getWarehouse(), inventoryRequest.getBarcode());
        //如果商城剩下的商品数量小于实际库存商品数量
        if (stockResult.getLeaving_stock() < Math.abs(inventoryRequest.getQuantity())) {
          //SKU数量上传响应(request,剩下的，目前持有的)
          return new SkuQuantityUploadResponse(request, false, StringUtil
              .format("唯品可扣减数不足，剩余：{0}件，锁定：{1}件", stockResult.getLeaving_stock(),
                  stockResult.getCurrent_hold()));
        }
      }
      //根据库存请求同步更新库存
      client.updateInventory(inventoryRequest);
      //返回一个新的SKU库存数量上传的对象
      return new SkuQuantityUploadResponse(request);
    } catch (OspException ex) {
      //ReturnCode:返回代码
      if (BREAK_ERROR_CODES.contains(ex.getReturnCode())) {
        inventoryRequest.setBatch_no(String.valueOf(uniqueCodeWorker.next()));
        //Circuit_break_ack_flag:电路中断确认标志
        inventoryRequest.setCircuit_break_ack_flag(1);
        try {
          //同步更新库存
          client.updateInventory(inventoryRequest);
          return new SkuQuantityUploadResponse(request);
        } catch (OspException e) {
          return new SkuQuantityUploadResponse(request, false, convertErrorMessage(ex));
        }
      }
      return new SkuQuantityUploadResponse(request, false, convertErrorMessage(ex));
    }
  }

  private GetSkuInventoryResult getStock(Store store, String warehouseCode, String barcode)
      throws OspException {
    mall.initContext(store);
    InventoryServiceHelper.InventoryServiceClient client = new InventoryServiceHelper.InventoryServiceClient();
    List<GetSkuInventoryResult> list = client
        .getSkuStock(store.getSetting().getVipVendorId(), store.getSetting().getVipCooperationNo(),
            warehouseCode, barcode, null);
    if (null != list && list.size() > 0) {
      return list.get(0);
    }
    return null;
  }

  @Override
  public ProductQueryResponse queryProduct(ProductQueryRequest request) {
    try {
      int pageSize = 100;
      InventoryServiceHelper.InventoryServiceClient client = new InventoryServiceHelper.InventoryServiceClient();
      mall.initContext(request.getStore());
      GetScheduleSkuListCriteria criteria = new GetScheduleSkuListCriteria();
      criteria.setVendor_id(request.getStore().getSetting().getVipVendorId());
      criteria.setCooperation_no(request.getStore().getSetting().getVipCooperationNo());
      if (!Assert.isNull(request.getBeginTime())) {
        criteria.setStart_query(DateTimeUtil.toTimestamp(request.getBeginTime()));
      }
      if (!Assert.isNull(request.getEndTime())) {
        criteria.setEnd_query(DateTimeUtil.toTimestamp(request.getEndTime()));
      }
      GetScheduleSkuListResult result = client.getSkuList(criteria, request.getPage(), pageSize);
      MALL_LOGGER.info(request.getStore(), "api:vipProductQuery,request:{},response:{}",
          JsonUtil.toJson(criteria), JsonUtil.toJson(result));
      ProductQueryResponse response = new ProductQueryResponse(request);
      response.setMappings(parseItem(request.getStore(), result.getList()));
      response.setHasNext(result.getHas_next());
      return response;
    } catch (OspException e) {
      return new ProductQueryResponse(request, false, e.getMessage());
    }
  }

  private List<ProductMallMapping> parseItem(Store store, List<ScheduleSku> list) {
    List<ProductMallMapping> mappings = new ArrayList<>(list.size());
    for (ScheduleSku sku : list) {
      ProductMallMapping mapping = new ProductMallMapping();
      mapping.setMallProductId(sku.getSn());
      mapping.setMallProductOutCode(sku.getSn());
      mapping.setMallSkuId(sku.getBarcode());
      mapping.setMallProductName(sku.getProduct_name());
      mapping.setMallSkuOutCode(sku.getBarcode());
      setMatchCode(store, mapping);
      mappings.add(mapping);
    }
    return mappings;
  }

  @Override
  public ProductQueryResponse queryProductById(ProductQueryRequest request) {
    request.setCode(request.getId());
    return queryProductByCode(request);
  }

  @Override
  public ProductQueryResponse queryProductByCode(ProductQueryRequest request) {
    try {
      VendorProductQueryServiceHelper.VendorProductQueryServiceClient client = new VendorProductQueryServiceHelper.VendorProductQueryServiceClient();
      mall.initContext(request.getStore());
      List<String> barcodes = StringUtil.words(request.getCode());
      ProductQueryResponse response = new ProductQueryResponse(request);
      List<ProductMallMapping> mappings = new ArrayList<>();
      for (String barcode : barcodes) {
        List<SpuWithSkusBaseInfo> list = client
            .queryByBarcode(request.getStore().getSetting().getVipVendorId(), barcode, 1);
        MALL_LOGGER.info(request.getStore(), "api:vipQueryProductByCode,request:{},response:{}",
            JsonUtil.toJson(request), JsonUtil.toJson(list));
        parseSkus(request.getStore(), barcode, mappings, list);
      }
      response.setMappings(mappings);
      return response;
    } catch (OspException e) {
      return new ProductQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  public boolean isSupportMultiStatus() {
    return true;
  }

  //TODO
  @Override
  public String getMallProductUrl(String mallProductId) {
    return null;
  }

  private void parseSkus(Store store, String barcode,
      List<ProductMallMapping> mappings, List<SpuWithSkusBaseInfo> list) {
    for (SpuWithSkusBaseInfo sku : list) {
      ProductMallMapping mapping = new ProductMallMapping();
      mapping.setMallProductId(sku.getSn());
      mapping.setMallProductOutCode(sku.getSn());
      mapping.setMallProductName(sku.getProduct_name());
      mapping.setMallSkuId(barcode);
      mapping.setMallSkuOutCode(barcode);
      setMatchCode(store, mapping);
      mappings.add(mapping);
    }
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.VIP};
  }
}

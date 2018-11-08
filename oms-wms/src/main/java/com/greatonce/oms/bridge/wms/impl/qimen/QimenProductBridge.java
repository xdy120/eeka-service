package com.greatonce.oms.bridge.wms.impl.qimen;

import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.AbstractProductBridge;
import com.greatonce.oms.bridge.wms.request.SkuCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockQueryRequest;
import com.greatonce.oms.bridge.wms.response.SkuCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockQueryResponse;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.util.FormatUtil;
import com.qimen.api.request.InventoryQueryRequest;
import com.qimen.api.request.SingleitemSynchronizeRequest;
import com.qimen.api.response.InventoryQueryResponse;
import com.qimen.api.response.SingleitemSynchronizeResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-08-14
 */
@Component
public class QimenProductBridge extends AbstractProductBridge {

  @Autowired
  protected QimenWms qimenWms;


  @Override
  public StockQueryResponse doQueryStock(StockQueryRequest request) {
    try {
      InventoryQueryRequest req = new InventoryQueryRequest();
      req.setCustomerId(request.getWarehouse().getWmsApp().getCustomerId());
      List<InventoryQueryRequest.Criteria> criteriaList = new ArrayList<>(
          request.getSkuCodes().size());
      request.getSkuCodes().forEach(sku -> {
        InventoryQueryRequest.Criteria criteria = new InventoryQueryRequest.Criteria();
        criteria.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
        criteria.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
        criteria.setItemCode(sku);
        criteriaList.add(criteria);
      });
      req.setCriteriaList(criteriaList);
      InventoryQueryResponse rsp = qimenWms.call(request.getWarehouse(), req);
      List<StockQueryResponse.SkuStock> stocks = new ArrayList<>();
      rsp.getItems().forEach(item -> {
        StockQueryResponse.SkuStock sr = new StockQueryResponse.SkuStock();
        sr.setLockQuantity(Math.toIntExact(item.getLockQuantity()));
        sr.setQuantity(Math.toIntExact(item.getQuantity()));
        sr.setSkuCode(item.getItemCode());
        sr.setWmsSkuId(item.getItemId());
        stocks.add(sr);
      });
      return new StockQueryResponse(request, stocks);
    } catch (WmsException e) {
      return new StockQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  public SkuCreateResponse doCreateSku(SkuCreateRequest request) {
    try {
      SingleitemSynchronizeRequest synchronizeRequest = new SingleitemSynchronizeRequest();
      ProductSku sku = request.getSku();
      synchronizeRequest.setActionType("add");
      synchronizeRequest.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
      synchronizeRequest.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      synchronizeRequest.setItem(buildItem(request.getSku()));
      SingleitemSynchronizeResponse rsp = qimenWms.call(request.getWarehouse(), synchronizeRequest);
      return new SkuCreateResponse(request);
    } catch (WmsException e) {
      return new SkuCreateResponse(request, false, e.getMessage());
    }
  }

  protected SingleitemSynchronizeRequest.Item buildItem(ProductSku sku) {
    SingleitemSynchronizeRequest.Item item = new SingleitemSynchronizeRequest.Item();
    item.setItemCode(sku.getSkuCode());
    item.setItemName(sku.getProductName());
    item.setBarCode(sku.getBarcode());
    item.setSkuProperty(sku.getSkuName());
    item.setVolume(FormatUtil.formatDouble(sku.getVolume()));
    item.setGrossWeight(FormatUtil.formatDouble(sku.getWeight()));
    item.setColor(sku.getColor());
    item.setSize(sku.getSize());
    item.setItemType(sku.getProduct().getProductType() == ProductType.VIRTUAL ? "XN" : "ZC");
    item.setTagPrice(FormatUtil.formatDouble(sku.getMarkedPrice()));
    item.setRetailPrice(FormatUtil.formatDouble(sku.getSellingPrice()));
    item.setCostPrice(FormatUtil.formatDouble(sku.getCostPrice()));
    item.setPurchasePrice(FormatUtil.formatDouble(sku.getPurchasePrice()));
    item.setBrandCode(sku.getProduct().getBarcode());
    item.setBrandName(sku.getProduct().getBrandName());
    item.setIsSNMgmt(item.getIsSNMgmt());
    item.setProductDate(item.getProductDate());
    item.setExpireDate(item.getExpireDate());
    item.setRemark(item.getRemark());
    item.setCreateTime(DateTimeUtil.format(sku.getCreatedTime()));
    item.setUpdateTime(DateTimeUtil.format(sku.getModifiedTime()));
    item.setIsSku("Y");
    return item;
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}

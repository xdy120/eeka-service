package com.greatonce.oms.bridge.wms.qimen.request;

import com.greatonce.core.supports.xml.JaxbMapAdapter;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 奇门库存异动.
 *
 * @author Byron
 * @version 2018-03-13
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsStockChangeReportRequest implements OmsBaseQimenRequest {

  private String orderCode;
  private String orderType;
  private String ownerCode;
  private String pageSize;
  private String remark;
  private String totalPage;
  private String warehouseCode;
  private String currentPage;
  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;
  @XmlElementWrapper(name = "items")
  @XmlElement(name = "item")
  private List<OmsStockChangeItem> items;


  @Override
  public String getOwnerCode() {
    return this.getItems().get(0).getOwnerCode();
  }

  @Override
  public String getOrderCode() {
    return this.getItems().get(0).getOrderCode();
  }

  @Override
  public String getOutCode() {
    return null;
  }

  @Override
  public String getWarehouseCode() {
    return this.getItems().get(0).getWarehouseCode();
  }

  @Override
  public String getOutBizCode() {
    return this.getItems().get(0).getOutBizCode();
  }


  public String getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(String currentPage) {
    this.currentPage = currentPage;
  }

  public Map<String, String> getExtendProps() {
    return extendProps;
  }

  public void setExtendProps(Map<String, String> extendProps) {
    this.extendProps = extendProps;
  }

  public List<OmsStockChangeItem> getItems() {
    return items;
  }

  public void setItems(List<OmsStockChangeItem> items) {
    this.items = items;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public void setOwnerCode(String ownerCode) {
    this.ownerCode = ownerCode;
  }

  public String getPageSize() {
    return pageSize;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(String totalPage) {
    this.totalPage = totalPage;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "item")
  public static class OmsStockChangeItem {

    private String actualAmount;
    private String actualQty;
    private String adventLifecycle;
    private String amount;
    private String approvalNumber;
    private String barCode;
    private String batchCode;
    private String batchRemark;
    private String brandCode;
    private String brandName;
    private String categoryId;
    private String categoryName;
    private String changeTime;
    private String channelCode;
    private String color;
    private String costPrice;
    private String defectiveQty;
    private String diffQuantity;
    private String discount;
    private String discountPrice;
    private String englishName;
    private String exCode;
    private String expireDate;
    private String extCode;
    private String goodsCode;
    private String grossWeight;
    private String height;
    private String inventoryType;
    private String isAreaSale;
    private String isBatchMgmt;
    private String isFragile;
    private String isHazardous;
    private String isSNMgmt;
    private String isShelfLifeMgmt;
    private String isSku;
    private String itemCode;
    private String itemId;
    private String itemName;
    private String itemType;
    private String lackQty;
    private String latestUpdateTime;
    private String length;
    private String lockQuantity;
    private String lockupLifecycle;
    private String netWeight;
    private String normalQty;
    private String orderCode;
    private String orderLineNo;
    private String orderType;
    private String originAddress;
    private String originCode;
    private String outBizCode;
    private String ownerCode;
    private String packCode;
    private String packageMaterial;
    private String paperQty;
    private String pcs;
    private String planQty;
    private String price;
    private String pricingCategory;
    private String produceCode;
    private String productCode;
    private String productDate;
    private String purchasePrice;
    private Long quantity;
    private String reason;
    private String receiveQty;
    private String referencePrice;
    private String rejectLifecycle;
    private String remark;
    private String retailPrice;
    private String safetyStock;
    private String seasonCode;
    private String seasonName;
    private String shelfLife;
    private String shortName;
    private String size;
    private String skuProperty;
    private String sn;
    private String snCode;
    private String sourceOrderCode;
    private String standardPrice;
    private String stockStatus;
    private String stockUnit;
    private String subSourceOrderCode;
    private String supplierCode;
    private String supplierName;
    private String tagPrice;
    private String tareWeight;
    private String tempRequirement;
    private String title;
    private String unit;
    private String volume;
    private String warehouseCode;
    private String width;

    public String getActualAmount() {
      return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
      this.actualAmount = actualAmount;
    }

    public String getActualQty() {
      return actualQty;
    }

    public void setActualQty(String actualQty) {
      this.actualQty = actualQty;
    }

    public String getAdventLifecycle() {
      return adventLifecycle;
    }

    public void setAdventLifecycle(String adventLifecycle) {
      this.adventLifecycle = adventLifecycle;
    }

    public String getAmount() {
      return amount;
    }

    public void setAmount(String amount) {
      this.amount = amount;
    }

    public String getApprovalNumber() {
      return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
      this.approvalNumber = approvalNumber;
    }

    public String getBarCode() {
      return barCode;
    }

    public void setBarCode(String barCode) {
      this.barCode = barCode;
    }

    public String getBatchCode() {
      return batchCode;
    }

    public void setBatchCode(String batchCode) {
      this.batchCode = batchCode;
    }

    public String getBatchRemark() {
      return batchRemark;
    }

    public void setBatchRemark(String batchRemark) {
      this.batchRemark = batchRemark;
    }

    public String getBrandCode() {
      return brandCode;
    }

    public void setBrandCode(String brandCode) {
      this.brandCode = brandCode;
    }

    public String getBrandName() {
      return brandName;
    }

    public void setBrandName(String brandName) {
      this.brandName = brandName;
    }

    public String getCategoryId() {
      return categoryId;
    }

    public void setCategoryId(String categoryId) {
      this.categoryId = categoryId;
    }

    public String getCategoryName() {
      return categoryName;
    }

    public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
    }

    public String getChangeTime() {
      return changeTime;
    }

    public void setChangeTime(String changeTime) {
      this.changeTime = changeTime;
    }

    public String getChannelCode() {
      return channelCode;
    }

    public void setChannelCode(String channelCode) {
      this.channelCode = channelCode;
    }

    public String getColor() {
      return color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public String getCostPrice() {
      return costPrice;
    }

    public void setCostPrice(String costPrice) {
      this.costPrice = costPrice;
    }

    public String getDefectiveQty() {
      return defectiveQty;
    }

    public void setDefectiveQty(String defectiveQty) {
      this.defectiveQty = defectiveQty;
    }

    public String getDiffQuantity() {
      return diffQuantity;
    }

    public void setDiffQuantity(String diffQuantity) {
      this.diffQuantity = diffQuantity;
    }

    public String getDiscount() {
      return discount;
    }

    public void setDiscount(String discount) {
      this.discount = discount;
    }

    public String getDiscountPrice() {
      return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
      this.discountPrice = discountPrice;
    }

    public String getEnglishName() {
      return englishName;
    }

    public void setEnglishName(String englishName) {
      this.englishName = englishName;
    }

    public String getExCode() {
      return exCode;
    }

    public void setExCode(String exCode) {
      this.exCode = exCode;
    }

    public String getExpireDate() {
      return expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

    public String getExtCode() {
      return extCode;
    }

    public void setExtCode(String extCode) {
      this.extCode = extCode;
    }

    public String getGoodsCode() {
      return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
      this.goodsCode = goodsCode;
    }

    public String getGrossWeight() {
      return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
      this.grossWeight = grossWeight;
    }

    public String getHeight() {
      return height;
    }

    public void setHeight(String height) {
      this.height = height;
    }

    public String getInventoryType() {
      return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
      this.inventoryType = inventoryType;
    }

    public String getIsAreaSale() {
      return isAreaSale;
    }

    public void setIsAreaSale(String isAreaSale) {
      this.isAreaSale = isAreaSale;
    }

    public String getIsBatchMgmt() {
      return isBatchMgmt;
    }

    public void setIsBatchMgmt(String isBatchMgmt) {
      this.isBatchMgmt = isBatchMgmt;
    }

    public String getIsFragile() {
      return isFragile;
    }

    public void setIsFragile(String isFragile) {
      this.isFragile = isFragile;
    }

    public String getIsHazardous() {
      return isHazardous;
    }

    public void setIsHazardous(String isHazardous) {
      this.isHazardous = isHazardous;
    }

    public String getIsSNMgmt() {
      return isSNMgmt;
    }

    public void setIsSNMgmt(String isSNMgmt) {
      this.isSNMgmt = isSNMgmt;
    }

    public String getIsShelfLifeMgmt() {
      return isShelfLifeMgmt;
    }

    public void setIsShelfLifeMgmt(String isShelfLifeMgmt) {
      this.isShelfLifeMgmt = isShelfLifeMgmt;
    }

    public String getIsSku() {
      return isSku;
    }

    public void setIsSku(String isSku) {
      this.isSku = isSku;
    }

    public String getItemCode() {
      return itemCode;
    }

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public String getItemId() {
      return itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public String getItemType() {
      return itemType;
    }

    public void setItemType(String itemType) {
      this.itemType = itemType;
    }

    public String getLackQty() {
      return lackQty;
    }

    public void setLackQty(String lackQty) {
      this.lackQty = lackQty;
    }

    public String getLatestUpdateTime() {
      return latestUpdateTime;
    }

    public void setLatestUpdateTime(String latestUpdateTime) {
      this.latestUpdateTime = latestUpdateTime;
    }

    public String getLength() {
      return length;
    }

    public void setLength(String length) {
      this.length = length;
    }

    public String getLockQuantity() {
      return lockQuantity;
    }

    public void setLockQuantity(String lockQuantity) {
      this.lockQuantity = lockQuantity;
    }

    public String getLockupLifecycle() {
      return lockupLifecycle;
    }

    public void setLockupLifecycle(String lockupLifecycle) {
      this.lockupLifecycle = lockupLifecycle;
    }

    public String getNetWeight() {
      return netWeight;
    }

    public void setNetWeight(String netWeight) {
      this.netWeight = netWeight;
    }

    public String getNormalQty() {
      return normalQty;
    }

    public void setNormalQty(String normalQty) {
      this.normalQty = normalQty;
    }

    public String getOrderCode() {
      return orderCode;
    }

    public void setOrderCode(String orderCode) {
      this.orderCode = orderCode;
    }

    public String getOrderLineNo() {
      return orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
      this.orderLineNo = orderLineNo;
    }

    public String getOrderType() {
      return orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getOriginAddress() {
      return originAddress;
    }

    public void setOriginAddress(String originAddress) {
      this.originAddress = originAddress;
    }

    public String getOriginCode() {
      return originCode;
    }

    public void setOriginCode(String originCode) {
      this.originCode = originCode;
    }

    public String getOutBizCode() {
      return outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
      this.outBizCode = outBizCode;
    }

    public String getOwnerCode() {
      return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }

    public String getPackCode() {
      return packCode;
    }

    public void setPackCode(String packCode) {
      this.packCode = packCode;
    }

    public String getPackageMaterial() {
      return packageMaterial;
    }

    public void setPackageMaterial(String packageMaterial) {
      this.packageMaterial = packageMaterial;
    }

    public String getPaperQty() {
      return paperQty;
    }

    public void setPaperQty(String paperQty) {
      this.paperQty = paperQty;
    }

    public String getPcs() {
      return pcs;
    }

    public void setPcs(String pcs) {
      this.pcs = pcs;
    }

    public String getPlanQty() {
      return planQty;
    }

    public void setPlanQty(String planQty) {
      this.planQty = planQty;
    }

    public String getPrice() {
      return price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    public String getPricingCategory() {
      return pricingCategory;
    }

    public void setPricingCategory(String pricingCategory) {
      this.pricingCategory = pricingCategory;
    }

    public String getProduceCode() {
      return produceCode;
    }

    public void setProduceCode(String produceCode) {
      this.produceCode = produceCode;
    }

    public String getProductCode() {
      return productCode;
    }

    public void setProductCode(String productCode) {
      this.productCode = productCode;
    }

    public String getProductDate() {
      return productDate;
    }

    public void setProductDate(String productDate) {
      this.productDate = productDate;
    }

    public String getPurchasePrice() {
      return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
      this.purchasePrice = purchasePrice;
    }

    public Long getQuantity() {
      return quantity;
    }

    public void setQuantity(Long quantity) {
      this.quantity = quantity;
    }

    public String getReason() {
      return reason;
    }

    public void setReason(String reason) {
      this.reason = reason;
    }

    public String getReceiveQty() {
      return receiveQty;
    }

    public void setReceiveQty(String receiveQty) {
      this.receiveQty = receiveQty;
    }

    public String getReferencePrice() {
      return referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
      this.referencePrice = referencePrice;
    }

    public String getRejectLifecycle() {
      return rejectLifecycle;
    }

    public void setRejectLifecycle(String rejectLifecycle) {
      this.rejectLifecycle = rejectLifecycle;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getRetailPrice() {
      return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
      this.retailPrice = retailPrice;
    }

    public String getSafetyStock() {
      return safetyStock;
    }

    public void setSafetyStock(String safetyStock) {
      this.safetyStock = safetyStock;
    }

    public String getSeasonCode() {
      return seasonCode;
    }

    public void setSeasonCode(String seasonCode) {
      this.seasonCode = seasonCode;
    }

    public String getSeasonName() {
      return seasonName;
    }

    public void setSeasonName(String seasonName) {
      this.seasonName = seasonName;
    }

    public String getShelfLife() {
      return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
      this.shelfLife = shelfLife;
    }

    public String getShortName() {
      return shortName;
    }

    public void setShortName(String shortName) {
      this.shortName = shortName;
    }

    public String getSize() {
      return size;
    }

    public void setSize(String size) {
      this.size = size;
    }

    public String getSkuProperty() {
      return skuProperty;
    }

    public void setSkuProperty(String skuProperty) {
      this.skuProperty = skuProperty;
    }

    public String getSn() {
      return sn;
    }

    public void setSn(String sn) {
      this.sn = sn;
    }

    public String getSnCode() {
      return snCode;
    }

    public void setSnCode(String snCode) {
      this.snCode = snCode;
    }

    public String getSourceOrderCode() {
      return sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
      this.sourceOrderCode = sourceOrderCode;
    }

    public String getStandardPrice() {
      return standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
      this.standardPrice = standardPrice;
    }

    public String getStockStatus() {
      return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
      this.stockStatus = stockStatus;
    }

    public String getStockUnit() {
      return stockUnit;
    }

    public void setStockUnit(String stockUnit) {
      this.stockUnit = stockUnit;
    }

    public String getSubSourceOrderCode() {
      return subSourceOrderCode;
    }

    public void setSubSourceOrderCode(String subSourceOrderCode) {
      this.subSourceOrderCode = subSourceOrderCode;
    }

    public String getSupplierCode() {
      return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
      this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
      return supplierName;
    }

    public void setSupplierName(String supplierName) {
      this.supplierName = supplierName;
    }

    public String getTagPrice() {
      return tagPrice;
    }

    public void setTagPrice(String tagPrice) {
      this.tagPrice = tagPrice;
    }

    public String getTareWeight() {
      return tareWeight;
    }

    public void setTareWeight(String tareWeight) {
      this.tareWeight = tareWeight;
    }

    public String getTempRequirement() {
      return tempRequirement;
    }

    public void setTempRequirement(String tempRequirement) {
      this.tempRequirement = tempRequirement;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getUnit() {
      return unit;
    }

    public void setUnit(String unit) {
      this.unit = unit;
    }

    public String getVolume() {
      return volume;
    }

    public void setVolume(String volume) {
      this.volume = volume;
    }

    public String getWarehouseCode() {
      return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }

    public String getWidth() {
      return width;
    }

    public void setWidth(String width) {
      this.width = width;
    }
  }
}


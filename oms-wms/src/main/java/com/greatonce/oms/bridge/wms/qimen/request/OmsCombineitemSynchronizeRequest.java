package com.greatonce.oms.bridge.wms.qimen.request;

import com.greatonce.core.supports.xml.JaxbMapAdapter;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * TOP API: taobao.qimen.combineitem.synchronize request
 *
 * @author top auto createDetail
 * @since 1.0, 2017.04.27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsCombineitemSynchronizeRequest implements OmsQimenRequest {

  private String itemCode;
  private String itemId;
  private String ownerCode;
  private String warehouseCode;

  @XmlElementWrapper(name = "items")
  @XmlElement(name = "item")
  private List<Item> items;

  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;

  public void setExtendProps(Map<String, String> extendProps) {
    this.extendProps = extendProps;
  }

  public Map<String, String> getExtendProps() {
    return this.extendProps;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public String getItemCode() {
    return this.itemCode;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getItemId() {
    return this.itemId;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public List<Item> getItems() {
    return this.items;
  }

  public void setOwnerCode(String ownerCode) {
    this.ownerCode = ownerCode;
  }

  public String getOwnerCode() {
    return this.ownerCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public String getWarehouseCode() {
    return this.warehouseCode;
  }


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "priceAdjustment")
  public static class PriceAdjustment {

    /**
     * test
     */
    @ApiField("discount")
    private String discount;
    /**
     * test
     */
    @ApiField("endDate")
    private String endDate;
    /**
     * test
     */
    @ApiField("standardPrice")
    private String standardPrice;
    /**
     * test
     */
    @ApiField("startDate")
    private String startDate;
    /**
     * test
     */
    @ApiField("type")
    private String type;


    public String getDiscount() {
      return this.discount;
    }

    public void setDiscount(String discount) {
      this.discount = discount;
    }

    public String getEndDate() {
      return this.endDate;
    }

    public void setEndDate(String endDate) {
      this.endDate = endDate;
    }

    public String getStandardPrice() {
      return this.standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
      this.standardPrice = standardPrice;
    }

    public String getStartDate() {
      return this.startDate;
    }

    public void setStartDate(String startDate) {
      this.startDate = startDate;
    }

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }

  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "batch")
  public static class Batch {

    /**
     * test
     */
    @ApiField("actualQty")
    private String actualQty;
    /**
     * test
     */
    @ApiField("batchCode")
    private String batchCode;
    /**
     * test
     */
    @ApiField("expireDate")
    private String expireDate;
    /**
     * test
     */
    @ApiField("inventoryType")
    private String inventoryType;
    /**
     * test
     */
    @ApiField("produceCode")
    private String produceCode;
    /**
     * test
     */
    @ApiField("productDate")
    private String productDate;
    /**
     * test
     */
    @ApiField("quantity")
    private String quantity;


    public String getActualQty() {
      return this.actualQty;
    }

    public void setActualQty(String actualQty) {
      this.actualQty = actualQty;
    }

    public String getBatchCode() {
      return this.batchCode;
    }

    public void setBatchCode(String batchCode) {
      this.batchCode = batchCode;
    }

    public String getExpireDate() {
      return this.expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

    public String getInventoryType() {
      return this.inventoryType;
    }

    public void setInventoryType(String inventoryType) {
      this.inventoryType = inventoryType;
    }

    public String getProduceCode() {
      return this.produceCode;
    }

    public void setProduceCode(String produceCode) {
      this.produceCode = produceCode;
    }

    public String getProductDate() {
      return this.productDate;
    }

    public void setProductDate(String productDate) {
      this.productDate = productDate;
    }

    public String getQuantity() {
      return this.quantity;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }

  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "item")
  public static class Item {

    /**
     * test
     */
    @ApiField("actualAmount")
    private String actualAmount;
    /**
     * test
     */
    @ApiField("actualQty")
    private String actualQty;
    /**
     * test
     */
    @ApiField("adventLifecycle")
    private String adventLifecycle;
    /**
     * test
     */
    @ApiField("amount")
    private String amount;
    /**
     * test
     */
    @ApiField("approvalNumber")
    private String approvalNumber;
    /**
     * test
     */
    @ApiField("barCode")
    private String barCode;
    /**
     * test
     */
    @ApiField("batchCode")
    private String batchCode;
    /**
     * test
     */
    @ApiField("batchRemark")
    private String batchRemark;
    /**
     * test
     */
    @ApiListField("batchs")
    @ApiField("batch")
    private List<Batch> batchs;
    /**
     * test
     */
    @ApiField("brandCode")
    private String brandCode;
    /**
     * test
     */
    @ApiField("brandName")
    private String brandName;
    /**
     * test
     */
    @ApiField("categoryId")
    private String categoryId;
    /**
     * test
     */
    @ApiField("categoryName")
    private String categoryName;
    /**
     * test
     */
    @ApiField("changeTime")
    private String changeTime;
    /**
     * test
     */
    @ApiField("channelCode")
    private String channelCode;
    /**
     * test
     */
    @ApiField("color")
    private String color;
    /**
     * test
     */
    @ApiField("costPrice")
    private String costPrice;
    /**
     * test
     */
    @ApiField("defectiveQty")
    private String defectiveQty;
    /**
     * test
     */
    @ApiField("diffQuantity")
    private String diffQuantity;
    /**
     * test
     */
    @ApiField("discount")
    private String discount;
    /**
     * test
     */
    @ApiField("discountPrice")
    private String discountPrice;
    /**
     * test
     */
    @ApiField("englishName")
    private String englishName;
    /**
     * test
     */
    @ApiField("exCode")
    private String exCode;
    /**
     * test
     */
    @ApiField("expireDate")
    private String expireDate;
    /**
     * test
     */
    @ApiField("extCode")
    private String extCode;
    /**
     * test
     */
    @ApiField("goodsCode")
    private String goodsCode;
    /**
     * test
     */
    @ApiField("grossWeight")
    private String grossWeight;
    /**
     * test
     */
    @ApiField("height")
    private String height;
    /**
     * test
     */
    @ApiField("inventoryType")
    private String inventoryType;
    /**
     * test
     */
    @ApiField("isAreaSale")
    private String isAreaSale;
    /**
     * test
     */
    @ApiField("isBatchMgmt")
    private String isBatchMgmt;
    /**
     * test
     */
    @ApiField("isFragile")
    private String isFragile;
    /**
     * test
     */
    @ApiField("isHazardous")
    private String isHazardous;
    /**
     * test
     */
    @ApiField("isSNMgmt")
    private String isSNMgmt;
    /**
     * test
     */
    @ApiField("isShelfLifeMgmt")
    private String isShelfLifeMgmt;
    /**
     * test
     */
    @ApiField("isSku")
    private String isSku;
    /**
     * 商品编码
     */
    @ApiField("itemCode")
    private String itemCode;
    /**
     * 后端商品编码
     */
    @ApiField("itemId")
    private String itemId;
    /**
     * test
     */
    @ApiField("itemName")
    private String itemName;
    /**
     * test
     */
    @ApiField("itemType")
    private String itemType;
    /**
     * test
     */
    @ApiField("lackQty")
    private String lackQty;
    /**
     * test
     */
    @ApiField("latestUpdateTime")
    private String latestUpdateTime;
    /**
     * test
     */
    @ApiField("length")
    private String length;
    /**
     * test
     */
    @ApiField("lockQuantity")
    private String lockQuantity;
    /**
     * test
     */
    @ApiField("lockupLifecycle")
    private String lockupLifecycle;
    /**
     * test
     */
    @ApiField("netWeight")
    private String netWeight;
    /**
     * test
     */
    @ApiField("normalQty")
    private String normalQty;
    /**
     * test
     */
    @ApiField("orderCode")
    private String orderCode;
    /**
     * test
     */
    @ApiField("orderLineNo")
    private String orderLineNo;
    /**
     * test
     */
    @ApiField("orderType")
    private String orderType;
    /**
     * test
     */
    @ApiField("originAddress")
    private String originAddress;
    /**
     * test
     */
    @ApiField("originCode")
    private String originCode;
    /**
     * test
     */
    @ApiField("outBizCode")
    private String outBizCode;
    /**
     * ownerCode
     */
    @ApiField("ownerCode")
    private String ownerCode;
    /**
     * test
     */
    @ApiField("packCode")
    private String packCode;
    /**
     * test
     */
    @ApiField("packageMaterial")
    private String packageMaterial;
    /**
     * test
     */
    @ApiField("paperQty")
    private String paperQty;
    /**
     * test
     */
    @ApiField("pcs")
    private String pcs;
    /**
     * test
     */
    @ApiField("planQty")
    private String planQty;
    /**
     * test
     */
    @ApiField("price")
    private String price;
    /**
     * test
     */
    @ApiField("priceAdjustment")
    private PriceAdjustment priceAdjustment;
    /**
     * test
     */
    @ApiField("pricingCategory")
    private String pricingCategory;
    /**
     * test
     */
    @ApiField("produceCode")
    private String produceCode;
    /**
     * test
     */
    @ApiField("productCode")
    private String productCode;
    /**
     * test
     */
    @ApiField("productDate")
    private String productDate;
    /**
     * test
     */
    @ApiField("purchasePrice")
    private String purchasePrice;
    /**
     * 组合商品中的该商品个数
     */
    @ApiField("quantity")
    private Long quantity;
    /**
     * test
     */
    @ApiField("reason")
    private String reason;
    /**
     * test
     */
    @ApiField("receiveQty")
    private String receiveQty;
    /**
     * test
     */
    @ApiField("referencePrice")
    private String referencePrice;
    /**
     * test
     */
    @ApiField("rejectLifecycle")
    private String rejectLifecycle;
    /**
     * test
     */
    @ApiField("retailPrice")
    private String retailPrice;
    /**
     * test
     */
    @ApiField("safetyStock")
    private String safetyStock;
    /**
     * test
     */
    @ApiField("seasonCode")
    private String seasonCode;
    /**
     * test
     */
    @ApiField("seasonName")
    private String seasonName;
    /**
     * test
     */
    @ApiField("shelfLife")
    private String shelfLife;
    /**
     * test
     */
    @ApiField("shortName")
    private String shortName;
    /**
     * test
     */
    @ApiField("size")
    private String size;
    /**
     * test
     */
    @ApiField("skuProperty")
    private String skuProperty;
    /**
     * test
     */
    @ApiField("sn")
    private String sn;
    /**
     * test
     */
    @ApiField("snCode")
    private String snCode;
    /**
     * test
     */
    @ApiField("sourceOrderCode")
    private String sourceOrderCode;
    /**
     * test
     */
    @ApiField("standardPrice")
    private String standardPrice;
    /**
     * test
     */
    @ApiField("stockStatus")
    private String stockStatus;
    /**
     * test
     */
    @ApiField("stockUnit")
    private String stockUnit;
    /**
     * test
     */
    @ApiField("subSourceOrderCode")
    private String subSourceOrderCode;
    /**
     * test
     */
    @ApiField("supplierCode")
    private String supplierCode;
    /**
     * test
     */
    @ApiField("supplierName")
    private String supplierName;
    /**
     * test
     */
    @ApiField("tagPrice")
    private String tagPrice;
    /**
     * test
     */
    @ApiField("tareWeight")
    private String tareWeight;
    /**
     * test
     */
    @ApiField("tempRequirement")
    private String tempRequirement;
    /**
     * test
     */
    @ApiField("title")
    private String title;
    /**
     * test
     */
    @ApiField("unit")
    private String unit;
    /**
     * test
     */
    @ApiField("volume")
    private String volume;
    /**
     * test
     */
    @ApiField("warehouseCode")
    private String warehouseCode;
    /**
     * test
     */
    @ApiField("width")
    private String width;


    public String getActualAmount() {
      return this.actualAmount;
    }

    public void setActualAmount(String actualAmount) {
      this.actualAmount = actualAmount;
    }

    public String getActualQty() {
      return this.actualQty;
    }

    public void setActualQty(String actualQty) {
      this.actualQty = actualQty;
    }

    public String getAdventLifecycle() {
      return this.adventLifecycle;
    }

    public void setAdventLifecycle(String adventLifecycle) {
      this.adventLifecycle = adventLifecycle;
    }

    public String getAmount() {
      return this.amount;
    }

    public void setAmount(String amount) {
      this.amount = amount;
    }

    public String getApprovalNumber() {
      return this.approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
      this.approvalNumber = approvalNumber;
    }

    public String getBarCode() {
      return this.barCode;
    }

    public void setBarCode(String barCode) {
      this.barCode = barCode;
    }

    public String getBatchCode() {
      return this.batchCode;
    }

    public void setBatchCode(String batchCode) {
      this.batchCode = batchCode;
    }

    public String getBatchRemark() {
      return this.batchRemark;
    }

    public void setBatchRemark(String batchRemark) {
      this.batchRemark = batchRemark;
    }

    public List<Batch> getBatchs() {
      return this.batchs;
    }

    public void setBatchs(List<Batch> batchs) {
      this.batchs = batchs;
    }

    public String getBrandCode() {
      return this.brandCode;
    }

    public void setBrandCode(String brandCode) {
      this.brandCode = brandCode;
    }

    public String getBrandName() {
      return this.brandName;
    }

    public void setBrandName(String brandName) {
      this.brandName = brandName;
    }

    public String getCategoryId() {
      return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
      this.categoryId = categoryId;
    }

    public String getCategoryName() {
      return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
    }

    public String getChangeTime() {
      return this.changeTime;
    }

    public void setChangeTime(String changeTime) {
      this.changeTime = changeTime;
    }

    public String getChannelCode() {
      return this.channelCode;
    }

    public void setChannelCode(String channelCode) {
      this.channelCode = channelCode;
    }

    public String getColor() {
      return this.color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public String getCostPrice() {
      return this.costPrice;
    }

    public void setCostPrice(String costPrice) {
      this.costPrice = costPrice;
    }

    public String getDefectiveQty() {
      return this.defectiveQty;
    }

    public void setDefectiveQty(String defectiveQty) {
      this.defectiveQty = defectiveQty;
    }

    public String getDiffQuantity() {
      return this.diffQuantity;
    }

    public void setDiffQuantity(String diffQuantity) {
      this.diffQuantity = diffQuantity;
    }

    public String getDiscount() {
      return this.discount;
    }

    public void setDiscount(String discount) {
      this.discount = discount;
    }

    public String getDiscountPrice() {
      return this.discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
      this.discountPrice = discountPrice;
    }

    public String getEnglishName() {
      return this.englishName;
    }

    public void setEnglishName(String englishName) {
      this.englishName = englishName;
    }

    public String getExCode() {
      return this.exCode;
    }

    public void setExCode(String exCode) {
      this.exCode = exCode;
    }

    public String getExpireDate() {
      return this.expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

    public String getExtCode() {
      return this.extCode;
    }

    public void setExtCode(String extCode) {
      this.extCode = extCode;
    }

    public String getGoodsCode() {
      return this.goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
      this.goodsCode = goodsCode;
    }

    public String getGrossWeight() {
      return this.grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
      this.grossWeight = grossWeight;
    }

    public String getHeight() {
      return this.height;
    }

    public void setHeight(String height) {
      this.height = height;
    }

    public String getInventoryType() {
      return this.inventoryType;
    }

    public void setInventoryType(String inventoryType) {
      this.inventoryType = inventoryType;
    }

    public String getIsAreaSale() {
      return this.isAreaSale;
    }

    public void setIsAreaSale(String isAreaSale) {
      this.isAreaSale = isAreaSale;
    }

    public String getIsBatchMgmt() {
      return this.isBatchMgmt;
    }

    public void setIsBatchMgmt(String isBatchMgmt) {
      this.isBatchMgmt = isBatchMgmt;
    }

    public String getIsFragile() {
      return this.isFragile;
    }

    public void setIsFragile(String isFragile) {
      this.isFragile = isFragile;
    }

    public String getIsHazardous() {
      return this.isHazardous;
    }

    public void setIsHazardous(String isHazardous) {
      this.isHazardous = isHazardous;
    }

    public String getIsSNMgmt() {
      return this.isSNMgmt;
    }

    public void setIsSNMgmt(String isSNMgmt) {
      this.isSNMgmt = isSNMgmt;
    }

    public String getIsShelfLifeMgmt() {
      return this.isShelfLifeMgmt;
    }

    public void setIsShelfLifeMgmt(String isShelfLifeMgmt) {
      this.isShelfLifeMgmt = isShelfLifeMgmt;
    }

    public String getIsSku() {
      return this.isSku;
    }

    public void setIsSku(String isSku) {
      this.isSku = isSku;
    }

    public String getItemCode() {
      return this.itemCode;
    }

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public String getItemId() {
      return this.itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public String getItemName() {
      return this.itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public String getItemType() {
      return this.itemType;
    }

    public void setItemType(String itemType) {
      this.itemType = itemType;
    }

    public String getLackQty() {
      return this.lackQty;
    }

    public void setLackQty(String lackQty) {
      this.lackQty = lackQty;
    }

    public String getLatestUpdateTime() {
      return this.latestUpdateTime;
    }

    public void setLatestUpdateTime(String latestUpdateTime) {
      this.latestUpdateTime = latestUpdateTime;
    }

    public String getLength() {
      return this.length;
    }

    public void setLength(String length) {
      this.length = length;
    }

    public String getLockQuantity() {
      return this.lockQuantity;
    }

    public void setLockQuantity(String lockQuantity) {
      this.lockQuantity = lockQuantity;
    }

    public String getLockupLifecycle() {
      return this.lockupLifecycle;
    }

    public void setLockupLifecycle(String lockupLifecycle) {
      this.lockupLifecycle = lockupLifecycle;
    }

    public String getNetWeight() {
      return this.netWeight;
    }

    public void setNetWeight(String netWeight) {
      this.netWeight = netWeight;
    }

    public String getNormalQty() {
      return this.normalQty;
    }

    public void setNormalQty(String normalQty) {
      this.normalQty = normalQty;
    }

    public String getOrderCode() {
      return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
      this.orderCode = orderCode;
    }

    public String getOrderLineNo() {
      return this.orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
      this.orderLineNo = orderLineNo;
    }

    public String getOrderType() {
      return this.orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getOriginAddress() {
      return this.originAddress;
    }

    public void setOriginAddress(String originAddress) {
      this.originAddress = originAddress;
    }

    public String getOriginCode() {
      return this.originCode;
    }

    public void setOriginCode(String originCode) {
      this.originCode = originCode;
    }

    public String getOutBizCode() {
      return this.outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
      this.outBizCode = outBizCode;
    }

    public String getOwnerCode() {
      return this.ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }

    public String getPackCode() {
      return this.packCode;
    }

    public void setPackCode(String packCode) {
      this.packCode = packCode;
    }

    public String getPackageMaterial() {
      return this.packageMaterial;
    }

    public void setPackageMaterial(String packageMaterial) {
      this.packageMaterial = packageMaterial;
    }

    public String getPaperQty() {
      return this.paperQty;
    }

    public void setPaperQty(String paperQty) {
      this.paperQty = paperQty;
    }

    public String getPcs() {
      return this.pcs;
    }

    public void setPcs(String pcs) {
      this.pcs = pcs;
    }

    public String getPlanQty() {
      return this.planQty;
    }

    public void setPlanQty(String planQty) {
      this.planQty = planQty;
    }

    public String getPrice() {
      return this.price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    public PriceAdjustment getPriceAdjustment() {
      return this.priceAdjustment;
    }

    public void setPriceAdjustment(PriceAdjustment priceAdjustment) {
      this.priceAdjustment = priceAdjustment;
    }

    public String getPricingCategory() {
      return this.pricingCategory;
    }

    public void setPricingCategory(String pricingCategory) {
      this.pricingCategory = pricingCategory;
    }

    public String getProduceCode() {
      return this.produceCode;
    }

    public void setProduceCode(String produceCode) {
      this.produceCode = produceCode;
    }

    public String getProductCode() {
      return this.productCode;
    }

    public void setProductCode(String productCode) {
      this.productCode = productCode;
    }

    public String getProductDate() {
      return this.productDate;
    }

    public void setProductDate(String productDate) {
      this.productDate = productDate;
    }

    public String getPurchasePrice() {
      return this.purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
      this.purchasePrice = purchasePrice;
    }

    public Long getQuantity() {
      return this.quantity;
    }

    public void setQuantity(Long quantity) {
      this.quantity = quantity;
    }

    public String getReason() {
      return this.reason;
    }

    public void setReason(String reason) {
      this.reason = reason;
    }

    public String getReceiveQty() {
      return this.receiveQty;
    }

    public void setReceiveQty(String receiveQty) {
      this.receiveQty = receiveQty;
    }

    public String getReferencePrice() {
      return this.referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
      this.referencePrice = referencePrice;
    }

    public String getRejectLifecycle() {
      return this.rejectLifecycle;
    }

    public void setRejectLifecycle(String rejectLifecycle) {
      this.rejectLifecycle = rejectLifecycle;
    }

    public String getRetailPrice() {
      return this.retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
      this.retailPrice = retailPrice;
    }

    public String getSafetyStock() {
      return this.safetyStock;
    }

    public void setSafetyStock(String safetyStock) {
      this.safetyStock = safetyStock;
    }

    public String getSeasonCode() {
      return this.seasonCode;
    }

    public void setSeasonCode(String seasonCode) {
      this.seasonCode = seasonCode;
    }

    public String getSeasonName() {
      return this.seasonName;
    }

    public void setSeasonName(String seasonName) {
      this.seasonName = seasonName;
    }

    public String getShelfLife() {
      return this.shelfLife;
    }

    public void setShelfLife(String shelfLife) {
      this.shelfLife = shelfLife;
    }

    public String getShortName() {
      return this.shortName;
    }

    public void setShortName(String shortName) {
      this.shortName = shortName;
    }

    public String getSize() {
      return this.size;
    }

    public void setSize(String size) {
      this.size = size;
    }

    public String getSkuProperty() {
      return this.skuProperty;
    }

    public void setSkuProperty(String skuProperty) {
      this.skuProperty = skuProperty;
    }

    public String getSn() {
      return this.sn;
    }

    public void setSn(String sn) {
      this.sn = sn;
    }

    public String getSnCode() {
      return this.snCode;
    }

    public void setSnCode(String snCode) {
      this.snCode = snCode;
    }

    public String getSourceOrderCode() {
      return this.sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
      this.sourceOrderCode = sourceOrderCode;
    }

    public String getStandardPrice() {
      return this.standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
      this.standardPrice = standardPrice;
    }

    public String getStockStatus() {
      return this.stockStatus;
    }

    public void setStockStatus(String stockStatus) {
      this.stockStatus = stockStatus;
    }

    public String getStockUnit() {
      return this.stockUnit;
    }

    public void setStockUnit(String stockUnit) {
      this.stockUnit = stockUnit;
    }

    public String getSubSourceOrderCode() {
      return this.subSourceOrderCode;
    }

    public void setSubSourceOrderCode(String subSourceOrderCode) {
      this.subSourceOrderCode = subSourceOrderCode;
    }

    public String getSupplierCode() {
      return this.supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
      this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
      return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
      this.supplierName = supplierName;
    }

    public String getTagPrice() {
      return this.tagPrice;
    }

    public void setTagPrice(String tagPrice) {
      this.tagPrice = tagPrice;
    }

    public String getTareWeight() {
      return this.tareWeight;
    }

    public void setTareWeight(String tareWeight) {
      this.tareWeight = tareWeight;
    }

    public String getTempRequirement() {
      return this.tempRequirement;
    }

    public void setTempRequirement(String tempRequirement) {
      this.tempRequirement = tempRequirement;
    }

    public String getTitle() {
      return this.title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getUnit() {
      return this.unit;
    }

    public void setUnit(String unit) {
      this.unit = unit;
    }

    public String getVolume() {
      return this.volume;
    }

    public void setVolume(String volume) {
      this.volume = volume;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }

    public String getWidth() {
      return this.width;
    }

    public void setWidth(String width) {
      this.width = width;
    }

  }
}
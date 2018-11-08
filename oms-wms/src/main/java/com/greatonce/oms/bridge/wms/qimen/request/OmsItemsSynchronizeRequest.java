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
 * TOP API: taobao.qimen.items.synchronize request
 *
 * @author top auto createDetail
 * @since 1.0, 2017.04.27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsItemsSynchronizeRequest implements OmsQimenRequest {

  private String ownerCode;
  private String warehouseCode;
  private String actionType;
  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;
  @XmlElementWrapper(name = "items")
  @XmlElement(name = "item")
  private List<Item> items;

  public void setActionType(String actionType) {
    this.actionType = actionType;
  }

  public String getActionType() {
    return this.actionType;
  }

  public void setExtendProps(Map extendProps) {
    this.extendProps = extendProps;
  }

  public Map getExtendProps() {
    return this.extendProps;
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

  public static class Batch {

    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("actualQty")
    private String actualQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("batchCode")
    private String batchCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("expireDate")
    private String expireDate;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("inventoryType")
    private String inventoryType;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("produceCode")
    private String produceCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("productDate")
    private String productDate;
    /**
     * 奇门仓储字段,说明,string(50),,
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
  @XmlRootElement(name = "priceAdjustment")
  public static class PriceAdjustment {

    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("discount")
    private String discount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("endDate")
    private String endDate;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("standardPrice")
    private String standardPrice;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("startDate")
    private String startDate;
    /**
     * 奇门仓储字段,说明,string(50),,
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
  @XmlRootElement(name = "item")
  public static class Item {

    /**
     * temp
     */
    @ApiField("actualAmount")
    private String actualAmount;
    /**
     * temp
     */
    @ApiField("actualQty")
    private String actualQty;
    /**
     * 保质期临期预警天数
     */
    @ApiField("adventLifecycle")
    private Long adventLifecycle;
    /**
     * temp
     */
    @ApiField("amount")
    private String amount;
    /**
     * 批准文号
     */
    @ApiField("approvalNumber")
    private String approvalNumber;
    /**
     * 条形码(可多个;用分号;隔开)
     */
    @ApiField("barCode")
    private String barCode;
    /**
     * 批次代码
     */
    @ApiField("batchCode")
    private String batchCode;
    /**
     * 批次备注
     */
    @ApiField("batchRemark")
    private String batchRemark;
    /**
     * batchs
     */
    @ApiListField("batchs")
    @ApiField("batch")
    private List<Batch> batchs;
    /**
     * 品牌代码
     */
    @ApiField("brandCode")
    private String brandCode;
    /**
     * 品牌名称
     */
    @ApiField("brandName")
    private String brandName;
    /**
     * 商品类别ID
     */
    @ApiField("categoryId")
    private String categoryId;
    /**
     * 商品类别名称
     */
    @ApiField("categoryName")
    private String categoryName;
    /**
     * temp
     */
    @ApiField("changeTime")
    private String changeTime;
    /**
     * temp
     */
    @ApiField("channelCode")
    private String channelCode;
    /**
     * 颜色
     */
    @ApiField("color")
    private String color;
    /**
     * 成本价
     */
    @ApiField("costPrice")
    private String costPrice;
    /**
     * 创建时间(YYYY-MM-DD HH:MM:SS)
     */
    @ApiField("createTime")
    private String createTime;
    /**
     * temp
     */
    @ApiField("defectiveQty")
    private String defectiveQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("diffQuantity")
    private String diffQuantity;
    /**
     * temp
     */
    @ApiField("discount")
    private String discount;
    /**
     * temp
     */
    @ApiField("discountPrice")
    private String discountPrice;
    /**
     * 英文名
     */
    @ApiField("englishName")
    private String englishName;
    /**
     * temp
     */
    @ApiField("exCode")
    private String exCode;
    /**
     * 过期日期(YYYY-MM-DD)
     */
    @ApiField("expireDate")
    private String expireDate;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("extCode")
    private String extCode;
    /**
     * 货号
     */
    @ApiField("goodsCode")
    private String goodsCode;
    /**
     * 毛重(单位：千克)
     */
    @ApiField("grossWeight")
    private String grossWeight;
    /**
     * 高(单位：厘米)
     */
    @ApiField("height")
    private String height;
    /**
     * temp
     */
    @ApiField("inventoryType")
    private String inventoryType;
    /**
     * temp
     */
    @ApiField("isAreaSale")
    private String isAreaSale;
    /**
     * 是否需要批次管理(Y/N ;默认为N)
     */
    @ApiField("isBatchMgmt")
    private String isBatchMgmt;
    /**
     * 是否易碎品(Y/N ;默认为N)
     */
    @ApiField("isFragile")
    private String isFragile;
    /**
     * 是否危险品(Y/N ;默认为N)
     */
    @ApiField("isHazardous")
    private String isHazardous;
    /**
     * 是否需要串号管理(Y/N ;默认为N)
     */
    @ApiField("isSNMgmt")
    private String isSNMgmt;
    /**
     * 是否需要保质期管理(Y/N ;默认为N)
     */
    @ApiField("isShelfLifeMgmt")
    private String isShelfLifeMgmt;
    /**
     * 是否sku(Y/N ;默认为N)
     */
    @ApiField("isSku")
    private String isSku;
    /**
     * 是否有效(Y/N ;默认为N)
     */
    @ApiField("isValid")
    private String isValid;
    /**
     * 商品编码
     */
    @ApiField("itemCode")
    private String itemCode;
    /**
     * 仓储系统商品编码(该字段是WMS分配的商品编号;WMS如果分配了商品编码;则后续的商品操作都需要传该字段;如果WMS不使用 ;WMS可 以返回itemId=itemCode的值)
     */
    @ApiField("itemId")
    private String itemId;
    /**
     * 商品名称
     */
    @ApiField("itemName")
    private String itemName;
    /**
     * 商品类型(ZC=正常商品;FX=分销商品;ZH=组合商品;ZP=赠品;BC=包材;HC=耗材;FL=辅料;XN=虚拟品;FS=附属品;CC=残次品;OTHER=其它;只传英文编码)
     */
    @ApiField("itemType")
    private String itemType;
    /**
     * temp
     */
    @ApiField("lackQty")
    private String lackQty;
    /**
     * temp
     */
    @ApiField("latestUpdateTime")
    private String latestUpdateTime;
    /**
     * 长(单位：厘米)
     */
    @ApiField("length")
    private String length;
    /**
     * temp
     */
    @ApiField("lockQuantity")
    private String lockQuantity;
    /**
     * 保质期禁售天数
     */
    @ApiField("lockupLifecycle")
    private Long lockupLifecycle;
    /**
     * 净重(单位：千克)
     */
    @ApiField("netWeight")
    private String netWeight;
    /**
     * temp
     */
    @ApiField("normalQty")
    private String normalQty;
    /**
     * temp
     */
    @ApiField("orderCode")
    private String orderCode;
    /**
     * temp
     */
    @ApiField("orderLineNo")
    private String orderLineNo;
    /**
     * temp
     */
    @ApiField("orderType")
    private String orderType;
    /**
     * 商品的原产地
     */
    @ApiField("originAddress")
    private String originAddress;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("originCode")
    private String originCode;
    /**
     * temp
     */
    @ApiField("outBizCode")
    private String outBizCode;
    /**
     * temp
     */
    @ApiField("ownerCode")
    private String ownerCode;
    /**
     * 包装代码
     */
    @ApiField("packCode")
    private String packCode;
    /**
     * 商品包装材料类型
     */
    @ApiField("packageMaterial")
    private String packageMaterial;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("paperQty")
    private String paperQty;
    /**
     * 箱规
     */
    @ApiField("pcs")
    private String pcs;
    /**
     * temp
     */
    @ApiField("planQty")
    private String planQty;
    /**
     * temp
     */
    @ApiField("price")
    private String price;
    /**
     * priceAdjustment
     */
    @ApiField("priceAdjustment")
    private PriceAdjustment priceAdjustment;
    /**
     * 计价货类
     */
    @ApiField("pricingCategory")
    private String pricingCategory;
    /**
     * temp
     */
    @ApiField("produceCode")
    private String produceCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("productCode")
    private String productCode;
    /**
     * 生产日期(YYYY-MM-DD)
     */
    @ApiField("productDate")
    private String productDate;
    /**
     * 采购价
     */
    @ApiField("purchasePrice")
    private String purchasePrice;
    /**
     * temp
     */
    @ApiField("quantity")
    private String quantity;
    /**
     * temp
     */
    @ApiField("reason")
    private String reason;
    /**
     * temp
     */
    @ApiField("receiveQty")
    private String receiveQty;
    /**
     * temp
     */
    @ApiField("referencePrice")
    private String referencePrice;
    /**
     * 保质期禁收天数
     */
    @ApiField("rejectLifecycle")
    private Long rejectLifecycle;
    /**
     * 备注
     */
    @ApiField("remark")
    private String remark;
    /**
     * 零售价
     */
    @ApiField("retailPrice")
    private String retailPrice;
    /**
     * 安全库存
     */
    @ApiField("safetyStock")
    private Long safetyStock;
    /**
     * 季节编码
     */
    @ApiField("seasonCode")
    private String seasonCode;
    /**
     * 季节名称
     */
    @ApiField("seasonName")
    private String seasonName;
    /**
     * 保质期(单位：小时)
     */
    @ApiField("shelfLife")
    private Long shelfLife;
    /**
     * 商品简称
     */
    @ApiField("shortName")
    private String shortName;
    /**
     * 尺寸
     */
    @ApiField("size")
    private String size;
    /**
     * 商品属性(如红色;XXL)
     */
    @ApiField("skuProperty")
    private String skuProperty;
    /**
     * temp
     */
    @ApiField("sn")
    private String sn;
    /**
     * temp
     */
    @ApiField("snCode")
    private String snCode;
    /**
     * temp
     */
    @ApiField("sourceOrderCode")
    private String sourceOrderCode;
    /**
     * temp
     */
    @ApiField("standardPrice")
    private String standardPrice;
    /**
     * temp
     */
    @ApiField("stockStatus")
    private String stockStatus;
    /**
     * 商品计量单位
     */
    @ApiField("stockUnit")
    private String stockUnit;
    /**
     * temp
     */
    @ApiField("subSourceOrderCode")
    private String subSourceOrderCode;
    /**
     * temp
     */
    @ApiField("supplierCode")
    private String supplierCode;
    /**
     * temp
     */
    @ApiField("supplierName")
    private String supplierName;
    /**
     * 吊牌价
     */
    @ApiField("tagPrice")
    private String tagPrice;
    /**
     * temp
     */
    @ApiField("tareWeight")
    private String tareWeight;
    /**
     * temp
     */
    @ApiField("tempRequirement")
    private String tempRequirement;
    /**
     * 渠道中的商品标题
     */
    @ApiField("title")
    private String title;
    /**
     * temp
     */
    @ApiField("unit")
    private String unit;
    /**
     * 更新时间(YYYY-MM-DD HH:MM:SS)
     */
    @ApiField("updateTime")
    private String updateTime;
    /**
     * 体积(单位：升)
     */
    @ApiField("volume")
    private String volume;
    /**
     * temp
     */
    @ApiField("warehouseCode")
    private String warehouseCode;
    /**
     * 宽(单位：厘米)
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

    public Long getAdventLifecycle() {
      return this.adventLifecycle;
    }

    public void setAdventLifecycle(Long adventLifecycle) {
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

    public String getCreateTime() {
      return this.createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
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

    public String getIsValid() {
      return this.isValid;
    }

    public void setIsValid(String isValid) {
      this.isValid = isValid;
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

    public Long getLockupLifecycle() {
      return this.lockupLifecycle;
    }

    public void setLockupLifecycle(Long lockupLifecycle) {
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

    public String getQuantity() {
      return this.quantity;
    }

    public void setQuantity(String quantity) {
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

    public Long getRejectLifecycle() {
      return this.rejectLifecycle;
    }

    public void setRejectLifecycle(Long rejectLifecycle) {
      this.rejectLifecycle = rejectLifecycle;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getRetailPrice() {
      return this.retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
      this.retailPrice = retailPrice;
    }

    public Long getSafetyStock() {
      return this.safetyStock;
    }

    public void setSafetyStock(Long safetyStock) {
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

    public Long getShelfLife() {
      return this.shelfLife;
    }

    public void setShelfLife(Long shelfLife) {
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

    public String getUpdateTime() {
      return this.updateTime;
    }

    public void setUpdateTime(String updateTime) {
      this.updateTime = updateTime;
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
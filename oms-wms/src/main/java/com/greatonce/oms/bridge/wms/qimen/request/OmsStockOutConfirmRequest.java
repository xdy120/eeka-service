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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsStockOutConfirmRequest implements OmsBaseQimenRequest {


  @Override
  public String getOwnerCode() {
    return this.getDeliveryOrder().getOwnerCode();
  }

  @Override
  public String getOrderCode() {
    return this.getDeliveryOrder().getDeliveryOrderCode();
  }

  @Override
  public String getOutCode() {
    return this.deliveryOrder.getDeliveryOrderId();
  }

  @Override
  public String getWarehouseCode() {
    return this.getDeliveryOrder().getWarehouseCode();
  }

  @Override
  public String getOutBizCode() {
    return this.getDeliveryOrder().getOutBizCode();
  }

  private OmsStockOutOrder deliveryOrder;
  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;
  @XmlElementWrapper(name = "orderLines")
  @XmlElement(name = "orderLine")
  private List<OmsStockOutOrderLine> orderLines;
  @XmlElementWrapper(name = "packages")
  @XmlElement(name = "package")
  private List<OmsStockOutOrderPackage> packages;


  public OmsStockOutOrder getDeliveryOrder() {
    return this.deliveryOrder;
  }

  public void setExtendProps(Map<String, String> extendProps) {
    this.extendProps = extendProps;
  }

  public Map<String, String> getExtendProps() {
    return this.extendProps;
  }

  public void setOrderLines(List<OmsStockOutOrderLine> orderLines) {
    this.orderLines = orderLines;
  }

  public List<OmsStockOutOrderLine> getOrderLines() {
    return this.orderLines;
  }

  public void setPackages(List<OmsStockOutOrderPackage> packages) {
    this.packages = packages;
  }

  public List<OmsStockOutOrderPackage> getPackages() {
    return this.packages;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "deliveryOrder")
  public static class OmsStockOutOrder {

    private String actualAmount;
    private String arAmount;
    private String batchCode;
    private String businessMemo;
    private String buyerMessage;
    private String buyerName;
    private String buyerNick;
    private String buyerPhone;
    private String collectedAmount;
    private Long confirmType;
    private String createTime;
    private String declaredAmount;
    private String deliveryNote;
    private String deliveryOrderCode;
    private String deliveryOrderId;
    private String discountAmount;
    private String exceptionCode;
    private String expressCode;
    private String fetchItemLocation;
    private String freight;
    private String gotAmount;
    private String identifyCode;
    private String insuranceFlag;
    private String invoiceFlag;
    @XmlElementWrapper(name = "invoices")
    @XmlElement(name = "invoice")
    private List<OmsInvoice> invoices;
    private String isCod;
    private String isPaymentCollected;
    private String isUrgency;
    private String isValueDeclared;
    private String itemAmount;
    private String itemCode;
    private String itemName;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OmsStockOutOrderItem> items;
    private String lineNumber;
    private String logisticsAreaCode;
    private String logisticsCode;
    private String logisticsName;
    private String maxArrivalTime;
    private String mergeOrderCodes;
    private String mergeOrderFlag;
    private String minArrivalTime;
    private String modifiedTime;
    private String noStackTag;
    private String operateTime;
    private String operatorCode;
    private String operatorName;
    private String orderConfirmTime;
    private String orderFlag;
    @XmlElementWrapper(name = "orderLines")
    @XmlElement(name = "orderLine")
    private List<OmsStockOutOrderLine> orderLines;
    private String orderNote;
    private String orderSourceCode;
    private String orderStatus;
    private String orderType;
    private String outBizCode;
    private String ownerCode;
    private String packCode;
    @XmlElementWrapper(name = "packages")
    @XmlElement(name = "package")
    private List<OmsStockOutOrderPackage> packages;
    private String payMethod;
    private String payNo;
    private String payTime;
    private String personalOrderNote;
    private String personalPackageNote;
    private OmsPickerInfo pickerInfo;
    private String placeOrderTime;
    private String planArrivalTime;
    private String planDeliveryDate;
    private String preDeliveryOrderCode;
    private String preDeliveryOrderId;
    private String presaleOrderType;
    private String price;
    private String priorityCode;
    private String produceDate;
    private String quantity;
    private String receiveOrderTime;
    private OmsReceiverInfo receiverInfo;
    private String remark;
    private String salesModel;
    private String scheduleDate;
    private String sellerId;
    private String sellerMessage;
    private String sellerNick;
    private OmsSenderInfo senderInfo;
    private String serviceCode;
    private String serviceFee;
    private String shelfLife;
    private String shopCode;
    private String shopNick;
    private String sourceOrderCode;
    private String sourcePlatformCode;
    private String sourcePlatformName;
    private String status;
    private String storageFee;
    private String supplierCode;
    private String supplierName;
    private String totalAmount;
    private Long totalOrderLines;
    private String transportMode;
    private String transpostSum;
    private String uomCode;
    private String warehouseAddressCode;
    private String warehouseCode;

    public OmsStockOutOrder() {
    }

    public String getActualAmount() {
      return this.actualAmount;
    }

    public void setActualAmount(String actualAmount) {
      this.actualAmount = actualAmount;
    }

    public String getArAmount() {
      return this.arAmount;
    }

    public void setArAmount(String arAmount) {
      this.arAmount = arAmount;
    }

    public String getBatchCode() {
      return this.batchCode;
    }

    public void setBatchCode(String batchCode) {
      this.batchCode = batchCode;
    }

    public String getBusinessMemo() {
      return this.businessMemo;
    }

    public void setBusinessMemo(String businessMemo) {
      this.businessMemo = businessMemo;
    }

    public String getBuyerMessage() {
      return this.buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
      this.buyerMessage = buyerMessage;
    }

    public String getBuyerName() {
      return this.buyerName;
    }

    public void setBuyerName(String buyerName) {
      this.buyerName = buyerName;
    }

    public String getBuyerNick() {
      return this.buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
      this.buyerNick = buyerNick;
    }

    public String getBuyerPhone() {
      return this.buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
      this.buyerPhone = buyerPhone;
    }

    public String getCollectedAmount() {
      return this.collectedAmount;
    }

    public void setCollectedAmount(String collectedAmount) {
      this.collectedAmount = collectedAmount;
    }

    public Long getConfirmType() {
      return this.confirmType;
    }

    public void setConfirmType(Long confirmType) {
      this.confirmType = confirmType;
    }

    public String getCreateTime() {
      return this.createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    public String getDeclaredAmount() {
      return this.declaredAmount;
    }

    public void setDeclaredAmount(String declaredAmount) {
      this.declaredAmount = declaredAmount;
    }

    public String getDeliveryNote() {
      return this.deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
      this.deliveryNote = deliveryNote;
    }

    public String getDeliveryOrderCode() {
      return this.deliveryOrderCode;
    }

    public void setDeliveryOrderCode(String deliveryOrderCode) {
      this.deliveryOrderCode = deliveryOrderCode;
    }

    public String getDeliveryOrderId() {
      return this.deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
      this.deliveryOrderId = deliveryOrderId;
    }

    public String getDiscountAmount() {
      return this.discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
      this.discountAmount = discountAmount;
    }

    public String getExceptionCode() {
      return this.exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
      this.exceptionCode = exceptionCode;
    }

    public String getExpressCode() {
      return this.expressCode;
    }

    public void setExpressCode(String expressCode) {
      this.expressCode = expressCode;
    }

    public String getFetchItemLocation() {
      return this.fetchItemLocation;
    }

    public void setFetchItemLocation(String fetchItemLocation) {
      this.fetchItemLocation = fetchItemLocation;
    }

    public String getFreight() {
      return this.freight;
    }

    public void setFreight(String freight) {
      this.freight = freight;
    }

    public String getGotAmount() {
      return this.gotAmount;
    }

    public void setGotAmount(String gotAmount) {
      this.gotAmount = gotAmount;
    }

    public String getIdentifyCode() {
      return this.identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
      this.identifyCode = identifyCode;
    }

    public String getInsuranceFlag() {
      return this.insuranceFlag;
    }

    public void setInsuranceFlag(String insuranceFlag) {
      this.insuranceFlag = insuranceFlag;
    }

    public String getInvoiceFlag() {
      return this.invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
      this.invoiceFlag = invoiceFlag;
    }

    public List<OmsInvoice> getInvoices() {
      return this.invoices;
    }

    public void setInvoices(List<OmsInvoice> invoices) {
      this.invoices = invoices;
    }

    public String getIsCod() {
      return this.isCod;
    }

    public void setIsCod(String isCod) {
      this.isCod = isCod;
    }

    public String getIsPaymentCollected() {
      return this.isPaymentCollected;
    }

    public void setIsPaymentCollected(String isPaymentCollected) {
      this.isPaymentCollected = isPaymentCollected;
    }

    public String getIsUrgency() {
      return this.isUrgency;
    }

    public void setIsUrgency(String isUrgency) {
      this.isUrgency = isUrgency;
    }

    public String getIsValueDeclared() {
      return this.isValueDeclared;
    }

    public void setIsValueDeclared(String isValueDeclared) {
      this.isValueDeclared = isValueDeclared;
    }

    public String getItemAmount() {
      return this.itemAmount;
    }

    public void setItemAmount(String itemAmount) {
      this.itemAmount = itemAmount;
    }

    public String getItemCode() {
      return this.itemCode;
    }

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public String getItemName() {
      return this.itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public List<OmsStockOutOrderItem> getItems() {
      return this.items;
    }

    public void setItems(List<OmsStockOutOrderItem> items) {
      this.items = items;
    }

    public String getLineNumber() {
      return this.lineNumber;
    }

    public void setLineNumber(String lineNumber) {
      this.lineNumber = lineNumber;
    }

    public String getLogisticsAreaCode() {
      return this.logisticsAreaCode;
    }

    public void setLogisticsAreaCode(String logisticsAreaCode) {
      this.logisticsAreaCode = logisticsAreaCode;
    }

    public String getLogisticsCode() {
      return this.logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
      this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
      return this.logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
      this.logisticsName = logisticsName;
    }

    public String getMaxArrivalTime() {
      return this.maxArrivalTime;
    }

    public void setMaxArrivalTime(String maxArrivalTime) {
      this.maxArrivalTime = maxArrivalTime;
    }

    public String getMergeOrderCodes() {
      return this.mergeOrderCodes;
    }

    public void setMergeOrderCodes(String mergeOrderCodes) {
      this.mergeOrderCodes = mergeOrderCodes;
    }

    public String getMergeOrderFlag() {
      return this.mergeOrderFlag;
    }

    public void setMergeOrderFlag(String mergeOrderFlag) {
      this.mergeOrderFlag = mergeOrderFlag;
    }

    public String getMinArrivalTime() {
      return this.minArrivalTime;
    }

    public void setMinArrivalTime(String minArrivalTime) {
      this.minArrivalTime = minArrivalTime;
    }

    public String getModifiedTime() {
      return this.modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
      this.modifiedTime = modifiedTime;
    }

    public String getNoStackTag() {
      return this.noStackTag;
    }

    public void setNoStackTag(String noStackTag) {
      this.noStackTag = noStackTag;
    }

    public String getOperateTime() {
      return this.operateTime;
    }

    public void setOperateTime(String operateTime) {
      this.operateTime = operateTime;
    }

    public String getOperatorCode() {
      return this.operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
      this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
      return this.operatorName;
    }

    public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
    }

    public String getOrderConfirmTime() {
      return this.orderConfirmTime;
    }

    public void setOrderConfirmTime(String orderConfirmTime) {
      this.orderConfirmTime = orderConfirmTime;
    }

    public String getOrderFlag() {
      return this.orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
      this.orderFlag = orderFlag;
    }

    public List<OmsStockOutOrderLine> getOrderLines() {
      return this.orderLines;
    }

    public void setOrderLines(List<OmsStockOutOrderLine> orderLines) {
      this.orderLines = orderLines;
    }

    public String getOrderNote() {
      return this.orderNote;
    }

    public void setOrderNote(String orderNote) {
      this.orderNote = orderNote;
    }

    public String getOrderSourceCode() {
      return this.orderSourceCode;
    }

    public void setOrderSourceCode(String orderSourceCode) {
      this.orderSourceCode = orderSourceCode;
    }

    public String getOrderStatus() {
      return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
      this.orderStatus = orderStatus;
    }

    public String getOrderType() {
      return this.orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
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

    public List<OmsStockOutOrderPackage> getPackages() {
      return this.packages;
    }

    public void setPackages(List<OmsStockOutOrderPackage> packages) {
      this.packages = packages;
    }

    public String getPayMethod() {
      return this.payMethod;
    }

    public void setPayMethod(String payMethod) {
      this.payMethod = payMethod;
    }

    public String getPayNo() {
      return this.payNo;
    }

    public void setPayNo(String payNo) {
      this.payNo = payNo;
    }

    public String getPayTime() {
      return this.payTime;
    }

    public void setPayTime(String payTime) {
      this.payTime = payTime;
    }

    public String getPersonalOrderNote() {
      return this.personalOrderNote;
    }

    public void setPersonalOrderNote(String personalOrderNote) {
      this.personalOrderNote = personalOrderNote;
    }

    public String getPersonalPackageNote() {
      return this.personalPackageNote;
    }

    public void setPersonalPackageNote(String personalPackageNote) {
      this.personalPackageNote = personalPackageNote;
    }

    public OmsPickerInfo getPickerInfo() {
      return this.pickerInfo;
    }

    public void setPickerInfo(OmsPickerInfo pickerInfo) {
      this.pickerInfo = pickerInfo;
    }

    public String getPlaceOrderTime() {
      return this.placeOrderTime;
    }

    public void setPlaceOrderTime(String placeOrderTime) {
      this.placeOrderTime = placeOrderTime;
    }

    public String getPlanArrivalTime() {
      return this.planArrivalTime;
    }

    public void setPlanArrivalTime(String planArrivalTime) {
      this.planArrivalTime = planArrivalTime;
    }

    public String getPlanDeliveryDate() {
      return this.planDeliveryDate;
    }

    public void setPlanDeliveryDate(String planDeliveryDate) {
      this.planDeliveryDate = planDeliveryDate;
    }

    public String getPreDeliveryOrderCode() {
      return this.preDeliveryOrderCode;
    }

    public void setPreDeliveryOrderCode(String preDeliveryOrderCode) {
      this.preDeliveryOrderCode = preDeliveryOrderCode;
    }

    public String getPreDeliveryOrderId() {
      return this.preDeliveryOrderId;
    }

    public void setPreDeliveryOrderId(String preDeliveryOrderId) {
      this.preDeliveryOrderId = preDeliveryOrderId;
    }

    public String getPresaleOrderType() {
      return this.presaleOrderType;
    }

    public void setPresaleOrderType(String presaleOrderType) {
      this.presaleOrderType = presaleOrderType;
    }

    public String getPrice() {
      return this.price;
    }

    public void setPrice(String price) {
      this.price = price;
    }

    public String getPriorityCode() {
      return this.priorityCode;
    }

    public void setPriorityCode(String priorityCode) {
      this.priorityCode = priorityCode;
    }

    public String getProduceDate() {
      return this.produceDate;
    }

    public void setProduceDate(String produceDate) {
      this.produceDate = produceDate;
    }

    public String getQuantity() {
      return this.quantity;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }

    public String getReceiveOrderTime() {
      return this.receiveOrderTime;
    }

    public void setReceiveOrderTime(String receiveOrderTime) {
      this.receiveOrderTime = receiveOrderTime;
    }

    public OmsReceiverInfo getReceiverInfo() {
      return this.receiverInfo;
    }

    public void setReceiverInfo(OmsReceiverInfo receiverInfo) {
      this.receiverInfo = receiverInfo;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getSalesModel() {
      return this.salesModel;
    }

    public void setSalesModel(String salesModel) {
      this.salesModel = salesModel;
    }

    public String getScheduleDate() {
      return this.scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
      this.scheduleDate = scheduleDate;
    }

    public String getSellerId() {
      return this.sellerId;
    }

    public void setSellerId(String sellerId) {
      this.sellerId = sellerId;
    }

    public String getSellerMessage() {
      return this.sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
      this.sellerMessage = sellerMessage;
    }

    public String getSellerNick() {
      return this.sellerNick;
    }

    public void setSellerNick(String sellerNick) {
      this.sellerNick = sellerNick;
    }

    public OmsSenderInfo getSenderInfo() {
      return this.senderInfo;
    }

    public void setSenderInfo(OmsSenderInfo senderInfo) {
      this.senderInfo = senderInfo;
    }

    public String getServiceCode() {
      return this.serviceCode;
    }

    public void setServiceCode(String serviceCode) {
      this.serviceCode = serviceCode;
    }

    public String getServiceFee() {
      return this.serviceFee;
    }

    public void setServiceFee(String serviceFee) {
      this.serviceFee = serviceFee;
    }

    public String getShelfLife() {
      return this.shelfLife;
    }

    public void setShelfLife(String shelfLife) {
      this.shelfLife = shelfLife;
    }

    public String getShopCode() {
      return this.shopCode;
    }

    public void setShopCode(String shopCode) {
      this.shopCode = shopCode;
    }

    public String getShopNick() {
      return this.shopNick;
    }

    public void setShopNick(String shopNick) {
      this.shopNick = shopNick;
    }

    public String getSourceOrderCode() {
      return this.sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
      this.sourceOrderCode = sourceOrderCode;
    }

    public String getSourcePlatformCode() {
      return this.sourcePlatformCode;
    }

    public void setSourcePlatformCode(String sourcePlatformCode) {
      this.sourcePlatformCode = sourcePlatformCode;
    }

    public String getSourcePlatformName() {
      return this.sourcePlatformName;
    }

    public void setSourcePlatformName(String sourcePlatformName) {
      this.sourcePlatformName = sourcePlatformName;
    }

    public String getStatus() {
      return this.status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getStorageFee() {
      return this.storageFee;
    }

    public void setStorageFee(String storageFee) {
      this.storageFee = storageFee;
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

    public String getTotalAmount() {
      return this.totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
      this.totalAmount = totalAmount;
    }

    public Long getTotalOrderLines() {
      return this.totalOrderLines;
    }

    public void setTotalOrderLines(Long totalOrderLines) {
      this.totalOrderLines = totalOrderLines;
    }

    public String getTransportMode() {
      return this.transportMode;
    }

    public void setTransportMode(String transportMode) {
      this.transportMode = transportMode;
    }

    public String getTranspostSum() {
      return this.transpostSum;
    }

    public void setTranspostSum(String transpostSum) {
      this.transpostSum = transpostSum;
    }

    public String getUomCode() {
      return this.uomCode;
    }

    public void setUomCode(String uomCode) {
      this.uomCode = uomCode;
    }

    public String getWarehouseAddressCode() {
      return this.warehouseAddressCode;
    }

    public void setWarehouseAddressCode(String warehouseAddressCode) {
      this.warehouseAddressCode = warehouseAddressCode;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "batch")
  public static class OmsStockOutOrderLineBatch {

    private String actualQty;
    private String batchCode;
    private String expireDate;
    private String inventoryType;
    private String produceCode;
    private String productDate;
    private String quantity;
    private String remark;

    public OmsStockOutOrderLineBatch() {
    }

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

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "item")
  public static class OmsStockOutOrderItem {

    private String actualAmount;
    private String actualQty;
    private String adventLifecycle;
    private String amount;
    private String approvalNumber;
    private String barCode;
    private String batchCode;
    private String batchRemark;
    @XmlElementWrapper(name = "batchs")
    @XmlElement(name = "batch")
    private List<OmsStockOutOrderLineBatch> batchs;
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
    private String quantity;
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

    public OmsStockOutOrderItem() {
    }

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

    public List<OmsStockOutOrderLineBatch> getBatchs() {
      return this.batchs;
    }

    public void setBatchs(List<OmsStockOutOrderLineBatch> batchs) {
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

    public String getRejectLifecycle() {
      return this.rejectLifecycle;
    }

    public void setRejectLifecycle(String rejectLifecycle) {
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

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "orderLine")
  public static class OmsStockOutOrderLine {

    private String actualPrice;
    private String actualQty;
    private String amount;
    private String batchCode;
    @XmlElementWrapper(name = "batchs")
    @XmlElement(name = "batch")
    private List<OmsStockOutOrderLineBatch> batchs;
    private String color;
    private String deliveryOrderId;
    private String discount;
    private String discountAmount;
    private String discountPrice;
    private String exceptionQty;
    private String expireDate;
    private String extCode;
    private String inventoryType;
    private String itemCode;
    private String itemId;
    private String itemName;
    private String locationCode;
    private String moveInLocation;
    private String moveOutLocation;
    private String orderLineNo;
    private String orderSourceCode;
    private String outBizCode;
    private String ownerCode;
    private String payNo;
    private String planQty;
    private String produceCode;
    private String productCode;
    private String productDate;
    private String purchasePrice;
    private String qrCode;
    private String quantity;
    private String referencePrice;
    private String remark;
    private String retailPrice;
    private String settlementAmount;
    private String size;
    private String skuProperty;
    private String sourceOrderCode;
    private String standardAmount;
    private String standardPrice;
    private String status;
    private String stockInQty;
    private String stockOutQty;
    private String subDeliveryOrderId;
    private String subSourceCode;
    private String subSourceOrderCode;
    private String taobaoItemCode;
    private String warehouseCode;

    public OmsStockOutOrderLine() {
    }

    public String getActualPrice() {
      return this.actualPrice;
    }

    public void setActualPrice(String actualPrice) {
      this.actualPrice = actualPrice;
    }

    public String getActualQty() {
      return this.actualQty;
    }

    public void setActualQty(String actualQty) {
      this.actualQty = actualQty;
    }

    public String getAmount() {
      return this.amount;
    }

    public void setAmount(String amount) {
      this.amount = amount;
    }

    public String getBatchCode() {
      return this.batchCode;
    }

    public void setBatchCode(String batchCode) {
      this.batchCode = batchCode;
    }

    public List<OmsStockOutOrderLineBatch> getBatchs() {
      return this.batchs;
    }

    public void setBatchs(List<OmsStockOutOrderLineBatch> batchs) {
      this.batchs = batchs;
    }

    public String getColor() {
      return this.color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public String getDeliveryOrderId() {
      return this.deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
      this.deliveryOrderId = deliveryOrderId;
    }

    public String getDiscount() {
      return this.discount;
    }

    public void setDiscount(String discount) {
      this.discount = discount;
    }

    public String getDiscountAmount() {
      return this.discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
      this.discountAmount = discountAmount;
    }

    public String getDiscountPrice() {
      return this.discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
      this.discountPrice = discountPrice;
    }

    public String getExceptionQty() {
      return this.exceptionQty;
    }

    public void setExceptionQty(String exceptionQty) {
      this.exceptionQty = exceptionQty;
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

    public String getInventoryType() {
      return this.inventoryType;
    }

    public void setInventoryType(String inventoryType) {
      this.inventoryType = inventoryType;
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

    public String getLocationCode() {
      return this.locationCode;
    }

    public void setLocationCode(String locationCode) {
      this.locationCode = locationCode;
    }

    public String getMoveInLocation() {
      return this.moveInLocation;
    }

    public void setMoveInLocation(String moveInLocation) {
      this.moveInLocation = moveInLocation;
    }

    public String getMoveOutLocation() {
      return this.moveOutLocation;
    }

    public void setMoveOutLocation(String moveOutLocation) {
      this.moveOutLocation = moveOutLocation;
    }

    public String getOrderLineNo() {
      return this.orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
      this.orderLineNo = orderLineNo;
    }

    public String getOrderSourceCode() {
      return this.orderSourceCode;
    }

    public void setOrderSourceCode(String orderSourceCode) {
      this.orderSourceCode = orderSourceCode;
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

    public String getPayNo() {
      return this.payNo;
    }

    public void setPayNo(String payNo) {
      this.payNo = payNo;
    }

    public String getPlanQty() {
      return this.planQty;
    }

    public void setPlanQty(String planQty) {
      this.planQty = planQty;
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

    public String getQrCode() {
      return this.qrCode;
    }

    public void setQrCode(String qrCode) {
      this.qrCode = qrCode;
    }

    public String getQuantity() {
      return this.quantity;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }

    public String getReferencePrice() {
      return this.referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
      this.referencePrice = referencePrice;
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

    public String getSettlementAmount() {
      return this.settlementAmount;
    }

    public void setSettlementAmount(String settlementAmount) {
      this.settlementAmount = settlementAmount;
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

    public String getSourceOrderCode() {
      return this.sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
      this.sourceOrderCode = sourceOrderCode;
    }

    public String getStandardAmount() {
      return this.standardAmount;
    }

    public void setStandardAmount(String standardAmount) {
      this.standardAmount = standardAmount;
    }

    public String getStandardPrice() {
      return this.standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
      this.standardPrice = standardPrice;
    }

    public String getStatus() {
      return this.status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getStockInQty() {
      return this.stockInQty;
    }

    public void setStockInQty(String stockInQty) {
      this.stockInQty = stockInQty;
    }

    public String getStockOutQty() {
      return this.stockOutQty;
    }

    public void setStockOutQty(String stockOutQty) {
      this.stockOutQty = stockOutQty;
    }

    public String getSubDeliveryOrderId() {
      return this.subDeliveryOrderId;
    }

    public void setSubDeliveryOrderId(String subDeliveryOrderId) {
      this.subDeliveryOrderId = subDeliveryOrderId;
    }

    public String getSubSourceCode() {
      return this.subSourceCode;
    }

    public void setSubSourceCode(String subSourceCode) {
      this.subSourceCode = subSourceCode;
    }

    public String getSubSourceOrderCode() {
      return this.subSourceOrderCode;
    }

    public void setSubSourceOrderCode(String subSourceOrderCode) {
      this.subSourceOrderCode = subSourceOrderCode;
    }

    public String getTaobaoItemCode() {
      return this.taobaoItemCode;
    }

    public void setTaobaoItemCode(String taobaoItemCode) {
      this.taobaoItemCode = taobaoItemCode;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "package")
  public static class OmsStockOutOrderPackage {

    private String expressCode;
    private String height;
    private String invoiceNo;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OmsStockOutOrderItem> items;
    private String length;
    private String logisticsCode;
    private String logisticsName;
    private String packageCode;
    private List<OmsPackageMaterial> packageMaterialList;
    private String remark;
    private String theoreticalWeight;
    private String volume;
    private String weight;
    private String width;

    public OmsStockOutOrderPackage() {
    }

    public String getExpressCode() {
      return this.expressCode;
    }

    public void setExpressCode(String expressCode) {
      this.expressCode = expressCode;
    }

    public String getHeight() {
      return this.height;
    }

    public void setHeight(String height) {
      this.height = height;
    }

    public String getInvoiceNo() {
      return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
      this.invoiceNo = invoiceNo;
    }

    public List<OmsStockOutOrderItem> getItems() {
      return this.items;
    }

    public void setItems(List<OmsStockOutOrderItem> items) {
      this.items = items;
    }

    public String getLength() {
      return this.length;
    }

    public void setLength(String length) {
      this.length = length;
    }

    public String getLogisticsCode() {
      return this.logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
      this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
      return this.logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
      this.logisticsName = logisticsName;
    }

    public String getPackageCode() {
      return this.packageCode;
    }

    public void setPackageCode(String packageCode) {
      this.packageCode = packageCode;
    }

    public List<OmsPackageMaterial> getPackageMaterialList() {
      return this.packageMaterialList;
    }

    public void setPackageMaterialList(
        List<OmsPackageMaterial> packageMaterialList) {
      this.packageMaterialList = packageMaterialList;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getTheoreticalWeight() {
      return this.theoreticalWeight;
    }

    public void setTheoreticalWeight(String theoreticalWeight) {
      this.theoreticalWeight = theoreticalWeight;
    }

    public String getVolume() {
      return this.volume;
    }

    public void setVolume(String volume) {
      this.volume = volume;
    }

    public String getWeight() {
      return this.weight;
    }

    public void setWeight(String weight) {
      this.weight = weight;
    }

    public String getWidth() {
      return this.width;
    }

    public void setWidth(String width) {
      this.width = width;
    }

  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "packageMaterial")
  public static class OmsPackageMaterial {

    private String quantity;
    private String remark;
    private String type;

    public OmsPackageMaterial() {
    }

    public String getQuantity() {
      return this.quantity;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "pickerInfo")
  public static class OmsPickerInfo {

    private String area;
    private String birthDate;
    private String carNo;
    private String career;
    private String city;
    private String company;
    private String countryCode;
    private String countryCodeCiq;
    private String countryCodeCus;
    private String detailAddress;
    private String email;
    private String fax;
    private String gender;
    private String id;
    private String idNumber;
    private String idType;
    private String mobile;
    private String name;
    private String nick;
    private String province;
    private String remark;
    private String tel;
    private String town;
    private String zipCode;

    public OmsPickerInfo() {
    }

    public String getArea() {
      return this.area;
    }

    public void setArea(String area) {
      this.area = area;
    }

    public String getBirthDate() {
      return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
      this.birthDate = birthDate;
    }

    public String getCarNo() {
      return this.carNo;
    }

    public void setCarNo(String carNo) {
      this.carNo = carNo;
    }

    public String getCareer() {
      return this.career;
    }

    public void setCareer(String career) {
      this.career = career;
    }

    public String getCity() {
      return this.city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getCompany() {
      return this.company;
    }

    public void setCompany(String company) {
      this.company = company;
    }

    public String getCountryCode() {
      return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
    }

    public String getCountryCodeCiq() {
      return this.countryCodeCiq;
    }

    public void setCountryCodeCiq(String countryCodeCiq) {
      this.countryCodeCiq = countryCodeCiq;
    }

    public String getCountryCodeCus() {
      return this.countryCodeCus;
    }

    public void setCountryCodeCus(String countryCodeCus) {
      this.countryCodeCus = countryCodeCus;
    }

    public String getDetailAddress() {
      return this.detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
      this.detailAddress = detailAddress;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getFax() {
      return this.fax;
    }

    public void setFax(String fax) {
      this.fax = fax;
    }

    public String getGender() {
      return this.gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    public String getId() {
      return this.id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getIdNumber() {
      return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
      this.idNumber = idNumber;
    }

    public String getIdType() {
      return this.idType;
    }

    public void setIdType(String idType) {
      this.idType = idType;
    }

    public String getMobile() {
      return this.mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNick() {
      return this.nick;
    }

    public void setNick(String nick) {
      this.nick = nick;
    }

    public String getProvince() {
      return this.province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getTel() {
      return this.tel;
    }

    public void setTel(String tel) {
      this.tel = tel;
    }

    public String getTown() {
      return this.town;
    }

    public void setTown(String town) {
      this.town = town;
    }

    public String getZipCode() {
      return this.zipCode;
    }

    public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "item")
  public static class OmsInvoice {

    private String amount;
    private String code;
    private String content;
    private OmsDetail detail;
    private String header;
    private String number;
    private String remark;
    private String type;

    public OmsInvoice() {
    }

    public String getAmount() {
      return this.amount;
    }

    public void setAmount(String amount) {
      this.amount = amount;
    }

    public String getCode() {
      return this.code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getContent() {
      return this.content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public OmsDetail getDetail() {
      return this.detail;
    }

    public void setDetail(OmsDetail detail) {
      this.detail = detail;
    }

    public String getHeader() {
      return this.header;
    }

    public void setHeader(String header) {
      this.header = header;
    }

    public String getNumber() {
      return this.number;
    }

    public void setNumber(String number) {
      this.number = number;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
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
  public static class OmsDetail {

    private List<OmsStockOutOrderItem> items;

    public List<OmsStockOutOrderItem> getItems() {
      return this.items;
    }

    public void setItems(List<OmsStockOutOrderItem> items) {
      this.items = items;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "item")
  public static class OmsPriceAdjustment {

    private String discount;
    private String endDate;
    private String remark;
    private String standardPrice;
    private String startDate;
    private String type;

    public OmsPriceAdjustment() {
    }

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

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
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
  @XmlRootElement(name = "receiverInfo")
  public static class OmsReceiverInfo {

    private String area;
    private String birthDate;
    private String carNo;
    private String career;
    private String city;
    private String company;
    private String countryCode;
    private String countryCodeCiq;
    private String countryCodeCus;
    private String detailAddress;
    private String email;
    private String fax;
    private String gender;
    private String id;
    private String idNumber;
    private String idType;
    private String mobile;
    private String name;
    private String nick;
    private String province;
    private String remark;
    private String tel;
    private String town;
    private String zipCode;

    public OmsReceiverInfo() {
    }

    public String getArea() {
      return this.area;
    }

    public void setArea(String area) {
      this.area = area;
    }

    public String getBirthDate() {
      return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
      this.birthDate = birthDate;
    }

    public String getCarNo() {
      return this.carNo;
    }

    public void setCarNo(String carNo) {
      this.carNo = carNo;
    }

    public String getCareer() {
      return this.career;
    }

    public void setCareer(String career) {
      this.career = career;
    }

    public String getCity() {
      return this.city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getCompany() {
      return this.company;
    }

    public void setCompany(String company) {
      this.company = company;
    }

    public String getCountryCode() {
      return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
    }

    public String getCountryCodeCiq() {
      return this.countryCodeCiq;
    }

    public void setCountryCodeCiq(String countryCodeCiq) {
      this.countryCodeCiq = countryCodeCiq;
    }

    public String getCountryCodeCus() {
      return this.countryCodeCus;
    }

    public void setCountryCodeCus(String countryCodeCus) {
      this.countryCodeCus = countryCodeCus;
    }

    public String getDetailAddress() {
      return this.detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
      this.detailAddress = detailAddress;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getFax() {
      return this.fax;
    }

    public void setFax(String fax) {
      this.fax = fax;
    }

    public String getGender() {
      return this.gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    public String getId() {
      return this.id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getIdNumber() {
      return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
      this.idNumber = idNumber;
    }

    public String getIdType() {
      return this.idType;
    }

    public void setIdType(String idType) {
      this.idType = idType;
    }

    public String getMobile() {
      return this.mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNick() {
      return this.nick;
    }

    public void setNick(String nick) {
      this.nick = nick;
    }

    public String getProvince() {
      return this.province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getTel() {
      return this.tel;
    }

    public void setTel(String tel) {
      this.tel = tel;
    }

    public String getTown() {
      return this.town;
    }

    public void setTown(String town) {
      this.town = town;
    }

    public String getZipCode() {
      return this.zipCode;
    }

    public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "senderInfo")
  public static class OmsSenderInfo {

    private String area;
    private String birthDate;
    private String carNo;
    private String career;
    private String city;
    private String company;
    private String countryCode;
    private String countryCodeCiq;
    private String countryCodeCus;
    private String detailAddress;
    private String email;
    private String fax;
    private String gender;
    private String id;
    private String idNumber;
    private String idType;
    private String mobile;
    private String name;
    private String nick;
    private String province;
    private String remark;
    private String tel;
    private String town;
    private String zipCode;

    public OmsSenderInfo() {
    }

    public String getArea() {
      return this.area;
    }

    public void setArea(String area) {
      this.area = area;
    }

    public String getBirthDate() {
      return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
      this.birthDate = birthDate;
    }

    public String getCarNo() {
      return this.carNo;
    }

    public void setCarNo(String carNo) {
      this.carNo = carNo;
    }

    public String getCareer() {
      return this.career;
    }

    public void setCareer(String career) {
      this.career = career;
    }

    public String getCity() {
      return this.city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getCompany() {
      return this.company;
    }

    public void setCompany(String company) {
      this.company = company;
    }

    public String getCountryCode() {
      return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
      this.countryCode = countryCode;
    }

    public String getCountryCodeCiq() {
      return this.countryCodeCiq;
    }

    public void setCountryCodeCiq(String countryCodeCiq) {
      this.countryCodeCiq = countryCodeCiq;
    }

    public String getCountryCodeCus() {
      return this.countryCodeCus;
    }

    public void setCountryCodeCus(String countryCodeCus) {
      this.countryCodeCus = countryCodeCus;
    }

    public String getDetailAddress() {
      return this.detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
      this.detailAddress = detailAddress;
    }

    public String getEmail() {
      return this.email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getFax() {
      return this.fax;
    }

    public void setFax(String fax) {
      this.fax = fax;
    }

    public String getGender() {
      return this.gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    public String getId() {
      return this.id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getIdNumber() {
      return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
      this.idNumber = idNumber;
    }

    public String getIdType() {
      return this.idType;
    }

    public void setIdType(String idType) {
      this.idType = idType;
    }

    public String getMobile() {
      return this.mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getNick() {
      return this.nick;
    }

    public void setNick(String nick) {
      this.nick = nick;
    }

    public String getProvince() {
      return this.province;
    }

    public void setProvince(String province) {
      this.province = province;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getTel() {
      return this.tel;
    }

    public void setTel(String tel) {
      this.tel = tel;
    }

    public String getTown() {
      return this.town;
    }

    public void setTown(String town) {
      this.town = town;
    }

    public String getZipCode() {
      return this.zipCode;
    }

    public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
    }
  }
}

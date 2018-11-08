package com.greatonce.oms.bo.report;

import com.greatonce.oms.domain.enums.marketing.PresellType;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.PayType;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import java.time.LocalDateTime;

public class SalesBO {

  private Integer quantity;
  private Double settlementAmount;
  private Double distributionAmount;
  private String salesOrderCode;
  private String tradeId;
  private LocalDateTime paidTime;
  private String expressName;
  private String expressNo;
  private String lastExpressName;
  private String lastExpressNo;
  private String buyerMemo;
  private String sellerMemo;
  private String status;
  private String storeName;
  private Double actualAmount;
  private String provinceName;
  private String cityName;
  private String districtName;
  private String contact;
  private String address;
  private String mobile;
  private Double expressFee;
  private String productName;
  private String memberName;
  private String barcode;
  private String color;
  private String size;
  private String productCode;
  private Long virtualWarehouseId;
  private String virtualWarehouseName;
  private String weight;
  private LocalDateTime deliveryTime;
  private Long presellId;
  private Double sellingAmount;
  private Double discountAmount;
  private Boolean combination;
  private PayType payType;
  private LocalDateTime mallCreatedTime;
  private DispatchStatus dispatchStatus;
  private Boolean oos;
  private DeliveryStatus deliveryStatus;
  private PresellType presellType;
  private String skuName;
  private String skuCode;
  private Double distributionPrice;
  private Double sellingPrice;
  private Double settlementPrice;
  private String warehouseName;
  private Double costPrice;
  private SalesOrderType salesOrderType;
  private Boolean dropShipping;
  private String reissueReason;
  private LocalDateTime createdTime;
  private SalesOrderDetailRefundStatus salesOrderDetailRefundStatus;

  private String dispatchOrderCode;
  private Integer noticeQuantity;
  private Integer outQuantity;

  public PayType getPayType() {
    return payType;
  }

  public void setPayType(PayType payType) {
    this.payType = payType;
  }

  public DispatchStatus getDispatchStatus() {
    return dispatchStatus;
  }

  public void setDispatchStatus(DispatchStatus dispatchStatus) {
    this.dispatchStatus = dispatchStatus;
  }

  public Boolean isOos() {
    return oos;
  }

  public void setOos(Boolean oos) {
    this.oos = oos;
  }

  public DeliveryStatus getDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public PresellType getPresellType() {
    return presellType;
  }

  public void setPresellType(PresellType presellType) {
    this.presellType = presellType;
  }

  public String getSkuName() {
    return skuName;
  }

  public void setSkuName(String skuName) {
    this.skuName = skuName;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getSettlementAmount() {
    return settlementAmount;
  }

  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  public Double getDistributionAmount() {
    return distributionAmount;
  }

  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  public String getSalesOrderCode() {
    return salesOrderCode;
  }

  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public LocalDateTime getPaidTime() {
    return paidTime;
  }

  public void setPaidTime(LocalDateTime paidTime) {
    this.paidTime = paidTime;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getBuyerMemo() {
    return buyerMemo;
  }

  public void setBuyerMemo(String buyerMemo) {
    this.buyerMemo = buyerMemo;
  }

  public String getSellerMemo() {
    return sellerMemo;
  }

  public void setSellerMemo(String sellerMemo) {
    this.sellerMemo = sellerMemo;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public Double getActualAmount() {
    return actualAmount;
  }

  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String districtName) {
    this.districtName = districtName;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Double getExpressFee() {
    return expressFee;
  }

  public void setExpressFee(Double expressFee) {
    this.expressFee = expressFee;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getBarcode() {
    return barcode;
  }

  public void setBarcode(String barcode) {
    this.barcode = barcode;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  public String getVirtualWarehouseName() {
    return virtualWarehouseName;
  }

  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public LocalDateTime getDeliveryTime() {
    return deliveryTime;
  }

  public void setDeliveryTime(LocalDateTime deliveryTime) {
    this.deliveryTime = deliveryTime;
  }

  public Long getPresellId() {
    return presellId;
  }

  public void setPresellId(Long presellId) {
    this.presellId = presellId;
  }

  public Double getSellingAmount() {
    return sellingAmount;
  }

  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  public Double getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  public Boolean isCombination() {
    return combination;
  }

  public void setCombination(Boolean combination) {
    this.combination = combination;
  }

  public LocalDateTime getMallCreatedTime() {
    return mallCreatedTime;
  }

  public void setMallCreatedTime(LocalDateTime mallCreatedTime) {
    this.mallCreatedTime = mallCreatedTime;
  }

  public Double getDistributionPrice() {
    return distributionPrice;
  }

  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  public Double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public Double getSettlementPrice() {
    return settlementPrice;
  }

  public void setSettlementPrice(Double settlementPrice) {
    this.settlementPrice = settlementPrice;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public Double getCostPrice() {
    return costPrice;
  }

  public void setCostPrice(Double costPrice) {
    this.costPrice = costPrice;
  }

  public SalesOrderType getSalesOrderType() {
    return salesOrderType;
  }

  public void setSalesOrderType(SalesOrderType salesOrderType) {
    this.salesOrderType = salesOrderType;
  }

  public Boolean isDropShipping() {
    return dropShipping;
  }

  public void setDropShipping(Boolean dropShipping) {
    this.dropShipping = dropShipping;
  }

  public String getReissueReason() {
    return reissueReason;
  }

  public void setReissueReason(String reissueReason) {
    this.reissueReason = reissueReason;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public SalesOrderDetailRefundStatus getSalesOrderDetailRefundStatus() {
    return salesOrderDetailRefundStatus;
  }

  public void setSalesOrderDetailRefundStatus(
      SalesOrderDetailRefundStatus salesOrderDetailRefundStatus) {
    this.salesOrderDetailRefundStatus = salesOrderDetailRefundStatus;
  }

  public String getDispatchOrderCode() {
    return dispatchOrderCode;
  }

  public void setDispatchOrderCode(String dispatchOrderCode) {
    this.dispatchOrderCode = dispatchOrderCode;
  }

  public Integer getNoticeQuantity() {
    return noticeQuantity;
  }

  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  public Integer getOutQuantity() {
    return outQuantity;
  }

  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  public String getLastExpressName() {
    return lastExpressName;
  }

  public void setLastExpressName(String lastExpressName) {
    this.lastExpressName = lastExpressName;
  }

  public String getLastExpressNo() {
    return lastExpressNo;
  }

  public void setLastExpressNo(String lastExpressNo) {
    this.lastExpressNo = lastExpressNo;
  }
}

package com.greatonce.oms.consumer.trade.dispatch.wrapper;

import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.WmsOrderStatus;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.InvoiceInfo;
import com.greatonce.oms.domain.trade.SalesOrder;

import com.greatonce.oms.domain.trade.WaybillInfo;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/28
 */
public class DispatchOrderWrapper extends DispatchOrder {

  private final DispatchOrder dispatchOrder;
  private SalesOrder mainSalesOrder;
  private boolean merged;

  public DispatchOrderWrapper(DispatchOrder dispatchOrder, SalesOrder mainSalesOrder) {
    this.dispatchOrder = dispatchOrder;
    this.mainSalesOrder = mainSalesOrder;
  }

  public boolean isMerged() {
    return merged;
  }

  public void setMerged(boolean merged) {
    this.merged = merged;
    dispatchOrder.setMerge(merged);
  }

  public SalesOrder getMainSalesOrder() {
    return mainSalesOrder;
  }

  public void setMainSalesOrder(SalesOrder mainSalesOrder) {
    this.mainSalesOrder = mainSalesOrder;
  }

  /**
   * @return 是否满足整单
   */
  public boolean isWhole() {
    return this.getDetails().stream().noneMatch(x -> ((DispatchOrderDetailWrapper) x).isOos());
  }

  /**
   * @return 是否整单缺货
   */
  public boolean isOutOfStock() {
    return this.getDetails().stream().anyMatch(x -> !((DispatchOrderDetailWrapper) x).isOos());
  }

  @Override
  public void setPrimaryKey(Long pk) {
    dispatchOrder.setPrimaryKey(pk);
  }

  @Override
  public Long getPrimaryKey() {
    return dispatchOrder.getPrimaryKey();
  }

  @Override
  public void setActualAmount(Double actualAmount) {
    dispatchOrder.setActualAmount(actualAmount);
  }

  @Override
  public Double getActualAmount() {
    return dispatchOrder.getActualAmount();
  }

  @Override
  public void setAddress(String address) {
    dispatchOrder.setAddress(address);
  }

  @Override
  public String getAddress() {
    return dispatchOrder.getAddress();
  }

  @Override
  public void setBuyerMemo(String buyerMemo) {
    dispatchOrder.setBuyerMemo(buyerMemo);
  }

  @Override
  public String getBuyerMemo() {
    return dispatchOrder.getBuyerMemo();
  }

  @Override
  public void setCainiaoOrderId(String cainiaoOrderId) {
    dispatchOrder.setCainiaoOrderId(cainiaoOrderId);
  }

  @Override
  public void setDeliveryFinish(Boolean deliveryFinish) {
    dispatchOrder.setDeliveryFinish(deliveryFinish);
  }

  @Override
  public Boolean isDeliveryFinish() {
    return dispatchOrder.isDeliveryFinish();
  }

  @Override
  public String getCainiaoOrderId() {
    return dispatchOrder.getCainiaoOrderId();
  }

  @Override
  public void setCityId(Long cityId) {
    dispatchOrder.setCityId(cityId);
  }

  @Override
  public Long getCityId() {
    return dispatchOrder.getCityId();
  }

  @Override
  public void setCityName(String cityName) {
    dispatchOrder.setCityName(cityName);
  }

  @Override
  public String getCityName() {
    return dispatchOrder.getCityName();
  }

  @Override
  public void setCodAmount(Double codAmount) {
    dispatchOrder.setCodAmount(codAmount);
  }

  @Override
  public Double getCodAmount() {
    return dispatchOrder.getCodAmount();
  }

  @Override
  public void setContact(String contact) {
    dispatchOrder.setContact(contact);
  }

  @Override
  public String getContact() {
    return dispatchOrder.getContact();
  }

  @Override
  public void setCountryId(Long countryId) {
    dispatchOrder.setCountryId(countryId);
  }

  @Override
  public Long getCountryId() {
    return dispatchOrder.getCountryId();
  }

  @Override
  public void setCountryName(String countryName) {
    dispatchOrder.setCountryName(countryName);
  }

  @Override
  public String getCountryName() {
    return dispatchOrder.getCountryName();
  }

  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    dispatchOrder.setCreatedTime(createdTime);
  }

  @Override
  public LocalDateTime getCreatedTime() {
    return dispatchOrder.getCreatedTime();
  }

  @Override
  public void setCreator(String creator) {
    dispatchOrder.setCreator(creator);
  }

  @Override
  public String getCreator() {
    return dispatchOrder.getCreator();
  }

  @Override
  public void setDiscountAmount(Double discountAmount) {
    dispatchOrder.setDiscountAmount(discountAmount);
  }

  @Override
  public Double getDiscountAmount() {
    return dispatchOrder.getDiscountAmount();
  }

  @Override
  public void setDispatchOrderCode(String dispatchOrderCode) {
    dispatchOrder.setDispatchOrderCode(dispatchOrderCode);
  }

  @Override
  public void setOfflineDelivery(Boolean offlineDelivery) {
    dispatchOrder.setOfflineDelivery(offlineDelivery);
  }

  @Override
  public Boolean isOfflineDelivery() {
    return dispatchOrder.isOfflineDelivery();
  }

  @Override
  public String getDispatchOrderCode() {
    return dispatchOrder.getDispatchOrderCode();
  }

  @Override
  public void setDispatchOrderId(Long dispatchOrderId) {
    dispatchOrder.setDispatchOrderId(dispatchOrderId);
  }

  @Override
  public Long getDispatchOrderId() {
    return dispatchOrder.getDispatchOrderId();
  }

  @Override
  public void setDistributionAmount(Double distributionAmount) {
    dispatchOrder.setDistributionAmount(distributionAmount);
  }

  @Override
  public Double getDistributionAmount() {
    return dispatchOrder.getDistributionAmount();
  }

  @Override
  public void setDistrictId(Long districtId) {
    dispatchOrder.setDistrictId(districtId);
  }

  @Override
  public Long getDistrictId() {
    return dispatchOrder.getDistrictId();
  }

  @Override
  public void setDistrictName(String districtName) {
    dispatchOrder.setDistrictName(districtName);
  }

  @Override
  public String getDistrictName() {
    return dispatchOrder.getDistrictName();
  }

  @Override
  public void setCod(Boolean cod) {
    dispatchOrder.setCod(cod);
  }

  @Override
  public Boolean isCod() {
    return dispatchOrder.isCod();
  }

  @Override
  public void setMerge(Boolean merge) {
    dispatchOrder.setMerge(merge);
  }

  @Override
  public Boolean isMerge() {
    return dispatchOrder.isMerge();
  }

  @Override
  public void setHasExchange(Boolean hasExchange) {
    dispatchOrder.setHasExchange(hasExchange);
  }

  @Override
  public Boolean isHasExchange() {
    return dispatchOrder.isHasExchange();
  }

  @Override
  public void setUrgent(Boolean urgent) {
    dispatchOrder.setUrgent(urgent);
  }

  @Override
  public Boolean isUrgent() {
    return dispatchOrder.isUrgent();
  }

  @Override
  public void setWmsCancel(Boolean wmsCancel) {
    dispatchOrder.setWmsCancel(wmsCancel);
  }

  @Override
  public Boolean isWmsCancel() {
    return dispatchOrder.isWmsCancel();
  }

  @Override
  public void setLastDeliveryTime(LocalDateTime lastDeliveryTime) {
    dispatchOrder.setLastDeliveryTime(lastDeliveryTime);
  }

  @Override
  public LocalDateTime getLastDeliveryTime() {
    return dispatchOrder.getLastDeliveryTime();
  }

  @Override
  public void setMassCode(String massCode) {
    dispatchOrder.setMassCode(massCode);
  }

  @Override
  public String getMassCode() {
    return dispatchOrder.getMassCode();
  }

  @Override
  public void setMassName(String massName) {
    dispatchOrder.setMassName(massName);
  }

  @Override
  public String getMassName() {
    return dispatchOrder.getMassName();
  }

  @Override
  public void setMassShortName(String massShortName) {
    dispatchOrder.setMassShortName(massShortName);
  }

  @Override
  public String getMassShortName() {
    return dispatchOrder.getMassShortName();
  }

  @Override
  public void setMemberId(Long memberId) {
    dispatchOrder.setMemberId(memberId);
  }

  @Override
  public Long getMemberId() {
    return dispatchOrder.getMemberId();
  }

  @Override
  public void setMemberName(String memberName) {
    dispatchOrder.setMemberName(memberName);
  }

  @Override
  public String getMemberName() {
    return dispatchOrder.getMemberName();
  }

  @Override
  public void setMobile(String mobile) {
    dispatchOrder.setMobile(mobile);
  }

  @Override
  public String getMobile() {
    return dispatchOrder.getMobile();
  }

  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    dispatchOrder.setModifiedTime(modifiedTime);
  }

  @Override
  public LocalDateTime getModifiedTime() {
    return dispatchOrder.getModifiedTime();
  }

  @Override
  public void setOutStatus(OutStatus outStatus) {
    dispatchOrder.setOutStatus(outStatus);
  }

  @Override
  public OutStatus getOutStatus() {
    return dispatchOrder.getOutStatus();
  }

  @Override
  public void setPackageNo(String packageNo) {
    dispatchOrder.setPackageNo(packageNo);
  }

  @Override
  public String getPackageNo() {
    return dispatchOrder.getPackageNo();
  }

  @Override
  public void setProvinceId(Long provinceId) {
    dispatchOrder.setProvinceId(provinceId);
  }

  @Override
  public Long getProvinceId() {
    return dispatchOrder.getProvinceId();
  }

  @Override
  public void setProvinceName(String provinceName) {
    dispatchOrder.setProvinceName(provinceName);
  }

  @Override
  public String getProvinceName() {
    return dispatchOrder.getProvinceName();
  }

  @Override
  public void setQuantity(Integer quantity) {
    dispatchOrder.setQuantity(quantity);
  }

  @Override
  public Integer getQuantity() {
    return dispatchOrder.getQuantity();
  }

  @Override
  public void setRemark(String remark) {
    dispatchOrder.setRemark(remark);
  }

  @Override
  public String getRemark() {
    return dispatchOrder.getRemark();
  }

  @Override
  public void setSellerMemo(String sellerMemo) {
    dispatchOrder.setSellerMemo(sellerMemo);
  }

  @Override
  public String getSellerMemo() {
    return dispatchOrder.getSellerMemo();
  }

  @Override
  public void setSellingAmount(Double sellingAmount) {
    dispatchOrder.setSellingAmount(sellingAmount);
  }

  @Override
  public Double getSellingAmount() {
    return dispatchOrder.getSellingAmount();
  }

  @Override
  public void setSettlementAmount(Double settlementAmount) {
    dispatchOrder.setSettlementAmount(settlementAmount);
  }

  @Override
  public Double getSettlementAmount() {
    return dispatchOrder.getSettlementAmount();
  }

  @Override
  public void setStatus(DispatchOrderStatus status) {
    dispatchOrder.setStatus(status);
  }

  @Override
  public DispatchOrderStatus getStatus() {
    return dispatchOrder.getStatus();
  }

  @Override
  public void setStoreId(Long storeId) {
    dispatchOrder.setStoreId(storeId);
  }

  @Override
  public Long getStoreId() {
    return dispatchOrder.getStoreId();
  }

  @Override
  public void setStoreName(String storeName) {
    dispatchOrder.setStoreName(storeName);
  }

  @Override
  public String getStoreName() {
    return dispatchOrder.getStoreName();
  }

  @Override
  public void setSuggestExpressId(Long suggestExpressId) {
    dispatchOrder.setSuggestExpressId(suggestExpressId);
  }

  @Override
  public Long getSuggestExpressId() {
    return dispatchOrder.getSuggestExpressId();
  }

  @Override
  public void setSuggestExpressName(String suggestExpressName) {
    dispatchOrder.setSuggestExpressName(suggestExpressName);
  }

  @Override
  public String getSuggestExpressName() {
    return dispatchOrder.getSuggestExpressName();
  }

  @Override
  public void setSuggestExpressNo(String suggestExpressNo) {
    dispatchOrder.setSuggestExpressNo(suggestExpressNo);
  }

  @Override
  public String getSuggestExpressNo() {
    return dispatchOrder.getSuggestExpressNo();
  }

  @Override
  public void setTelephone(String telephone) {
    dispatchOrder.setTelephone(telephone);
  }

  @Override
  public String getTelephone() {
    return dispatchOrder.getTelephone();
  }

  @Override
  public void setVersion(Integer version) {
    dispatchOrder.setVersion(version);
  }

  @Override
  public Integer getVersion() {
    return dispatchOrder.getVersion();
  }

  @Override
  public void setWarehouseId(Long warehouseId) {
    dispatchOrder.setWarehouseId(warehouseId);
  }

  @Override
  public Long getWarehouseId() {
    return dispatchOrder.getWarehouseId();
  }

  @Override
  public void setWarehouseName(String warehouseName) {
    dispatchOrder.setWarehouseName(warehouseName);
  }

  @Override
  public String getWarehouseName() {
    return dispatchOrder.getWarehouseName();
  }

  @Override
  public void setWeight(Double weight) {
    dispatchOrder.setWeight(weight);
  }

  @Override
  public Double getWeight() {
    return dispatchOrder.getWeight();
  }

  @Override
  public void setZipcode(String zipcode) {
    dispatchOrder.setZipcode(zipcode);
  }

  @Override
  public String getZipcode() {
    return dispatchOrder.getZipcode();
  }

  @Override
  public void setDetails(List<DispatchOrderDetail> details) {
    dispatchOrder.setDetails(details);
  }

  @Override
  public List<DispatchOrderDetail> getDetails() {
    return dispatchOrder.getDetails();
  }

  @Override
  public void setWmsStatus(WmsOrderStatus wmsStatus) {
    dispatchOrder.setWmsStatus(wmsStatus);
  }

  @Override
  public WmsOrderStatus getWmsStatus() {
    return dispatchOrder.getWmsStatus();
  }

  @Override
  public void setInvoiceInfoJson(String invoiceInfoJson) {
    dispatchOrder.setInvoiceInfoJson(invoiceInfoJson);
  }

  @Override
  public String getInvoiceInfoJson() {
    return dispatchOrder.getInvoiceInfoJson();
  }

  @Override
  public void setGetWaybill(Boolean getWaybill) {
    dispatchOrder.setGetWaybill(getWaybill);
  }

  @Override
  public Boolean isGetWaybill() {
    return dispatchOrder.isGetWaybill();
  }

  @Override
  public void setNeedInvoice(Boolean needInvoice) {
    dispatchOrder.setNeedInvoice(needInvoice);
  }

  @Override
  public Boolean isNeedInvoice() {
    return dispatchOrder.isNeedInvoice();
  }

  @Override
  public void setWaybillInfoJson(String waybillInfoJson) {
    dispatchOrder.setWaybillInfoJson(waybillInfoJson);
  }

  @Override
  public String getWaybillInfoJson() {
    return dispatchOrder.getWaybillInfoJson();
  }

  @Override
  public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
    dispatchOrder.setInvoiceInfo(invoiceInfo);
  }

  @Override
  public InvoiceInfo getInvoiceInfo() {
    return dispatchOrder.getInvoiceInfo();
  }

  @Override
  public void setWaybillInfo(WaybillInfo waybillInfo) {
    dispatchOrder.setWaybillInfo(waybillInfo);
  }

  @Override
  public WaybillInfo getWaybillInfo() {
    return dispatchOrder.getWaybillInfo();
  }

  @Override
  public Object clone() {
    return dispatchOrder.clone();
  }
}

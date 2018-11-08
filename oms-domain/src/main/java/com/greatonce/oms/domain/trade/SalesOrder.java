package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 销售订单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class SalesOrder extends VersionDO {
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 地址.
   */
  private String address;
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 市id.
   */
  private Long cityId;
  /**
   * 市.
   */
  private String cityName;
  /**
   * 到付金额.
   */
  private Double codAmount;
  /**
   * 联系人.
   */
  private String contact;
  /**
   * 国家id.
   */
  private Long countryId;
  /**
   * 国家.
   */
  private String countryName;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 发货状态.
   */
  private DeliveryStatus deliveryStatus;
  /**
   * 优惠金额.
   */
  private Double discountAmount;
  /**
   * 配货状态.
   */
  private DispatchStatus dispatchStatus;
  /**
   * 分销金额.
   */
  private Double distributionAmount;
  /**
   * 分销订单号.
   */
  private String distributionTradeId;
  /**
   * 区id.
   */
  private Long districtId;
  /**
   * 区.
   */
  private String districtName;
  /**
   * 第一次发货时间.
   */
  private LocalDateTime firstDeliveryTime;
  /**
   * 留单日期.
   */
  private LocalDate holdDate;
  /**
   * 是否强制拆单.
   */
  private Boolean forceSplit;
  /**
   * 是否留单.
   */
  private Boolean hold;
  /**
   * 是否要手工处理.
   */
  private Boolean manual;
  /**
   * 是否缺货.
   */
  private Boolean oos;
  /**
   * 是否申请预退款.
   */
  private Boolean prerefund;
  /**
   * 是否商品异常.
   */
  private Boolean productAbnormal;
  /**
   * 是否加急.
   */
  private Boolean urgent;
  /**
   * 最后一次发货时间.
   */
  private LocalDateTime lastDeliveryTime;
  /**
   * 最后一次快递名称.
   */
  private String lastExpressName;
  /**
   * 最后一次快递单号.
   */
  private String lastExpressNo;
  /**
   * 商城状态.
   */
  private MallSalesOrderStatus mallSalesOrderStatus;
  /**
   * 手工标记人.
   */
  private String manualUser;
  /**
   * 合单标记.
   */
  private String mergeFlag;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 支付状态.
   */
  private SalesOrderPayStatus payStatus;
  /**
   * 省id.
   */
  private Long provinceId;
  /**
   * 省.
   */
  private String provinceName;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 退款状态.
   */
  private RefundStatus refundStatus;
  /**
   * 补发原因.
   */
  private String reissueReason;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 销售单编码.
   */
  private String salesOrderCode;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 销售金额.
   */
  private Double sellingAmount;
  /**
   * 结算金额.
   */
  private Double settlementAmount;
  /**
   * 状态.
   */
  private SalesOrderStatus status;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 建议快递id.
   */
  private Long suggestExpressId;
  /**
   * 建议快递名称.
   */
  private String suggestExpressName;
  /**
   * 建议快递单号.
   */
  private String suggestExpressNo;
  /**
   * 建议发货仓库id.
   */
  private Long suggestVirtualWarehouseId;
  /**
   * 建议发货仓库名称.
   */
  private String suggestVirtualWarehouseName;
  /**
   * 建议实体仓id.
   */
  private Long suggestWarehouseId;
  /**
   * 建议实体仓名称.
   */
  private String suggestWarehouseName;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 交易号.
   */
  private String tradeId;
  /**
   * 版本.
   */
  private Integer version;

  /**
   * 订单明细.
   */
  private List<SalesOrderDetail> details;
  /**
   * 订单优惠明细.
   */
  private List<SalesOrderDiscount> discounts;
  /**
   * 发票明细.
   */
  private List<SalesOrderInvoice> invoices;
  /**
   * 营销活动明细.
   */
  private List<SalesOrderMarketing> marketings;
  /**
   * 支付明细.
   */
  private List<SalesOrderPayment> payments;
  /**
   * 附属信息.
   */
  private SalesOrderSub sub;

  @Override
  public void setPrimaryKey(Long pk) {
    this.salesOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.salesOrderId;
  }




  /**
   * 实际金额.
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 实际金额.
   */
  public Double getActualAmount() {
    return this.actualAmount;
  }

  /**
   * 地址.
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * 审核时间.
   */
  public void setAuditedTime(LocalDateTime auditedTime) {
    this.auditedTime = auditedTime;
  }

  /**
   * 审核时间.
   */
  public LocalDateTime getAuditedTime() {
    return this.auditedTime;
  }

  /**
   * 审核人.
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   */
  public String getAuditor() {
    return this.auditor;
  }

  /**
   * 市id.
   */
  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  /**
   * 市id.
   */
  public Long getCityId() {
    return this.cityId;
  }

  /**
   * 市.
   */
  public void setCityName(String cityName) {
    this.cityName = cityName == null ? null : cityName.trim();
  }

  /**
   * 市.
   */
  public String getCityName() {
    return this.cityName;
  }

  /**
   * 到付金额.
   */
  public void setCodAmount(Double codAmount) {
    this.codAmount = codAmount;
  }

  /**
   * 到付金额.
   */
  public Double getCodAmount() {
    return this.codAmount;
  }

  /**
   * 联系人.
   */
  public void setContact(String contact) {
    this.contact = contact == null ? null : contact.trim();
  }

  /**
   * 联系人.
   */
  public String getContact() {
    return this.contact;
  }


  /**
   * 国家id.
   */
  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  /**
   * 国家id.
   */
  public Long getCountryId() {
    return this.countryId;
  }

  /**
   * 国家.
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName == null ? null : countryName.trim();
  }

  /**
   * 国家.
   */
  public String getCountryName() {
    return this.countryName;
  }

  /**
   * 创建时间.
   */
  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  /**
   * 创建人.
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   */
  public String getCreator() {
    return this.creator;
  }

  /**
   * 发货状态.
   */
  public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  /**
   * 发货状态.
   */
  public DeliveryStatus getDeliveryStatus() {
    return this.deliveryStatus;
  }




  /**
   * 优惠金额.
   */
  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 优惠金额.
   */
  public Double getDiscountAmount() {
    return this.discountAmount;
  }


  /**
   * 配货状态.
   */
  public void setDispatchStatus(DispatchStatus dispatchStatus) {
    this.dispatchStatus = dispatchStatus;
  }

  /**
   * 配货状态.
   */
  public DispatchStatus getDispatchStatus() {
    return this.dispatchStatus;
  }


  /**
   * 分销金额.
   */
  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  /**
   * 分销金额.
   */
  public Double getDistributionAmount() {
    return this.distributionAmount;
  }

  /**
   * 分销订单号.
   */
  public void setDistributionTradeId(String distributionTradeId) {
    this.distributionTradeId = distributionTradeId == null ? null : distributionTradeId.trim();
  }

  /**
   * 分销订单号.
   */
  public String getDistributionTradeId() {
    return this.distributionTradeId;
  }


  /**
   * 区id.
   */
  public void setDistrictId(Long districtId) {
    this.districtId = districtId;
  }

  /**
   * 区id.
   */
  public Long getDistrictId() {
    return this.districtId;
  }

  /**
   * 区.
   */
  public void setDistrictName(String districtName) {
    this.districtName = districtName == null ? null : districtName.trim();
  }

  /**
   * 区.
   */
  public String getDistrictName() {
    return this.districtName;
  }


  /**
   * 第一次发货时间.
   */
  public void setFirstDeliveryTime(LocalDateTime firstDeliveryTime) {
    this.firstDeliveryTime = firstDeliveryTime;
  }

  /**
   * 第一次发货时间.
   */
  public LocalDateTime getFirstDeliveryTime() {
    return this.firstDeliveryTime;
  }

  /**
   * 留单日期.
   */
  public void setHoldDate(LocalDate holdDate) {
    this.holdDate = holdDate;
  }

  /**
   * 留单日期.
   */
  public LocalDate getHoldDate() {
    return this.holdDate;
  }


  /**
   * 是否强制拆单.
   */
  public void setForceSplit(Boolean forceSplit) {
    this.forceSplit = forceSplit;
  }

  /**
   * 是否强制拆单.
   */
  public Boolean isForceSplit() {
    return this.forceSplit;
  }

  /**
   * 是否留单.
   */
  public void setHold(Boolean hold) {
    this.hold = hold;
  }

  /**
   * 是否留单.
   */
  public Boolean isHold() {
    return this.hold;
  }

  /**
   * 是否要手工处理.
   */
  public void setManual(Boolean manual) {
    this.manual = manual;
  }

  /**
   * 是否要手工处理.
   */
  public Boolean isManual() {
    return this.manual;
  }

  /**
   * 是否缺货.
   */
  public void setOos(Boolean oos) {
    this.oos = oos;
  }

  /**
   * 是否缺货.
   */
  public Boolean isOos() {
    return this.oos;
  }

  /**
   * 是否申请预退款.
   */
  public void setPrerefund(Boolean prerefund) {
    this.prerefund = prerefund;
  }

  /**
   * 是否申请预退款.
   */
  public Boolean isPrerefund() {
    return this.prerefund;
  }

  /**
   * 是否商品异常.
   */
  public void setProductAbnormal(Boolean productAbnormal) {
    this.productAbnormal = productAbnormal;
  }

  /**
   * 是否商品异常.
   */
  public Boolean isProductAbnormal() {
    return this.productAbnormal;
  }

  /**
   * 是否加急.
   */
  public void setUrgent(Boolean urgent) {
    this.urgent = urgent;
  }

  /**
   * 是否加急.
   */
  public Boolean isUrgent() {
    return this.urgent;
  }

  /**
   * 最后一次发货时间.
   */
  public void setLastDeliveryTime(LocalDateTime lastDeliveryTime) {
    this.lastDeliveryTime = lastDeliveryTime;
  }

  /**
   * 最后一次发货时间.
   */
  public LocalDateTime getLastDeliveryTime() {
    return this.lastDeliveryTime;
  }

  /**
   * 最后一次快递名称.
   */
  public void setLastExpressName(String lastExpressName) {
    this.lastExpressName = lastExpressName == null ? null : lastExpressName.trim();
  }

  /**
   * 最后一次快递名称.
   */
  public String getLastExpressName() {
    return this.lastExpressName;
  }

  /**
   * 最后一次快递单号.
   */
  public void setLastExpressNo(String lastExpressNo) {
    this.lastExpressNo = lastExpressNo == null ? null : lastExpressNo.trim();
  }

  /**
   * 最后一次快递单号.
   */
  public String getLastExpressNo() {
    return this.lastExpressNo;
  }

  /**
   * 商城状态.
   */
  public void setMallSalesOrderStatus(MallSalesOrderStatus mallSalesOrderStatus) {
    this.mallSalesOrderStatus = mallSalesOrderStatus;
  }

  /**
   * 商城状态.
   */
  public MallSalesOrderStatus getMallSalesOrderStatus() {
    return this.mallSalesOrderStatus;
  }

  /**
   * 手工标记人.
   */
  public void setManualUser(String manualUser) {
    this.manualUser = manualUser == null ? null : manualUser.trim();
  }

  /**
   * 手工标记人.
   */
  public String getManualUser() {
    return this.manualUser;
  }


  /**
   * 合单标记.
   */
  public void setMergeFlag(String mergeFlag) {
    this.mergeFlag = mergeFlag == null ? null : mergeFlag.trim();
  }

  /**
   * 合单标记.
   */
  public String getMergeFlag() {
    return this.mergeFlag;
  }

  /**
   * 手机.
   */
  public void setMobile(String mobile) {
    this.mobile = mobile == null ? null : mobile.trim();
  }

  /**
   * 手机.
   */
  public String getMobile() {
    return this.mobile;
  }


  /**
   * 更新时间.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * 支付状态.
   */
  public void setPayStatus(SalesOrderPayStatus payStatus) {
    this.payStatus = payStatus;
  }

  /**
   * 支付状态.
   */
  public SalesOrderPayStatus getPayStatus() {
    return this.payStatus;
  }



  /**
   * 省id.
   */
  public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
  }

  /**
   * 省id.
   */
  public Long getProvinceId() {
    return this.provinceId;
  }

  /**
   * 省.
   */
  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName == null ? null : provinceName.trim();
  }

  /**
   * 省.
   */
  public String getProvinceName() {
    return this.provinceName;
  }

  /**
   * 数量.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   */
  public Integer getQuantity() {
    return this.quantity;
  }

  /**
   * 退款状态.
   */
  public void setRefundStatus(RefundStatus refundStatus) {
    this.refundStatus = refundStatus;
  }

  /**
   * 退款状态.
   */
  public RefundStatus getRefundStatus() {
    return this.refundStatus;
  }

  /**
   * 补发原因.
   */
  public void setReissueReason(String reissueReason) {
    this.reissueReason = reissueReason == null ? null : reissueReason.trim();
  }

  /**
   * 补发原因.
   */
  public String getReissueReason() {
    return this.reissueReason;
  }

  /**
   * 备注.
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   */
  public String getRemark() {
    return this.remark;
  }

  /**
   * 销售单编码.
   */
  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode == null ? null : salesOrderCode.trim();
  }

  /**
   * 销售单编码.
   */
  public String getSalesOrderCode() {
    return this.salesOrderCode;
  }



  /**
   * 销售单id.
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   */
  public Long getSalesOrderId() {
    return this.salesOrderId;
  }


  /**
   * 销售金额.
   */
  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  /**
   * 销售金额.
   */
  public Double getSellingAmount() {
    return this.sellingAmount;
  }

  /**
   * 结算金额.
   */
  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  /**
   * 结算金额.
   */
  public Double getSettlementAmount() {
    return this.settlementAmount;
  }



  /**
   * 状态.
   */
  public void setStatus(SalesOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public SalesOrderStatus getStatus() {
    return this.status;
  }


  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }


  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }


  /**
   * 建议快递id.
   */
  public void setSuggestExpressId(Long suggestExpressId) {
    this.suggestExpressId = suggestExpressId;
  }

  /**
   * 建议快递id.
   */
  public Long getSuggestExpressId() {
    return this.suggestExpressId;
  }

  /**
   * 建议快递名称.
   */
  public void setSuggestExpressName(String suggestExpressName) {
    this.suggestExpressName = suggestExpressName == null ? null : suggestExpressName.trim();
  }

  /**
   * 建议快递名称.
   */
  public String getSuggestExpressName() {
    return this.suggestExpressName;
  }

  /**
   * 建议快递单号.
   */
  public void setSuggestExpressNo(String suggestExpressNo) {
    this.suggestExpressNo = suggestExpressNo == null ? null : suggestExpressNo.trim();
  }

  /**
   * 建议快递单号.
   */
  public String getSuggestExpressNo() {
    return this.suggestExpressNo;
  }

  /**
   * 建议发货仓库id.
   */
  public void setSuggestVirtualWarehouseId(Long suggestVirtualWarehouseId) {
    this.suggestVirtualWarehouseId = suggestVirtualWarehouseId;
  }

  /**
   * 建议发货仓库id.
   */
  public Long getSuggestVirtualWarehouseId() {
    return this.suggestVirtualWarehouseId;
  }

  /**
   * 建议发货仓库名称.
   */
  public void setSuggestVirtualWarehouseName(String suggestVirtualWarehouseName) {
    this.suggestVirtualWarehouseName = suggestVirtualWarehouseName == null ? null : suggestVirtualWarehouseName.trim();
  }

  /**
   * 建议发货仓库名称.
   */
  public String getSuggestVirtualWarehouseName() {
    return this.suggestVirtualWarehouseName;
  }

  /**
   * 建议实体仓id.
   */
  public void setSuggestWarehouseId(Long suggestWarehouseId) {
    this.suggestWarehouseId = suggestWarehouseId;
  }

  /**
   * 建议实体仓id.
   */
  public Long getSuggestWarehouseId() {
    return this.suggestWarehouseId;
  }

  /**
   * 建议实体仓名称.
   */
  public void setSuggestWarehouseName(String suggestWarehouseName) {
    this.suggestWarehouseName = suggestWarehouseName == null ? null : suggestWarehouseName.trim();
  }

  /**
   * 建议实体仓名称.
   */
  public String getSuggestWarehouseName() {
    return this.suggestWarehouseName;
  }

  /**
   * 电话.
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   */
  public String getTelephone() {
    return this.telephone;
  }


  /**
   * 交易号.
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   */
  public String getTradeId() {
    return this.tradeId;
  }


  /**
   * 版本.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   */
  @Override
  public Integer getVersion() {
    return this.version;
  }

  /**
   * 订单明细.
   */
  public void setDetails(List<SalesOrderDetail> details) {
    this.details = details;
  }

  /**
   * 订单明细.
   */
  public List<SalesOrderDetail> getDetails() {
    return this.details;
  }

  /**
   * 订单优惠明细.
   */
  public void setDiscounts(List<SalesOrderDiscount> discounts) {
    this.discounts = discounts;
  }

  /**
   * 订单优惠明细.
   */
  public List<SalesOrderDiscount> getDiscounts() {
    return this.discounts;
  }

  /**
   * 发票明细.
   */
  public void setInvoices(List<SalesOrderInvoice> invoices) {
    this.invoices = invoices;
  }

  /**
   * 发票明细.
   */
  public List<SalesOrderInvoice> getInvoices() {
    return this.invoices;
  }

  /**
   * 营销活动明细.
   */
  public void setMarketings(List<SalesOrderMarketing> marketings) {
    this.marketings = marketings;
  }

  /**
   * 营销活动明细.
   */
  public List<SalesOrderMarketing> getMarketings() {
    return this.marketings;
  }

  /**
   * 支付明细.
   */
  public void setPayments(List<SalesOrderPayment> payments) {
    this.payments = payments;
  }

  /**
   * 支付明细.
   */
  public List<SalesOrderPayment> getPayments() {
    return this.payments;
  }

  /**
   * 附属信息.
   */
  public void setSub(SalesOrderSub sub) {
    this.sub = sub;
  }

  /**
   * 附属信息.
   */
  public SalesOrderSub getSub() {
    return this.sub;
  }
}
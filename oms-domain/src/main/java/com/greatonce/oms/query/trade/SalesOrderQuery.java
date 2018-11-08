package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
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
public class SalesOrderQuery extends Query {
  /**
   * .
   */
  private Double actualAmountBegin;
  /**
   * .
   */
  private Double actualAmountEnd;
  /**
   * 实际金额.
   */
  private Double actualAmount;
  /**
   * 地址.
   */
  private String address;
  /**
   * 审核时间开始.
   */
  private LocalDateTime auditedTimeBegin;
  /**
   * 审核时间结束.
   */
  private LocalDateTime auditedTimeEnd;
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
   * .
   */
  private List<String> contacts;
  /**
   * 国家id.
   */
  private Long countryId;
  /**
   * 国家.
   */
  private String countryName;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 发货状态.
   */
  private DeliveryStatus deliveryStatus;
  /**
   * .
   */
  private List<DeliveryStatus> deliveryStatuses;
  /**
   * 明细数量开始.
   */
  private Integer detailQuantityBegin;
  /**
   * 明细数量结束.
   */
  private Integer detailQuantityEnd;
  /**
   * 优惠金额.
   */
  private Double discountAmount;
  /**
   * .
   */
  private List<String> dispatchOrderCodes;
  /**
   * 配货状态.
   */
  private DispatchStatus dispatchStatus;
  /**
   * .
   */
  private List<DispatchStatus> dispatchStatuses;
  /**
   * 分销金额.
   */
  private Double distributionAmount;
  /**
   * 分销订单号.
   */
  private String distributionTradeId;
  /**
   * .
   */
  private List<String> distributionTradeIds;
  /**
   * 区id.
   */
  private Long districtId;
  /**
   * 区.
   */
  private String districtName;
  /**
   * 发货明细快递单号.
   */
  private String expressNo;
  /**
   * 第一次发货时间开始.
   */
  private LocalDateTime firstDeliveryTimeBegin;
  /**
   * 第一次发货时间结束.
   */
  private LocalDateTime firstDeliveryTimeEnd;
  /**
   * 留单日期开始.
   */
  private LocalDate holdDateBegin;
  /**
   * 留单日期结束.
   */
  private LocalDate holdDateEnd;
  /**
   * 是否代发.
   */
  private Boolean dropShipping;
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
   * 最后一次发货时间开始.
   */
  private LocalDateTime lastDeliveryTimeBegin;
  /**
   * 最后一次发货时间结束.
   */
  private LocalDateTime lastDeliveryTimeEnd;
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
   * .
   */
  private String marketingCode;
  /**
   * 合单标记.
   */
  private String mergeFlag;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * .
   */
  private List<String> mobiles;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 支付状态.
   */
  private SalesOrderPayStatus payStatus;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * 商品名称.
   */
  private String productName;
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
   * .
   */
  private List<String> salesOrderCodes;
  /**
   * 明细退款状态.
   */
  private SalesOrderDetailRefundStatus salesOrderDetailRefundStatus;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * .
   */
  private List<Long> salesOrderIds;
  /**
   * 销售金额.
   */
  private Double sellingAmount;
  /**
   * 结算金额.
   */
  private Double settlementAmount;
  /**
   * 规格名称.
   */
  private String skuCode;
  /**
   * .
   */
  private String smartKeys;
  /**
   * 状态.
   */
  private SalesOrderStatus status;
  /**
   * .
   */
  private List<SalesOrderStatus> statuses;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * .
   */
  private List<Long> storeIds;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * .
   */
  private SalesOrderSubQuery sub;
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
   * .
   */
  private List<String> telephones;
  /**
   * 交易号.
   */
  private String tradeId;
  /**
   * .
   */
  private List<String> tradeIds;
  /**
   * 版本.
   */
  private Integer version;


  /**
   * .
   * @param actualAmountBegin 
   */
  public void setActualAmountBegin(Double actualAmountBegin) {
    this.actualAmountBegin = actualAmountBegin;
  }

  /**
   * .
   * @return 
   */
  public Double getActualAmountBegin() {
      return this.actualAmountBegin;
  }

  /**
   * .
   * @param actualAmountEnd 
   */
  public void setActualAmountEnd(Double actualAmountEnd) {
    this.actualAmountEnd = actualAmountEnd;
  }

  /**
   * .
   * @return 
   */
  public Double getActualAmountEnd() {
      return this.actualAmountEnd;
  }

  /**
   * 实际金额.
   * @param actualAmount 实际金额
   */
  public void setActualAmount(Double actualAmount) {
    this.actualAmount = actualAmount;
  }

  /**
   * 实际金额.
   * @return 实际金额
   */
  public Double getActualAmount() {
      return this.actualAmount;
  }

  /**
   * 地址.
   * @param address 地址
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   * @return 地址
   */
  public String getAddress() {
      return this.address;
  }

  /**
   * 审核时间开始.
   * @param auditedTimeBegin 开始.
   */
  public void setAuditedTimeBegin(LocalDateTime auditedTimeBegin) {
    this.auditedTimeBegin = auditedTimeBegin;
  }

  /**
   * 审核时间开始.
   * @return 审核时间开始
   */
  public LocalDateTime getAuditedTimeBegin() {
    return this.auditedTimeBegin;
  }

  /**
   * 审核时间结束.
   * @param auditedTimeEnd 结束
   */
  public void setAuditedTimeEnd(LocalDateTime auditedTimeEnd) {
    this.auditedTimeEnd = auditedTimeEnd;
  }

  /**
   * 审核时间结束.
   * @return 审核时间结束
   */
  public LocalDateTime getAuditedTimeEnd() {
    return this.auditedTimeEnd;
  }

  /**
   * 审核人.
   * @param auditor 审核人
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   * @return 审核人
   */
  public String getAuditor() {
      return this.auditor;
  }

  /**
   * 市id.
   * @param cityId 市id
   */
  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  /**
   * 市id.
   * @return 市id
   */
  public Long getCityId() {
      return this.cityId;
  }

  /**
   * 市.
   * @param cityName 市
   */
  public void setCityName(String cityName) {
    this.cityName = cityName == null ? null : cityName.trim();
  }

  /**
   * 市.
   * @return 市
   */
  public String getCityName() {
      return this.cityName;
  }

  /**
   * 到付金额.
   * @param codAmount 到付金额
   */
  public void setCodAmount(Double codAmount) {
    this.codAmount = codAmount;
  }

  /**
   * 到付金额.
   * @return 到付金额
   */
  public Double getCodAmount() {
      return this.codAmount;
  }

  /**
   * 联系人.
   * @param contact 联系人
   */
  public void setContact(String contact) {
    this.contact = contact == null ? null : contact.trim();
  }

  /**
   * 联系人.
   * @return 联系人
   */
  public String getContact() {
      return this.contact;
  }

  /**
   * .
   * @param contacts 
   */
  public void setContacts(List<String> contacts) {
    this.contacts = contacts;
  }

  /**
   * .
   * @return 
   */
  public List<String> getContacts() {
      return this.contacts;
  }

  /**
   * 国家id.
   * @param countryId 国家id
   */
  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  /**
   * 国家id.
   * @return 国家id
   */
  public Long getCountryId() {
      return this.countryId;
  }

  /**
   * 国家.
   * @param countryName 国家
   */
  public void setCountryName(String countryName) {
    this.countryName = countryName == null ? null : countryName.trim();
  }

  /**
   * 国家.
   * @return 国家
   */
  public String getCountryName() {
      return this.countryName;
  }

  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 创建人.
   * @param creator 创建人
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   * @return 创建人
   */
  public String getCreator() {
      return this.creator;
  }

  /**
   * 发货状态.
   * @param deliveryStatus 发货状态
   */
  public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  /**
   * 发货状态.
   * @return 发货状态
   */
  public DeliveryStatus getDeliveryStatus() {
      return this.deliveryStatus;
  }

  /**
   * .
   * @param deliveryStatuses 
   */
  public void setDeliveryStatuses(List<DeliveryStatus> deliveryStatuses) {
    this.deliveryStatuses = deliveryStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<DeliveryStatus> getDeliveryStatuses() {
      return this.deliveryStatuses;
  }

  /**
   * 明细数量开始.
   * @param detailQuantityBegin 明细数量开始
   */
  public void setDetailQuantityBegin(Integer detailQuantityBegin) {
    this.detailQuantityBegin = detailQuantityBegin;
  }

  /**
   * 明细数量开始.
   * @return 明细数量开始
   */
  public Integer getDetailQuantityBegin() {
      return this.detailQuantityBegin;
  }

  /**
   * 明细数量结束.
   * @param detailQuantityEnd 明细数量结束
   */
  public void setDetailQuantityEnd(Integer detailQuantityEnd) {
    this.detailQuantityEnd = detailQuantityEnd;
  }

  /**
   * 明细数量结束.
   * @return 明细数量结束
   */
  public Integer getDetailQuantityEnd() {
      return this.detailQuantityEnd;
  }

  /**
   * 优惠金额.
   * @param discountAmount 优惠金额
   */
  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  /**
   * 优惠金额.
   * @return 优惠金额
   */
  public Double getDiscountAmount() {
      return this.discountAmount;
  }

  /**
   * .
   * @param dispatchOrderCodes 
   */
  public void setDispatchOrderCodes(List<String> dispatchOrderCodes) {
    this.dispatchOrderCodes = dispatchOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getDispatchOrderCodes() {
      return this.dispatchOrderCodes;
  }

  /**
   * 配货状态.
   * @param dispatchStatus 配货状态
   */
  public void setDispatchStatus(DispatchStatus dispatchStatus) {
    this.dispatchStatus = dispatchStatus;
  }

  /**
   * 配货状态.
   * @return 配货状态
   */
  public DispatchStatus getDispatchStatus() {
      return this.dispatchStatus;
  }

  /**
   * .
   * @param dispatchStatuses 
   */
  public void setDispatchStatuses(List<DispatchStatus> dispatchStatuses) {
    this.dispatchStatuses = dispatchStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<DispatchStatus> getDispatchStatuses() {
      return this.dispatchStatuses;
  }

  /**
   * 分销金额.
   * @param distributionAmount 分销金额
   */
  public void setDistributionAmount(Double distributionAmount) {
    this.distributionAmount = distributionAmount;
  }

  /**
   * 分销金额.
   * @return 分销金额
   */
  public Double getDistributionAmount() {
      return this.distributionAmount;
  }

  /**
   * 分销订单号.
   * @param distributionTradeId 分销订单号
   */
  public void setDistributionTradeId(String distributionTradeId) {
    this.distributionTradeId = distributionTradeId == null ? null : distributionTradeId.trim();
  }

  /**
   * 分销订单号.
   * @return 分销订单号
   */
  public String getDistributionTradeId() {
      return this.distributionTradeId;
  }

  /**
   * .
   * @param distributionTradeIds 
   */
  public void setDistributionTradeIds(List<String> distributionTradeIds) {
    this.distributionTradeIds = distributionTradeIds;
  }

  /**
   * .
   * @return 
   */
  public List<String> getDistributionTradeIds() {
      return this.distributionTradeIds;
  }

  /**
   * 区id.
   * @param districtId 区id
   */
  public void setDistrictId(Long districtId) {
    this.districtId = districtId;
  }

  /**
   * 区id.
   * @return 区id
   */
  public Long getDistrictId() {
      return this.districtId;
  }

  /**
   * 区.
   * @param districtName 区
   */
  public void setDistrictName(String districtName) {
    this.districtName = districtName == null ? null : districtName.trim();
  }

  /**
   * 区.
   * @return 区
   */
  public String getDistrictName() {
      return this.districtName;
  }

  /**
   * 发货明细快递单号.
   * @param expressNo 发货明细快递单号
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 发货明细快递单号.
   * @return 发货明细快递单号
   */
  public String getExpressNo() {
      return this.expressNo;
  }

  /**
   * 第一次发货时间开始.
   * @param firstDeliveryTimeBegin 开始.
   */
  public void setFirstDeliveryTimeBegin(LocalDateTime firstDeliveryTimeBegin) {
    this.firstDeliveryTimeBegin = firstDeliveryTimeBegin;
  }

  /**
   * 第一次发货时间开始.
   * @return 第一次发货时间开始
   */
  public LocalDateTime getFirstDeliveryTimeBegin() {
    return this.firstDeliveryTimeBegin;
  }

  /**
   * 第一次发货时间结束.
   * @param firstDeliveryTimeEnd 结束
   */
  public void setFirstDeliveryTimeEnd(LocalDateTime firstDeliveryTimeEnd) {
    this.firstDeliveryTimeEnd = firstDeliveryTimeEnd;
  }

  /**
   * 第一次发货时间结束.
   * @return 第一次发货时间结束
   */
  public LocalDateTime getFirstDeliveryTimeEnd() {
    return this.firstDeliveryTimeEnd;
  }

  /**
   * 留单日期开始.
   * @param holdDateBegin 开始.
   */
  public void setHoldDateBegin(LocalDate holdDateBegin) {
    this.holdDateBegin = holdDateBegin;
  }

  /**
   * 留单日期开始.
   * @return 留单日期开始
   */
  public LocalDate getHoldDateBegin() {
    return this.holdDateBegin;
  }

  /**
   * 留单日期结束.
   * @param holdDateEnd 结束
   */
  public void setHoldDateEnd(LocalDate holdDateEnd) {
    this.holdDateEnd = holdDateEnd;
  }

  /**
   * 留单日期结束.
   * @return 留单日期结束
   */
  public LocalDate getHoldDateEnd() {
    return this.holdDateEnd;
  }

  /**
   * 是否代发.
   * @param dropShipping 是否代发
   */
  public void setDropShipping(Boolean dropShipping) {
    this.dropShipping = dropShipping;
  }

  /**
   * 是否代发.
   * @return 是否代发
   */
  public Boolean isDropShipping() {
      return this.dropShipping;
  }

  /**
   * 是否强制拆单.
   * @param forceSplit 是否强制拆单
   */
  public void setForceSplit(Boolean forceSplit) {
    this.forceSplit = forceSplit;
  }

  /**
   * 是否强制拆单.
   * @return 是否强制拆单
   */
  public Boolean isForceSplit() {
      return this.forceSplit;
  }

  /**
   * 是否留单.
   * @param hold 是否留单
   */
  public void setHold(Boolean hold) {
    this.hold = hold;
  }

  /**
   * 是否留单.
   * @return 是否留单
   */
  public Boolean isHold() {
      return this.hold;
  }

  /**
   * 是否要手工处理.
   * @param manual 是否要手工处理
   */
  public void setManual(Boolean manual) {
    this.manual = manual;
  }

  /**
   * 是否要手工处理.
   * @return 是否要手工处理
   */
  public Boolean isManual() {
      return this.manual;
  }

  /**
   * 是否缺货.
   * @param oos 是否缺货
   */
  public void setOos(Boolean oos) {
    this.oos = oos;
  }

  /**
   * 是否缺货.
   * @return 是否缺货
   */
  public Boolean isOos() {
      return this.oos;
  }

  /**
   * 是否申请预退款.
   * @param prerefund 是否申请预退款
   */
  public void setPrerefund(Boolean prerefund) {
    this.prerefund = prerefund;
  }

  /**
   * 是否申请预退款.
   * @return 是否申请预退款
   */
  public Boolean isPrerefund() {
      return this.prerefund;
  }

  /**
   * 是否商品异常.
   * @param productAbnormal 是否商品异常
   */
  public void setProductAbnormal(Boolean productAbnormal) {
    this.productAbnormal = productAbnormal;
  }

  /**
   * 是否商品异常.
   * @return 是否商品异常
   */
  public Boolean isProductAbnormal() {
      return this.productAbnormal;
  }

  /**
   * 是否加急.
   * @param urgent 是否加急
   */
  public void setUrgent(Boolean urgent) {
    this.urgent = urgent;
  }

  /**
   * 是否加急.
   * @return 是否加急
   */
  public Boolean isUrgent() {
      return this.urgent;
  }

  /**
   * 最后一次发货时间开始.
   * @param lastDeliveryTimeBegin 开始.
   */
  public void setLastDeliveryTimeBegin(LocalDateTime lastDeliveryTimeBegin) {
    this.lastDeliveryTimeBegin = lastDeliveryTimeBegin;
  }

  /**
   * 最后一次发货时间开始.
   * @return 最后一次发货时间开始
   */
  public LocalDateTime getLastDeliveryTimeBegin() {
    return this.lastDeliveryTimeBegin;
  }

  /**
   * 最后一次发货时间结束.
   * @param lastDeliveryTimeEnd 结束
   */
  public void setLastDeliveryTimeEnd(LocalDateTime lastDeliveryTimeEnd) {
    this.lastDeliveryTimeEnd = lastDeliveryTimeEnd;
  }

  /**
   * 最后一次发货时间结束.
   * @return 最后一次发货时间结束
   */
  public LocalDateTime getLastDeliveryTimeEnd() {
    return this.lastDeliveryTimeEnd;
  }

  /**
   * 最后一次快递名称.
   * @param lastExpressName 最后一次快递名称
   */
  public void setLastExpressName(String lastExpressName) {
    this.lastExpressName = lastExpressName == null ? null : lastExpressName.trim();
  }

  /**
   * 最后一次快递名称.
   * @return 最后一次快递名称
   */
  public String getLastExpressName() {
      return this.lastExpressName;
  }

  /**
   * 最后一次快递单号.
   * @param lastExpressNo 最后一次快递单号
   */
  public void setLastExpressNo(String lastExpressNo) {
    this.lastExpressNo = lastExpressNo == null ? null : lastExpressNo.trim();
  }

  /**
   * 最后一次快递单号.
   * @return 最后一次快递单号
   */
  public String getLastExpressNo() {
      return this.lastExpressNo;
  }

  /**
   * 商城状态.
   * @param mallSalesOrderStatus 商城状态
   */
  public void setMallSalesOrderStatus(MallSalesOrderStatus mallSalesOrderStatus) {
    this.mallSalesOrderStatus = mallSalesOrderStatus;
  }

  /**
   * 商城状态.
   * @return 商城状态
   */
  public MallSalesOrderStatus getMallSalesOrderStatus() {
      return this.mallSalesOrderStatus;
  }

  /**
   * 手工标记人.
   * @param manualUser 手工标记人
   */
  public void setManualUser(String manualUser) {
    this.manualUser = manualUser == null ? null : manualUser.trim();
  }

  /**
   * 手工标记人.
   * @return 手工标记人
   */
  public String getManualUser() {
      return this.manualUser;
  }

  /**
   * .
   * @param marketingCode 
   */
  public void setMarketingCode(String marketingCode) {
    this.marketingCode = marketingCode == null ? null : marketingCode.trim();
  }

  /**
   * .
   * @return 
   */
  public String getMarketingCode() {
      return this.marketingCode;
  }

  /**
   * 合单标记.
   * @param mergeFlag 合单标记
   */
  public void setMergeFlag(String mergeFlag) {
    this.mergeFlag = mergeFlag == null ? null : mergeFlag.trim();
  }

  /**
   * 合单标记.
   * @return 合单标记
   */
  public String getMergeFlag() {
      return this.mergeFlag;
  }

  /**
   * 手机.
   * @param mobile 手机
   */
  public void setMobile(String mobile) {
    this.mobile = mobile == null ? null : mobile.trim();
  }

  /**
   * 手机.
   * @return 手机
   */
  public String getMobile() {
      return this.mobile;
  }

  /**
   * .
   * @param mobiles 
   */
  public void setMobiles(List<String> mobiles) {
    this.mobiles = mobiles;
  }

  /**
   * .
   * @return 
   */
  public List<String> getMobiles() {
      return this.mobiles;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * 支付状态.
   * @param payStatus 支付状态
   */
  public void setPayStatus(SalesOrderPayStatus payStatus) {
    this.payStatus = payStatus;
  }

  /**
   * 支付状态.
   * @return 支付状态
   */
  public SalesOrderPayStatus getPayStatus() {
      return this.payStatus;
  }

  /**
   * 商品编码.
   * @param productCode 商品编码
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   * @return 商品编码
   */
  public String getProductCode() {
      return this.productCode;
  }

  /**
   * 商品名称.
   * @param productName 商品名称
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   * @return 商品名称
   */
  public String getProductName() {
      return this.productName;
  }

  /**
   * 省id.
   * @param provinceId 省id
   */
  public void setProvinceId(Long provinceId) {
    this.provinceId = provinceId;
  }

  /**
   * 省id.
   * @return 省id
   */
  public Long getProvinceId() {
      return this.provinceId;
  }

  /**
   * 省.
   * @param provinceName 省
   */
  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName == null ? null : provinceName.trim();
  }

  /**
   * 省.
   * @return 省
   */
  public String getProvinceName() {
      return this.provinceName;
  }

  /**
   * 数量.
   * @param quantity 数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   * @return 数量
   */
  public Integer getQuantity() {
      return this.quantity;
  }

  /**
   * 退款状态.
   * @param refundStatus 退款状态
   */
  public void setRefundStatus(RefundStatus refundStatus) {
    this.refundStatus = refundStatus;
  }

  /**
   * 退款状态.
   * @return 退款状态
   */
  public RefundStatus getRefundStatus() {
      return this.refundStatus;
  }

  /**
   * 补发原因.
   * @param reissueReason 补发原因
   */
  public void setReissueReason(String reissueReason) {
    this.reissueReason = reissueReason == null ? null : reissueReason.trim();
  }

  /**
   * 补发原因.
   * @return 补发原因
   */
  public String getReissueReason() {
      return this.reissueReason;
  }

  /**
   * 备注.
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getRemark() {
      return this.remark;
  }

  /**
   * 销售单编码.
   * @param salesOrderCode 销售单编码
   */
  public void setSalesOrderCode(String salesOrderCode) {
    this.salesOrderCode = salesOrderCode == null ? null : salesOrderCode.trim();
  }

  /**
   * 销售单编码.
   * @return 销售单编码
   */
  public String getSalesOrderCode() {
      return this.salesOrderCode;
  }

  /**
   * .
   * @param salesOrderCodes 
   */
  public void setSalesOrderCodes(List<String> salesOrderCodes) {
    this.salesOrderCodes = salesOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSalesOrderCodes() {
      return this.salesOrderCodes;
  }

  /**
   * 明细退款状态.
   * @param salesOrderDetailRefundStatus 明细退款状态
   */
  public void setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus salesOrderDetailRefundStatus) {
    this.salesOrderDetailRefundStatus = salesOrderDetailRefundStatus;
  }

  /**
   * 明细退款状态.
   * @return 明细退款状态
   */
  public SalesOrderDetailRefundStatus getSalesOrderDetailRefundStatus() {
      return this.salesOrderDetailRefundStatus;
  }

  /**
   * 销售单id.
   * @param salesOrderId 销售单id
   */
  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  /**
   * 销售单id.
   * @return 销售单id
   */
  public Long getSalesOrderId() {
      return this.salesOrderId;
  }

  /**
   * .
   * @param salesOrderIds 
   */
  public void setSalesOrderIds(List<Long> salesOrderIds) {
    this.salesOrderIds = salesOrderIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getSalesOrderIds() {
      return this.salesOrderIds;
  }

  /**
   * 销售金额.
   * @param sellingAmount 销售金额
   */
  public void setSellingAmount(Double sellingAmount) {
    this.sellingAmount = sellingAmount;
  }

  /**
   * 销售金额.
   * @return 销售金额
   */
  public Double getSellingAmount() {
      return this.sellingAmount;
  }

  /**
   * 结算金额.
   * @param settlementAmount 结算金额
   */
  public void setSettlementAmount(Double settlementAmount) {
    this.settlementAmount = settlementAmount;
  }

  /**
   * 结算金额.
   * @return 结算金额
   */
  public Double getSettlementAmount() {
      return this.settlementAmount;
  }

  /**
   * 规格名称.
   * @param skuCode 规格名称
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 规格名称.
   * @return 规格名称
   */
  public String getSkuCode() {
      return this.skuCode;
  }

  /**
   * .
   * @param smartKeys 
   */
  public void setSmartKeys(String smartKeys) {
    this.smartKeys = smartKeys == null ? null : smartKeys.trim();
  }

  /**
   * .
   * @return 
   */
  public String getSmartKeys() {
      return this.smartKeys;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(SalesOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public SalesOrderStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<SalesOrderStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<SalesOrderStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }

  /**
   * .
   * @param storeIds 
   */
  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getStoreIds() {
      return this.storeIds;
  }

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
  }

  /**
   * .
   * @param sub 
   */
  public void setSub(SalesOrderSubQuery sub) {
    this.sub = sub;
  }

  /**
   * .
   * @return 
   */
  public SalesOrderSubQuery getSub() {
      return this.sub;
  }

  /**
   * 建议快递id.
   * @param suggestExpressId 建议快递id
   */
  public void setSuggestExpressId(Long suggestExpressId) {
    this.suggestExpressId = suggestExpressId;
  }

  /**
   * 建议快递id.
   * @return 建议快递id
   */
  public Long getSuggestExpressId() {
      return this.suggestExpressId;
  }

  /**
   * 建议快递名称.
   * @param suggestExpressName 建议快递名称
   */
  public void setSuggestExpressName(String suggestExpressName) {
    this.suggestExpressName = suggestExpressName == null ? null : suggestExpressName.trim();
  }

  /**
   * 建议快递名称.
   * @return 建议快递名称
   */
  public String getSuggestExpressName() {
      return this.suggestExpressName;
  }

  /**
   * 建议快递单号.
   * @param suggestExpressNo 建议快递单号
   */
  public void setSuggestExpressNo(String suggestExpressNo) {
    this.suggestExpressNo = suggestExpressNo == null ? null : suggestExpressNo.trim();
  }

  /**
   * 建议快递单号.
   * @return 建议快递单号
   */
  public String getSuggestExpressNo() {
      return this.suggestExpressNo;
  }

  /**
   * 建议发货仓库id.
   * @param suggestVirtualWarehouseId 建议发货仓库id
   */
  public void setSuggestVirtualWarehouseId(Long suggestVirtualWarehouseId) {
    this.suggestVirtualWarehouseId = suggestVirtualWarehouseId;
  }

  /**
   * 建议发货仓库id.
   * @return 建议发货仓库id
   */
  public Long getSuggestVirtualWarehouseId() {
      return this.suggestVirtualWarehouseId;
  }

  /**
   * 建议发货仓库名称.
   * @param suggestVirtualWarehouseName 建议发货仓库名称
   */
  public void setSuggestVirtualWarehouseName(String suggestVirtualWarehouseName) {
    this.suggestVirtualWarehouseName = suggestVirtualWarehouseName == null ? null : suggestVirtualWarehouseName.trim();
  }

  /**
   * 建议发货仓库名称.
   * @return 建议发货仓库名称
   */
  public String getSuggestVirtualWarehouseName() {
      return this.suggestVirtualWarehouseName;
  }

  /**
   * 建议实体仓id.
   * @param suggestWarehouseId 建议实体仓id
   */
  public void setSuggestWarehouseId(Long suggestWarehouseId) {
    this.suggestWarehouseId = suggestWarehouseId;
  }

  /**
   * 建议实体仓id.
   * @return 建议实体仓id
   */
  public Long getSuggestWarehouseId() {
      return this.suggestWarehouseId;
  }

  /**
   * 建议实体仓名称.
   * @param suggestWarehouseName 建议实体仓名称
   */
  public void setSuggestWarehouseName(String suggestWarehouseName) {
    this.suggestWarehouseName = suggestWarehouseName == null ? null : suggestWarehouseName.trim();
  }

  /**
   * 建议实体仓名称.
   * @return 建议实体仓名称
   */
  public String getSuggestWarehouseName() {
      return this.suggestWarehouseName;
  }

  /**
   * 电话.
   * @param telephone 电话
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   * @return 电话
   */
  public String getTelephone() {
      return this.telephone;
  }

  /**
   * .
   * @param telephones 
   */
  public void setTelephones(List<String> telephones) {
    this.telephones = telephones;
  }

  /**
   * .
   * @return 
   */
  public List<String> getTelephones() {
      return this.telephones;
  }

  /**
   * 交易号.
   * @param tradeId 交易号
   */
  public void setTradeId(String tradeId) {
    this.tradeId = tradeId == null ? null : tradeId.trim();
  }

  /**
   * 交易号.
   * @return 交易号
   */
  public String getTradeId() {
      return this.tradeId;
  }

  /**
   * .
   * @param tradeIds 
   */
  public void setTradeIds(List<String> tradeIds) {
    this.tradeIds = tradeIds;
  }

  /**
   * .
   * @return 
   */
  public List<String> getTradeIds() {
      return this.tradeIds;
  }

  /**
   * 版本.
   * @param version 版本
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   * @return 版本
   */
  public Integer getVersion() {
      return this.version;
  }
}
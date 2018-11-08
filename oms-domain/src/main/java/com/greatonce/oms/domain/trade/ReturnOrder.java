package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WmsNoticeStatus;
import com.greatonce.oms.domain.enums.trade.RefundType;
import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退换货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ReturnOrder extends VersionDO {
  /**
   * 地址.
   */
  private String address;
  /**
   * 申请数量.
   */
  private Integer applyQuantity;
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 箱码.
   */
  private String boxNo;
  /**
   * 市id.
   */
  private Long cityId;
  /**
   * 市.
   */
  private String cityName;
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
   * 快递名称.
   */
  private String expressName;
  /**
   * 快递单号.
   */
  private String expressNo;
  /**
   * 入库虚拟仓id.
   */
  private Long inVirtualWarehouseId;
  /**
   * 入库虚拟仓名称.
   */
  private String inVirtualWarehouseName;
  /**
   * 是否异常.
   */
  private Boolean abnormal;
  /**
   * 是否换货单.
   */
  private Boolean exchange;
  /**
   * 是否异常规格.
   */
  private Boolean skuAbnormal;
  /**
   * 最后入库时间.
   */
  private LocalDateTime lastInTime;
  /**
   * 商城类型.
   */
  private MallType mallType;
  /**
   * 会员id.
   */
  private Long memberId;
  /**
   * 会员名称.
   */
  private String memberName;
  /**
   * 手机.
   */
  private String mobile;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 通知状态.
   */
  private WmsNoticeStatus noticeStatus;
  /**
   * 出库虚拟仓id.
   */
  private Long outVirtualWarehouseId;
  /**
   * 出库虚拟仓名称.
   */
  private String outVirtualWarehouseName;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 过账状态.
   */
  private PostStatus postStatus;
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
   * 退款类型.
   */
  private RefundType refundType;
  /**
   * 应退金额.
   */
  private Double refundableAmount;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 退换货单code.
   */
  private String returnOrderCode;
  /**
   * 退换货单id.
   */
  private Long returnOrderId;
  /**
   * 退货原因.
   */
  private String returnReasonType;
  /**
   * 退换类型.
   */
  private String returnType;
  /**
   * 复核时间.
   */
  private LocalDateTime reviewTime;
  /**
   * 复核人.
   */
  private String reviewer;
  /**
   * 销售单编码.
   */
  private String salesOrderCode;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * 状态.
   */
  private ReturnOrderStatus status;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 交易号.
   */
  private String tradeId;
  /**
   * 拆包时间.
   */
  private LocalDateTime unpackedTime;
  /**
   * 拆包人.
   */
  private String unpacker;
  /**
   * 版本.
   */
  private Integer version;

  /**
   * 退入明细.
   */
  private List<ReturnOrderDetail> details;
  /**
   * 换出明细.
   */
  private List<ReturnOrderOutDetail> outDetails;

  @Override
  public void setPrimaryKey(Long pk) {
    this.returnOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.returnOrderId;
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
   * 申请数量.
   */
  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  /**
   * 申请数量.
   */
  public Integer getApplyQuantity() {
    return this.applyQuantity;
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
   * 箱码.
   */
  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo == null ? null : boxNo.trim();
  }

  /**
   * 箱码.
   */
  public String getBoxNo() {
    return this.boxNo;
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
   * 快递名称.
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   */
  public String getExpressName() {
    return this.expressName;
  }

  /**
   * 快递单号.
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 快递单号.
   */
  public String getExpressNo() {
    return this.expressNo;
  }


  /**
   * 入库虚拟仓id.
   */
  public void setInVirtualWarehouseId(Long inVirtualWarehouseId) {
    this.inVirtualWarehouseId = inVirtualWarehouseId;
  }

  /**
   * 入库虚拟仓id.
   */
  public Long getInVirtualWarehouseId() {
    return this.inVirtualWarehouseId;
  }


  /**
   * 入库虚拟仓名称.
   */
  public void setInVirtualWarehouseName(String inVirtualWarehouseName) {
    this.inVirtualWarehouseName = inVirtualWarehouseName == null ? null : inVirtualWarehouseName.trim();
  }

  /**
   * 入库虚拟仓名称.
   */
  public String getInVirtualWarehouseName() {
    return this.inVirtualWarehouseName;
  }

  /**
   * 是否异常.
   */
  public void setAbnormal(Boolean abnormal) {
    this.abnormal = abnormal;
  }

  /**
   * 是否异常.
   */
  public Boolean isAbnormal() {
    return this.abnormal;
  }

  /**
   * 是否换货单.
   */
  public void setExchange(Boolean exchange) {
    this.exchange = exchange;
  }

  /**
   * 是否换货单.
   */
  public Boolean isExchange() {
    return this.exchange;
  }

  /**
   * 是否异常规格.
   */
  public void setSkuAbnormal(Boolean skuAbnormal) {
    this.skuAbnormal = skuAbnormal;
  }

  /**
   * 是否异常规格.
   */
  public Boolean isSkuAbnormal() {
    return this.skuAbnormal;
  }

  /**
   * 最后入库时间.
   */
  public void setLastInTime(LocalDateTime lastInTime) {
    this.lastInTime = lastInTime;
  }

  /**
   * 最后入库时间.
   */
  public LocalDateTime getLastInTime() {
    return this.lastInTime;
  }

  /**
   * 商城类型.
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   */
  public MallType getMallType() {
    return this.mallType;
  }

  /**
   * 会员id.
   */
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  /**
   * 会员id.
   */
  public Long getMemberId() {
    return this.memberId;
  }

  /**
   * 会员名称.
   */
  public void setMemberName(String memberName) {
    this.memberName = memberName == null ? null : memberName.trim();
  }

  /**
   * 会员名称.
   */
  public String getMemberName() {
    return this.memberName;
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
   * 通知状态.
   */
  public void setNoticeStatus(WmsNoticeStatus noticeStatus) {
    this.noticeStatus = noticeStatus;
  }

  /**
   * 通知状态.
   */
  public WmsNoticeStatus getNoticeStatus() {
    return this.noticeStatus;
  }


  /**
   * 出库虚拟仓id.
   */
  public void setOutVirtualWarehouseId(Long outVirtualWarehouseId) {
    this.outVirtualWarehouseId = outVirtualWarehouseId;
  }

  /**
   * 出库虚拟仓id.
   */
  public Long getOutVirtualWarehouseId() {
    return this.outVirtualWarehouseId;
  }

  /**
   * 出库虚拟仓名称.
   */
  public void setOutVirtualWarehouseName(String outVirtualWarehouseName) {
    this.outVirtualWarehouseName = outVirtualWarehouseName == null ? null : outVirtualWarehouseName.trim();
  }

  /**
   * 出库虚拟仓名称.
   */
  public String getOutVirtualWarehouseName() {
    return this.outVirtualWarehouseName;
  }

  /**
   * 外部单号.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   */
  public String getOuterCode() {
    return this.outerCode;
  }

  /**
   * 过账状态.
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   */
  public PostStatus getPostStatus() {
    return this.postStatus;
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
   * 退款类型.
   */
  public void setRefundType(RefundType refundType) {
    this.refundType = refundType;
  }

  /**
   * 退款类型.
   */
  public RefundType getRefundType() {
    return this.refundType;
  }

  /**
   * 应退金额.
   */
  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  /**
   * 应退金额.
   */
  public Double getRefundableAmount() {
    return this.refundableAmount;
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
   * 退换货单code.
   */
  public void setReturnOrderCode(String returnOrderCode) {
    this.returnOrderCode = returnOrderCode == null ? null : returnOrderCode.trim();
  }

  /**
   * 退换货单code.
   */
  public String getReturnOrderCode() {
    return this.returnOrderCode;
  }


  /**
   * 退换货单id.
   */
  public void setReturnOrderId(Long returnOrderId) {
    this.returnOrderId = returnOrderId;
  }

  /**
   * 退换货单id.
   */
  public Long getReturnOrderId() {
    return this.returnOrderId;
  }

  /**
   * 退货原因.
   */
  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType == null ? null : returnReasonType.trim();
  }

  /**
   * 退货原因.
   */
  public String getReturnReasonType() {
    return this.returnReasonType;
  }

  /**
   * 退换类型.
   */
  public void setReturnType(String returnType) {
    this.returnType = returnType == null ? null : returnType.trim();
  }

  /**
   * 退换类型.
   */
  public String getReturnType() {
    return this.returnType;
  }

  /**
   * 复核时间.
   */
  public void setReviewTime(LocalDateTime reviewTime) {
    this.reviewTime = reviewTime;
  }

  /**
   * 复核时间.
   */
  public LocalDateTime getReviewTime() {
    return this.reviewTime;
  }

  /**
   * 复核人.
   */
  public void setReviewer(String reviewer) {
    this.reviewer = reviewer == null ? null : reviewer.trim();
  }

  /**
   * 复核人.
   */
  public String getReviewer() {
    return this.reviewer;
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
   * 状态.
   */
  public void setStatus(ReturnOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public ReturnOrderStatus getStatus() {
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
   * 拆包时间.
   */
  public void setUnpackedTime(LocalDateTime unpackedTime) {
    this.unpackedTime = unpackedTime;
  }

  /**
   * 拆包时间.
   */
  public LocalDateTime getUnpackedTime() {
    return this.unpackedTime;
  }

  /**
   * 拆包人.
   */
  public void setUnpacker(String unpacker) {
    this.unpacker = unpacker == null ? null : unpacker.trim();
  }

  /**
   * 拆包人.
   */
  public String getUnpacker() {
    return this.unpacker;
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
   * 退入明细.
   */
  public void setDetails(List<ReturnOrderDetail> details) {
    this.details = details;
  }

  /**
   * 退入明细.
   */
  public List<ReturnOrderDetail> getDetails() {
    return this.details;
  }

  /**
   * 换出明细.
   */
  public void setOutDetails(List<ReturnOrderOutDetail> outDetails) {
    this.outDetails = outDetails;
  }

  /**
   * 换出明细.
   */
  public List<ReturnOrderOutDetail> getOutDetails() {
    return this.outDetails;
  }
}
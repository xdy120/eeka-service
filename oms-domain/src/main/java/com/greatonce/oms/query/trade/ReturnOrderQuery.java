package com.greatonce.oms.query.trade;

import com.greatonce.core.database.Query;
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
public class ReturnOrderQuery extends Query {
  /**
   * 地址.
   */
  private String address;
  /**
   * .
   */
  private String applyId;
  /**
   * 申请数量.
   */
  private Integer applyQuantity;
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
   * 箱码.
   */
  private String boxNo;
  /**
   * .
   */
  private List<String> boxNos;
  /**
   * .
   */
  private List<String> brandCodes;
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
   * .
   */
  private Boolean detailNotice;
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
   * .
   */
  private List<String> expressNos;
  /**
   * 入库虚拟仓id.
   */
  private Long inVirtualWarehouseId;
  /**
   * .
   */
  private List<Long> inVirtualWarehouseIds;
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
   * 最后入库时间开始.
   */
  private LocalDateTime lastInTimeBegin;
  /**
   * 最后入库时间结束.
   */
  private LocalDateTime lastInTimeEnd;
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
   * 通知状态.
   */
  private WmsNoticeStatus noticeStatus;
  /**
   * .
   */
  private List<WmsNoticeStatus> noticeStatuses;
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
   * .
   */
  private List<String> outProductCodes;
  /**
   * .
   */
  private List<String> productCodes;
  /**
   * 省id.
   */
  private Long provinceId;
  /**
   * 省.
   */
  private String provinceName;
  /**
   * .
   */
  private Boolean quantityAbnormal;
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
   * .
   */
  private List<String> returnOrderCodes;
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
   * 复核时间开始.
   */
  private LocalDateTime reviewTimeBegin;
  /**
   * 复核时间结束.
   */
  private LocalDateTime reviewTimeEnd;
  /**
   * 复核人.
   */
  private String reviewer;
  /**
   * 销售单编码.
   */
  private String salesOrderCode;
  /**
   * .
   */
  private List<String> salesOrderCodes;
  /**
   * 销售单id.
   */
  private Long salesOrderId;
  /**
   * .
   */
  private List<String> outSkuCodes;
  /**
   * .
   */
  private List<String> skuCodes;
  /**
   * .
   */
  private String smartKeys;
  /**
   * 状态.
   */
  private ReturnOrderStatus status;
  /**
   * .
   */
  private List<ReturnOrderStatus> statuses;
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
   * 电话.
   */
  private String telephone;
  /**
   * 交易号.
   */
  private String tradeId;
  /**
   * .
   */
  private List<String> tradeIds;
  /**
   * 拆包时间开始.
   */
  private LocalDateTime unpackedTimeBegin;
  /**
   * 拆包时间结束.
   */
  private LocalDateTime unpackedTimeEnd;
  /**
   * 拆包人.
   */
  private String unpacker;
  /**
   * 版本.
   */
  private Integer version;


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
   * .
   * @param applyId 
   */
  public void setApplyId(String applyId) {
    this.applyId = applyId == null ? null : applyId.trim();
  }

  /**
   * .
   * @return 
   */
  public String getApplyId() {
      return this.applyId;
  }

  /**
   * 申请数量.
   * @param applyQuantity 申请数量
   */
  public void setApplyQuantity(Integer applyQuantity) {
    this.applyQuantity = applyQuantity;
  }

  /**
   * 申请数量.
   * @return 申请数量
   */
  public Integer getApplyQuantity() {
      return this.applyQuantity;
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
   * 箱码.
   * @param boxNo 箱码
   */
  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo == null ? null : boxNo.trim();
  }

  /**
   * 箱码.
   * @return 箱码
   */
  public String getBoxNo() {
      return this.boxNo;
  }

  /**
   * .
   * @param boxNos 
   */
  public void setBoxNos(List<String> boxNos) {
    this.boxNos = boxNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getBoxNos() {
      return this.boxNos;
  }

  /**
   * .
   * @param brandCodes 
   */
  public void setBrandCodes(List<String> brandCodes) {
    this.brandCodes = brandCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getBrandCodes() {
      return this.brandCodes;
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
   * .
   * @param detailNotice 
   */
  public void setDetailNotice(Boolean detailNotice) {
    this.detailNotice = detailNotice;
  }

  /**
   * .
   * @return 
   */
  public Boolean getDetailNotice() {
      return this.detailNotice;
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
   * 快递名称.
   * @param expressName 快递名称
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   * @return 快递名称
   */
  public String getExpressName() {
      return this.expressName;
  }

  /**
   * 快递单号.
   * @param expressNo 快递单号
   */
  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo == null ? null : expressNo.trim();
  }

  /**
   * 快递单号.
   * @return 快递单号
   */
  public String getExpressNo() {
      return this.expressNo;
  }

  /**
   * .
   * @param expressNos 
   */
  public void setExpressNos(List<String> expressNos) {
    this.expressNos = expressNos;
  }

  /**
   * .
   * @return 
   */
  public List<String> getExpressNos() {
      return this.expressNos;
  }

  /**
   * 入库虚拟仓id.
   * @param inVirtualWarehouseId 入库虚拟仓id
   */
  public void setInVirtualWarehouseId(Long inVirtualWarehouseId) {
    this.inVirtualWarehouseId = inVirtualWarehouseId;
  }

  /**
   * 入库虚拟仓id.
   * @return 入库虚拟仓id
   */
  public Long getInVirtualWarehouseId() {
      return this.inVirtualWarehouseId;
  }

  /**
   * .
   * @param inVirtualWarehouseIds 
   */
  public void setInVirtualWarehouseIds(List<Long> inVirtualWarehouseIds) {
    this.inVirtualWarehouseIds = inVirtualWarehouseIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getInVirtualWarehouseIds() {
      return this.inVirtualWarehouseIds;
  }

  /**
   * 入库虚拟仓名称.
   * @param inVirtualWarehouseName 入库虚拟仓名称
   */
  public void setInVirtualWarehouseName(String inVirtualWarehouseName) {
    this.inVirtualWarehouseName = inVirtualWarehouseName == null ? null : inVirtualWarehouseName.trim();
  }

  /**
   * 入库虚拟仓名称.
   * @return 入库虚拟仓名称
   */
  public String getInVirtualWarehouseName() {
      return this.inVirtualWarehouseName;
  }

  /**
   * 是否异常.
   * @param abnormal 是否异常
   */
  public void setAbnormal(Boolean abnormal) {
    this.abnormal = abnormal;
  }

  /**
   * 是否异常.
   * @return 是否异常
   */
  public Boolean isAbnormal() {
      return this.abnormal;
  }

  /**
   * 是否换货单.
   * @param exchange 是否换货单
   */
  public void setExchange(Boolean exchange) {
    this.exchange = exchange;
  }

  /**
   * 是否换货单.
   * @return 是否换货单
   */
  public Boolean isExchange() {
      return this.exchange;
  }

  /**
   * 是否异常规格.
   * @param skuAbnormal 是否异常规格
   */
  public void setSkuAbnormal(Boolean skuAbnormal) {
    this.skuAbnormal = skuAbnormal;
  }

  /**
   * 是否异常规格.
   * @return 是否异常规格
   */
  public Boolean isSkuAbnormal() {
      return this.skuAbnormal;
  }

  /**
   * 最后入库时间开始.
   * @param lastInTimeBegin 开始.
   */
  public void setLastInTimeBegin(LocalDateTime lastInTimeBegin) {
    this.lastInTimeBegin = lastInTimeBegin;
  }

  /**
   * 最后入库时间开始.
   * @return 最后入库时间开始
   */
  public LocalDateTime getLastInTimeBegin() {
    return this.lastInTimeBegin;
  }

  /**
   * 最后入库时间结束.
   * @param lastInTimeEnd 结束
   */
  public void setLastInTimeEnd(LocalDateTime lastInTimeEnd) {
    this.lastInTimeEnd = lastInTimeEnd;
  }

  /**
   * 最后入库时间结束.
   * @return 最后入库时间结束
   */
  public LocalDateTime getLastInTimeEnd() {
    return this.lastInTimeEnd;
  }

  /**
   * 商城类型.
   * @param mallType 商城类型
   */
  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  /**
   * 商城类型.
   * @return 商城类型
   */
  public MallType getMallType() {
      return this.mallType;
  }

  /**
   * 会员id.
   * @param memberId 会员id
   */
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  /**
   * 会员id.
   * @return 会员id
   */
  public Long getMemberId() {
      return this.memberId;
  }

  /**
   * 会员名称.
   * @param memberName 会员名称
   */
  public void setMemberName(String memberName) {
    this.memberName = memberName == null ? null : memberName.trim();
  }

  /**
   * 会员名称.
   * @return 会员名称
   */
  public String getMemberName() {
      return this.memberName;
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
   * 通知状态.
   * @param noticeStatus 通知状态
   */
  public void setNoticeStatus(WmsNoticeStatus noticeStatus) {
    this.noticeStatus = noticeStatus;
  }

  /**
   * 通知状态.
   * @return 通知状态
   */
  public WmsNoticeStatus getNoticeStatus() {
      return this.noticeStatus;
  }

  /**
   * .
   * @param noticeStatuses 
   */
  public void setNoticeStatuses(List<WmsNoticeStatus> noticeStatuses) {
    this.noticeStatuses = noticeStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<WmsNoticeStatus> getNoticeStatuses() {
      return this.noticeStatuses;
  }

  /**
   * 出库虚拟仓id.
   * @param outVirtualWarehouseId 出库虚拟仓id
   */
  public void setOutVirtualWarehouseId(Long outVirtualWarehouseId) {
    this.outVirtualWarehouseId = outVirtualWarehouseId;
  }

  /**
   * 出库虚拟仓id.
   * @return 出库虚拟仓id
   */
  public Long getOutVirtualWarehouseId() {
      return this.outVirtualWarehouseId;
  }

  /**
   * 出库虚拟仓名称.
   * @param outVirtualWarehouseName 出库虚拟仓名称
   */
  public void setOutVirtualWarehouseName(String outVirtualWarehouseName) {
    this.outVirtualWarehouseName = outVirtualWarehouseName == null ? null : outVirtualWarehouseName.trim();
  }

  /**
   * 出库虚拟仓名称.
   * @return 出库虚拟仓名称
   */
  public String getOutVirtualWarehouseName() {
      return this.outVirtualWarehouseName;
  }

  /**
   * 外部单号.
   * @param outerCode 外部单号
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   * @return 外部单号
   */
  public String getOuterCode() {
      return this.outerCode;
  }

  /**
   * 过账状态.
   * @param postStatus 过账状态
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   * @return 过账状态
   */
  public PostStatus getPostStatus() {
      return this.postStatus;
  }

  /**
   * .
   * @param outProductCodes 
   */
  public void setOutProductCodes(List<String> outProductCodes) {
    this.outProductCodes = outProductCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOutProductCodes() {
      return this.outProductCodes;
  }

  /**
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
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
   * .
   * @param quantityAbnormal 
   */
  public void setQuantityAbnormal(Boolean quantityAbnormal) {
    this.quantityAbnormal = quantityAbnormal;
  }

  /**
   * .
   * @return 
   */
  public Boolean getQuantityAbnormal() {
      return this.quantityAbnormal;
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
   * 退款类型.
   * @param refundType 退款类型
   */
  public void setRefundType(RefundType refundType) {
    this.refundType = refundType;
  }

  /**
   * 退款类型.
   * @return 退款类型
   */
  public RefundType getRefundType() {
      return this.refundType;
  }

  /**
   * 应退金额.
   * @param refundableAmount 应退金额
   */
  public void setRefundableAmount(Double refundableAmount) {
    this.refundableAmount = refundableAmount;
  }

  /**
   * 应退金额.
   * @return 应退金额
   */
  public Double getRefundableAmount() {
      return this.refundableAmount;
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
   * 退换货单code.
   * @param returnOrderCode 退换货单code
   */
  public void setReturnOrderCode(String returnOrderCode) {
    this.returnOrderCode = returnOrderCode == null ? null : returnOrderCode.trim();
  }

  /**
   * 退换货单code.
   * @return 退换货单code
   */
  public String getReturnOrderCode() {
      return this.returnOrderCode;
  }

  /**
   * .
   * @param returnOrderCodes 
   */
  public void setReturnOrderCodes(List<String> returnOrderCodes) {
    this.returnOrderCodes = returnOrderCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getReturnOrderCodes() {
      return this.returnOrderCodes;
  }

  /**
   * 退换货单id.
   * @param returnOrderId 退换货单id
   */
  public void setReturnOrderId(Long returnOrderId) {
    this.returnOrderId = returnOrderId;
  }

  /**
   * 退换货单id.
   * @return 退换货单id
   */
  public Long getReturnOrderId() {
      return this.returnOrderId;
  }

  /**
   * 退货原因.
   * @param returnReasonType 退货原因
   */
  public void setReturnReasonType(String returnReasonType) {
    this.returnReasonType = returnReasonType == null ? null : returnReasonType.trim();
  }

  /**
   * 退货原因.
   * @return 退货原因
   */
  public String getReturnReasonType() {
      return this.returnReasonType;
  }

  /**
   * 退换类型.
   * @param returnType 退换类型
   */
  public void setReturnType(String returnType) {
    this.returnType = returnType == null ? null : returnType.trim();
  }

  /**
   * 退换类型.
   * @return 退换类型
   */
  public String getReturnType() {
      return this.returnType;
  }

  /**
   * 复核时间开始.
   * @param reviewTimeBegin 开始.
   */
  public void setReviewTimeBegin(LocalDateTime reviewTimeBegin) {
    this.reviewTimeBegin = reviewTimeBegin;
  }

  /**
   * 复核时间开始.
   * @return 复核时间开始
   */
  public LocalDateTime getReviewTimeBegin() {
    return this.reviewTimeBegin;
  }

  /**
   * 复核时间结束.
   * @param reviewTimeEnd 结束
   */
  public void setReviewTimeEnd(LocalDateTime reviewTimeEnd) {
    this.reviewTimeEnd = reviewTimeEnd;
  }

  /**
   * 复核时间结束.
   * @return 复核时间结束
   */
  public LocalDateTime getReviewTimeEnd() {
    return this.reviewTimeEnd;
  }

  /**
   * 复核人.
   * @param reviewer 复核人
   */
  public void setReviewer(String reviewer) {
    this.reviewer = reviewer == null ? null : reviewer.trim();
  }

  /**
   * 复核人.
   * @return 复核人
   */
  public String getReviewer() {
      return this.reviewer;
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
   * @param outSkuCodes 
   */
  public void setOutSkuCodes(List<String> outSkuCodes) {
    this.outSkuCodes = outSkuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOutSkuCodes() {
      return this.outSkuCodes;
  }

  /**
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
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
  public void setStatus(ReturnOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public ReturnOrderStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<ReturnOrderStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<ReturnOrderStatus> getStatuses() {
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
   * 拆包时间开始.
   * @param unpackedTimeBegin 开始.
   */
  public void setUnpackedTimeBegin(LocalDateTime unpackedTimeBegin) {
    this.unpackedTimeBegin = unpackedTimeBegin;
  }

  /**
   * 拆包时间开始.
   * @return 拆包时间开始
   */
  public LocalDateTime getUnpackedTimeBegin() {
    return this.unpackedTimeBegin;
  }

  /**
   * 拆包时间结束.
   * @param unpackedTimeEnd 结束
   */
  public void setUnpackedTimeEnd(LocalDateTime unpackedTimeEnd) {
    this.unpackedTimeEnd = unpackedTimeEnd;
  }

  /**
   * 拆包时间结束.
   * @return 拆包时间结束
   */
  public LocalDateTime getUnpackedTimeEnd() {
    return this.unpackedTimeEnd;
  }

  /**
   * 拆包人.
   * @param unpacker 拆包人
   */
  public void setUnpacker(String unpacker) {
    this.unpacker = unpacker == null ? null : unpacker.trim();
  }

  /**
   * 拆包人.
   * @return 拆包人
   */
  public String getUnpacker() {
      return this.unpacker;
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
package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.MallType;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 发货单创建请求.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class DeliveryOrderCreateRequest extends WmsOrderRequest {

  public DeliveryOrderCreateRequest(Warehouse warehouse) {
    super(warehouse);
  }

  /**
   * 订单标记.
   * 订单标记(用字符串格式来表示订单标记列表:例如COD=货到付款;LIMIT=限时配 送;PRESELL=预 售;COMPLAIN=已投诉;SPLIT=拆单;EXCHANGE=换货;VISIT=上门;MODIFYTRANSPORT=是否 可改配送方式;CONSIGN = 物流宝代理发货;SELLER_AFFORD=是否卖家承担运费;FENXIAO=分销订单)
   */
  private String orderFlag;
  /**
   * 来源平台名*.
   */
  private MallType mallType;
  /**
   * 平台下单时间*.
   */
  private LocalDateTime mallCreatedTime;
  /**
   * 订单支付时间.
   */
  private LocalDateTime mallPaidTime;
  /**
   * 店铺编码*.
   */
  private String storeCode;
  /**
   * 店铺名称*.
   */
  private String storeName;
  /**
   * 订单总金额*.
   */
  private Double totalAmount;
  /**
   * 商品总金额*.
   */
  private Double goodsAmount;
  /**
   * 支付金额*.
   */
  private Double paidAmount;
  /**
   * 订单折扣金额(元).
   */
  private Double discountAmount;
  /**
   * 快递费用*.
   */
  private Double expressFee;
  /**
   * 应收金额*.
   */
  private Double codAmount;
  /**
   * COD服务费*.
   */
  private Double codFee;
  /**
   * 物流公司编码*.
   * 物流公司编码(SF=顺丰、EMS=标准快递、EYB=经济快件、ZJS=宅急送、YTO=圆通 、ZTO=中 通(ZTO)、HTKY=百世汇通、UC=优速、STO=申通、TTKDEX=天天快递、QFKD=全峰、FAST=快捷 、POSTB=邮政小包、 GTO=国通、YUNDA=韵达、JD=京东配送、DD=当当宅配、AMAZON=亚马逊物流、 OTHER=其他)
   */
  private String expressCode;
  /**
   * 物流公司名*.
   */
  private String expressName;
  /**
   * 运单号.
   */
  private String expressNo;
  /**
   * 快递区域编码.
   */
  private String expressAreaCode;
  /**
   * 是否紧急.
   */
  private boolean urgency;
  /**
   * 是否需要发票.
   */
  private boolean needInvoice;
  /**
   * 买家信息.
   */
  private String buyer;
  /**
   * 卖家备注.
   */
  private String seller;
  /**
   * 买家信息.
   */
  private String buyerMemo;
  /**
   * 卖家备注.
   */
  private String sellerMemo;
  /**
   * 收件人*.
   */
  private String receiverName;
  /**
   * 收件邮编.
   */
  private String receiverZipCode;
  /**
   * 收件人电话*.
   */
  private String receiverTelephone;
  /**
   * 收件人手机*.
   */
  private String receiverMobile;
  /**
   * 收件人邮箱.
   */
  private String receiverEmail;
  /**
   * 收件人国家*.
   */
  private String receiverCountry;
  /**
   * 收件人省*.
   */
  private String receiverProvince;
  /**
   * 收件人市*.
   */
  private String receiverCity;
  /**
   * 收件人区*.
   */
  private String receiverDistrict;
  /**
   * 收件人村镇.
   */
  private String receiverTown;
  /**
   * 收件人地址*.
   */
  private String receiverAddress;

  /**
   * 收件人国家*.
   */
  private String receiverCountryCode;
  /**
   * 收件人省*.
   */
  private String receiverProvinceCode;
  /**
   * 收件人市*.
   */
  private String receiverCityCode;
  /**
   * 收件人区*.
   */
  private String receiverDistrictCode;
  /**
   * 收件人村镇.
   */
  private String receiverTownCode;
  /**
   * 发件人.
   */
  private String senderName;
  /**
   * 发件人手机.
   */
  private String senderMobile;
  /**
   * 发件人省.
   */
  private String senderProvince;
  /**
   * 发件人市.
   */
  private String senderCity;
  /**
   * 发件人区.
   */
  private String senderDistrict;
  /**
   * 发件人地址.
   */
  private String senderAddress;
  /**
   * 发件人公司.
   */
  private String senderCompany;
  /**
   * 发件人邮政编码.
   */
  private String senderZipCode;
  /**
   * 发件人电话.
   */
  private String senderTelephone;
  /**
   * 发件人邮箱.
   */
  private String senderEmail;
  /**
   * 发件人国家编码.
   */
  private String senderCountry;
  /**
   * 发件人村镇.
   */
  private String senderTown;
  /**
   * 发件人备注.
   */
  private String senderRemark;
  /**
   * 发票类型.
   */
  private String invoiceType;
  /**
   * 发票抬头.
   */
  private String invoiceHeader;
  /**
   * 发票金额.
   */
  private String invoiceAmount;
  /**
   * 发票内容.
   */
  private String invoiceContent;
  /**
   * 是否需要保险.
   */
  private boolean insurance;
  /**
   * 保险金额.
   */
  private Double insuranceAmount;
  /**
   * 保险类型.
   */
  private String insuranceType;
  /**
   * 集包地编码.
   */
  private String consolidationCode;
  /**
   * 集包地名称.
   */
  private String consolidationName;
  /**
   * 大头笔信息.
   */
  private String sortation;
  /**
   * 三段码.
   */
  private String routeCode;


  private Collection<DeliveryOrderDetail> details;

  public String getOrderFlag() {
    return orderFlag;
  }

  public void setOrderFlag(String orderFlag) {
    this.orderFlag = orderFlag;
  }

  public MallType getMallType() {
    return mallType;
  }

  public void setMallType(MallType mallType) {
    this.mallType = mallType;
  }

  public LocalDateTime getMallCreatedTime() {
    return mallCreatedTime;
  }

  public void setMallCreatedTime(LocalDateTime mallCreatedTime) {
    this.mallCreatedTime = mallCreatedTime;
  }

  public LocalDateTime getMallPaidTime() {
    return mallPaidTime;
  }

  public void setMallPaidTime(LocalDateTime mallPaidTime) {
    this.mallPaidTime = mallPaidTime;
  }

  public String getStoreCode() {
    return storeCode;
  }

  public void setStoreCode(String storeCode) {
    this.storeCode = storeCode;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Double getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(Double paidAmount) {
    this.paidAmount = paidAmount;
  }

  public Double getGoodsAmount() {
    return goodsAmount;
  }

  public void setGoodsAmount(Double goodsAmount) {
    this.goodsAmount = goodsAmount;
  }

  public Double getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(Double discountAmount) {
    this.discountAmount = discountAmount;
  }

  public Double getExpressFee() {
    return expressFee;
  }

  public void setExpressFee(Double expressFee) {
    this.expressFee = expressFee;
  }

  public Double getCodAmount() {
    return codAmount;
  }

  public void setCodAmount(Double codAmount) {
    this.codAmount = codAmount;
  }

  public Double getCodFee() {
    return codFee;
  }

  public void setCodFee(Double codFee) {
    this.codFee = codFee;
  }

  public String getExpressCode() {
    return expressCode;
  }

  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode;
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

  public String getExpressAreaCode() {
    return expressAreaCode;
  }

  public void setExpressAreaCode(String expressAreaCode) {
    this.expressAreaCode = expressAreaCode;
  }

  public boolean isUrgency() {
    return urgency;
  }

  public void setUrgency(boolean urgency) {
    this.urgency = urgency;
  }

  public boolean isNeedInvoice() {
    return needInvoice;
  }

  public void setNeedInvoice(boolean needInvoice) {
    this.needInvoice = needInvoice;
  }

  public String getBuyer() {
    return buyer;
  }

  public void setBuyer(String buyer) {
    this.buyer = buyer;
  }

  public String getSeller() {
    return seller;
  }

  public void setSeller(String seller) {
    this.seller = seller;
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

  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }

  public String getReceiverZipCode() {
    return receiverZipCode;
  }

  public void setReceiverZipCode(String receiverZipCode) {
    this.receiverZipCode = receiverZipCode;
  }

  public String getReceiverTelephone() {
    return receiverTelephone;
  }

  public void setReceiverTelephone(String receiverTelephone) {
    this.receiverTelephone = receiverTelephone;
  }

  public String getReceiverMobile() {
    return receiverMobile;
  }

  public void setReceiverMobile(String receiverMobile) {
    this.receiverMobile = receiverMobile;
  }

  public String getReceiverEmail() {
    return receiverEmail;
  }

  public void setReceiverEmail(String receiverEmail) {
    this.receiverEmail = receiverEmail;
  }

  public String getReceiverCountry() {
    return receiverCountry;
  }

  public void setReceiverCountry(String receiverCountry) {
    this.receiverCountry = receiverCountry;
  }

  public String getReceiverProvince() {
    return receiverProvince;
  }

  public void setReceiverProvince(String receiverProvince) {
    this.receiverProvince = receiverProvince;
  }

  public String getReceiverCity() {
    return receiverCity;
  }

  public void setReceiverCity(String receiverCity) {
    this.receiverCity = receiverCity;
  }

  public String getReceiverDistrict() {
    return receiverDistrict;
  }

  public void setReceiverDistrict(String receiverDistrict) {
    this.receiverDistrict = receiverDistrict;
  }

  public String getReceiverTown() {
    return receiverTown;
  }

  public void setReceiverTown(String receiverTown) {
    this.receiverTown = receiverTown;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
  }

  public String getReceiverCountryCode() {
    return receiverCountryCode;
  }

  public void setReceiverCountryCode(String receiverCountryCode) {
    this.receiverCountryCode = receiverCountryCode;
  }

  public String getReceiverProvinceCode() {
    return receiverProvinceCode;
  }

  public void setReceiverProvinceCode(String receiverProvinceCode) {
    this.receiverProvinceCode = receiverProvinceCode;
  }

  public String getReceiverCityCode() {
    return receiverCityCode;
  }

  public void setReceiverCityCode(String receiverCityCode) {
    this.receiverCityCode = receiverCityCode;
  }

  public String getReceiverDistrictCode() {
    return receiverDistrictCode;
  }

  public void setReceiverDistrictCode(String receiverDistrictCode) {
    this.receiverDistrictCode = receiverDistrictCode;
  }

  public String getReceiverTownCode() {
    return receiverTownCode;
  }

  public void setReceiverTownCode(String receiverTownCode) {
    this.receiverTownCode = receiverTownCode;
  }

  public String getSenderName() {
    return senderName;
  }

  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }

  public String getSenderMobile() {
    return senderMobile;
  }

  public void setSenderMobile(String senderMobile) {
    this.senderMobile = senderMobile;
  }

  public String getSenderProvince() {
    return senderProvince;
  }

  public void setSenderProvince(String senderProvince) {
    this.senderProvince = senderProvince;
  }

  public String getSenderCity() {
    return senderCity;
  }

  public void setSenderCity(String senderCity) {
    this.senderCity = senderCity;
  }

  public String getSenderDistrict() {
    return senderDistrict;
  }

  public void setSenderDistrict(String senderDistrict) {
    this.senderDistrict = senderDistrict;
  }

  public String getSenderAddress() {
    return senderAddress;
  }

  public void setSenderAddress(String senderAddress) {
    this.senderAddress = senderAddress;
  }

  public String getSenderCompany() {
    return senderCompany;
  }

  public void setSenderCompany(String senderCompany) {
    this.senderCompany = senderCompany;
  }

  public String getSenderZipCode() {
    return senderZipCode;
  }

  public void setSenderZipCode(String senderZipCode) {
    this.senderZipCode = senderZipCode;
  }

  public String getSenderTelephone() {
    return senderTelephone;
  }

  public void setSenderTelephone(String senderTelephone) {
    this.senderTelephone = senderTelephone;
  }

  public String getSenderEmail() {
    return senderEmail;
  }

  public void setSenderEmail(String senderEmail) {
    this.senderEmail = senderEmail;
  }

  public String getSenderCountry() {
    return senderCountry;
  }

  public void setSenderCountry(String senderCountry) {
    this.senderCountry = senderCountry;
  }

  public String getSenderTown() {
    return senderTown;
  }

  public void setSenderTown(String senderTown) {
    this.senderTown = senderTown;
  }

  public String getSenderRemark() {
    return senderRemark;
  }

  public void setSenderRemark(String senderRemark) {
    this.senderRemark = senderRemark;
  }

  public String getInvoiceType() {
    return invoiceType;
  }

  public void setInvoiceType(String invoiceType) {
    this.invoiceType = invoiceType;
  }

  public String getInvoiceHeader() {
    return invoiceHeader;
  }

  public void setInvoiceHeader(String invoiceHeader) {
    this.invoiceHeader = invoiceHeader;
  }

  public String getInvoiceAmount() {
    return invoiceAmount;
  }

  public void setInvoiceAmount(String invoiceAmount) {
    this.invoiceAmount = invoiceAmount;
  }

  public String getInvoiceContent() {
    return invoiceContent;
  }

  public void setInvoiceContent(String invoiceContent) {
    this.invoiceContent = invoiceContent;
  }

  public boolean isInsurance() {
    return insurance;
  }

  public void setInsurance(boolean insurance) {
    this.insurance = insurance;
  }

  public Double getInsuranceAmount() {
    return insuranceAmount;
  }

  public void setInsuranceAmount(Double insuranceAmount) {
    this.insuranceAmount = insuranceAmount;
  }

  public String getInsuranceType() {
    return insuranceType;
  }

  public void setInsuranceType(String insuranceType) {
    this.insuranceType = insuranceType;
  }

  public String getConsolidationCode() {
    return consolidationCode;
  }

  public void setConsolidationCode(String consolidationCode) {
    this.consolidationCode = consolidationCode;
  }

  public String getConsolidationName() {
    return consolidationName;
  }

  public void setConsolidationName(String consolidationName) {
    this.consolidationName = consolidationName;
  }

  public String getSortation() {
    return sortation;
  }

  public void setSortation(String sortation) {
    this.sortation = sortation;
  }

  public String getRouteCode() {
    return routeCode;
  }

  public void setRouteCode(String routeCode) {
    this.routeCode = routeCode;
  }

  public Collection<DeliveryOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(Collection<DeliveryOrderDetail> details) {
    this.details = details;
  }

  public static class DeliveryOrderDetail extends WmsOrderDetail {

    /**
     * 交易平台订单.
     */
    private String tradeId;
    /**
     * 交易平台子订单编码.
     */
    private String oid;
    /**
     * 支付平台交易号(淘系订单传支付宝交易号).
     */
    private String payNo;
    /**
     * 零售价*.
     */
    private Double sellingPrice;
    /**
     * 结算价*.
     */
    private Double settlementPrice;

    /**
     * 零售金额*.
     */
    private Double sellingAmount;
    /**
     * 结算金额*.
     */
    private Double settlementAmount;

    public String getTradeId() {
      return tradeId;
    }

    public void setTradeId(String tradeId) {
      this.tradeId = tradeId;
    }

    public String getOid() {
      return oid;
    }

    public void setOid(String oid) {
      this.oid = oid;
    }

    public String getPayNo() {
      return payNo;
    }

    public void setPayNo(String payNo) {
      this.payNo = payNo;
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

    public Double getSellingAmount() {
      return sellingAmount;
    }

    public void setSellingAmount(Double sellingAmount) {
      this.sellingAmount = sellingAmount;
    }

    public Double getSettlementAmount() {
      return settlementAmount;
    }

    public void setSettlementAmount(Double settlementAmount) {
      this.settlementAmount = settlementAmount;
    }
  }
}

package com.greatonce.oms.bridge.mall.impl.jd.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 京东面单请求报文序列化对象.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/13
 */
public class JdWayBillContentConverter {

  /**
   * 运单类型：1.普通运单
   */
  private Integer waybillType;

  /**
   * 所需运单的数量，顺丰只能传1，非顺丰快递公司最多只能传99
   */
  private Integer waybillCount;

  /**
   * 承运商id (providerId 与 providerCode 两者必填一个)
   */
  private Integer providerId;

  /**
   * 承运商编码(providerID与providerCode两者必填一个)
   */
  private String providerCode;

  /**
   * 承运商发货网点编码，加盟型快递公司必传
   */
  private String branchCode;

  /**
   * 财务结算编码，直营型快递公司必传
   */
  private String settlementCode;

  /**
   * 平台订单号，即 pop	订单号，如果多订单合并发货，每个订单号之间用“,”逗号分隔，每个订单号最多32位
   */
  private String platformOrderNo;
  /**
   * 商家编码，是用 POP 是 10001
   */
  private String vendorCode;

  /**
   * 商家名称 是 XXX旗舰店
   */
  private String vendorName;

  /**
   * 商家自有订单号 是 ELS9292003
   */
  private String vendorOrderCode;

  /**
   * 销售平台
   * 0010001 代表京
   东平台下的订单
   0010002 天猫、
   淘宝订单
   0030001 其他平
   台订单
   */
  private String salePlatform;

  /**
   * 发货地址
   */
  private FromAddress fromAddress;

  /**
   * 收货人地址
   */
  private ToAddress toAddress;

  /**
   * 重量，单位为千克 两位小数
   */
  private BigDecimal weight;

  /**
   * 体积，单位为统一为立方厘米 两位小数
   */
  private BigDecimal volume;

  /**
   * 付款方式0-在线支付 目前暂时不支持货到付款业务
   */
  private Integer payType;

  /**
   * 代收金额 两位小数 付款方式为在线支付时，代收金额必须为0
   */
  private BigDecimal shouldPayMoney;

  /**
   * 是否要保价（系统暂不开放报价业务）
   */
  private Boolean needGuarantee;

  /**
   * 保价金额 两位小数 needGuarantee 为false 时，保价金额必须为0
   */
  private BigDecimal guaranteeMoney;

  /**
   * 收货时间类型，0任何时间，1工作日2节假日
   */
  private Integer receiveTimeType;

  /**
   * 商品名称
   */
  private String goodsName;

  /**
   * 承诺时效类型 无时效默认传0
   */
  private Integer promiseTimeType;

  /**
   * 承诺完成时间，若未承诺时效，则不考虑此字段
   */
  private LocalDate promiseCompleteTime;

  public Integer getWaybillType() {
    return waybillType;
  }

  public void setWaybillType(Integer waybillType) {
    this.waybillType = waybillType;
  }

  public Integer getWaybillCount() {
    return waybillCount;
  }

  public void setWaybillCount(Integer waybillCount) {
    this.waybillCount = waybillCount;
  }

  public Integer getProviderId() {
    return providerId;
  }

  public void setProviderId(Integer providerId) {
    this.providerId = providerId;
  }

  public String getProviderCode() {
    return providerCode;
  }

  public void setProviderCode(String providerCode) {
    this.providerCode = providerCode;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getPlatformOrderNo() {
    return platformOrderNo;
  }

  public void setPlatformOrderNo(String platformOrderNo) {
    this.platformOrderNo = platformOrderNo;
  }

  public String getVendorCode() {
    return vendorCode;
  }

  public void setVendorCode(String vendorCode) {
    this.vendorCode = vendorCode;
  }

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getVendorOrderCode() {
    return vendorOrderCode;
  }

  public void setVendorOrderCode(String vendorOrderCode) {
    this.vendorOrderCode = vendorOrderCode;
  }

  public String getSalePlatform() {
    return salePlatform;
  }

  public void setSalePlatform(String salePlatform) {
    this.salePlatform = salePlatform;
  }

  public FromAddress getFromAddress() {
    return fromAddress;
  }

  public void setFromAddress(FromAddress fromAddress) {
    this.fromAddress = fromAddress;
  }

  public ToAddress getToAddress() {
    return toAddress;
  }

  public void setToAddress(ToAddress toAddress) {
    this.toAddress = toAddress;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public void setVolume(BigDecimal volume) {
    this.volume = volume;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public BigDecimal getShouldPayMoney() {
    return shouldPayMoney;
  }

  public void setShouldPayMoney(BigDecimal shouldPayMoney) {
    this.shouldPayMoney = shouldPayMoney;
  }

  public Boolean getNeedGuarantee() {
    return needGuarantee;
  }

  public void setNeedGuarantee(Boolean needGuarantee) {
    this.needGuarantee = needGuarantee;
  }

  public BigDecimal getGuaranteeMoney() {
    return guaranteeMoney;
  }

  public void setGuaranteeMoney(BigDecimal guaranteeMoney) {
    this.guaranteeMoney = guaranteeMoney;
  }

  public Integer getReceiveTimeType() {
    return receiveTimeType;
  }

  public void setReceiveTimeType(Integer receiveTimeType) {
    this.receiveTimeType = receiveTimeType;
  }

  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  public Integer getPromiseTimeType() {
    return promiseTimeType;
  }

  public void setPromiseTimeType(Integer promiseTimeType) {
    this.promiseTimeType = promiseTimeType;
  }

  public LocalDate getPromiseCompleteTime() {
    return promiseCompleteTime;
  }

  public void setPromiseCompleteTime(LocalDate promiseCompleteTime) {
    this.promiseCompleteTime = promiseCompleteTime;
  }

  public String getSettlementCode() {
    return settlementCode;
  }

  public void setSettlementCode(String settlementCode) {
    this.settlementCode = settlementCode;
  }

  /**
   * 发货地址
   * @author GuoRuiHua
   *
   */
  public static class FromAddress {

    /**
     * 省/直辖市id
     */
    private int provinceId;

    /**
     * 省/直辖市名称
     */
    private String provinceName;

    /**
     * 市id
     */
    private int cityId;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区域ID
     */
    private int countryId;

    /**
     * 区域名称
     */
    private String countryName;

    /**
     * 乡镇/街道 id
     */
    private int countrysideId;

    /**
     * 县镇/街道名称
     */
    private String countrysideName;

    /**
     * 发货详细地址
     */
    private String address;

    /**
     * 发货联系人
     */
    private String contact;

    /**
     * 发货人电话
     */
    private String phone;

    /**
     * 发货人手机
     */
    private String mobile;

    public void setProvinceId(int provinceId) {
      this.provinceId = provinceId;
    }

    public int getProvinceId() {
      return this.provinceId;
    }

    public void setProvinceName(String provinceName) {
      this.provinceName = provinceName;
    }

    public String getProvinceName() {
      return this.provinceName;
    }

    public void setCityId(int cityId) {
      this.cityId = cityId;
    }

    public int getCityId() {
      return this.cityId;
    }

    public void setCityName(String cityName) {
      this.cityName = cityName;
    }

    public String getCityName() {
      return this.cityName;
    }

    public void setCountryId(int countryId) {
      this.countryId = countryId;
    }

    public int getCountryId() {
      return this.countryId;
    }

    public void setCountryName(String countryName) {
      this.countryName = countryName;
    }

    public String getCountryName() {
      return this.countryName;
    }

    public void setCountrysideId(int countrysideId) {
      this.countrysideId = countrysideId;
    }

    public int getCountrysideId() {
      return this.countrysideId;
    }

    public void setCountrysideName(String countrysideName) {
      this.countrysideName = countrysideName;
    }

    public String getCountrysideName() {
      return this.countrysideName;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getAddress() {
      return this.address;
    }

    public void setContact(String contact) {
      this.contact = contact;
    }

    public String getContact() {
      return this.contact;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getPhone() {
      return this.phone;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getMobile() {
      return this.mobile;
    }
  }

  /**
   * 收货人信息
   * @author GuoRuiHua
   *
   */
  public static class ToAddress {

    /**
     * 省/直辖市id
     */
    private int provinceId;

    /**
     * 省/直辖市名称
     */
    private String provinceName;

    /**
     * 市id
     */
    private int cityId;

    /**
     * 市名称
     */
    private String cityName;

    /**
     * 区域ID
     */
    private int countryId;

    /**
     * 区域名称
     */
    private String countryName;

    /**
     * 乡镇/街道 id
     */
    private int countrysideId;

    /**
     * 县镇/街道名称
     */
    private String countrysideName;

    /**
     * 发货详细地址
     */
    private String address;

    /**
     * 发货联系人
     */
    private String contact;

    /**
     * 发货人电话
     */
    private String phone;

    /**
     * 发货人手机
     */
    private String mobile;

    public void setProvinceId(int provinceId) {
      this.provinceId = provinceId;
    }

    public int getProvinceId() {
      return this.provinceId;
    }

    public void setProvinceName(String provinceName) {
      this.provinceName = provinceName;
    }

    public String getProvinceName() {
      return this.provinceName;
    }

    public void setCityId(int cityId) {
      this.cityId = cityId;
    }

    public int getCityId() {
      return this.cityId;
    }

    public void setCityName(String cityName) {
      this.cityName = cityName;
    }

    public String getCityName() {
      return this.cityName;
    }

    public void setCountryId(int countryId) {
      this.countryId = countryId;
    }

    public int getCountryId() {
      return this.countryId;
    }

    public void setCountryName(String countryName) {
      this.countryName = countryName;
    }

    public String getCountryName() {
      return this.countryName;
    }

    public void setCountrysideId(int countrysideId) {
      this.countrysideId = countrysideId;
    }

    public int getCountrysideId() {
      return this.countrysideId;
    }

    public void setCountrysideName(String countrysideName) {
      this.countrysideName = countrysideName;
    }

    public String getCountrysideName() {
      return this.countrysideName;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getAddress() {
      return this.address;
    }

    public void setContact(String contact) {
      this.contact = contact;
    }

    public String getContact() {
      return this.contact;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getPhone() {
      return this.phone;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getMobile() {
      return this.mobile;
    }
  }
}

package com.greatonce.oms.bridge.wms.qimen.request;

import com.greatonce.core.supports.xml.JaxbMapAdapter;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * TOP API: taobao.qimen.orderprocess.report request
 *
 * @author top auto create
 * @since 1.0, 2017.09.19
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsOrderProcessReportRequest implements OmsBaseQimenRequest {


  /**
   * 扩展属性
   */
  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;

  /**
   * 订单信息
   */
  private Order order;

  /**
   * 订单处理信息
   */
  private Process process;

  /**
   * 备注
   */
  private String remark;

  public void setExtendProps(Map<String, String> extendProps) {
    this.extendProps = extendProps;
  }

  public Map<String, String> getExtendProps() {
    return this.extendProps;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Order getOrder() {
    return this.order;
  }

  public void setProcess(Process process) {
    this.process = process;
  }

  public Process getProcess() {
    return this.process;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getRemark() {
    return this.remark;
  }

  @Override
  public String getOwnerCode() {
    return null;
  }

  @Override
  public String getOrderCode() {
    return this.order.getOrderCode();
  }

  @Override
  public String getOutCode() {
    return this.order.getOrderId();
  }

  @Override
  public String getWarehouseCode() {
    return this.order.getWarehouseCode();
  }

  @Override
  public String getOutBizCode() {
    return null;
  }

  /**
   * orderInfo
   *
   * @author top auto create
   * @since 1.0, null
   */

  public static class OrderInfo {

    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("VIPCardNo")
    private String vIPCardNo;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("VIPCode")
    private String vIPCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("VIPMobilePhone")
    private String vIPMobilePhone;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("VIPName")
    private String vIPName;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("change")
    private String change;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("deliveryOrderId")
    private String deliveryOrderId;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("discount")
    private String discount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("logisticsCode")
    private String logisticsCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("operatorCode")
    private String operatorCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("operatorName")
    private String operatorName;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orderCode")
    private String orderCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orderCreateTime")
    private String orderCreateTime;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orderDate")
    private String orderDate;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orderStatus")
    private String orderStatus;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orderType")
    private String orderType;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orgCode")
    private String orgCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("page")
    private String page;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("paidAmount")
    private String paidAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("paymentAccount")
    private String paymentAccount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("paymentType")
    private String paymentType;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("posCode")
    private String posCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("preOrderCode")
    private String preOrderCode;
    /**
     * 备注
     */
    @ApiField("remark")
    private String remark;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("roundingAmount")
    private String roundingAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("saleDate")
    private String saleDate;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("salesPersonCode")
    private String salesPersonCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("salesPersonName")
    private String salesPersonName;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("salesPersonTaobaoNick")
    private String salesPersonTaobaoNick;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("standardAmount")
    private String standardAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("stockInTime")
    private String stockInTime;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("stockOutTime")
    private String stockOutTime;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("storeCode")
    private String storeCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("taobaoStoreCode")
    private String taobaoStoreCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("targetStoreCode")
    private String targetStoreCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalActualAmount")
    private String totalActualAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalActualQty")
    private String totalActualQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalAmount")
    private String totalAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalAmountBeforeDiscount")
    private String totalAmountBeforeDiscount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalPage")
    private String totalPage;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalPlanQty")
    private String totalPlanQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalQty")
    private String totalQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalStockInQty")
    private String totalStockInQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("totalStockOutQty")
    private String totalStockOutQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("warehouseCode")
    private String warehouseCode;


    public String getvIPCardNo() {
      return this.vIPCardNo;
    }

    public void setvIPCardNo(String vIPCardNo) {
      this.vIPCardNo = vIPCardNo;
    }

    public String getvIPCode() {
      return this.vIPCode;
    }

    public void setvIPCode(String vIPCode) {
      this.vIPCode = vIPCode;
    }

    public String getvIPMobilePhone() {
      return this.vIPMobilePhone;
    }

    public void setvIPMobilePhone(String vIPMobilePhone) {
      this.vIPMobilePhone = vIPMobilePhone;
    }

    public String getvIPName() {
      return this.vIPName;
    }

    public void setvIPName(String vIPName) {
      this.vIPName = vIPName;
    }

    public String getChange() {
      return this.change;
    }

    public void setChange(String change) {
      this.change = change;
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

    public String getLogisticsCode() {
      return this.logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
      this.logisticsCode = logisticsCode;
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

    public String getOrderCode() {
      return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
      this.orderCode = orderCode;
    }

    public String getOrderCreateTime() {
      return this.orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
      this.orderCreateTime = orderCreateTime;
    }

    public String getOrderDate() {
      return this.orderDate;
    }

    public void setOrderDate(String orderDate) {
      this.orderDate = orderDate;
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

    public String getOrgCode() {
      return this.orgCode;
    }

    public void setOrgCode(String orgCode) {
      this.orgCode = orgCode;
    }

    public String getPage() {
      return this.page;
    }

    public void setPage(String page) {
      this.page = page;
    }

    public String getPaidAmount() {
      return this.paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
      this.paidAmount = paidAmount;
    }

    public String getPaymentAccount() {
      return this.paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
      this.paymentAccount = paymentAccount;
    }

    public String getPaymentType() {
      return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
      this.paymentType = paymentType;
    }

    public String getPosCode() {
      return this.posCode;
    }

    public void setPosCode(String posCode) {
      this.posCode = posCode;
    }

    public String getPreOrderCode() {
      return this.preOrderCode;
    }

    public void setPreOrderCode(String preOrderCode) {
      this.preOrderCode = preOrderCode;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getRoundingAmount() {
      return this.roundingAmount;
    }

    public void setRoundingAmount(String roundingAmount) {
      this.roundingAmount = roundingAmount;
    }

    public String getSaleDate() {
      return this.saleDate;
    }

    public void setSaleDate(String saleDate) {
      this.saleDate = saleDate;
    }

    public String getSalesPersonCode() {
      return this.salesPersonCode;
    }

    public void setSalesPersonCode(String salesPersonCode) {
      this.salesPersonCode = salesPersonCode;
    }

    public String getSalesPersonName() {
      return this.salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
      this.salesPersonName = salesPersonName;
    }

    public String getSalesPersonTaobaoNick() {
      return this.salesPersonTaobaoNick;
    }

    public void setSalesPersonTaobaoNick(String salesPersonTaobaoNick) {
      this.salesPersonTaobaoNick = salesPersonTaobaoNick;
    }

    public String getStandardAmount() {
      return this.standardAmount;
    }

    public void setStandardAmount(String standardAmount) {
      this.standardAmount = standardAmount;
    }

    public String getStockInTime() {
      return this.stockInTime;
    }

    public void setStockInTime(String stockInTime) {
      this.stockInTime = stockInTime;
    }

    public String getStockOutTime() {
      return this.stockOutTime;
    }

    public void setStockOutTime(String stockOutTime) {
      this.stockOutTime = stockOutTime;
    }

    public String getStoreCode() {
      return this.storeCode;
    }

    public void setStoreCode(String storeCode) {
      this.storeCode = storeCode;
    }

    public String getTaobaoStoreCode() {
      return this.taobaoStoreCode;
    }

    public void setTaobaoStoreCode(String taobaoStoreCode) {
      this.taobaoStoreCode = taobaoStoreCode;
    }

    public String getTargetStoreCode() {
      return this.targetStoreCode;
    }

    public void setTargetStoreCode(String targetStoreCode) {
      this.targetStoreCode = targetStoreCode;
    }

    public String getTotalActualAmount() {
      return this.totalActualAmount;
    }

    public void setTotalActualAmount(String totalActualAmount) {
      this.totalActualAmount = totalActualAmount;
    }

    public String getTotalActualQty() {
      return this.totalActualQty;
    }

    public void setTotalActualQty(String totalActualQty) {
      this.totalActualQty = totalActualQty;
    }

    public String getTotalAmount() {
      return this.totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
      this.totalAmount = totalAmount;
    }

    public String getTotalAmountBeforeDiscount() {
      return this.totalAmountBeforeDiscount;
    }

    public void setTotalAmountBeforeDiscount(String totalAmountBeforeDiscount) {
      this.totalAmountBeforeDiscount = totalAmountBeforeDiscount;
    }

    public String getTotalPage() {
      return this.totalPage;
    }

    public void setTotalPage(String totalPage) {
      this.totalPage = totalPage;
    }

    public String getTotalPlanQty() {
      return this.totalPlanQty;
    }

    public void setTotalPlanQty(String totalPlanQty) {
      this.totalPlanQty = totalPlanQty;
    }

    public String getTotalQty() {
      return this.totalQty;
    }

    public void setTotalQty(String totalQty) {
      this.totalQty = totalQty;
    }

    public String getTotalStockInQty() {
      return this.totalStockInQty;
    }

    public void setTotalStockInQty(String totalStockInQty) {
      this.totalStockInQty = totalStockInQty;
    }

    public String getTotalStockOutQty() {
      return this.totalStockOutQty;
    }

    public void setTotalStockOutQty(String totalStockOutQty) {
      this.totalStockOutQty = totalStockOutQty;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }

  }

  /**
   * batchs
   *
   * @author top auto create
   * @since 1.0, null
   */

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
    /**
     * 备注
     */
    @ApiField("remark")
    private String remark;


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

  /**
   * orderLines
   *
   * @author top auto create
   * @since 1.0, null
   */

  public static class OrderLine {

    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("actualPrice")
    private String actualPrice;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("actualQty")
    private String actualQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("amount")
    private String amount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("batchCode")
    private String batchCode;
    /**
     * batchs
     */
    @ApiListField("batchs")
    @ApiField("batch")
    private List<Batch> batchs;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("color")
    private String color;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("deliveryOrderId")
    private String deliveryOrderId;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("discount")
    private String discount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("discountAmount")
    private String discountAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("discountPrice")
    private String discountPrice;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("exceptionQty")
    private String exceptionQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("expireDate")
    private String expireDate;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("extCode")
    private String extCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("inventoryType")
    private String inventoryType;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("itemCode")
    private String itemCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("itemId")
    private String itemId;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("itemName")
    private String itemName;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("locationCode")
    private String locationCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("moveInLocation")
    private String moveInLocation;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("moveOutLocation")
    private String moveOutLocation;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orderLineNo")
    private String orderLineNo;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("orderSourceCode")
    private String orderSourceCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("outBizCode")
    private String outBizCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("ownerCode")
    private String ownerCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("payNo")
    private String payNo;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("planQty")
    private String planQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("produceCode")
    private String produceCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("productCode")
    private String productCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("productDate")
    private String productDate;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("purchasePrice")
    private String purchasePrice;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("qrCode")
    private String qrCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("quantity")
    private String quantity;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("referencePrice")
    private String referencePrice;
    /**
     * 备注
     */
    @ApiField("remark")
    private String remark;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("retailPrice")
    private String retailPrice;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("settlementAmount")
    private String settlementAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("size")
    private String size;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("skuProperty")
    private String skuProperty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("sourceOrderCode")
    private String sourceOrderCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("standardAmount")
    private String standardAmount;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("standardPrice")
    private String standardPrice;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("status")
    private String status;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("stockInQty")
    private String stockInQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("stockOutQty")
    private String stockOutQty;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("subDeliveryOrderId")
    private String subDeliveryOrderId;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("subSourceCode")
    private String subSourceCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("subSourceOrderCode")
    private String subSourceOrderCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("taobaoItemCode")
    private String taobaoItemCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("warehouseCode")
    private String warehouseCode;


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

    public List<Batch> getBatchs() {
      return this.batchs;
    }

    public void setBatchs(List<Batch> batchs) {
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

  /**
   * 订单信息
   *
   * @author top auto create
   * @since 1.0, null
   */

  public static class Order {

    /**
     * 单据号
     */
    @ApiField("orderCode")
    private String orderCode;
    /**
     * 仓储系统单据号
     */
    @ApiField("orderId")
    private String orderId;
    /**
     * orderInfo
     */
    @ApiField("orderInfo")
    private OrderInfo orderInfo;
    /**
     * orderLines
     */
    @ApiListField("orderLines")
    @ApiField("order_line")
    private List<OrderLine> orderLines;
    /**
     * 单据类型(JYCK=一般交易出库单;HHCK=换货出库;BFCK=补发出库;PTCK=普通出库单;DBCK=调拨出库;B2BRK=B2B入 库;B2BCK=B2B出库;QTCK=其他出库;SCRK=生产入库;LYRK=领用入库;CCRK=残次品入库;CGRK=采购入库;DBRK= 调拨入库;QTRK= 其他入 库;XTRK= 销退入库;HHRK= 换货入库;CNJG= 仓内加工单)
     */
    @ApiField("orderType")
    private String orderType;
    /**
     * 备注
     */
    @ApiField("remark")
    private String remark;
    /**
     * 仓库编码
     */
    @ApiField("warehouseCode")
    private String warehouseCode;
    /**
     * 奇门仓储字段,说明,string(50),,
     */
    @ApiField("warehouseName")
    private String warehouseName;


    public String getOrderCode() {
      return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
      this.orderCode = orderCode;
    }

    public String getOrderId() {
      return this.orderId;
    }

    public void setOrderId(String orderId) {
      this.orderId = orderId;
    }

    public OrderInfo getOrderInfo() {
      return this.orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
      this.orderInfo = orderInfo;
    }

    public List<OrderLine> getOrderLines() {
      return this.orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
      this.orderLines = orderLines;
    }

    public String getOrderType() {
      return this.orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
      return this.warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
      this.warehouseName = warehouseName;
    }

  }

  /**
   * 订单处理信息
   *
   * @author top auto create
   * @since 1.0, null
   */

  public static class Process {

    /**
     * 运单号
     */
    @ApiField("expressCode")
    private String expressCode;
    /**
     * 操作内容
     */
    @ApiField("operateInfo")
    private String operateInfo;
    /**
     * 当前状态操作时间(YYYY-MM-DD HH:MM:SS)
     */
    @ApiField("operateTime")
    private String operateTime;
    /**
     * 当前状态操作员编码
     */
    @ApiField("operatorCode")
    private String operatorCode;
    /**
     * 当前状态操作员姓名
     */
    @ApiField("operatorName")
    private String operatorName;
    /**
     * 单据状态(ACCEPT=仓库接单;PARTFULFILLED-部分收货完成;FULFILLED=收货完成;PRINT = 打印;PICK=捡货;CHECK = 复核 ;PACKAGE= 打包;WEIGH= 称重;READY=待提货;DELIVERED=已发货;REFUSE=买家拒签;EXCEPTION =异常;CLOSED= 关闭;CANCELED= 取 消;REJECT=仓库拒单;SIGN=签收;TMSCANCELED=快递拦截;OTHER=其他;PARTDELIVERED=部分发货完成;TMSCANCELFAILED=快递拦截失败;只传英 文编码)
     */
    @ApiField("processStatus")
    private String processStatus;
    /**
     * 备注
     */
    @ApiField("remark")
    private String remark;


    public String getExpressCode() {
      return this.expressCode;
    }

    public void setExpressCode(String expressCode) {
      this.expressCode = expressCode;
    }

    public String getOperateInfo() {
      return this.operateInfo;
    }

    public void setOperateInfo(String operateInfo) {
      this.operateInfo = operateInfo;
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

    public String getProcessStatus() {
      return this.processStatus;
    }

    public void setProcessStatus(String processStatus) {
      this.processStatus = processStatus;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

  }


}
package com.greatonce.oms.bo.mall;

import com.greatonce.oms.domain.enums.mall.MallGoodsStatus;
import com.greatonce.oms.domain.enums.mall.MallRefundPhase;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;

import java.time.LocalDateTime;

/**
 * MallAfterSalesApplyInfo
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/9
 */
public class MallRefundOrderInfo {

    /**
     * 交易号
     */
    private String tradeId;
    /**
     * 退款号
     */
    private String refundId;
    /**
     * 平台子订单号
     */
    private String oid;
    /**
     * 买家昵称
     */
    private String buyerNick;
    /**
     * 卖家昵称
     */
    private String sellerNick;
    /**
     * 退款申请时间
     */
    private LocalDateTime refundTime;
    /**
     * 更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 原因
     */
    private String reason;
    /**
     * 描述
     */
    private String description;
    /**
     * 标题
     */
    private String title;
    /**
     * 快递名称
     */
    private String expressName;
    /**
     * 快递单号
     */
    private String expressNo;
    /**
     * 支付号
     */
    private String payNo;
    /**
     * 订单状态
     */
    private MallSalesOrderStatus orderStatus;

    /**
     * 售后申请状态
     */
    private MallRefundStatus applyStatus;
    /**
     * 退款阶段
     */
    private MallRefundPhase refundPhase;
    /**
     * 商城商品状态
     */
    private MallGoodsStatus goodsStatus;
    /**
     * 退还金额
     */
    private Double refundAmount;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 是否退货
     */
    private Boolean hasGoodReturn;
    /**
     * 退款版本
     */
    private String version;
    /**
     * 整单退
     */
    private Boolean whole;
    /**
     * 平台退入商品ID
     */
    private String mallProductId;
    /**
     * 平台退入商品名称
     */
    private String mallProductName;
    /**
     * 外部退入商品编码
     */
    private String mallProductOuterCode;
    /**
     * 平台退入Sku信息
     */
    private String mallSkuId;
    /**
     * 平台退入Sku信息
     */
    private String mallSkuName;
    /**
     * 外部退入规格编码
     */
    private String mallSkuOuterSkuCode;
    /**
     * 退入数量
     */
    private Integer quantity;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public LocalDateTime getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(LocalDateTime refundTime) {
        this.refundTime = refundTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public MallSalesOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(MallSalesOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public MallRefundStatus getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(MallRefundStatus applyStatus) {
        this.applyStatus = applyStatus;
    }

    public MallRefundPhase getRefundPhase() {
        return refundPhase;
    }

    public void setRefundPhase(MallRefundPhase refundPhase) {
        this.refundPhase = refundPhase;
    }

    public MallGoodsStatus getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(MallGoodsStatus goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean isHasGoodReturn() {
        return hasGoodReturn;
    }

    public void setHasGoodReturn(Boolean hasGoodReturn) {
        this.hasGoodReturn = hasGoodReturn;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean isWhole() {
        return whole;
    }

    public void setWhole(Boolean whole) {
        this.whole = whole;
    }

    public String getMallProductId() {
        return mallProductId;
    }

    public void setMallProductId(String mallProductId) {
        this.mallProductId = mallProductId;
    }

    public String getMallProductName() {
        return mallProductName;
    }

    public void setMallProductName(String mallProductName) {
        this.mallProductName = mallProductName;
    }

    public String getMallProductOuterCode() {
        return mallProductOuterCode;
    }

    public void setMallProductOuterCode(String mallProductOuterCode) {
        this.mallProductOuterCode = mallProductOuterCode;
    }

    public String getMallSkuId() {
        return mallSkuId;
    }

    public void setMallSkuId(String mallSkuId) {
        this.mallSkuId = mallSkuId;
    }

    public String getMallSkuName() {
        return mallSkuName;
    }

    public void setMallSkuName(String mallSkuName) {
        this.mallSkuName = mallSkuName;
    }

    public String getMallSkuOuterSkuCode() {
        return mallSkuOuterSkuCode;
    }

    public void setMallSkuOuterSkuCode(String mallSkuOuterSkuCode) {
        this.mallSkuOuterSkuCode = mallSkuOuterSkuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}


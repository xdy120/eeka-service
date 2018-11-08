package com.greatonce.oms.bo.mall;

import com.greatonce.oms.domain.enums.trade.InvoiceType;

/**
 * ODS订单发票明细
 *
 * @author daniel
 */
public class MallSalesOrderInvoiceInfo {

    /**
     * 发票抬头
     */
    private String title;
    /**
     * 发票内容
     */
    private String content;
    /**
     * 发票金额
     */
    private Double amount;
    /**
     * 发票类型
     */
    private InvoiceType invoiceType;
    /**
     * 购买方税号
     */
    private String taxpayerId;
    /**
     * 注册地址
     */
    private String GmfAddress;
    /**
     * 注册电话
     */
    private String GmfMobile;
    /**
     * 开户银行
     */
    private String GmfBankName;
    /**
     * 银行账户
     */
    private String GmfBankNo;

    public MallSalesOrderInvoiceInfo(String title, String content, Double amount) {
        this.title = title;
        this.content = content;
        this.amount = amount;
        this.invoiceType = InvoiceType.NORMAL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTaxpayerId() {
        return taxpayerId;
    }

    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    public String getGmfAddress() {
        return GmfAddress;
    }

    public void setGmfAddress(String gmfAddress) {
        GmfAddress = gmfAddress;
    }

    public String getGmfMobile() {
        return GmfMobile;
    }

    public void setGmfMobile(String gmfMobile) {
        GmfMobile = gmfMobile;
    }

    public String getGmfBankName() {
        return GmfBankName;
    }

    public void setGmfBankName(String gmfBankName) {
        GmfBankName = gmfBankName;
    }

    public String getGmfBankNo() {
        return GmfBankNo;
    }

    public void setGmfBankNo(String gmfBankNo) {
        GmfBankNo = gmfBankNo;
    }
}

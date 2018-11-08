package com.greatonce.oms.bo.mall;

import com.greatonce.oms.domain.enums.trade.PayType;

import java.time.LocalDateTime;

/**
 * @author ginta
 */
public class MallSalesOrderPaymentInfo {

    private Double actualAmount;
    private PayType payType;
    private LocalDateTime paidTime;

    public MallSalesOrderPaymentInfo(Double actualAmount, PayType payType, LocalDateTime paidTime) {
        this.actualAmount = actualAmount;
        this.payType = payType;
        this.paidTime = paidTime;
    }

    public Double getActualAmount(){
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount){
        this.actualAmount = actualAmount;
    }

    public PayType getPayType(){
        return payType;
    }

    public void setPayType(PayType payType){
        this.payType = payType;
    }

    public LocalDateTime getPaidTime(){
        return paidTime;
    }

    public void setPaidTime(LocalDateTime paidTime){
        this.paidTime = paidTime;
    }
}

package com.greatonce.oms.message.trade;


/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/20
 */
public class ReturnOrderReviewMessage extends ReturnOrderMessage {


    public ReturnOrderReviewMessage(Long returnOrderId) {
        super(returnOrderId,"review");
    }
}

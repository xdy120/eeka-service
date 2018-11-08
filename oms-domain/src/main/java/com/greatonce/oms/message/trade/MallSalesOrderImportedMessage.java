package com.greatonce.oms.message.trade;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/7/10
 */
public class MallSalesOrderImportedMessage extends MallSalesOrderMessage {

  public MallSalesOrderImportedMessage(Long mallSalesOrderId, Long storeId,
      String tradeId) {
    super(mallSalesOrderId, storeId, tradeId,"imported");
  }
}

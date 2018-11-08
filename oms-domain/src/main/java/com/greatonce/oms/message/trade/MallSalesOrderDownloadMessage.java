package com.greatonce.oms.message.trade;

/**
 * 销售单已下载消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class MallSalesOrderDownloadMessage extends MallSalesOrderMessage {

  public MallSalesOrderDownloadMessage(Long mallSalesOrderId, Long storeId, String tradeId) {
    super(mallSalesOrderId, storeId, tradeId,"download");
  }
}

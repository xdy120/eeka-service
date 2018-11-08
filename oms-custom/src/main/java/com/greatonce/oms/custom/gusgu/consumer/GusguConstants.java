package com.greatonce.oms.custom.gusgu.consumer;

/**
 * 古尚古常量.
 *
 * @author skp
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/9/13
 */
public class GusguConstants {

  /**
   * B2C销售出库、调拨出库
   */
  public static final String QUEUE_DELIVERY_ORDER_PUSH = "oms.gusgu.kingdee.dispatch.order.delivery.push";
  public static final String QUEUE_DELIVERY_ORDER_PUSH_BINDING_KEY = "oms.trade.dispatch.order.delivery";
  public static final String QUEUE_STOCK_OUT_ORDER_PUSH_BINDING_KEY = "oms.stock.out.out";

  /**
   * B2C销售退货
   */
  public static final String QUEUE_RETURN_ORDER_PUSH = "oms.gusgu.kingdee.return.order.audited.push";
  public static final String QUEUE_RETURN_ORDER_PUSH_BINDING_KEY = "oms.trade.return.order.audited";

  /**
   * 商品资料同步
   */
  public static final String QUEUE_PRODUCT_SKU_PUSH = "oms.gusgu.kingdee.product.sku.push";
  public static final String QUEUE_PRODUCT_SKU_PUSH_BINDING_KEY = "oms.product.sku.*";

  /**
   * 收款单保存
   */
  public static final String QUEUE_ALIPAY_RECORD_PUSH = "oms.gusgu.kingdee.alipay.record.push";
  public static final String QUEUE_ALIPAY_RECORD_PUSH_BINDING_KEY = "oms.finance.alipay.record.created";


  /**
   * 采购退入
   */
  public static final String QUEUE_PURCHASE_IN_NOTICE = "omsgusgu.kingdee.purchase.in.notice";
  public static final String QUEUE_PURCHASE_IN_NOTICE_BINDING_KEY = "oms.purchase.order.in";

  /**
   * 采购出库
   */
  public static final String QUEUE_PURCHASE_RETURN_OUT_NOTICE = "oms.gusgu.kingdee.purchase.return.out.notice";
  public static final String QUEUE_PURCHASE_RETURN_OUT_NOTICE_BINDING_KEY = "oms.purchase.return.out";

  /**
   * 会员保存
   */
  public static final String QUEUE_MEMBER_CREATE_NOTICE = "oms.gusgu.kingdee.member.create.notice";
  public static final String QUEUE_MEMBER_CREATE_NOTICE_BINDING_KEY = "oms.marketing.member.created";
}

package com.greatonce.oms.consumer.trade.translator.order;


/**
 * Translatable.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/12
 */
public interface OrderTranslatable {

  /**
   * 转换数据.
   *
   * @param context 上下文
   */
  void translate(OrderTranslatableContext context);

  /**
   * 保存数据.
   */
  void save(OrderTranslatableContext context);

  /**
   * 回滚数据.
   */
  void rollback(OrderTranslatableContext context);
}

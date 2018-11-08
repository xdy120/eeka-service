package com.greatonce.oms.consumer.trade.translator.order;


/**
 * 订单转化模式
 * OrderTranslatableMode
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/12
 */
public enum OrderTranslatableMode {
  /**
   * 新建，首次转化标记为新建
   */
  CREATE,
  /**
   * 修改，后续转化标记为修改
   */
  MODIFY,
  /**
   * 关闭，未交易完成订单关闭
   */
  CLOSE,
  /**
   * 结束，交易完成订单结束
   */
  FINISH,
  /**
   * 错误，如果因为数据不完整或意外异常的标记为ERROR，与ABANDON区别为此部分数据是失败的，需要重新处理
   */
  ERROR,
  /**
   * 放弃，如果因为数据存在或校验不通过的标记为ABANDON，与ERROR区别为此部分数据是正常的，按业务逻辑处理为此结果，不需要重新处理
   */
  ABANDON
}

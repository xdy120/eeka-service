package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * createOrder date:2017/6/19
 * remark:
 */
@Component("orderExpressTranslator")
@TranslatorOrderCondition
public class ExpressTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  static final Pattern EXPRESS_PATTERN = Pattern.compile("\\{(?<name>\\S+?)\\}");
  @Autowired
  private ExpressService expressService;

  @Override
  public void translate(OrderTranslatableContext context) {
    if (context.getMode() == OrderTranslatableMode.CREATE) {
      MallSalesOrderInfo mallsalesorderinfo = context.getMallSalesOrderInfo();
      SalesOrder salesOrder = context.getSalesOrder();
      if (!Assert.isEmpty(mallsalesorderinfo.getSellerMemo())) {
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "匹配卖家留言快递：{}",
            mallsalesorderinfo.getSellerMemo());
        Matcher matcher = EXPRESS_PATTERN.matcher(mallsalesorderinfo.getSellerMemo());
        if (matcher.find()) {
          String name = matcher.group("name");
          LOGGER.info(context.getSerialNo(), context.getStore(),
              context.getMallSalesOrder().getTradeId(), "匹配快递，快递名称：{}", name);
          Express express = expressService.getByName(name);
          if (express != null) {
            salesOrder.setSuggestExpressId(express.getExpressId());
            salesOrder.setSuggestExpressName(express.getExpressName());
          }
        }
      }
    }
  }

  @Override
  public void save(OrderTranslatableContext context) {

  }

  @Override
  public void rollback(OrderTranslatableContext context) {

  }
}

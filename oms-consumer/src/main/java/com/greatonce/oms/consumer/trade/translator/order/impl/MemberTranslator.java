package com.greatonce.oms.consumer.trade.translator.order.impl;


import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.marketing.MemberService;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.SexType;
import com.greatonce.oms.domain.enums.marketing.MemberStatus;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MemberTranslator
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/26
 */
@Component("orderMemberTranslator")
@TranslatorOrderCondition
public class MemberTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  @Autowired
  private MemberService memberService;
  @Autowired
  private MqProducer mqProducer;

  @Override
  public void translate(OrderTranslatableContext context) {
    if (context.getMode() == OrderTranslatableMode.CREATE) {
      MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
      SalesOrder salesOrder = context.getSalesOrder();
      SalesOrderSub orderSub = salesOrder.getSub();
      String name = mallSalesOrderInfo.getBuyerNick();

      Member member;
      synchronized (("MEMBER_TRANSLATE_" + context.getStore().getStoreId() + name).intern()) {
        member = memberService.getByName(context.getStore().getStoreId(), name);
        if (member == null) {
          member = buildCustomer(context, mallSalesOrderInfo, salesOrder, name);
          memberService.create(member);
          orderSub.setNewMember(true);
          mqProducer.send(new GeneralMessage(OmsModule.MARKETING_MEMBER,member.getMemberId(),GeneralMessage.EventType.CREATED));
          LOGGER.info(context.getSerialNo(), context.getStore(),
              context.getMallSalesOrder().getTradeId(), "创建新会员：{}", name);
        }
      }
      orderSub.setMemberId(member.getMemberId());
      orderSub.setMemberName(name);
      salesOrder.setUrgent(Assert.isTrue(member.isUrgent()));
      if (member.getStatus() == MemberStatus.BLACK_LIST) {
        orderSub.setBlacklist(true);
      }
    }
  }

  private Member buildCustomer(OrderTranslatableContext context,
      MallSalesOrderInfo mallSalesOrderInfo, SalesOrder salesOrder, String name) {
    Member member = new Member();
    member.setMemberId(memberService.getIdGenerator().next());
    member.setMemberName(name);
    member.setEmail(mallSalesOrderInfo.getBuyerEmail());
    member.setMobile(mallSalesOrderInfo.getMobile());
    member.setTelephone(mallSalesOrderInfo.getTelephone());
    member.setAddress(mallSalesOrderInfo.getAddress());
    member.setCountryId(salesOrder.getCountryId());
    member.setCountryName(salesOrder.getCountryName());
    member.setProvinceId(salesOrder.getProvinceId());
    member.setProvinceName(salesOrder.getProvinceName());
    member.setCityId(salesOrder.getCityId());
    member.setCityName(salesOrder.getCityName());
    member.setDistrictId(salesOrder.getDistrictId());
    member.setDistrictName(salesOrder.getDistrictName());
    member.setContact(mallSalesOrderInfo.getContact());
    member.setStoreId(context.getStore().getStoreId());
    member.setStoreName(context.getStore().getStoreName());
    member.setStatus(MemberStatus.NORMAL);
    member.setSex(SexType.MALE);
    member.setUrgent(false);
    return member;
  }

  @Override
  public void save(OrderTranslatableContext context) {
  }

  @Override
  public void rollback(OrderTranslatableContext context) {

  }
}

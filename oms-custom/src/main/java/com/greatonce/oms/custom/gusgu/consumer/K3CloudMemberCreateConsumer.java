package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.marketing.MemberService;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.custom.gusgu.GusguUtil;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudCustomerSaveRequest;
import com.greatonce.oms.custom.kingdee.response.K3CloudCustomerSaveResponse;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.message.GeneralMessage;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Shenzhen cca
 * @version 2018/9/20
 */
@Component
@GusguConsumerCondition
@RabbitListener(queues = GusguConstants.QUEUE_MEMBER_CREATE_NOTICE,containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class K3CloudMemberCreateConsumer extends AbstractCustomConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(K3CloudMemberCreateConsumer.class);

  @Autowired
  private K3CloudClient k3CloudClient;
  @Autowired
  private MemberService memberService;
  @Autowired
  private GusguUtil util;

  @RabbitHandler
  void process(Channel channel,GeneralMessage message,@Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    run(message,channel,tag,()->{
      Member member = memberService.getByKey(message.getDataId());
      if (!Assert.isNull(member)) {
        if (Assert.isNull(util.convertToOrgId(member.getStoreId()))){
          LOGGER.info("奇克摩克旗舰店会员信息不推送:{} ",member.getMemberName());
          return;
        }
        K3CloudCustomerSaveRequest request = new K3CloudCustomerSaveRequest();
        K3CloudCustomerSaveRequest.Customer customer = new K3CloudCustomerSaveRequest.Customer();
        customer.setFCreateOrgId(new FNumber(util.convertToOrgId(member.getStoreId())));
        customer.setFUseOrgId(new FNumber(util.convertToOrgId(member.getStoreId())));
        customer.setFNumber(String.valueOf(member.getMemberId()));
        customer.setFName(member.getMemberName());
        request.setCustomer(customer);
        K3CloudCustomerSaveResponse response = k3CloudClient.execute(request);
        if (response.getResult().getStatus().isSuccess()) {
          LOGGER.info("推送会员信息:{} 到金蝶成功",member.getMemberName());
        } else {
          LOGGER.info("推送会员信息到金蝶失败,会员id:{},错误信息:{}",member.getMemberName(),
              JsonUtil.toJson(response.getResult().getStatus().getErrors()));
        }
      }else {
        LOGGER.info("推送会员信息:{} 不存在",member.getDistrictId());
      }
    });
  }

}

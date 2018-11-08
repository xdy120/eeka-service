package com.greatonce.oms.consumer.trade.prerefund;

import com.greatonce.oms.consumer.Application;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/10/27
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "dev")
public class MessageListenerTest {

  @Autowired
  MessageListener messageListener;

  @Test
  public void prerefundTest() {

    Message message = new Message();
    message.setContent(
        "{\"action_type\":\"1\",\"oid\":\"13958613714027520\",\"tid\":\"134886851392253\",\"seller_nick\":\"AA淘宝专卖店\"}");
    message.setUserNick("AA淘宝专卖店");

    MessageStatus messageStatus = new MessageStatus();
//    messageListener.handlePrerefund(message, messageStatus);
  }
}
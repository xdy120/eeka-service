package com.greatonce.oms.consumer.custom.purchase;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.consumer.Application;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.message.GeneralMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Shenzhen cca
 * @version 2018/9/18
 */
@ActiveProfiles("dev")
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class PurchaseReturnOutTest {

  @Autowired
  private MqProducer mqProducer;

  @Test
  public void PurchaseReturnOut() {
    GeneralMessage message = new GeneralMessage(OmsModule.PURCHASE_RETURN, 13920467857572864L,
        GeneralMessage.EventType.OUT);
    mqProducer.send(message);
  }

  @Test
  public void purchaseOrderIN(){
    GeneralMessage message = new GeneralMessage(OmsModule.PURCHASE_ORDER, 180904000494L,
        GeneralMessage.EventType.IN);
    mqProducer.send(message);
  }
}

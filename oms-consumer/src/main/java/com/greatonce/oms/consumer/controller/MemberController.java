package com.greatonce.oms.consumer.controller;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.marketing.MemberService;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.message.GeneralMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shenzhen cca
 * @version 2018/9/21
 */
@RestController
@RequestMapping("/marketing")
public class MemberController {

  @Autowired
  private MemberService memberService;
  @Autowired
  private MqProducer mqProducer;

  @GetMapping("/create")
  public void create(){
    PageList<Member> pageList = memberService.listPage(null, 1, 500);
    if (!Assert.isEmpty(pageList.getData())){
      pageList.getData().parallelStream().forEach(x-> mqProducer.send(
          new GeneralMessage(OmsModule.MARKETING_MEMBER,x.getMemberId(),GeneralMessage.EventType.CREATED)));
      int total = pageList.getTotal();
      while (pageList.getPage()*500< total){
        pageList = memberService.listPage(null, pageList.getPage()+1, 500);
        pageList.getData().parallelStream().forEach(x-> mqProducer.send(
            new GeneralMessage(OmsModule.MARKETING_MEMBER,x.getMemberId(),GeneralMessage.EventType.CREATED)));
      }
    }
  }

}

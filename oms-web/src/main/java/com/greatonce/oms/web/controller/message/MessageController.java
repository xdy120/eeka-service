package com.greatonce.oms.web.controller.message;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.message.MessageService;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.query.message.MessageQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
@CrossOrigin
public class MessageController implements PageListController<Message, MessageQuery> {

  @Autowired
  private MessageService messageService;

  @Override
  public BizService<Message, MessageQuery> getBizService() {
    return messageService;
  }

  @GetMapping
  @Override
  public PageList<Message> listPage(MessageQuery messageQuery, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    messageQuery.setUserId(BizContext.getUserId());
    return messageService.listPage(messageQuery, page, pageSize);
  }

  @GetMapping(path = "/countUnread")
  public int countUnread() {
    return messageService.countUnread(BizContext.getUserId());
  }

  @PutMapping(path = "/{messageId}/markRead")
  public void markRead(@PathVariable("messageId") Long messageId) {
    Message message = messageService.getByKey(messageId);
    if (!message.isRead()) {
      message = new Message();
      message.setMessageId(messageId);
      message.setRead(true);
      messageService.modify(message);
    }
  }
}

package com.greatonce.oms.biz.message;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.query.message.MessageQuery;

/**
 * Message <br/>
 * 消息
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface MessageService extends BizService<Message,MessageQuery>{

  int countUnread(Long userId);
}
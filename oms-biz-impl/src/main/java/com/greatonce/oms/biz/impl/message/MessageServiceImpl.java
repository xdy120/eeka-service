package com.greatonce.oms.biz.impl.message;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.message.MessageService;
import com.greatonce.oms.dao.message.MessageDao;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.query.message.MessageQuery;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Message <br/>
 * 消息
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-01-15
 */
@Service
public class MessageServiceImpl extends AbstractService<Message, MessageQuery> implements
    MessageService {

  @Autowired
  private MessageDao dao;
  @Resource
  private IdGenerator messageIdGenerator;

  @Override
  protected QueryDao<Message, MessageQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return messageIdGenerator;
  }

  @Override
  protected void initDefaultValue(Message entity) {
    super.initDefaultValue(entity);
    entity.setRead(false);
  }

  @Override
  public int countUnread(Long userId) {
    return dao.countUnread(userId);
  }
}
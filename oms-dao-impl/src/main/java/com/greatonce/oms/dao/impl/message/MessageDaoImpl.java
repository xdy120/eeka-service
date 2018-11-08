package com.greatonce.oms.dao.impl.message;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.message.MessageDao;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.query.message.MessageQuery;
import org.springframework.stereotype.Repository;

/**
 * Message <br/>
 * 消息
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class MessageDaoImpl extends AbstractOmsDao<Message, MessageQuery> implements MessageDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.message.MessageMapper";
  }

  @Override
  public int countUnread(Long userId) {
    return getSqlSessionDecorator().selectOne(getStatement("countUnread"), userId);
  }
}
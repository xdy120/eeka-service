package com.greatonce.oms.dao.message;

import com.greatonce.core.database.QueryDao;
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

public interface MessageDao extends QueryDao<Message,MessageQuery> {

  int countUnread(Long userId);
}

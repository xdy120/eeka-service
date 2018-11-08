package com.greatonce.oms.message;

import java.text.MessageFormat;

/**
 * 重新过账消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-08
 */
public class DataRepostingMessage extends Message {

  private final Long dataId;
  private final String key;

  public DataRepostingMessage(Long dataId, String module) {
    this.dataId = dataId;
    this.key = MessageFormat.format("oms.{0}.reposting", module);
  }

  public Long getDataId() {
    return dataId;
  }

  @Override
  public String routingKey() {
    return this.key;
  }
}

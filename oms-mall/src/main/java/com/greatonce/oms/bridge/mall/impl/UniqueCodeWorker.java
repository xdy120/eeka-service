package com.greatonce.oms.bridge.mall.impl;

import org.springframework.stereotype.Component;

/**
 * 请求唯一码.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-27
 */
@Component
public class UniqueCodeWorker {

  private int sequence;
  private int lastTimestamp;
  private static final int sequenceMask = 255;
  private static final int twepoch = 1482403334;

  /**
   * 下一个唯一码.
   */
  public synchronized int next() {
    int timestamp = getTimestamp();
    if (this.lastTimestamp == timestamp) {
      sequence = sequence + 1 & sequenceMask;
      if (sequenceMask == 0) {
        timestamp = getNextTimestamp(lastTimestamp);
      }
    } else {
      sequence = 0;
    }
    this.lastTimestamp = timestamp;
    return (timestamp - twepoch << 8) | sequence;
  }

  private int getNextTimestamp(int lastTimestamp) {
    int timestamp = getTimestamp();
    while (timestamp <= lastTimestamp) {
      timestamp = getTimestamp();
    }
    return timestamp;
  }

  private int getTimestamp() {
    return (int) (System.currentTimeMillis() / 1000);
  }
}

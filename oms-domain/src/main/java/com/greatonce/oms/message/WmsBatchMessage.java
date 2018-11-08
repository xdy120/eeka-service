package com.greatonce.oms.message;

import com.greatonce.oms.domain.enums.OmsModule;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-10-16
 */
public class WmsBatchMessage extends GeneralMessage {

  /**
   * 批次ID
   */
  private Long batchId;

  public WmsBatchMessage(OmsModule module, Long dataId,
      EventType eventType, Long batchId) {
    super(module, dataId, eventType);
    this.batchId = batchId;
  }

  public Long getBatchId() {
    return batchId;
  }
}

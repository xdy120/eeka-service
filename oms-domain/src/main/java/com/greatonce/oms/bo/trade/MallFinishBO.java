package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import java.time.LocalDateTime;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/12
 */
public class MallFinishBO extends VersionBO {

  private LocalDateTime finishTime;

  public LocalDateTime getFinishTime() {
    return finishTime;
  }

  public void setFinishTime(LocalDateTime finishTime) {
    this.finishTime = finishTime;
  }
}

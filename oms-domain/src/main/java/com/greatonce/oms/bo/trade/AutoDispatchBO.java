package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import java.util.Map;

/**
 * @author buer
 * @version 2018-01-06 10:31
 */
public class AutoDispatchBO extends VersionBO {

  Map<Long, Integer> versionMap;

  public Map<Long, Integer> getVersionMap() {
    return versionMap;
  }

  public void setVersionMap(Map<Long, Integer> versionMap) {
    this.versionMap = versionMap;
  }
}

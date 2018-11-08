package com.greatonce.oms.bridge.wms;

/**
 * 仓库异常.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-06-27
 */
public class WmsException extends Exception {

  public WmsException(String message, Throwable ex) {
    super(message, ex);
  }

  public WmsException(String message) {
    super(message);
  }
}

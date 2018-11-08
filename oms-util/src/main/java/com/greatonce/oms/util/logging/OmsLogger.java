package com.greatonce.oms.util.logging;

import com.greatonce.core.util.Assert;
import org.slf4j.Logger;
import org.slf4j.MDC;

/**
 * OmsLogger
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/28
 */
public abstract class OmsLogger {

  /**
   * 获取日志
   */
  protected abstract Logger getLogger();

  private String preProcessMessage(String message) {
    return Assert.isEmpty(message) ? "" : message;
  }

  protected void putMDCItem(String key, Object value) {
    if (!Assert.isNull(key) && !Assert.isNull(value)) {
      MDC.put(key, String.valueOf(value));
    }
  }

  protected void doInfo(String message, Object... args) {
    getLogger().info(preProcessMessage(message), args);
    cleanMDC();
  }

  protected void doWarn(String message, Object... args) {
    getLogger().warn(preProcessMessage(message), args);
    cleanMDC();
  }

  protected void doDebug(String message, Object... args) {
    getLogger().debug(preProcessMessage(message), args);
    cleanMDC();
  }

  protected void doError(String message, Object... args) {
    getLogger().error(preProcessMessage(message), args);
    cleanMDC();
  }

  protected void doError(String message, Throwable throwable) {
    getLogger().error(message, throwable);
    cleanMDC();
  }

  protected void doTrace(String message, Object... args) {
    getLogger().trace(preProcessMessage(message), args);
    cleanMDC();
  }

  public boolean isTraceEnabled() {
    return getLogger().isTraceEnabled();
  }

  public boolean isDebugEnabled() {
    return getLogger().isDebugEnabled();
  }

  public boolean isInfoEnabled() {
    return getLogger().isInfoEnabled();
  }

  public boolean isWarnEnabled() {
    return getLogger().isWarnEnabled();
  }

  public boolean isErrorEnabled() {
    return getLogger().isErrorEnabled();
  }

  protected void cleanMDC() {
  }
}

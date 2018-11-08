package com.greatonce.oms.util;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.Constants;

/**
 * 业务上下文.
 *
 * @author buer
 * @version 2017-12-28 9:55
 */
public final class BizContext {

  private BizContext() {
  }

  private static ThreadLocal<Long> localUserId = new ThreadLocal<>();
  private static ThreadLocal<String> localNickname = new ThreadLocal<>();

  public static void setUserId(Long userId) {
    localUserId.set(userId);
  }

  public static Long getUserId() {
    return localUserId.get();
  }

  public static void setNickname(String userName) {
    localNickname.set(userName);
  }

  public static String getNickname() {
    String userName = localNickname.get();
    return Assert.isEmpty(userName) ? Constants.SYSTEM_OPERATOR : userName;
  }
}

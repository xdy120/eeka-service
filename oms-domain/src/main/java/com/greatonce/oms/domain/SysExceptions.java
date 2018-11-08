package com.greatonce.oms.domain;

/**
 * 系统级异常
 *
 * @author buer
 */
public final class SysExceptions {

  public final static OmsException NO_AUTH = new OmsException(10001, "没有权限");
  public final static OmsException THREAD_ERROR = new OmsException(10002, "线程任务异常");
  public final static OmsException PARAMETER_NOT_ALLOW_EMPTY = new OmsException(10103, "ID不能为空");
  public final static OmsException ENUM_CONVERT_FAILED = new OmsException(10002, "枚举转化异常");
  public final static OmsException DATETIME_FORMATTE_FAILED = new OmsException(10003, "日期格式化错误");
  public final static OmsException VERSION_CHANGED = new OmsException(10101, "单据已变更，请刷新后再试");
  public final static OmsException VERSION_NOT_ALLOW_EMPTY = new OmsException(10102, "单据版本不能为空");
  public final static OmsException PRIMARY_KEY_NOT_ALLOW_EMPTY = new OmsException(10103, "ID不能为空");
  public final static OmsException DATA_NOT_ALLOW_EMPTY = new OmsException(10104, "单据不能为空");
  public final static OmsException DATA_NOT_FOUND = new OmsException(10105, "未找到单据");
  public final static OmsException DATA_INVALID = new OmsException(10106, "单据已作废");
  public final static OmsException DATA_NOT_ALLOW_DELETE = new OmsException(10107, "单据不允许删除！");
  public final static OmsException STATUS_ERROR = new OmsException(10108, "状态错误！");
  public final static OmsException DATA_DISABLED = new OmsException(10109, "数据已禁用！");
  public final static OmsException DATA_DUPLICATION = new OmsException(10109, "已存在相同数据！");

}

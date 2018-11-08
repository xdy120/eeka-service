package com.greatonce.oms.util.logging;

import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.util.BizContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * 业务日志.
 *
 * @author buer
 */
public class BizLogger extends OmsLogger {

  public static final String ADD = "新增";
  public static final String AUDIT = "审核";
  public static final String RESET_AUDIT = "重置审核";
  public static final String REVIEW = "复核";
  public static final String UPDATE = "修改";
  public static final String DELETE = "删除";
  public static final String CANCEL = "取消";
  public static final String DELIVERY = "发货";
  public static final String ENTRY = "入库";
  public static final String DISPATCH = "配货";
  public static final String AUTO_DISPATCH = "自动配货";
  public static final String CANCEL_DISPATCH = "取消配货";
  public static final String MALL_DELIVERY = "回传发货";
  public static final String MALL_RESET_EXPRESS = "回传快递修改";
  public static final String ENABLE = "启用";
  public static final String DISABLE = "禁用";
  public static final String INVALID = "作废";
  public static final String CONFIM = "确认";
  public static final String FINISH = "完结";
  public static final String BEGIN = "开始";
  public static final String END = "结束";
  public static final String NOTICE_WMS = "通知WMS";
  public static final String SIGN = "签收";
  public static final String SCAN = "扫描";

  private static final String BIZ_ID = "bizid";
  private static final String DETAIL_ID = "bizdtl";
  private static final String ACTION = "act";
  private static final String OPER = "oper";
  private final Logger logger;
  private final String module;

  public BizLogger(final OmsModule module) {
    this.module = module.toString();
    this.logger = getLogger(this.module);
  }

  public BizLogger(String module) {
    this.module = module;
    this.logger = getLogger(this.module);
  }

  private Logger getLogger(String type) {
    return LoggerFactory.getLogger("oms.biz.logging." + type.toLowerCase().replace("_", "."));
  }

  public void log(Long id, Long detailId, String action, String message, Object... args) {
    putMDC(id, detailId, action);
    this.doInfo(message, args);
  }

  public void log(Long id, Long detailId, String action) {
    log(id, detailId, action, null);
  }

  public void log(Long id, String action, String message, Object... args) {
    log(id, null, action, message, args);
  }

  public void log(Long id, String action) {
    log(id, action, null);
  }

  public void log(String action, String message, Object... args) {
    log(null, action, message, args);
  }

  protected void putMDC(Long id, Long detailId, String action) {
    putMDCItem(BIZ_ID, id);
    putMDCItem(DETAIL_ID, detailId);
    putMDCItem(OPER, BizContext.getNickname());
    putMDCItem(ACTION, action);
  }

  @Override
  protected Logger getLogger() {
    return this.logger;
  }

  @Override
  protected void cleanMDC() {
    super.cleanMDC();
    MDC.remove(BIZ_ID);
    MDC.remove(DETAIL_ID);
    MDC.remove(ACTION);
    MDC.remove(OPER);
  }
}

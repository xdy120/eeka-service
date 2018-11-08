package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.marketing.PresellStore;
import com.greatonce.oms.query.marketing.PresellQuery;

/**
 * Presell <br/>
 * 预售
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PresellService extends
    com.greatonce.oms.biz.BizService<Presell, PresellQuery> {


  /**
   * 开始.
   */
  void begin(Presell presell, VersionBO bo);

  /**
   * 结束.
   */
  void end(Presell presell, VersionBO bo);

  /**
   * 审核.
   */
  void audit(Presell presell, VersionBO bo);

  /**
   * 失败重试.
   */
  void retry(Presell presell, PresellStore presellStore);

  /**
   * 作废.
   */
  void invalid(Presell presell, VersionBO bo);

  /**
   * 获取预售信息，带预售店铺.
   *
   * @param presellId 预售ID
   * @param storeId 店铺ID
   */
  Integer getStoreRate(Long presellId, Long storeId);
}
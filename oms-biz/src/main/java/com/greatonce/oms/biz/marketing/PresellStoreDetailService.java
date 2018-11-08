package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.marketing.PresellStoreDetail;
import com.greatonce.oms.query.marketing.PresellStoreDetailQuery;
import java.util.List;

/**
 * PresellStoreDetail <br/> 预售店铺明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PresellStoreDetailService extends
    BatchBizService<PresellStoreDetail, PresellStoreDetailQuery> {

  /**
   * 结束
   */
  void end(PresellStoreDetail detail);

  /**
   * 开始
   */
  void begin(PresellStoreDetail detail);

  /**
   * 获取未开始的预售店铺明细
   */
  List<PresellStoreDetail> listNotStart(Long presellId, Long storeId);

  /**
   * 获取已开始的预售店铺明细
   */
  List<PresellStoreDetail> listStarted(Long presellId, Long storeId);

  /**
   * 导出未开始的预售店铺明细
   */
  void exportNotStartByIds(List<Long> notStartDetailIds, String fileName);
}
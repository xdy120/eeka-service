package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.marketing.PresellStore;
import com.greatonce.oms.query.marketing.PresellStoreQuery;

import java.util.List;

/**
 * PresellStore <br/>
 * 预售店铺
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PresellStoreService extends BatchBizService<PresellStore, PresellStoreQuery> {

  /**
   * 获取预售的店铺
   */
  List<PresellStore> listStores(Long presellId);
}
package com.greatonce.oms.biz.finance;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.finance.AlipayRecordDownloadBO;
import com.greatonce.oms.domain.finance.AlipayRecord;
import com.greatonce.oms.query.finance.AlipayRecordQuery;

/**
 * 支付宝账单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface AlipayRecordService extends BizService<AlipayRecord, AlipayRecordQuery> {

  /**
   * 异步下载
   */
  void asyncDownload(AlipayRecordDownloadBO downloadBO);

  /**
   * 同步下载
   */
  void download(AlipayRecordDownloadBO downloadBO);

}
package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.marketing.PresellDetail;
import com.greatonce.oms.query.marketing.PresellDetailQuery;
import com.greatonce.oms.query.marketing.PresellQuery;
import java.util.List;

/**
 * PresellDetail <br/>
 * 预售商品信息
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface PresellDetailService extends DetailService<Presell, PresellDetail, PresellDetailQuery> {


  void exportPresell(String fileName, PresellQuery query);

  List<PresellDetail> listDetails(Long presellId);
}
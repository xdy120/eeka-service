package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.ReturnSign;
import com.greatonce.oms.query.trade.ReturnSignQuery;
import java.util.List;

/**
 * ReturnSign <br/>
 * 退货签收
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ReturnSignService extends BatchBizService<ReturnSign, ReturnSignQuery> {

  void invalid(ReturnSign returnSign,VersionBO bo);

  List<ReturnSign> getExpressSign(String expressNo);

  /**
   * @param expressNo 更新新建的快递 为已拆包
   */
  void confirmUnpack(String expressNo);

  void exportSign(String fileName, ReturnSignQuery query);
}
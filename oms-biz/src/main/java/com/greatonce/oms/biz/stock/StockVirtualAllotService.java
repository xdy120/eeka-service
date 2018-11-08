package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.stock.StockVirtualAllot;
import com.greatonce.oms.query.stock.StockVirtualAllotQuery;

/**
 * StockVirtualAllot <br/> 虚拟调拨
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockVirtualAllotService extends
    BizService<StockVirtualAllot, StockVirtualAllotQuery> {


  void audit(StockVirtualAllot allot, VersionBO bo);

  void invalid(StockVirtualAllot allot, VersionBO bo);

  void exportAllot(Long allotId, String fileName);
}
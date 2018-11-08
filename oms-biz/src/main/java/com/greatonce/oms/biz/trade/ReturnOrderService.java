package com.greatonce.oms.biz.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.CreateReturnNoticeOrderBO;
import com.greatonce.oms.bo.trade.ExchangeOccupancyBO;
import com.greatonce.oms.bo.trade.RelateSourceOrderBO;
import com.greatonce.oms.bo.trade.ReturnOrderExportBO;
import com.greatonce.oms.bo.trade.ReturnOrderSaveBO;
import com.greatonce.oms.bo.trade.ScanExpressBO;
import com.greatonce.oms.bo.trade.SourceOrderFilterBO;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import java.util.List;

/**
 * ReturnOrder <br/> 退换货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ReturnOrderService extends
    com.greatonce.oms.biz.BizService<ReturnOrder, ReturnOrderQuery> {

  /**
   * 审核
   */
  void audit(ReturnOrder returnOrder, VersionBO bo);

  /**
   * 作废
   */
  void invalid(ReturnOrder returnOrder, VersionBO bo);

  /**
   * 复核
   */
  void review(ReturnOrder returnOrder, VersionBO bo);

  /**
   * @return 查询 退换货单 和明细
   */
  ReturnOrder getOrderAndDetail(String tradeId);

  ReturnOrder getOrderAndDetail(Long returnOrderId);

  /**
   * 根据 订单  和  明细的skuCode 找到指定的 退换货单
   */
  ReturnOrder getOrderAndDetailByApplyId(Long salesOrderId, String outSkuCode);

  /**
   * 更新主单 和 明细单
   *
   * @param versionBO 更新主单 和 明细单
   */
  void updateOrderAndDetail(ReturnOrder returnOrder, VersionBO versionBO);

  /**
   * 拆包扫描
   */
  void unpack(ReturnOrder returnOrder);

  void addReturnOrder(ReturnOrderSaveBO returnOrderSaveBO);

  /**
   * 创建通知单
   */
  void createNotice(CreateReturnNoticeOrderBO bo);

  /**
   * 退换货单查询
   */
  List<ReturnOrder> listReturnOrder(CreateReturnNoticeOrderBO bo);


  /**
   * 为无头件添加原单
   */
  RelateSourceOrderBO addSourceOrder(VersionBO<ReturnOrder> returnOrderBO);


  /**
   * 将关联好的 异常单更新
   */
  void updateAbnormal(VersionBO<ReturnOrder> bo);

  /**
   * 将一个退换货单里的多个无头件，拆分为一个退换货单
   */
  void splitUnknownOrder(ReturnOrder returnOrderBO);

  /**
   * 添加换货单 的换出商品
   */
  void addOutDetail(ReturnOrder returnOrder);


  /**
   * @return 找原订单
   */
  ScanExpressBO getSourceOrder(SourceOrderFilterBO filterBO);

  /**
   * 修改退入和换出的金额
   */
  Integer modifyAmount(VersionBO<ReturnOrder> returnOrderVersionBO);

  /**
   * 重新过账.
   */
  void reposting(ReturnOrder returnOrder);

  /**
   * @param returnOrder
   * 检查复核的退换货单 是否存在重扫
   */
  List<ReturnOrderDetail> checkReview(ReturnOrder returnOrder);

  /**
   * 独立换货占用
   *
   * @param stockQuery
   * @param page
   * @param pageSize
   * @return
   */
  PageList<ExchangeOccupancyBO> getStockOccupancyDetail(StockQuery stockQuery, int page, int pageSize);

  /**
   * 导出退换货单
   *
   * @param fileName
   * @param filter
   */
  void exportReturn(String fileName, ReturnOrderQuery filter);


  /**
   * 查询要导出退换货单
   * @param returnOrderFilter
   * @param page
   * @param pageSize
   * @return
   */
  PageList<ReturnOrderExportBO> listExportReturn(ReturnOrderQuery returnOrderFilter, int page,
      int pageSize);
}
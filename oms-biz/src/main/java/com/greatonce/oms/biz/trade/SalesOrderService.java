package com.greatonce.oms.biz.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.DownloadSalesOrderBO;
import com.greatonce.oms.bo.trade.MallDeliveryBO;
import com.greatonce.oms.bo.trade.MallExpressResetBo;
import com.greatonce.oms.bo.trade.MallFinishBO;
import com.greatonce.oms.bo.trade.ManualDispatchBO;
import com.greatonce.oms.bo.trade.ManualDispatchDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderAddDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderBO;
import com.greatonce.oms.bo.trade.SalesOrderCancelDispatchBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailBatchBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailSplitBO;
import com.greatonce.oms.bo.trade.SalesOrderDispatchBO;
import com.greatonce.oms.bo.trade.SalesOrderExpressNoticeBo;
import com.greatonce.oms.bo.trade.SalesOrderHoldBO;
import com.greatonce.oms.bo.trade.SalesOrderImportBO;
import com.greatonce.oms.bo.trade.SalesOrderManualBO;
import com.greatonce.oms.bo.trade.SalesOrderModifyReceiverInfoBO;
import com.greatonce.oms.bo.trade.SalesOrderRemarkBo;
import com.greatonce.oms.bo.trade.SalesOrderReplaceDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderResetDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderWmsDeliveryBO;
import com.greatonce.oms.bo.trade.SuggestExpressBO;
import com.greatonce.oms.bo.trade.SuggestWarehouseBO;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import java.util.List;

/**
 * SalesOrder <br/> 销售订单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesOrderService extends BizService<SalesOrder, SalesOrderQuery> {

  /**
   * 销售单保存方法 共外部调用
   * @param salesOrder
   * @return
   */
  int insertByOther(SalesOrder salesOrder);

  /**
   * 获取订单信息（带附属信息）.
   *
   * @param salesOrderId 订单ID
   */
  SalesOrder getFullInfo(Long salesOrderId);

  /**
   * 获取订单，带明细.
   */
  SalesOrder getDetailInfo(Long salesOrderId);

  /**
   * 获取简单订单信息.
   *
   * @param salesOrderId 订单ID
   */
  SalesOrder getSimpleInfo(Long salesOrderId);

  /**
   * 获取订单用于配货的信息.
   *
   * @param salesOrderId 订单ID
   */
  SalesOrder getDispatchInfo(Long salesOrderId);

  /**
   * 生成合单MD5标记.
   *
   * @param order 订单
   */
  String buildMergeFlag(SalesOrder order);

  /**
   * 根据MD5值获取待配货的订单，包含明细.
   */
  List<SalesOrder> listWaitingDispatchOrder(String mergeMD5);

  /**
   * 获取订单，包含明细.
   */
  List<SalesOrder> listDetail(SalesOrderQuery filter);

  /**
   * 自动创建订单（转化服务）.
   */
  void autoCreate(SalesOrder salesOrder);

  /**
   * 手工创建订单.
   */
  void manualCreate(SalesOrder salesOrder);

  /**
   * 作废.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void invalid(SalesOrder salesOrder, VersionBO version);

  /**
   * 审核.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void audit(SalesOrder salesOrder, SalesOrderBO version);

  /**
   * 下载订单.
   *
   * @param bo 下载对象
   */
  void download(DownloadSalesOrderBO bo);

  /**
   * 留单.
   *
   * @param salesOrder 订单
   * @param salesOrderHoldBO 数据版本
   */
  void hold(SalesOrder salesOrder, SalesOrderHoldBO salesOrderHoldBO);

  /**
   * 留单.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void cancelHold(SalesOrder salesOrder, VersionBO version);

  /**
   * 加急.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void urgent(SalesOrder salesOrder, VersionBO version);

  /**
   * 取消加急.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void cancelUrgent(SalesOrder salesOrder, VersionBO version);

  /**
   * 手工处理.
   *
   * @param salesOrder 订单
   * @param bo 数据版本
   */
  void manual(SalesOrder salesOrder, SalesOrderManualBO bo);

  /**
   * 取消手工处理.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void cancelManual(SalesOrder salesOrder, VersionBO version);

  /**
   * 重置状态.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void reset(SalesOrder salesOrder, VersionBO version);

  /**
   * 重置明细.
   *
   * @param salesOrder 订单
   * @param bo 明细重置对象
   */
  void reset(SalesOrder salesOrder, SalesOrderResetDetailBO bo);

  /**
   * 设置推荐仓库.
   *
   * @param salesOrder 订单
   * @param suggestWarehouseBO 建议仓库对象
   */
  void suggestWarehouse(SalesOrder salesOrder, SuggestWarehouseBO suggestWarehouseBO);

  /**
   * 设置推荐快递.
   *
   * @param salesOrder 订单
   * @param suggestExpressBO 建议快递对象
   */
  void suggestExpress(SalesOrder salesOrder, SuggestExpressBO suggestExpressBO);

  /**
   * 匹配商品.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void matchProduct(SalesOrder salesOrder, VersionBO version);

  /**
   * 添加赠品.
   *
   * @param salesOrder 订单
   * @param salesOrderAddDetailBO 添加赠品对象
   */
  void addGift(SalesOrder salesOrder, SalesOrderAddDetailBO salesOrderAddDetailBO);

  /**
   * 替换赠品 如果原赠品数量为0则只换SKU 如果原赠品数量不为0，则匹配数量，并且替换赠品也使用新的数量，需校验两处数量.
   *
   * @param salesOrder 订单
   * @param replaceBO 替换商品对象
   */
  void replaceGift(SalesOrder salesOrder, SalesOrderReplaceDetailBO replaceBO);

  /**
   * 删除赠品，如果指定了数量则只删除数量相同的赠品.
   *
   * @param salesOrder 订单
   * @param detailBO 删除赠品对象
   */
  void removeGift(SalesOrder salesOrder, SalesOrderDetailBO detailBO);

  /**
   * 修改收货信息.
   *
   * @param salesOrder 订单
   * @param salesOrderModifyReceiverInfoBO 修改收货人信息对象
   */
  void modifyReceiverInfo(SalesOrder salesOrder,
      SalesOrderModifyReceiverInfoBO salesOrderModifyReceiverInfoBO);

  /**
   * 整单退款.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void refund(SalesOrder salesOrder, VersionBO version);

  /**
   * 取消.
   *
   * @param salesOrder 订单
   * @param version 数据版本
   */
  void cancelRefund(SalesOrder salesOrder, VersionBO version);

  /**
   * 添加商品.
   *
   * @param salesOrder 订单
   * @param salesOrderAddDetailBO 添加商品对象
   */
  void addDetail(SalesOrder salesOrder, SalesOrderAddDetailBO salesOrderAddDetailBO);

  /**
   * 替换商品.
   *
   * @param salesOrder 订单
   * @param salesOrderReplaceDetailBO 替换商品对象
   */
  void replaceDetail(SalesOrder salesOrder, SalesOrderReplaceDetailBO salesOrderReplaceDetailBO,
      boolean isBatchReplace);

  /**
   * 作废明细.
   *
   * @param salesOrder 订单
   * @param detailBO 订单明细
   */
  void invalid(SalesOrder salesOrder, SalesOrderDetailBO detailBO);

  /**
   * 明细退款.
   *
   * @param salesOrder 订单
   * @param detailBO 订单明细
   */
  void refund(SalesOrder salesOrder, SalesOrderDetailBO detailBO);

  /**
   * 明细退款.
   *
   * @param salesOrder 订单
   * @param detailBO 订单明细
   */
  void refund(SalesOrder salesOrder, SalesOrderDetailBatchBO detailBO);

  /**
   * 申请退款.
   */
  void applyRefund(SalesOrder salesOrder, SalesOrderDetailBO detailBO);

  /**
   * 申请退款.
   */
  void applyRefund(SalesOrder salesOrder, SalesOrderDetailBatchBO detailBO);

  /**
   * 取消明细退款.
   *
   * @param salesOrder 订单
   * @param detailBO 订单明细
   */
  void cancelRefund(SalesOrder salesOrder, SalesOrderDetailBO detailBO);

  /**
   * 取消明细退款.
   *
   * @param salesOrder 订单
   * @param detailBO 订单明细
   */
  void cancelRefund(SalesOrder salesOrder, SalesOrderDetailBatchBO detailBO);

  /**
   * 校验配货库存.
   *
   * @return 缺货的订单明细ID
   */
  List<ManualDispatchDetailBO> checkDispatchStock(ManualDispatchBO bo);

  /**
   * 商城发货.
   */
  void mallDelivery(SalesOrder salesOrder, MallDeliveryBO bo);

  /**
   * WMS发货.
   */
  void wmsDelivery(SalesOrder salesOrder, SalesOrderWmsDeliveryBO bo);

  /**
   * 订单导入.
   */
  void importSalesOrder(SalesOrderImportBO bo);

  /**
   * 导出.
   *
   * @param name 导出文件名
   */
  void export(String name, SalesOrderQuery filter);

  /**
   * 获取店铺订单.
   *
   * @param storeId 店铺ID
   * @param tradeId 交易号
   */
  SalesOrder getByTradeId(Long storeId, String tradeId);

  /**
   * 商城订单完成.
   */
  void mallFinish(SalesOrder salesOrder, MallFinishBO finishBO);

  /**
   * 获取可以合单的订单ID.
   */
  List<Long> listMergeOrderId(String mergeFlag);


  /**
   * 配货.
   */
  void dispatch(SalesOrder salesOrder, SalesOrderDispatchBO bo);

  /**
   * 取消配货.
   */
  void cancelDispatch(SalesOrder salesOrder, SalesOrderCancelDispatchBO bo);

  /**
   * 复制订单.
   */
  SalesOrder copySalesOrder(Long salesOrderId);

  /**
   * 分页查询库存占用明细.
   */
  PageList<SalesOrder> getStockOccupancyDetail(StockQuery stockQuery, int page, int pageSize);

  /**
   * 自动配货失败修改状态.
   */
  void setDispatchFailingStatus(SalesOrder salesOrder, VersionBO bo);

  /**
   * 自动配货失败处理.
   */
  void autoDispatchOOS(SalesOrder salesOrder, List<SalesOrderDetail> oosDetails);

  /**
   * 不需要退款.
   */
  void noReturnGood(SalesOrder salesOrder, VersionBO<SalesOrderDetail> versionBO);

  /**
   * 需要退货.
   */
  void needReturnGood(SalesOrder salesOrder, VersionBO<SalesOrderDetail> versionBO);

  /**
   * 平台修改快递信息.
   */
  void mallResetExpress(SalesOrder salesOrder, MallExpressResetBo deliveries);

  /**
   * 自动配货.
   */
  void autoDispatch(SalesOrder salesOrder, SalesOrderDispatchBO bo);

  /**
   * 回传平台物流通知.
   */
  void mallExpressNotice(SalesOrder salesOrder, SalesOrderExpressNoticeBo bo);

  /**
   * 修改仓库备注.
   */
  void modifyRemark(SalesOrder salesOrder,
      SalesOrderRemarkBo bo);

  /**
   * 查询缺货.
   */
  List<Long> listOOSRedispatchSalesOrderIds();

  /**
   * 清除缺货标记.
   */
  void clearOos();

  /**
   * 根据es搜索结果，查询订单列表.
   */
  List<SalesOrder> listForBatch(SalesOrderQuery filter);

  /**
   * 根据店铺id和交易号获取订单id.
   */
  Long getSalesOrderIdByStoreIdAndTradeId(Long storeId, String id);

  /**
   * 手工设置明细代发.
   */
  void manualDetailDropShipping(SalesOrder salesOrder, SalesOrderDetailBO bo);

  /**
   * 订单拆分明细.
   */
  void splitDetail(SalesOrder salesOrder, SalesOrderDetail detail, SalesOrderDetailSplitBO bo);

  /**
   * 预付款订单付尾款后更新.
   */
  void finalPaidUpdateOrder(SalesOrder salesOrder);

  /**
   * 订单预退款.
   */
  void prerefund(SalesOrder salesOrder);

  /**
   * 订单取消预退款.
   */
  void cancelPrerefund(SalesOrder salesOrder, VersionBO bo);
}
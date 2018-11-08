package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.vip.VipDispatchBindDeliveryBO;
import com.greatonce.oms.bo.vip.VipDispatchCancelBO;
import com.greatonce.oms.bo.vip.VipDispatchOrderBO;
import com.greatonce.oms.bo.vip.VipPickOrderDownloadBO;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderQueryRequest;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.query.vip.VipDispatchQuery;
import java.util.List;
import java.util.Map;

/**
 * VipDispatch <br/> 唯品配货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipDispatchService extends
    BizService<VipDispatch, VipDispatchQuery> {

  /**
   * 绑定发货单
   */
  void bindDelivery(VipDispatch vipDispatch, VipDispatchBindDeliveryBO bo);

  /**
   * 完结
   */
  void finish(VipDispatch vipDispatch, VersionBO bo);

  /**
   * 匹配异常商品
   */
  void match(VipDispatch dispatch);

  /**
   * 根据发货单id 按配货单 汇总明细 通知数量 出库数量
   */
  List<VipDispatchOrderBO> queryDispatch(Long vipDeliveryId);

  /**
   * 通知wms
   */
  void noticeWms(VipDispatch vipDispatch);

  /**
   * 根据拣货单号获取
   */
  VipDispatch getByCode(String orderCode);

  /**
   * 根据唯品拣货单号获取
   */
  VipDispatch getByPickCode(String pickCode);

  /**
   * 取消
   */
  void cancel(VipDispatch vipDispatch, VipDispatchCancelBO bo);

  /**
   * wms自动发货
   */
  void wmsAutoDelivery(VipDispatch vipDispatch, WmsAutoOutBO bo);

  /**
   * 自动创建拣货单，用于从唯品接口下载 <p> 1、通过调用唯品接口获取拣货单信息 2、匹配商品明细，优先使用铺货关系，没有铺货关系使用SKU
   * 3、获取拣货单对应的销售单，将销售单占用转为拣货单占用（删除销售单占用，增加拣货单占用） <p> 本期不做 4、如果配货仓库对应的实体仓库不支持此品牌，需要拆为多个配货单
   */
  void autoCreate(Store store, List<VipDispatch> orders, List<String> outCodes);

  /**
   * 导出唯品拣货单
   */
  void exportDispatch(String fileName, VipDispatchQuery vipDispatchQuery);

  /**
   * 审核唯品拣货单
   */
  void audit(VipDispatch vipDispatch, VersionBO bo);

  /**
   * 手动下载唯品拣货单
   */
  void automaticDownload(VipPickOrderDownloadBO bo);

  /**
   * 下载唯品拣货单
   */
  void download(Store store, VipPickOrderQueryRequest request,
      StockDispatchStrategy setting, Map<String, String> itemMap);

  /**
   * 重新计算唯品拣货单金额
   */
  void recalculateAmount(VipDispatch vipDispatch, VersionBO bo);

  /**
   * 重新过账.
   */
  void reposting(VipDispatch vipDispatch);

  /**
   * 补货
   */
  VipDispatch replenishment(VipDispatch vipDispatch);
}
package com.greatonce.oms.biz.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipReturnNoticeExportBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bo.vip.VipReturnNoticeCancelBO;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.query.vip.VipReturnNoticeQuery;

/**
 * VipReturnNotice <br/> 唯品退供通知单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipReturnNoticeService extends
    BizService<VipReturnNotice, VipReturnNoticeQuery> {

  /**
   * 通知WMS
   */
  void noticeWms(VipReturnNotice vipReturnNotice);

  /**
   * 根据编码获取
   */
  VipReturnNotice getByCode(String orderCode);

  /**
   * WMS自动入库 1、修改退货通知单明细的入库数量、状态 2、修改退货通知单的入库状态 3、增加对应入库仓库的库存 4、删除唯品配货单占用
   *
   * @param vipReturnNotice 退货入库通知单
   * @param bo 入库BO
   */
  void wmsAutoIn(VipReturnNotice vipReturnNotice, WmsAutoInBO bo);

  /**
   * 取消
   */
  void cancel(VipReturnNotice vipReturnNotice, VipReturnNoticeCancelBO bo);

  /**
   * 重新计算唯品金额
   */
  void recalculateAmount(VipReturnNotice vipReturnNotice, VersionBO bo);

  /**
   * 重新过账.
   */
  void reposting(VipReturnNotice vipReturnNotice);

  /**
   * 导出
   */
  void exportVipReturnNotice(String fileName, VipReturnNoticeQuery query);

  /**
   * 分页查询导出
   */
  PageList<VipReturnNoticeExportBO> listExportVipReturnNotice(VipReturnNoticeQuery query, int page,
      int pageSize);

  void finish(VipReturnNotice notice, VersionBO bo);
}
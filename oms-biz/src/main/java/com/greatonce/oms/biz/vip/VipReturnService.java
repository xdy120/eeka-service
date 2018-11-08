package com.greatonce.oms.biz.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipRefundOrderDownloadBO;
import com.greatonce.oms.bo.vip.VipReturnExportBO;
import com.greatonce.oms.bo.vip.VipReturnScanBO;
import com.greatonce.oms.bo.vip.VipReturnSignBO;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipReturnQueryRequest;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.query.vip.VipReturnQuery;
import vipapis.vreturn.ReturnDeliveryInfo;

/**
 * VipReturn <br/> 唯品退供单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipReturnService extends
    com.greatonce.oms.biz.BizService<VipReturn, VipReturnQuery> {

  /**
   * 签收
   */
  void sign(VipReturn vipReturn, VipReturnSignBO bo);

  /**
   * 扫描
   */
  void scan(VipReturn vipReturn, VipReturnScanBO bo);

  /**
   * 创建通知单
   */
  void createNotice(VipReturn vipReturn, VersionBO bo);

  /**
   * 审核
   */
  void audit(VipReturn vipReturn, VersionBO bo);

  /**
   * 根据唯品退供单号获取OMS退货单
   */
  VipReturn getByOuterCode(String outerCode);

  /**
   * 自动创建 1、状态为已审核
   */
  void autoCreate(VipReturn vipReturn);

  /**
   * 匹配异常
   */
  void match(VipReturn record);


  /**
   * 导出唯品退货单
   */
  void exportReturn(String fileName, VipReturnQuery vipReturnQuery);

  PageList<VipReturnExportBO> exportListVipReturn(VipReturnQuery query, Integer page,
      Integer pageSize);

  /**
   * 退货单重新计算金额
   */
  void recalculateAmount(VipReturn vipReturn, VersionBO bo);

  /**
   * 手动下载唯品退货单
   */
  void automaticDownload(VipRefundOrderDownloadBO bo);

  /**
   * 下载唯品退货单
   */
  void download(VipReturnQueryRequest request, Store store, DataDictItem warehouse);

  /**
   * 封装唯品退货单
   */
  VipReturn parseVipReturn(Store store, DataDictItem warehouse,
      ReturnDeliveryInfo returnDeliveryInfo);

  /**
   * 封装退货单明细
   */
  void parseVipReturnDetail(VipReturn vipReturn, ReturnDeliveryInfo returnDeliveryInfo);
}
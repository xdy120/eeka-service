package com.greatonce.oms.biz.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.ReturnNoticeOrderExportBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.query.trade.ReturnNoticeOrderQuery;

/**
 * ReturnNoticeOrder <br/>
 * 退货通知单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ReturnNoticeOrderService extends BizService<ReturnNoticeOrder, ReturnNoticeOrderQuery> {
    /**
     * @param returnNoticeOrder
     */
    void cancel(ReturnNoticeOrder returnNoticeOrder,VersionBO bo);

    /**
     * @param returnNoticeOrder
     */
    void noticeWms(ReturnNoticeOrder returnNoticeOrder,VersionBO bo);

    /**
     * WMS自动入库
     *
     * 1、修改明细入库数量
     * 2、修改退货通知单状态
     * 3、增加入库仓库的商品库存
     *
     * @param returnNoticeOrder
     * @param wmsAutoInBO
     */
    void wmsAutoIn(ReturnNoticeOrder returnNoticeOrder, WmsAutoInBO wmsAutoInBO);

    /**
     * @param orderCode
     * @return
     */
    ReturnNoticeOrder getByCode(String orderCode);

    void exportNoticeOrder(String fileName, ReturnNoticeOrderQuery query);

    /**
     * 查询要导出退换货通知单
     * @param query
     * @param page
     * @param pageSize
     * @return
     */
    PageList<ReturnNoticeOrderExportBO> listExportReturnNoticeOrder(ReturnNoticeOrderQuery query, int page,
        int pageSize);

  void reposting(ReturnNoticeOrder returnNoticeOrder);

}
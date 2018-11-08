package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.stock.StockVirtualAllotDetail;
import com.greatonce.oms.query.stock.StockVirtualAllotDetailQuery;

import java.util.List;

/**
 * StockVirtualAllotDetail <br/>
 * 虚拟调拨明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

public interface StockVirtualAllotDetailDao extends QueryDao<StockVirtualAllotDetail, StockVirtualAllotDetailQuery> {
    /**
     * 获取可用库存
     *
     * @param stockVirtualAllotId
     * @return
     */
    List<StockVirtualAllotDetail> listAvailable(Long stockVirtualAllotId);

    /**
     * 获取可销库存
     *
     * @param stockVirtualAllotId
     */
    List<StockVirtualAllotDetail> listSaleable(Long stockVirtualAllotId);
}

package com.greatonce.oms.biz.impl.stock;

import com.greatonce.oms.domain.OmsException;

/**
 * @author buer
 * @version 2017-12-26 19:26
 */
public final class StockExceptions {

  public static Integer STOCK_ERROR_CODE = 100001;
  public static OmsException STOCK_MAIN_ID_NOT_ALLOW_EMPTY = new OmsException(STOCK_ERROR_CODE,
      "占用主ID不能为空！");
  public static OmsException STOCK_DETAIL_ID_NOT_ALLOW_EMPTY = new OmsException(STOCK_ERROR_CODE,
      "占用明细ID不能为空！");
  public static OmsException STOCK_WAREHOUSE_ID_NOT_ALLOW_EMPTY = new OmsException(STOCK_ERROR_CODE,
      "仓库ID不能为空！");
  public static OmsException STOCK_SKU_ID_NOT_ALLOW_EMPTY = new OmsException(STOCK_ERROR_CODE,
      "商品规格ID不能为空！");
  public static OmsException STOCK_SKU_CODE_NOT_ALLOW_EMPTY = new OmsException(STOCK_ERROR_CODE,
      "商品规格编码不能为空！");
  public static OmsException STOCK_QUANTITY_NOT_ALLOW_EMPTY = new OmsException(STOCK_ERROR_CODE,
      "商品数量不能为空！");
  public static OmsException STOCK_OCCUPANCY_TYPE_NOT_ALLOW_EMPTY = new OmsException(
      STOCK_ERROR_CODE, "占用类型不能为空！");
}

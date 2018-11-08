package com.greatonce.oms.domain.enums;

import com.greatonce.core.CaptionEnum;

/**
 * 模块枚举.
 *
 * @author coby
 * @author buer
 * @version 3.0
 */
public enum OmsModule implements CaptionEnum {

  OTHER("其他", "other"),
  BASE_REGION("区域", "region"),
  BASE_STORE("店铺", "store"),
  BASE_DATA_DICT("字典", "dict"),
  BASE_USER("用户", "user"),
  BASE_COMPANY("公司", "company"),
  BASE_DEPARTMENT("部门", "department"),
  BASE_ROLE("角色", "role"),
  BASE_PRIVILEGE("权限", "privilege"),
  BASE_ROLE_USER("角色用户", "role.user"),
  BASE_WAREHOUSE("仓库", "warehouse"),
  BASE_VIRTUAL_WAREHOUSE("虚拟仓库", "virtual.warehouse"),
  BASE_EXPRESS("快递", "express"),
  BASE_EXPRESS_WMS_MAPPING("仓库快递映射", "express.wms.mapping"),
  BASE_SETTING("配置", "setting"),
  BASE_SMS_TEMPLATE("短信模板", "sms.template"),
  BASE_SMS_ACCOUNT("短信账号", "sms.account"),
  BASE_STOCK_UPLOAD_STRATEGY("上传策略", "stock.upload.strategy"),
  BASE_STOCK_DISPATCH_STRATEGY("配货策略", "stock.dispatch.strategy"),
  BASE_STORE_DOWNLOAD_CONFIG("下载配置", "store.download.config"),
  BASE_EXPRESS_STRATEGY("快递配置", "express.strategy"),

  //营销计划
  GIFT_STRATEGY("赠品策略", "gift.strategy"),
  MARKETING_ACTIVITY("活动报名", "marketing.activity"),
  MARKETING_PRESELL("预售计划", "marketing.presell"),
  MARKETING_MEMBER("会员", "marketing.member"),
  //采购
  PURCHASE_SUPPLIER("供应商", "purchase.supplier"),
  PURCHASE_ORDER("采购订单", "purchase.order"),
  PURCHASE_NOTICE("采购通知单", "purchase.notice.order"),
  PURCHASE_RETURN("采购退货单", "purchase.return"),
  //商品
  PRODUCT_MALL_MAPPING("铺货关系", "product.mall.mapping"),
  PRODUCT("商品管理", "product"),
  PRODUCT_SKU("商品规格", "product.sku"),
  PRODUCT_CATEGORY("商品分类", "product.category"),

  USER_LOGIN("登陆", "user.login"),

  STOCK("库存", "stock"),
  STOCK_OCCUPANCY("库存占用", "stock.occupancy"),
  STOCK_UPLOAD("库存上传", "stock.upload"),
  STOCK_VIRTUAL_ALLOT("虚拟调拨", "stock.virtual.allot"),
  STOCK_IN("入库单", "stock.in"),
  STOCK_OUT("出库单", "stock.out"),
  STOCK_LOAN_IN("还入单", "stock.loan.in"),
  STOCK_LOAN_OUT("借出单", "stock.loan.out"),
  STOCK_OUT_BATCH("出库批次", "stock.out.batch"),
  STOCK_IN_BATCH("入库批次", "stock.in.batch"),
  //B2C订单

  SALES_ORDER("B2C订单", "trade.sales.order"),
  DISPATCH_ORDER("B2C配货单", "trade.dispatch.order"),
  RETURN_SIGN("快递签收", "trade.return.sign"),
  RETURN_ORDER("退换货单", "trade.return.order"),
  RETURN_NOTICE("退货通知单", "trade.return.notice.order"),
  REFUND_ORDER("退款单", "trade.refund.order"),
  RETURN_APPLY("退款申请", "trade.return.apply"),
  EXCHANGE_APPLY("换货申请", "trade.exchange.apply"),

  //唯品
  VIP_SCHEDULE("唯品档期", "vip.schedule"),
  VIP_SCHEDULE_ADJUST("唯品档期调整单", "vip.schedule.adjust"),
  VIP_DISPATCH("唯品拣货单", "vip.dispatch"),
  VIP_DELIVERY("唯品出仓单", "vip.delivery"),
  VIP_RETURN("唯品退货单", "vip.return"),
  VIP_RETURN_NOTICE("唯品退货通知单", "vip.return.notice"),

  //财务
  ALIPAY_RECORD("支付宝账单", "finance.alipay.record");

  private final String caption;
  private final String path;

  OmsModule(String caption, String path) {
    this.caption = caption;
    this.path = path;
  }

  @Override
  public String caption() {
    return this.caption;
  }

  public String getPath() {
    return path;
  }
}

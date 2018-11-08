package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.Setting;
import com.greatonce.oms.domain.base.setting.*;
import com.greatonce.oms.query.base.SettingQuery;

/**
 * Setting <br/>
 * ????
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-30
 */
public interface SettingService extends BizService<Setting,SettingQuery>{

    OrderAuditStrategy getOrderAuditStrategy(Long settingId);

    SalesOrderSetting getSalesOrderSetting();

    ReturnOrderSetting getReturnOrderSetting();

    StockSetting getInventorySetting();

    ProductSetting getProductSetting();

    IoBillSetting getIoBillSetting();

}
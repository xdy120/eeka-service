package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.util.logging.BizLogger;

/**
 * 单据处理抽象类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */
public abstract class AbstractOrderHandler implements OrderHandler {

  protected void log(BizLogger logger, Long mainId, OmsOrderProcessReportRequest request) {
    logger.log(mainId, "仓库处理", "状态：{},操作人：{}，操作时间：{}",
        QimenConverter.getOrderProcessStatus(request.getProcess().getProcessStatus()),
        request.getProcess().getOperatorName(), request.getProcess().getOperateTime());
  }
}

package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.trade.RefundOrderDetailService;
import com.greatonce.oms.biz.trade.RefundOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.dao.trade.RefundOrderDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.trade.RefundOrderStatus;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.domain.trade.RefundOrderDetail;
import com.greatonce.oms.query.trade.RefundOrderQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退款单. RefundOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class RefundOrderServiceImpl extends
    AbstractVersionService<RefundOrder, RefundOrderQuery> implements RefundOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.REFUND_ORDER);
  private static final Logger LOGGER = LoggerFactory.getLogger(RefundOrderServiceImpl.class);
  @Autowired
  private RefundOrderDao dao;
  @Autowired
  private RefundOrderDetailService refundOrderDetailService;
  @Resource
  private IdGenerator refundOrderIdGenerator;
  @Resource
  private CodeGenerator refundOrderCodeGenerator;


  @Override
  protected QueryDao<RefundOrder, RefundOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.refundOrderIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public int create(RefundOrder record) {
    record.setRefundOrderId(refundOrderIdGenerator.next());
    record.setRefundOrderCode(refundOrderCodeGenerator.next());
    record.setVersion(0);
    record.setStatus(RefundOrderStatus.CREATED);
    Double actual = 0.00;
    Double refundable = 0.00;
    if (record.isCod() == null) {
      record.setCod(false);
    }
    if (record.isQuickRefund() == null) {
      record.setQuickRefund(false);
    }
    if (record.isRefunded() == null) {
      record.setRefunded(false);
    }
    for (RefundOrderDetail detail : record.getDetails()) {
      detail.setRefundOrderId(record.getRefundOrderId());
      actual += detail.getActualAmount();
      refundable += detail.getRefundableAmount();
    }

    record.setActualAmount(actual);
    record.setRefundableAmount(refundable);

    try {
      int result = getTransactionTemplate().executeWithResult(() -> {
        refundOrderDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getRefundOrderId(), BizLogger.ADD);
      return result;
    } catch (Exception e) {
      LOGGER.error("新增退款单失败，退款单：{}", JsonUtil.toJson(record));
      LOGGER.error("新增退款单失败，堆栈信息：", e);
      throw new OmsException("新增退款单失败！");
    }
  }

  @Override
  public int modify(RefundOrder record) {
    int update = super.update(record);
    validateChangedCount(update);
    return update;
  }

  @Override
  public void audit(RefundOrder refundOrder, VersionBO bo) {
    if (refundOrder.getStatus() != RefundOrderStatus.CREATED) {
      throw SysExceptions.STATUS_ERROR;
    }
    refundOrder.setStatus(RefundOrderStatus.AUDITED);
    refundOrder.setVersion(bo.getVersion());
    modify(refundOrder);
    BIZ_LOGGER.log(refundOrder.getRefundOrderId(), BizLogger.AUDIT);
  }

  @Override
  public void invalid(RefundOrder refundOrder, VersionBO bo) {
    if (refundOrder.getStatus() != RefundOrderStatus.CREATED) {
      throw SysExceptions.STATUS_ERROR;
    }
    refundOrder.setStatus(RefundOrderStatus.INVALID);
    refundOrder.setVersion(bo.getVersion());
    modify(refundOrder);
    BIZ_LOGGER.log(refundOrder.getRefundOrderId(), BizLogger.INVALID);
  }

  @Override
  public void review(RefundOrder refundOrder, VersionBO bo) {
    if (refundOrder.getStatus() != RefundOrderStatus.AUDITED) {
      throw SysExceptions.STATUS_ERROR;
    }
    refundOrder.setStatus(RefundOrderStatus.REVIEWED);
    refundOrder.setReviewTime(LocalDateTime.now());
    refundOrder.setVersion(bo.getVersion());
    modify(refundOrder);
    BIZ_LOGGER.log(refundOrder.getRefundOrderId(), BizLogger.REVIEW);

  }
}
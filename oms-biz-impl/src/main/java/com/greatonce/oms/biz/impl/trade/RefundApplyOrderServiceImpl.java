package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.trade.RefundApplyOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.dao.trade.RefundApplyOrderDao;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.query.trade.RefundApplyOrderQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 售后申请单.
 * RefundApplyOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-19
 */

@Service
public class RefundApplyOrderServiceImpl extends
    AbstractService<RefundApplyOrder, RefundApplyOrderQuery> implements RefundApplyOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.RETURN_APPLY);

  @Autowired
  RefundApplyOrderDao dao;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;


  @Override
  protected QueryDao<RefundApplyOrder, RefundApplyOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public RefundApplyOrder getByMallRefundId(Long storeId, String mallRefundId) {
    RefundApplyOrder eg = new RefundApplyOrder();
    eg.setStoreId(storeId);
    eg.setMallRefundId(mallRefundId);
    return getByExample(eg);
  }

  @Override
  public List<RefundApplyOrder> getRefundApplyOrderByExpressNo(String expressNo) {
    RefundApplyOrder applyOrder = new RefundApplyOrder();
    applyOrder.setExpressNo(expressNo);
    return dao.listExample(applyOrder);
  }

  @Override
  public void matchAbnormalApply(RefundApplyOrder refundApplyOrder) {
    RefundApplyOrder applyOrder = new RefundApplyOrder();
    applyOrder.setRefundApplyOrderId(refundApplyOrder.getRefundApplyOrderId());
    //退款申请单没有会员,就认为这个退款单没有原单
    if (Assert.isNull(refundApplyOrder.getMemberId())){
      SalesOrder salesOrder = salesOrderService
          .getByTradeId(refundApplyOrder.getStoreId(), refundApplyOrder.getTradeId());
      if (!Assert.isNull(salesOrder)){
        //会员的信息
        applyOrder.setMemberId(salesOrder.getSub().getMemberId());
        applyOrder.setMemberName(salesOrder.getSub().getMemberName());
        List<SalesOrderDetail> salesOrderDetails = salesOrderDetailService.listByMallDetailId(salesOrder.getSalesOrderId(), refundApplyOrder.getMallDetailId());

        if (!refundApplyOrder.isWhole() && !Assert.isNull(refundApplyOrder.getMallDetailId())) {
          if (salesOrderDetails.size() == 1) {
            SalesOrderDetail detail = salesOrderDetails.get(0);
            applyOrder.setInProductId(detail.getProductId());
            applyOrder.setInProductCode(detail.getProductCode());
            applyOrder.setInProductName(detail.getProductName());
            applyOrder.setInSkuCode(detail.getSkuCode());
            applyOrder.setInSkuId(detail.getSkuId());
            applyOrder.setInSkuName(detail.getSkuName());
          } else if (salesOrderDetails.size() > 1) {
            //如果是组合套装 , 那就取原始明细作为 退款申请单的退入信息   (原始明细就是组合套装)
            List<SalesOrderDetail> normalDetail = salesOrderDetails.stream()
                .filter(x -> SalesOrderDetailType.ORIGINAL == x.getSalesOrderDetailType())
                .collect(Collectors.toList());
            if (!Assert.isEmpty(normalDetail)) {
              SalesOrderDetail detail = normalDetail.get(0);
              applyOrder.setInProductId(detail.getProductId());
              applyOrder.setInProductCode(detail.getProductCode());
              applyOrder.setInProductName(detail.getProductName());
              applyOrder.setInSkuCode(detail.getSkuCode());
              applyOrder.setInSkuId(detail.getSkuId());
              applyOrder.setInSkuName(detail.getSkuName());
            }
          }
        }
      }
    }
    modify(applyOrder);
  }


}
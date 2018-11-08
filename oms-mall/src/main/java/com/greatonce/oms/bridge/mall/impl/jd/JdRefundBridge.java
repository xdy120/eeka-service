package com.greatonce.oms.bridge.mall.impl.jd;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.bridge.mall.impl.AbstractRefundBridge;
import com.greatonce.oms.bridge.mall.request.RefundAuditRequest;
import com.greatonce.oms.bridge.mall.request.RefundGetRequest;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.bridge.mall.response.RefundAuditResponse;
import com.greatonce.oms.bridge.mall.response.RefundGetResponse;
import com.greatonce.oms.bridge.mall.response.RefundQueryResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundPhase;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.jd.open.api.sdk.domain.refundapply.RefundapplySaf.QueryMap;
import com.jd.open.api.sdk.request.refundapply.PopAfsRefundapplyQuerybyidRequest;
import com.jd.open.api.sdk.request.refundapply.PopAfsRefundapplyQuerylistRequest;
import com.jd.open.api.sdk.request.refundapply.PopAfsSoaRefundapplyReplyRefundRequest;
import com.jd.open.api.sdk.response.refundapply.PopAfsRefundapplyQuerybyidResponse;
import com.jd.open.api.sdk.response.refundapply.PopAfsRefundapplyQuerylistResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CREATED by sunying on 2017/6/12.
 */
@Component
public class JdRefundBridge extends AbstractRefundBridge {

  static final int PAGE_SIZE = 100;
  @Autowired
  private JdMall mall;

  private static final Logger LOGGER = LoggerFactory.getLogger(JdRefundBridge.class);

  private static final Map<String, MallRefundStatus> OMS_REFUND_STATUS = new HashMap<>(6);

  static {
// 0:待审核，1：商家审核通过，2：商家审核不通过，3：财务审核通过，4：财务审核不通过，5：人工审核通过。不传是查询全部状态
    OMS_REFUND_STATUS.put("0", MallRefundStatus.WAIT_SELLER_AGREE);
    OMS_REFUND_STATUS.put("1", MallRefundStatus.WAIT_BUYER_RETURN_GOODS);
    OMS_REFUND_STATUS.put("2", MallRefundStatus.SELLER_REFUSE_BUYER);
    OMS_REFUND_STATUS.put("3", MallRefundStatus.SUCCESS);
    OMS_REFUND_STATUS.put("4", MallRefundStatus.CLOSED);
    OMS_REFUND_STATUS.put("5", MallRefundStatus.WAIT_BUYER_RETURN_GOODS);
//    default :  MallRefundStatus.UNKNOWN
  }


  @Override
  protected RefundAuditResponse doAudit(RefundAuditRequest request) {
    try {
      RefundApplyOrder refundApplyOrder = request.getRefundApplyOrder();
      PopAfsSoaRefundapplyReplyRefundRequest refundRequest = new PopAfsSoaRefundapplyReplyRefundRequest();
      refundRequest.setCheckUserName(request.getOperator());
      refundRequest.setId(Long.parseLong(refundApplyOrder.getMallRefundId()));
      refundRequest.setStatus(1L);
      refundRequest.setRemark(request.getReason());
      mall.call(request.getStore(), refundRequest, false);
      return new RefundAuditResponse(request);
    } catch (Exception e) {
      return new RefundAuditResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected RefundQueryResponse doQueryRefund(RefundQueryRequest request) {
    try {
      RefundQueryResponse response = new RefundQueryResponse(request);
      PopAfsRefundapplyQuerylistRequest JDrequest = new PopAfsRefundapplyQuerylistRequest();
      JDrequest.setApplyTimeStart(DateTimeUtil.format(request.getBeginTime()));
      JDrequest.setApplyTimeEnd(DateTimeUtil.format(request.getEndTime()));
      JDrequest.setPageSize(PAGE_SIZE);
      JDrequest.setPageIndex(request.getPage());
      PopAfsRefundapplyQuerylistResponse JDresponse = mall
          .call(request.getStore(), JDrequest, true);
      if (JDresponse != null && JDresponse.getRefundApplyResponse() != null
          && JDresponse.getRefundApplyResponse().getResults() != null && !JDresponse
          .getRefundApplyResponse().getResults().isEmpty()) {
        long count = JDresponse.getRefundApplyResponse().getCount();
        long totalPage = (count % PAGE_SIZE) == 0 ? count / PAGE_SIZE : count / PAGE_SIZE + 1;
        if (count > 0) {
          response.setHasNext(request.getPage() < totalPage);
          List<MallRefundOrderInfo> refundOrderList = new ArrayList<>(100);
          for (QueryMap queryMap : JDresponse.getRefundApplyResponse().getResults()) {
            refundOrderList.add(convertRefundOrder(queryMap, request.getStore()));
          }
          response.setOrders(refundOrderList);
        }
      }
      return response;
    } catch (Exception e) {
      LOGGER.info("jd退款单下载失败：", e);
      return new RefundQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected RefundGetResponse doGetRefund(RefundGetRequest request) {
    try {
      PopAfsRefundapplyQuerybyidRequest JDrequest = new PopAfsRefundapplyQuerybyidRequest();
      JDrequest.setRaId(Long.parseLong(request.getRefundId()));
      PopAfsRefundapplyQuerybyidResponse JDresponse = mall
          .call(request.getStore(), JDrequest, false);
      QueryMap queryMap = JDresponse.getRefundapplyResponse().getResults().get(0);
      return new RefundGetResponse(request, convertRefundOrder(queryMap, request.getStore()));
    } catch (Exception e) {
      return new RefundGetResponse(request, false, e.getMessage());
    }
  }

  private MallRefundOrderInfo convertRefundOrder(QueryMap queryMap, Store store) {
    MallRefundOrderInfo refundOrder = new MallRefundOrderInfo();
    refundOrder.setRefundId(queryMap.getId());
    refundOrder.setBuyerNick(queryMap.getBuyerName());
    //创建时间
    refundOrder.setCreatedTime(Assert.isNull(queryMap.getApplyTime()) ? LocalDateTime.now() : DateTimeUtil.parserLocalDateTime(queryMap.getApplyTime()));
    //退款时间
    refundOrder.setRefundTime(Assert.isNull(queryMap.getCheckTime()) ? LocalDateTime.now() : DateTimeUtil.parserLocalDateTime(queryMap.getCheckTime()));
    //修改时间
    refundOrder.setModifiedTime(Assert.isNull(queryMap.getApplyTime()) ? LocalDateTime.now() : DateTimeUtil.parserLocalDateTime(queryMap.getApplyTime()));
    refundOrder.setRefundAmount(Double.valueOf(queryMap.getApplyRefundSum()) / 100);//申请退款金额
    refundOrder.setApplyStatus(
        OMS_REFUND_STATUS.getOrDefault(queryMap.getStatus(), MallRefundStatus.UNKNOWN));
    refundOrder.setTradeId(queryMap.getOrderId());
    refundOrder.setRefundPhase(MallRefundPhase.ON_SALE);
    refundOrder.setVersion("0");
    refundOrder.setHasGoodReturn(false);
    refundOrder.setSellerNick(store.getStoreName());
    refundOrder.setWhole(true);
    return refundOrder;
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.JD};
  }
}

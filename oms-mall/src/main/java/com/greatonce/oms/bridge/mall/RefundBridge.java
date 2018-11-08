package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.bridge.mall.request.RefundAgreeRequest;
import com.greatonce.oms.bridge.mall.request.RefundAuditRequest;
import com.greatonce.oms.bridge.mall.request.RefundGetRequest;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.bridge.mall.response.RefundAgreeResponse;
import com.greatonce.oms.bridge.mall.response.RefundAuditResponse;
import com.greatonce.oms.bridge.mall.response.RefundGetResponse;
import com.greatonce.oms.bridge.mall.response.RefundQueryResponse;

/**
 * 售后申请.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public interface RefundBridge extends MallBridge {

  RefundQueryResponse queryRefund(RefundQueryRequest request);

  RefundGetResponse getRefund(RefundGetRequest request);

  RefundAuditResponse audit(RefundAuditRequest request);

  RefundAgreeResponse agree(RefundAgreeRequest request);
}

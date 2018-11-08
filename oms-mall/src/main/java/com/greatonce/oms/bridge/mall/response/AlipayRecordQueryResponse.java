package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.AlipayRecordQueryRequest;
import com.greatonce.oms.domain.finance.AlipayRecord;
import java.util.List;

/**
 * 支付宝账单查询响应
 */
public class AlipayRecordQueryResponse extends MallResponse<AlipayRecordQueryRequest> {

  private boolean hasNext;
  private int total;
  private List<AlipayRecord> alipayRecords;

  public AlipayRecordQueryResponse(AlipayRecordQueryRequest request) {
    super(request);
  }

  public AlipayRecordQueryResponse(AlipayRecordQueryRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public List<AlipayRecord> getAlipayRecords() {
    return alipayRecords;
  }

  public void setAlipayRecords(List<AlipayRecord> alipayRecords) {
    this.alipayRecords = alipayRecords;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

}

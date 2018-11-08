package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.mall.impl.AbstractBridge;
import com.greatonce.oms.bridge.mall.impl.taobao.AlipayRecordAnalysis.TradeIdRule;
import com.greatonce.oms.bridge.mall.request.AlipayRecordQueryRequest;
import com.greatonce.oms.bridge.mall.response.AlipayRecordQueryResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.finance.AlipayRecordType;
import com.greatonce.oms.domain.finance.AlipayRecord;
import com.taobao.api.request.AlipayUserAccountreportGetRequest;
import com.taobao.api.response.AlipayUserAccountreportGetResponse;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlipayRecordBridge extends AbstractBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlipayRecordBridge.class);
  private static String FIELDS = "account_id,account_code,account_name,account_type,related_order,gmt_create,gmt_modified,status";
  private static long PAGE_SIZE = 40;
  @Autowired
  private TaobaoMall mall;
  @Resource
  private AlipayRecordAnalysis alipayRecordAnalysis;

  public AlipayRecordQueryResponse queryAlipayRecordByApi(AlipayRecordQueryRequest req) {
    AlipayRecordQueryResponse resp = new AlipayRecordQueryResponse(req);

    AlipayUserAccountreportGetRequest request = new AlipayUserAccountreportGetRequest();
    request.setFields(FIELDS);
    request.setStartTime(DateTimeUtil.localDateTimeToDate(req.getBeginTime()));
    request.setEndTime(DateTimeUtil.localDateTimeToDate(req.getEndTime()));
    request.setPageNo((long) req.getPage());
    request.setPageSize(PAGE_SIZE);
    AlipayUserAccountreportGetResponse response = mall.call(req.getStore(), request);

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("查询支付宝账单接口。响应结果：{}；当前页数：{}；查询时间段：{} - {}",
          JsonUtil.toJson(response), request.getPageNo(),
          request.getStartTime(), request.getEndTime());
    }
    if (response.isSuccess() && !Assert.isEmpty(response.getAlipayRecords())) {
      ArrayList<AlipayRecord> list = new ArrayList<>();
      List<com.taobao.api.domain.AlipayRecord> alipayRecords = response.getAlipayRecords();
      for (com.taobao.api.domain.AlipayRecord alipayRecord : alipayRecords) {
        AlipayRecord item = analysisRecord(alipayRecord, req.getStore());
        list.add(item);
      }
      resp.setAlipayRecords(list);
    } else {
      resp.setResult(response.getSubMsg());
    }
    resp.setSuccess(response.isSuccess());
    resp.setHasNext(response.getTotalPages() > request.getPageNo());
    resp.setTotal(Math.toIntExact(response.getTotalResults()));
    return resp;
  }

  private AlipayRecord analysisRecord(com.taobao.api.domain.AlipayRecord alipayRecord, Store store) {
    AlipayRecord record = new AlipayRecord();
    record.setAlipayOrderNo(alipayRecord.getAlipayOrderNo());
    record.setBusinessType(alipayRecord.getBusinessType());
    record.setAlipayCreatedTime(DateTimeUtil.parserLocalDateTime(alipayRecord.getCreateTime()));
    record.setMemo(alipayRecord.getMemo());
    record.setMerchantOrderNo(alipayRecord.getMerchantOrderNo());
    record.setOptUserId(alipayRecord.getOptUserId());
    record.setSelfUserId(alipayRecord.getSelfUserId());
    record.setType(toAlipayRecordType(alipayRecord.getType()));
    record.setTypeDesc(record.getType().caption());
    if (!Assert.isEmpty(alipayRecord.getBalance())) {
      record.setBalance(Double.valueOf(alipayRecord.getBalance()));
    }
    if (!Assert.isEmpty(alipayRecord.getInAmount())) {
      record.setInAmount(Double.valueOf(alipayRecord.getInAmount()));
    }
    if (!Assert.isEmpty(alipayRecord.getOutAmount())) {
      record.setOutAmount(Double.valueOf(alipayRecord.getOutAmount()));
    }
    record.setStoreId(store.getStoreId());
    record.setStoreName(store.getStoreName());

    // 解析规则。不包含对账
    if (!Assert.isEmpty(record.getMemo())) {
      Matcher matcher = alipayRecordAnalysis.getTypeRulePattern().matcher(record.getMemo());
      if (matcher.find()) {
        record.setBusinessTypeDesc(matcher.group());
      }
    }
    TradeIdRule rule = alipayRecordAnalysis.getTradeIdRules()
        .get(Assert.isEmpty(record.getBusinessTypeDesc()) ? "" : record.getBusinessTypeDesc());
    if (rule != null && rule.getRulePattern() != null) {
      if (!Assert.isEmpty(rule.getTarget())) {
        record.setBusinessTypeDesc(rule.getTarget());
      }

      PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(record.getClass(),
          Assert.isNull(rule.getValueField()) ? "merchantOrderNo" : rule.getValueField());
      try {
        Object value = propertyDescriptor.getReadMethod().invoke(record, new Object[]{});
        Matcher matcher = rule.getRulePattern().matcher(String.valueOf(value));
        if (matcher.find()) {
          record.setTradeId(matcher.group());
        }
      } catch (Exception e) {
        LOGGER.error("record调用getter方法出错。堆栈信息：{}", e);
      }
    }
    return record;
  }

  private AlipayRecordType toAlipayRecordType(String type) {
    if (!Assert.isEmpty(type)) {
      for (AlipayRecordType alipayRecordType : AlipayRecordType.values()) {
        if (alipayRecordType.toString().equalsIgnoreCase(type)) {
          return alipayRecordType;
        }
      }
    }
    return null;
  }

}

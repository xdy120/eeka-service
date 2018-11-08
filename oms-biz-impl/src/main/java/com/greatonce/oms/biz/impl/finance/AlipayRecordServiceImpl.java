package com.greatonce.oms.biz.impl.finance;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.finance.AlipayRecordService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.bo.finance.AlipayRecordDownloadBO;
import com.greatonce.oms.bridge.mall.impl.taobao.AlipayRecordBridge;
import com.greatonce.oms.bridge.mall.request.AlipayRecordQueryRequest;
import com.greatonce.oms.bridge.mall.response.AlipayRecordQueryResponse;
import com.greatonce.oms.dao.finance.AlipayRecordDao;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.finance.AlipayRecord;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.query.finance.AlipayRecordQuery;
import com.greatonce.oms.util.BizContext;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 支付宝账单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-12
 */
@Service
public class AlipayRecordServiceImpl extends
    AbstractService<AlipayRecord, AlipayRecordQuery> implements AlipayRecordService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlipayRecordServiceImpl.class);
  @Autowired
  private AlipayRecordDao dao;
  @Autowired
  private StoreService storeService;
  @Resource
  private AlipayRecordBridge alipayRecordBridge;

  @Override
  protected QueryDao<AlipayRecord, AlipayRecordQuery> getDAO() {
    return this.dao;
  }

  @Override
  @Async("bizExecutor")
  public void asyncDownload(AlipayRecordDownloadBO downloadBO) {
    BizContext.setNickname(downloadBO.getOperator());
    download(downloadBO);
  }

  @Override
  public void download(AlipayRecordDownloadBO downloadBO) {
    Assert.notNull(downloadBO.getStoreId(), "店铺id为空");
    Assert.notNull(downloadBO.getBeginTime(), "开始时间为空");
    Assert.notNull(downloadBO.getEndTime(), "结束时间为空");
    if (Assert.isNull(downloadBO.getStore())) {
      Store store = storeService.getByKey(downloadBO.getStoreId());
      downloadBO.setStore(store);
    }

    AlipayRecordQueryRequest req = new AlipayRecordQueryRequest(downloadBO.getStore());
    req.setBeginTime(downloadBO.getBeginTime());
    req.setEndTime(downloadBO.getEndTime());

    downloadInBatch(req);
  }

  private void downloadInBatch(AlipayRecordQueryRequest req) {
    AlipayRecordQueryResponse resp = alipayRecordBridge.queryAlipayRecordByApi(req);
    LOGGER.info("【{}】下载支付宝账单，{}~{}第{}页共{}条订单，下一页：{}...",
        req.getStore().getStoreName(), req.getBeginTime(), req.getEndTime(), req.getPage(),
        resp.getTotal(), resp.isHasNext());
    saveAlipayRecord(req, resp);
    if (resp.isHasNext()) {
      req.setPage(req.getPage() + 1);
      downloadInBatch(req);
    }
  }

  private void saveAlipayRecord(AlipayRecordQueryRequest req, AlipayRecordQueryResponse resp) {
    if (resp.isSuccess() && !Assert.isEmpty(resp.getAlipayRecords())) {
      resp.getAlipayRecords().forEach(this::initDefaultValue);
      insertBatch(resp.getAlipayRecords());
      List<GeneralMessage> messages = resp.getAlipayRecords().stream()
          .map(x -> new GeneralMessage(OmsModule.ALIPAY_RECORD, x.getAlipayRecordId(),
              EventType.CREATED))
          .collect(Collectors.toList());
      getMqProducer().send(messages);
    } else {
      LOGGER.info("店铺【{}】下载支付宝账单第{}页失败。下一页：{}；错误信息：{}",
          resp.isHasNext(), req.getStore().getStoreName(), req.getPage(), resp.getResult());
    }
  }

}
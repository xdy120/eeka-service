package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.trade.ReturnSignService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.report.SalesOrderOosBO;
import com.greatonce.oms.dao.trade.ReturnSignDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.trade.ReturnSignStatus;
import com.greatonce.oms.domain.trade.ReturnSign;
import com.greatonce.oms.query.trade.ReturnSignQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 退货签收. ReturnSign <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ReturnSignServiceImpl extends
    AbstractVersionService<ReturnSign, ReturnSignQuery> implements
    ReturnSignService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.RETURN_SIGN);
  private static final Logger LOGGER = LoggerFactory.getLogger(ReturnSignServiceImpl.class);
  @Autowired
  private ReturnSignDao dao;
  @Resource
  private IdGenerator returnSignIdGenerator;
  @Autowired
  private MessageExporter messageExporter;

  @Override
  protected QueryDao<ReturnSign, ReturnSignQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return returnSignIdGenerator;
  }

  @Override
  protected void initDefaultValue(ReturnSign entity) {
    super.initDefaultValue(entity);
    entity.setStatus(ReturnSignStatus.CREATED);
    if (Assert.isNull(entity.getCreator())) {
      entity.setCreator(BizContext.getNickname());
    }
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public void invalid(ReturnSign returnSign, VersionBO bo) {
    if (returnSign.getStatus() == ReturnSignStatus.INVALID) {
      throw new OmsException("已作废的不可再作废");
    }
    returnSign.setStatus(ReturnSignStatus.INVALID);
    returnSign.setVersion(bo.getVersion());
    int update = update(returnSign);
    BIZ_LOGGER.log(returnSign.getReturnSignId(), BizLogger.INVALID);
    if (update == 0) {
      throw SysExceptions.VERSION_CHANGED;
    }
  }

  /**
   * 根据快递的单号获得 签收的快递.
   *
   * @param expressNo 快递编号
   */
  @Override
  public List<ReturnSign> getExpressSign(String expressNo) {
    ReturnSign returnSign = new ReturnSign();
    returnSign.setStatus(ReturnSignStatus.UNPACK);
    returnSign.setExpressNo(expressNo);
    return dao.listExample(returnSign);
  }

  @Override
  public void confirmUnpack(String expressNo) {
    ReturnSign sign = new ReturnSign();
    sign.setExpressNo(expressNo);
    sign.setStatus(ReturnSignStatus.CREATED);
    ReturnSign returnSign = dao.getByExample(sign);
    if (returnSign != null) {
      returnSign.setAuditor(BizContext.getNickname());
      returnSign.setAuditedTime(LocalDateTime.now());
      returnSign.setStatus(ReturnSignStatus.UNPACK);
      dao.update(returnSign);
      BIZ_LOGGER.log(returnSign.getReturnSignId(), BizLogger.SIGN);
    }
  }

  @Override
  public void exportSign(String fileName, ReturnSignQuery query) {
    ExcelHeaderCollection<ReturnSign> headers = new ExcelHeaderCollection<>();
    headers.add("快递单号", ReturnSign::getExpressNo);
    headers.add("快递公司", ReturnSign::getExpressName);
    headers.add("签收人", ReturnSign::getAuditor);
    headers.add("签收时间", x -> FormatUtil.formatLocalDateTime(x.getAuditedTime()));
    messageExporter.exportExcel(this::listPage, headers, query, fileName);
  }

  @Override
  public int batchCreate(Collection<? extends ReturnSign> collection) {

    try {
      collection.forEach(this::initDefaultValue);
      int count = getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
      collection.forEach(record -> BIZ_LOGGER.log(record.getReturnSignId(), BizLogger.ADD));
      return count;
    } catch (Exception e) {
      LOGGER.error("批量新增快递签收失败，快递签收：{}", JsonUtil.toJson(collection));
      LOGGER.error("批量新增快递签收失败，堆栈信息：", e);
      throw new OmsException("批量新增快递签收失败！");
    }
  }

  @Override
  public int batchModify(Collection<? extends ReturnSign> collection) {
    return updateBatch(collection);
  }
}
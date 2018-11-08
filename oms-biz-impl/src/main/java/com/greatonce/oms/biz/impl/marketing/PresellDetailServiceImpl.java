package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.marketing.PresellDetailService;
import com.greatonce.oms.biz.marketing.PresellService;
import com.greatonce.oms.dao.marketing.PresellDetailDao;
import com.greatonce.oms.domain.enums.marketing.PresellStatus;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.marketing.PresellDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.marketing.PresellDetailQuery;
import com.greatonce.oms.query.marketing.PresellQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预售商品信息.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class PresellDetailServiceImpl extends
    AbstractDetailService<Presell, PresellDetail, PresellDetailQuery> implements
    PresellDetailService {

  @Resource
  private IdGenerator presellDetailIdGenerator;
  @Autowired
  private PresellDetailDao dao;
  @Autowired
  private PresellService presellService;
  @Autowired
  private MessageExporter messageExporter;

  @Override
  protected QueryDao<PresellDetail, PresellDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<PresellDetail> getDetails(Presell presell) {
    return presell.getDetails();
  }

  @Override
  protected BizService<Presell, ? extends Query> getMainService() {
    return presellService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return presellDetailIdGenerator;
  }


  @Override
  public void exportPresell(String fileName, PresellQuery query) {
    ExcelHeaderCollection<PresellDetail> headers = new ExcelHeaderCollection<>();
    headers.add("预售名称", detail -> detail.getPresell().getPresellName());
    headers.add("预售编号", detail -> detail.getPresell().getPresellCode());
    headers.add("商品名称", PresellDetail::getProductName);
    headers.add("商品编码", PresellDetail::getProductCode);
    headers.add("规格名称", PresellDetail::getSkuName);
    headers.add("规格编码", PresellDetail::getSkuCode);
    headers.add("数量", detail -> FormatUtil.formatInteger(detail.getQuantity()));
    PresellDetailQuery detailQuery = new PresellDetailQuery();
    detailQuery.setPresellId(query.getPresellId());
    messageExporter.exportExcel(this, headers, detailQuery, fileName);
  }

  @Override
  public List<PresellDetail> listDetails(Long presellId) {
    PresellDetail eg = new PresellDetail();
    eg.setPresellId(presellId);
    return listExample(eg);
  }

  @Override
  public int modifyDetail(Presell mainEntity) {
    int result = getTransactionTemplate().executeWithResult(()-> super.modifyDetail(mainEntity));
    Presell presell = presellService.getByKey(mainEntity.getPresellId());
    if (mainEntity.getStatus() == PresellStatus.BEGIN){
      getMqProducer().send(new StockChangedMessage(presell.getPresellCode(),mainEntity.getDetails().get(0).getSkuId(),
          BizContext.getNickname(),"预售数量调整"));
    }
    return result;
  }
}
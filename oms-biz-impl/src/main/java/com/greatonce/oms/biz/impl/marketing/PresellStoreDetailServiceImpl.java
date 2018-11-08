package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.marketing.PresellDetailService;
import com.greatonce.oms.biz.marketing.PresellStoreDetailService;
import com.greatonce.oms.bo.marketing.PresellStoreDetailBO;
import com.greatonce.oms.dao.marketing.PresellStoreDetailDao;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.marketing.PresellDetailStatus;
import com.greatonce.oms.domain.marketing.PresellDetail;
import com.greatonce.oms.domain.marketing.PresellStoreDetail;
import com.greatonce.oms.query.marketing.PresellStoreDetailQuery;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预售店铺明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class PresellStoreDetailServiceImpl extends
    AbstractService<PresellStoreDetail, PresellStoreDetailQuery> implements
    PresellStoreDetailService {

  @Autowired
  private PresellStoreDetailDao dao;
  @Autowired
  private StoreService storeService;
  @Autowired
  private PresellDetailService presellDetailService;
  @Autowired
  private MessageExporter messageExporter;
  @Resource
  private IdGenerator presellStoreDetailIdGenerator;

  @Override
  protected QueryDao<PresellStoreDetail, PresellStoreDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return presellStoreDetailIdGenerator;
  }

  @Override
  public void end(PresellStoreDetail record) {
    record.setStatus(PresellDetailStatus.FINISHED);
    update(record);
  }

  @Override
  public void begin(PresellStoreDetail record) {
    record.setStatus(PresellDetailStatus.STARTED);
    update(record);
  }

  @Override
  public List<PresellStoreDetail> listNotStart(Long presellId, Long storeId) {
    PresellStoreDetailQuery filter = new PresellStoreDetailQuery();
    filter.setPresellId(presellId);
    filter.setStoreId(storeId);
    filter.setStatus(PresellDetailStatus.NOT_STARTED);
    return list(filter);
  }

  @Override
  public List<PresellStoreDetail> listStarted(Long presellId, Long storeId) {
    PresellStoreDetailQuery filter = new PresellStoreDetailQuery();
    filter.setPresellId(presellId);
    filter.setStoreId(storeId);
    filter.setStatus(PresellDetailStatus.STARTED);
    return list(filter);
  }

  @Override
  public int batchCreate(Collection<? extends PresellStoreDetail> collection) {
    collection.forEach(this::initDefaultValue);
    return getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
  }

  @Override
  public int batchModify(Collection<? extends PresellStoreDetail> collection) {
    return getTransactionTemplate().executeWithResult(() -> updateBatch(collection));
  }

  @Override
  public void exportNotStartByIds(List<Long> notStartDetailIds, String fileName) {
    Assert.notEmpty(notStartDetailIds, "未开始的预售商店明细集合为空");

    List<PresellStoreDetailBO> data = new ArrayList<>(notStartDetailIds.size());
    for (Long id : notStartDetailIds) {
      PresellStoreDetailBO bo = new PresellStoreDetailBO();
      PresellStoreDetail detail = getByKey(id);
      Store store = storeService.getByKey(detail.getStoreId());
      PresellDetail presellDetail = presellDetailService.getByKey(detail.getPresellDetailId());
      if (!Assert.isNull(store)) {
        bo.setStoreName(store.getStoreName());
      }
      if (!Assert.isNull(presellDetail)) {
        bo.setProductCode(presellDetail.getProductCode());
        bo.setProductName(presellDetail.getProductName());
        bo.setSkuCode(presellDetail.getSkuCode());
        bo.setSkuName(presellDetail.getSkuName());
      }
      bo.setStatus(detail.getStatus());
      bo.setRemark(detail.getRemark());
      data.add(bo);
    }

    ExcelHeaderCollection<PresellStoreDetailBO> headers = new ExcelHeaderCollection<>();
    headers.add("店铺", PresellStoreDetailBO::getStoreName);
    headers.add("商品编码", PresellStoreDetailBO::getProductCode);
    headers.add("商品名称", PresellStoreDetailBO::getProductName);
    headers.add("规格编码", PresellStoreDetailBO::getSkuCode);
    headers.add("规格名称", PresellStoreDetailBO::getSkuName);
    headers.add("状态", x -> FormatUtil.formatEnum(x.getStatus()));
    headers.add("失败信息", PresellStoreDetailBO::getRemark);
    messageExporter.exportExcel(headers, data, fileName);
  }
}
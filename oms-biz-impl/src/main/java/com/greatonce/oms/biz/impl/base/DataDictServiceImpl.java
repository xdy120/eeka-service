package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.DataDictService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.DataDictDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.DataDict;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.DataDictQuery;
import com.greatonce.oms.util.CidBuilder;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据字典. DataDict <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-17
 */
@Service
public class DataDictServiceImpl extends AbstractService<DataDict, DataDictQuery>
    implements DataDictService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataDictServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_DATA_DICT);
  @Autowired
  private DataDictDao dao;

  @Override
  protected QueryDao<DataDict, DataDictQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  /**
   * 更新子节点数量.
   */
  @Override
  public int updateChildrenQuantity(Long id, int quantity) {
    return dao.updateChildrenQuantity(id, quantity);
  }

  @Override
  protected void validateCreate(DataDict entity) {
    super.validateCreate(entity);
    Assert.notNull(entity.getParentId(), "分组ID不能为空");
  }

  @Override
  protected void initDefaultValue(DataDict dataDict) {
    super.initDefaultValue(dataDict);
    dataDict.setChildrenQuantity(0);
    dataDict.setSystem(false);
    DataDict parent = getByKey(dataDict.getParentId());
    String parentCid = parent == null ? null : parent.getCid();
    dataDict.setCid(CidBuilder.createCid(dataDict.getParentId(), parentCid, dataDict.getDataDictId()));
  }

  @Override
  public int create(DataDict dataDict) {
    try {
      initDefaultValue(dataDict);
      int count = getTransactionTemplate().executeWithResult(() -> {
        updateChildrenQuantity(dataDict.getParentId(), 1);
        return insert(dataDict);
      });
      BIZ_LOGGER.log(dataDict.getDataDictId(), BizLogger.ADD);
      return count;
    } catch (Exception e) {
      LOGGER.error("新建数据字典失败,{}", JsonUtil.toJson(dataDict));
      LOGGER.error("新建数据字典失败：", e);
      throw new OmsException("新建数据字典失败");
    }
  }


  @Override
  public int remove(DataDict record) {
    int count = delete(record.getDataDictId());
    updateChildrenQuantity(record.getParentId(), -1);
    BIZ_LOGGER.log(record.getDataDictId(), "删除", record.getDataDictName());
    return count;
  }

  /**
   * 获取子数据.
   */
  @Override
  public List<DataDict> listChildren(Long id) {
    DataDict example = new DataDict();
    example.setParentId(id);
    return getDAO().listExample(example);
  }
}
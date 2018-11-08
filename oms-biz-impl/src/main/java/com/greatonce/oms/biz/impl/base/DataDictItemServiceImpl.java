package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.DataDictItemDao;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.DataDictItemQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * DataDictItem <br/> 数据字典项.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-17
 */
@Service
public class DataDictItemServiceImpl extends
    AbstractService<DataDictItem, DataDictItemQuery> implements DataDictItemService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_DATA_DICT);
  private static final String CACHE_NAME = "DATA_DICT";
  @Autowired
  private DataDictItemDao dao;

  @Override
  protected QueryDao<DataDictItem, DataDictItemQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(DataDictItem entity) {
    super.initDefaultValue(entity);
    entity.setSystem(false);
  }

  @Override
  public List<DataDictItem> listByDictId(Long dictId) {
    DataDictItem eg = new DataDictItem();
    eg.setDataDictId(dictId);
    return listExample(eg);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'DID_MAP_'+#dictId")
  public Map<String, String> listMapByDictId(Long dictId) {
    final List<DataDictItem> dataDictItems = listByDictId(dictId);
    return dataDictItems.stream().collect(
        Collectors.toMap(DataDictItem::getDataDictItemCode, DataDictItem::getDataDictItemName));
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'DID_SET_'+#dictId")
  public Set<String> listSetByDictId(Long dictId) {
    final List<DataDictItem> dataDictItems = listByDictId(dictId);
    return dataDictItems.stream().map(DataDictItem::getDataDictItemName)
        .collect(Collectors.toSet());
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'DID_MAP_'+#entity.dataDictId"),
      @CacheEvict(value = CACHE_NAME, key = "'DID_SET_'+#entity.dataDictId")
  })
  public int create(DataDictItem entity) {
    return super.create(entity);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'DID_'+#entity.dataDictId"),
      @CacheEvict(value = CACHE_NAME, key = "'DID_MAP_'+#entity.dataDictId"),
      @CacheEvict(value = CACHE_NAME, key = "'DID_SET_'+#entity.dataDictId")
  })
  public int modify(DataDictItem entity) {
    return super.modify(entity);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'DID_'+#entity.dataDictId"),
      @CacheEvict(value = CACHE_NAME, key = "'DID_MAP_'+#entity.dataDictId"),
      @CacheEvict(value = CACHE_NAME, key = "'DID_SET_'+#entity.dataDictId")
  })
  public int remove(DataDictItem entity) {
    return super.remove(entity);
  }


  @Override
  public DataDictItem getByKey(Long id) {
    return super.getByKey(id);
  }
}
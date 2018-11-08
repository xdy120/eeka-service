package com.greatonce.oms.biz.impl;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.VersionDO;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractDetailService<T extends BaseDO, D extends BaseDO, Q extends Query> extends
    AbstractService<D, Q> implements
    DetailService<T, D, Q> {

  protected abstract BizService<T, ? extends Query> getMainService();

  protected abstract QueryDao<D, Q> getDAO();

  protected abstract List<D> getDetails(T t);

  @Override
  public int createDetail(T mainEntity) {
    List<D> details = getDetails(mainEntity);
    Assert.notEmpty(details, "明细不能为空！");
    details.removeIf(x -> x.getPrimaryKey() != null);
    details.forEach(this::initDefaultValue);
    int count = insertBatch(details);
    getMainService().modify(mainEntity);
    return count;
  }

  @Override
  public int modifyDetail(T mainEntity) {
    List<D> details = getDetails(mainEntity);
    Assert.notEmpty(details, "明细不能为空！");
    details.removeIf(x -> x.getPrimaryKey() == null);
    int count = updateBatch(details);
    getMainService().modify(mainEntity);
    return count;
  }

  @Override
  public int removeDetail(T mainEntity) {
    List<D> details = getDetails(mainEntity);
    Assert.notEmpty(details, "明细不能为空！");

    Set<Long> ids = details.stream().map(BaseDO::getPrimaryKey)
        .collect(Collectors.toSet());
    int count = deleteBatch(ids);
    getMainService().modify(mainEntity);
    return count;
  }

  @Override
  public int batchCreate(Collection<? extends D> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends D> collection) {
    return updateBatch(collection);
  }
}

package com.greatonce.oms.biz;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.BaseDO;
import java.util.Collection;

public interface DetailService<T extends BaseDO, D extends BaseDO, Q extends Query> extends
    BizService<D, Q> {

  int createDetail(T mainEntity);

  int modifyDetail(T mainEntity);

  int removeDetail(T mainEntity);

  int batchCreate(Collection<? extends D> collection);

  int batchModify(Collection<? extends D> collection);
}

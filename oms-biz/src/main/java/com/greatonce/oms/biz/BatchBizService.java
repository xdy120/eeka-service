package com.greatonce.oms.biz;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.BaseDO;
import java.util.Collection;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 6/13/2018
 */
public interface BatchBizService<T extends BaseDO, Q extends Query> extends
    BizService<T, Q> {

  /**
   * 批量创建
   *
   * @param collection 集合
   * @return 行数
   */
  int batchCreate(Collection<? extends T> collection);

  /**
   * 批量修改
   *
   * @param collection 集合
   * @return 行数
   */
  int batchModify(Collection<? extends T> collection);

}

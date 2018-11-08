package com.greatonce.oms.biz;

import com.greatonce.oms.domain.EnableDO;

/**
 * 启用禁用service.
 *
 * @author buer
 * @version 2017-11-14 17:59
 */
public interface EnableBizService<T extends EnableDO> {

  /**
   * 启用.
   *
   * @return 影响行数
   */
  int enable(T entity);

  /**
   * 禁用.
   *
   * @return 影响行数
   */
  int disable(T entity);
}

package com.greatonce.oms.web.controller;

import com.greatonce.core.database.Query;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.EnableDO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 启用控制器.
 *
 * @author buer
 * @version 2017-11-14 17:58
 */
public interface EnableController<T extends EnableDO, Q extends Query> extends
    CommonController<T, Q> {

  @SuppressWarnings("unchecked")
  default EnableBizService<T> getEnableBizService() {
    return (EnableBizService<T>) this.getBizService();
  }

  @PutMapping(path = "{id}/disable")
  default void disable(@PathVariable("id") Long id) {
    T entity = getBizService().getByKey(id);
    getEnableBizService().disable(entity);
  }

  @PutMapping(path = "{id}/enable")
  default void enable(@PathVariable("id") Long id) {
    T entity = getBizService().getByKey(id);
    getEnableBizService().enable(entity);
  }
}

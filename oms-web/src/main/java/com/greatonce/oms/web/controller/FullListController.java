package com.greatonce.oms.web.controller;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.BaseDO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 全部查询.
 *
 * @author buer
 * @version 2017-11-16 15:09
 */
public interface FullListController<T extends BaseDO, Q extends Query> extends
    CommonController<T, Q> {


  /**
   * 全部查询.
   * 慎用，只适用于基础数据量小不需要分页的数据
   */
  @GetMapping
  default List<T> list(Q q) {
    return getBizService().list(q);
  }
}

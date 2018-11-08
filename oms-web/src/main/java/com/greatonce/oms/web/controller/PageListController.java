package com.greatonce.oms.web.controller;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.BaseDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 分页查询Controller.
 *
 * @author buer
 * @version 2017-11-16 15:10
 */
public interface PageListController<T extends BaseDO, Q extends Query> extends
    CommonController<T, Q> {

  /**
   * 分页查询.
   * 必须传入page与pageSize
   */
  @GetMapping
  default PageList<T> listPage(Q q, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    return getBizService().listPage(q, page, pageSize);
  }
}

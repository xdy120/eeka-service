package com.greatonce.oms.web.controller;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.VersionDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Coby
 * @version 2018-02-07
 */
public interface DetailPageCommonController<T extends VersionDO, D extends BaseDO, Q extends Query> extends
    DetailCommonController<T, D, Q> {

  /**
   * 分页查询，必须传入page与pageSize
   *
   * @param filter 明细分页
   */
  @GetMapping
  default PageList<D> listPage(Q filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    return getBizService().listPage(filter, page, pageSize);
  }

}

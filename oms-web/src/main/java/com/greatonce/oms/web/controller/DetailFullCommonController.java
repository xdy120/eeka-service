package com.greatonce.oms.web.controller;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.VersionDO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Coby
 * @version 2018-02-07
 */
public interface DetailFullCommonController<T extends VersionDO, D extends BaseDO, Q extends Query> extends
    DetailCommonController<T, D, Q> {

  @GetMapping
  default List<D> list(Q filter) {
    return getBizService().list(filter);
  }
}

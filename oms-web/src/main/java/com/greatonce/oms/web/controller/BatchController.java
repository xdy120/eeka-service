package com.greatonce.oms.web.controller;

import com.greatonce.core.database.Query;
import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.BaseDO;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 6/14/2018
 */
public interface BatchController<T extends BaseDO, Q extends Query> extends
    CommonController<T, Q> {

  /**
   * 获取业务实现
   */
  BatchBizService<T, Q> getBizService();

  /**
   * 批量插入
   *
   * @param list 实体
   */
  @PostMapping(path = "batch")
  default void insertBatch(@RequestBody List<T> list) {
    getBizService().batchCreate(list);
  }


  /**
   * 批量修改
   */
  @PutMapping(path = "batch")
  default void updateBatch(@RequestBody List<T> list) {
    getBizService().batchModify(list);
  }
}

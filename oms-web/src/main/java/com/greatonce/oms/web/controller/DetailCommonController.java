package com.greatonce.oms.web.controller;

import com.greatonce.core.database.Query;
import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.VersionDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Coby
 * @version 2018-02-07
 */
public interface DetailCommonController<T extends VersionDO, D extends BaseDO, Q extends Query> {

  DetailService<T, D, Q> getBizService();

  /**
   * 新增
   *
   * @param id 主键ID
   * @param t 实体
   */
  @PostMapping("{id}")
  default T insert(@PathVariable("id") Long id, @RequestBody T t) {
    getBizService().createDetail(t);
    t.setVersion(t.getVersion() + 1);
    return t;
  }

  /**
   * 修改
   *
   * @param id 主键ID
   * @param t 实体
   */
  @PutMapping("{id}")
  default T update(@PathVariable("id") Long id, @RequestBody T t) {
    getBizService().modifyDetail(t);
    t.setVersion(t.getVersion() + 1);
    return t;
  }

  /**
   * 删除
   *
   * @param id 主键ID
   */
  @PatchMapping("{id}")
  default T delete(@PathVariable("id") Long id, @RequestBody T t) {
    getBizService().removeDetail(t);
    t.setVersion(t.getVersion() + 1);
    return t;
  }

  /**
   * 获取单条数据
   *
   * @param id 主键ID
   */
  @GetMapping("{id}")
  default D get(@PathVariable("id") Long id) {
    return getBizService().getByKey(id);
  }

}

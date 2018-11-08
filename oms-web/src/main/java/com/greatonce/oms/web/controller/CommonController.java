package com.greatonce.oms.web.controller;

import com.greatonce.core.database.Query;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.VersionDO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author buer
 * @version 2017-11-16 16:05
 */
public interface CommonController<T extends BaseDO, Q extends Query> {

  /**
   * 获取业务实现
   */
  BizService<T, Q> getBizService();

  /**
   * 插入
   *
   * @param t 实体
   */
  @PostMapping
  default T insert(@RequestBody T t) {
    getBizService().create(t);
    return t;
  }


  /**
   * 修改
   *
   * @param id 主键ID
   * @param t 实体
   */
  @PutMapping(path = "{id}")
  default T update(@PathVariable("id") Long id, @RequestBody T t) {
    getBizService().modify(t);
    return t;
  }


  /**
   * 删除
   *
   * @param id 主键ID
   */
  @DeleteMapping(path = "{id}")
  default void delete(@PathVariable("id") Long id) {
    T t = getBizService().getByKey(id);
    getBizService().remove(t);
  }

  /**
   * 获取单条数据
   *
   * @param id 主键ID
   */
  @GetMapping(path = "{id}")
  default T get(@PathVariable("id") Long id) {
    return getBizService().getByKey(id);
  }

  /**
   * 校验版本.
   */
  default void validateVersion(VersionBO bo, VersionDO entity) {
    if (!bo.getVersion().equals(entity.getVersion())) {
      throw SysExceptions.VERSION_CHANGED;
    }
  }
}
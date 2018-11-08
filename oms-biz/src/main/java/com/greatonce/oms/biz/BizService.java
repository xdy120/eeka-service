package com.greatonce.oms.biz;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.Query;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.util.logging.BizLogger;
import java.util.List;

/**
 * 服务基础接口.
 *
 * @author buer
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-19
 */
public interface BizService<T, Q extends Query> {

  BizLogger getBizLogger();

  IdGenerator getIdGenerator();

  /**
   * 新写入数据库记录.
   *
   * @return 影响的记录数
   */
  int create(T entity);

  /**
   * 根据主键来更新符合条件的数据库记录.
   *
   * @return 影响的记录数
   */
  int modify(T entity);

  /**
   * 根据主键删除记录.
   *
   * @return 影响的记录数
   */
  int remove(T entity);

  /**
   * 根据主键查询数据.
   *
   * @return 实体对象
   */
  T getByKey(Long id);

  /**
   * 根据示例获取单条数据.
   *
   * @return 实体对象
   */
  T getByExample(T t);

  /**
   * 根据条件查询实体对象.
   *
   * @return 实体对象列表
   */
  List<T> list(Q q);

  /**
   * 根据实体查询实体对象.
   *
   * @return 实体对象列表
   */
  List<T> listExample(T t);

  /**
   * 根据条件查询实体对象返回分页结果.
   *
   * @return 分页的实体对象列表
   */
  PageList<T> listPage(Q q, int page, int pageSize);

  /**
   * 根据实体查询实体对象返回分页结果.
   *
   * @return 分页的实体对象列表
   */
  PageList<T> listExamplePage(T t, int page, int pageSize);

  /**
   * 根据条件查询返回指定的列.
   *
   * @return 分页的实体对象列表
   */
  List<T> listFields(String fields, Q q);

  /**
   * 根据条件查询返回指定的列, 返回分页结果.
   *
   * @return 分页的实体对象列表
   */
  PageList<T> listFieldsPage(String fields, Q q, int page, int pageSize);
}

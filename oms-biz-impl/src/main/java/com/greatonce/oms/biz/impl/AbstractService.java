package com.greatonce.oms.biz.impl;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.database.PageList;
import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 抽象业务类实现.
 *
 * @author buer
 * @version 3.0
 */
public abstract class AbstractService<T extends BaseDO, Q extends Query> implements
    BizService<T, Q> {

  private static final Integer MAX_PAGE_SIZE = 1000;
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.OTHER);


  @Resource
  private IdGenerator idGenerator;
  @Autowired
  private RabbitMqProducer mqProducer;
  @Autowired
  private ManualTransactionTemplate transactionTemplate;

  /**
   * 获取Id生成器.
   */
  @Override
  public IdGenerator getIdGenerator() {
    return idGenerator;
  }

  /**
   * 获取业务日志对象.
   *
   * @return BizLogger
   */
  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  /**
   * 获取消息发生器.
   */
  protected RabbitMqProducer getMqProducer() {
    return mqProducer;
  }

  /**
   * 获取事务模板.
   */
  protected ManualTransactionTemplate getTransactionTemplate() {
    return transactionTemplate;
  }

  protected void initDefaultValue(T entity) {
    if (entity.getPrimaryKey() == null) {
      entity.setPrimaryKey(getIdGenerator().next());
    }
  }

  /**
   * create时的验证.
   */
  protected void validateCreate(T entity) {
  }

  /**
   * 修改验证.
   * 默认调用validateCreate
   *
   * @see #validateCreate
   */
  protected void validateModify(T entity) {
    validateCreate(entity);
  }


  @Override
  public int create(T entity) {
    validateCreate(entity);
    initDefaultValue(entity);
    int count = insert(entity);
    getBizLogger().log(entity.getPrimaryKey(), BizLogger.ADD);
    return count;
  }

  @Override
  public int modify(T entity) {
    validateModify(entity);
    int count = update(entity);
    getBizLogger().log(entity.getPrimaryKey(), BizLogger.UPDATE);
    return count;
  }

  @Override
  public int remove(T entity) {
    int count = delete(entity.getPrimaryKey());
    getBizLogger().log(entity.getPrimaryKey(), BizLogger.DELETE);
    return count;
  }

  /**
   * 获取 DAO 对象.
   *
   * @return DAO 对象
   */
  protected abstract QueryDao<T, Q> getDAO();


  /**
   * 新写入数据库记录.
   *
   * @return 影响的记录数
   */
  protected int insert(T entity) {
    return getDAO().insert(entity);
  }

  /**
   * 批量写入.
   *
   * @return 影响的记录数
   */
  protected int insertBatch(Collection<? extends T> list) {
    return getDAO().insertBatch(list);
  }

  /**
   * 根据主键来更新符合条件的数据库记录.
   *
   * @return 影响的记录数
   */
  protected int update(T entity) {
    return getDAO().update(entity);
  }

  /**
   * 根据示例更新.
   *
   * @param entity 更新数据
   * @param eg 比对数据
   * @return 影响的记录数
   */
  protected int updateByExample(T entity, T eg) {
    return getDAO().updateByExample(entity, eg);
  }

  /**
   * 批量更新.
   *
   * @param list 集合
   * @return 影响的记录数
   */
  protected int updateBatch(Collection<? extends T> list) {
    return getDAO().updateBatch(list);
  }

  /**
   * 根据主键删除记录.
   *
   * @return 影响的记录数
   */
  protected int delete(Long id) {
    return getDAO().delete(id);
  }

  /**
   * 批量删除.
   *
   * @return 影响的记录数
   */
  protected int deleteBatch(Collection<Long> ids) {
    return getDAO().deleteBatch(ids);
  }

  /**
   * 根据示例删除.
   *
   * @return 影响的记录数
   */
  protected int deleteByExample(T t) {
    return getDAO().deleteByExample(t);
  }

  @Override
  public T getByKey(Long id) {
    return getDAO().getByKey(id);
  }

  @Override
  public T getByExample(T t) {
    return getDAO().getByExample(t);
  }

  @Override
  public List<T> list(Q q) {
    return getDAO().list(q);
  }

  @Override
  public PageList<T> listPage(Q q, int page, int pageSize) {
    validatePageParameter(page, pageSize);
    return getDAO().listPage(q, page, pageSize);
  }

  @Override
  public List<T> listFields(String fields, Q q) {
    return getDAO().listField(fields, q);
  }

  @Override
  public PageList<T> listFieldsPage(String fields, Q q, int page, int pageSize) {
    validatePageParameter(page, pageSize);
    return getDAO().listFieldPage(fields, q, page, pageSize);
  }

  @Override
  public List<T> listExample(T t) {
    return getDAO().listExample(t);
  }

  @Override
  public PageList<T> listExamplePage(T t, int page, int pageSize) {
    validatePageParameter(page, pageSize);
    return getDAO().listExamplePage(t, page, pageSize);
  }

  protected void validatePageParameter(int page, int pageSize) {
    if (page <= 0) {
      throw new InvalidParameterException("page 必须大于0");
    }
    if (pageSize < 0 || pageSize > MAX_PAGE_SIZE) {
      throw new InvalidParameterException("pageSize 必须大于0小于" + MAX_PAGE_SIZE);
    }
  }

  /**
   * 校验影响行数.
   *
   * @param count 改变行数
   */
  protected void validateChangedCount(int count) {
    if (count == 0) {
      throw SysExceptions.VERSION_CHANGED;
    }
  }
}

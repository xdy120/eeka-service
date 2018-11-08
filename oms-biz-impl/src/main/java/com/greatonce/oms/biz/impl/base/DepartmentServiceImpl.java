package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.DepartmentService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.DepartmentDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Department;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.DepartmentQuery;
import com.greatonce.oms.util.CidBuilder;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Department <br/> 部门.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class DepartmentServiceImpl extends AbstractService<Department, DepartmentQuery> implements
    DepartmentService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_DEPARTMENT);
  private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
  @Autowired
  private DepartmentDao dao;

  @Override
  protected QueryDao<Department, DepartmentQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(Department entity) {
    super.initDefaultValue(entity);
    entity.setChildrenQuantity(0);
    if (entity.getParentId() == null) {
      entity.setParentId(0L);
      entity.setCid(
          CidBuilder.createCid(entity.getParentId(), null, entity.getDepartmentId()));
    } else {
      Department parent = dao.getByKey(entity.getParentId());
      entity.setCid(
          CidBuilder.createCid(entity.getParentId(), parent.getCid(), entity.getDepartmentId()));
    }
  }

  @Override
  public int create(Department department) {
    try {
      initDefaultValue(department);
      int count = getTransactionTemplate().executeWithResult(() -> {
        dao.updateChildrenQuantity(department.getParentId(), 1);
        return insert(department);
      });
      BIZ_LOGGER.log(department.getDepartmentId(), BizLogger.ADD);
      return count;
    } catch (Exception e) {
      LOGGER.error("新增部门失败,部门信息:{}", JsonUtil.toJson(department));
      LOGGER.error("新增部门失败,堆栈信息:", e);
      throw new OmsException("新增部门失败");
    }

  }

  @Override
  public int countDepartments(Long companyId) {
    return dao.countDepartments(companyId);
  }

  @Override
  public List<Department> listChildrenByParentId(Long id) {
    Department eg = new Department();
    eg.setParentId(id);
    return dao.listExample(eg);
  }

  @Override
  public int remove(Department department) {
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        dao.updateChildrenQuantity(department.getParentId(), -1);
        return delete(department.getDepartmentId());
      });
      BIZ_LOGGER.log(department.getDepartmentId(), BizLogger.DELETE);
      return count;
    } catch (Exception e) {
      LOGGER.error("删除部门失败,部门信息:{}", JsonUtil.toJson(department));
      LOGGER.error("删除部门失败,堆栈信息:", e);
      throw new OmsException("删除部门失败");
    }
  }
}
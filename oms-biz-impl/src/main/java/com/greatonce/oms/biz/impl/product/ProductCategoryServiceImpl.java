package com.greatonce.oms.biz.impl.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.product.ProductCategoryService;
import com.greatonce.oms.dao.product.ProductCategoryDao;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.product.ProductCategory;
import com.greatonce.oms.query.product.ProductCategoryQuery;
import com.greatonce.oms.util.CidBuilder;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductCategory <br/>
 * 商品分类
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class ProductCategoryServiceImpl extends
    AbstractService<ProductCategory, ProductCategoryQuery> implements ProductCategoryService {

  private static final BizLogger bizLog = OmsLoggerFactory.getLogger(OmsModule.PRODUCT_CATEGORY);
  @Autowired
  private ProductCategoryDao dao;

  @Override
  protected QueryDao<ProductCategory, ProductCategoryQuery> getDAO() {
    return this.dao;
  }

  @Override
  public int updateChildrenQuantity(Long id, int quantity) {
    return dao.updateChildrenQuantity(id, quantity);
  }

  @Override
  public int create(ProductCategory record) {
    Assert.notNull(record.getParentId(), "父分类不能为空！");
    record.setProductCategoryId(getIdGenerator().next());
    record.setChildrenQuantity(0);
    ProductCategory parent = getByKey(record.getParentId());
    String parentCid = parent == null ? null : parent.getCid();
    record.setCid(
        CidBuilder.createCid(record.getParentId(), parentCid, record.getProductCategoryId()));
    int count = getTransactionTemplate().executeWithResult(() -> {
      int num = insert(record);
      updateChildrenQuantity(record.getParentId(), 1);
      return num;
    });
    bizLog.log(record.getProductCategoryId(), BizLogger.ADD, record.getProductCategoryName());
    return count;
  }

  @Override
  public int modify(ProductCategory record) {
    int count = update(record);
    bizLog.log(record.getProductCategoryId(), BizLogger.UPDATE, record.getProductCategoryName());
    return count;
  }

  @Override
  public int remove(ProductCategory category) {
    int count = getTransactionTemplate().executeWithResult(() -> {
      int num = delete(category.getProductCategoryId());
      updateChildrenQuantity(category.getParentId(), -1);
      return num;
    });

    bizLog
        .log(category.getProductCategoryId(), BizLogger.DELETE, category.getProductCategoryName());
    return count;
  }

  @Override
  public List<ProductCategory> listChildren(Long parentId) {
    ProductCategory eg = new ProductCategory();
    eg.setParentId(parentId);
    return getDAO().listExample(eg);
  }
}
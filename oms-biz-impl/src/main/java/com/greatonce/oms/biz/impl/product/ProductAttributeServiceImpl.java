package com.greatonce.oms.biz.impl.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.product.ProductAttributeService;
import com.greatonce.oms.dao.product.ProductAttributeDao;
import com.greatonce.oms.domain.enums.product.ProductAttributeType;
import com.greatonce.oms.domain.product.ProductAttribute;
import com.greatonce.oms.query.product.ProductAttributeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductAttribute <br/>
 * 商品属性
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class ProductAttributeServiceImpl extends
    AbstractService<ProductAttribute, ProductAttributeQuery> implements ProductAttributeService {

  @Autowired
  private ProductAttributeDao dao;

  @Override
  protected QueryDao<ProductAttribute, ProductAttributeQuery> getDAO() {
    return this.dao;
  }

  @Override
  public int create(ProductAttribute record) {
    //重复为false，不重复为true
    Boolean result = validateAttributeName(record.getAttributeName(), null);
    if (result) {
      record.setAttributeId(getIdGenerator().next());
      record.setSystem(false);
      return insert(record);
    } else {
      //重复返回-1
      return -1;
    }
  }

  @Override
  public int modify(ProductAttribute record) {
    Boolean result = validateAttributeName(record.getAttributeName(), record.getAttributeId());
    if (result) {
      //当设置为文本时需要清空数据字典id
      if (ProductAttributeType.TEXT.equals(record.getAttributeType())) {
        record.setDataDictId(0L);
      }
      return update(record);
    } else {
      //重复返回-1
      return -1;
    }

  }

  @Override
  public int remove(ProductAttribute record) {
    return delete(record.getAttributeId());
  }

  @Override
  public Boolean validateAttributeName(String attributeName, Long attributeId) {
    ProductAttribute example = new ProductAttribute();
    example.setAttributeName(attributeName);
    //如果找得到说明重复
    ProductAttribute result = dao.getByExample(example);
    if (attributeId == null) {
      //新增
      return result == null;
    } else {
      //更新
      return result == null || (result.getAttributeId().equals(attributeId));
    }
  }
}
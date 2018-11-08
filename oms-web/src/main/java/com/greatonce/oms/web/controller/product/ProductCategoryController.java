package com.greatonce.oms.web.controller.product;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.TreeBizService;
import com.greatonce.oms.biz.product.ProductCategoryService;
import com.greatonce.oms.domain.product.ProductCategory;
import com.greatonce.oms.query.product.ProductCategoryQuery;
import com.greatonce.oms.web.controller.CommonController;
import com.greatonce.oms.web.controller.TreeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-11-14 17:41
 */
@RestController
@RequestMapping(value = "/product/category")
@CrossOrigin
public class ProductCategoryController implements
    CommonController<ProductCategory, ProductCategoryQuery>, TreeController<ProductCategory> {

  @Autowired
  private ProductCategoryService productCategoryService;

  @Override
  public TreeBizService<ProductCategory> getTreeBizService() {
    return productCategoryService;
  }

  @Override
  public BizService<ProductCategory, ProductCategoryQuery> getBizService() {
    return productCategoryService;
  }
}

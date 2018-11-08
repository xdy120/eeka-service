package com.greatonce.oms.web.controller.product;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.product.ProductAttributeService;
import com.greatonce.oms.domain.enums.product.ProductAttributeType;
import com.greatonce.oms.domain.product.ProductAttribute;
import com.greatonce.oms.query.product.ProductAttributeQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-11-16 15:45
 */
@RestController
@RequestMapping(value = "/product/attribute")
@CrossOrigin
public class ProductAttributeController implements
    FullListController<ProductAttribute, ProductAttributeQuery> {

  @Autowired
  private ProductAttributeService productAttributeService;

  @Override
  public BizService<ProductAttribute, ProductAttributeQuery> getBizService() {
    return productAttributeService;
  }

  /**
   * 获取用于生成编码的属性
   */
  @GetMapping(path = "/coder")
  public List<ProductAttribute> listCoder() {
    return productAttributeService.listExample(new ProductAttribute() {{
      setAttributeType(ProductAttributeType.DATA_DICT);
      setUseCode(true);
    }});
  }

  /**
   * 获取自定义属性
   */
  @GetMapping(path = "/custom")
  public List<ProductAttribute> listCustom() {
    return productAttributeService.listExample(new ProductAttribute() {{
      setSystem(false);
    }});
  }

  /**
   * 新增属性重复校验
   */
  @GetMapping(path = "/{attributeName}/validate")
  public Boolean validateAttributeName(@PathVariable("attributeName") String attributeName) {
    return productAttributeService.validateAttributeName(attributeName, null);
  }

  @Override
  @RequestMapping(method = RequestMethod.PUT, path = "{id}")
  public ProductAttribute update(@PathVariable("id") Long id,
      @RequestBody ProductAttribute productAttribute) {
    if (productAttribute.getAttributeType() == ProductAttributeType.TEXT) {
      productAttribute.setDataDictName("");
      productAttribute.setUseCode(false);
    }
    int result = productAttributeService.modify(productAttribute);
    return result == -1 ? null : productAttribute;
  }

  @Override
  @RequestMapping(method = RequestMethod.POST)
  public ProductAttribute insert(@RequestBody ProductAttribute productAttribute) {
    int result = productAttributeService.create(productAttribute);
    return result == -1 ? null : productAttribute;
  }
}

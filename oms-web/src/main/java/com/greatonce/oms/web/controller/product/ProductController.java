package com.greatonce.oms.web.controller.product;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.product.ProductService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.query.product.ProductQuery;
import com.greatonce.oms.query.product.ProductSkuQuery;
import com.greatonce.oms.search.HttpElasticsearchTemplate;
import com.greatonce.oms.search.entity.AdvanceQuery;
import com.greatonce.oms.search.entity.AdvanceQuery.Field;
import com.greatonce.oms.search.entity.BoolType;
import com.greatonce.oms.search.entity.FieldType;
import com.greatonce.oms.search.entity.Operator;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.PageListController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-11-16 15:14
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController implements PageListController<Product, ProductQuery>,
    EnableController<Product, ProductQuery> {

  private Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private HttpElasticsearchTemplate httpElasticsearchTemplate;

  @Override
  public BizService<Product, ProductQuery> getBizService() {
    return productService;
  }

  /**
   * 查询商品列表.
   */
  @Override
  public PageList<Product> listPage(ProductQuery productQuery, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    if (!Assert.isEmpty(productQuery.getProductName())) {
      Field field = new Field();
      field.setField("product_name");
      field.setValue(productQuery.getProductName());
      field.setType(FieldType.TEXT);
      field.setOperator(Operator.FUZZY);
      field.setBoolType(BoolType.MUST);
      PageList<Map<String, Object>> pageList = null;
      try {
        pageList = httpElasticsearchTemplate
            .searchSource("product_sku", "doc", 1, 10000, new AdvanceQuery(Arrays.asList(field)),
                new String[]{"product_id"}, new String[]{});
      } catch (IOException e) {
        LOGGER.error("es商品查询出错，堆栈信息：{}", e);
        throw new OmsException("查询es索引出错，请联系系统管理员");
      }
      if (!Assert.isEmpty(pageList.getData())) {
        Set<Long> set = pageList.getData().stream()
            .map(x -> Long.valueOf(String.valueOf(x.get("product_id"))))
            .collect(Collectors.toSet());
        productQuery.setProductIds(new ArrayList<>(set));
        productQuery.setProductName(null);
      } else {
        return new PageList<>(pageSize, page, 0, null);
      }
    }
    return productService.listPage(productQuery, page, pageSize);
  }

  @GetMapping("{productId}/sku")
  public List<ProductSku> listSkus(@PathVariable("productId") Long productId) {
    ProductSkuQuery query = new ProductSkuQuery();
    query.setProductId(productId);
    return productSkuService.listFullInfo(query);
  }

  @PutMapping("{productId}/sku")
  public void modifySkuInfo(@PathVariable("productId") Long productId, @RequestBody ProductSku t) {
    productService.modifySkuInfo(productId, t);
  }

  @PutMapping("{productId}/noticeWms")
  public void modifySkuInfo(@PathVariable("productId") Long productId) {
    final Product product = productService.getByKey(productId);
    productService.noticeWms(product);
  }

  @PostMapping("/import")
  public void importProduct(@RequestBody List<Map<String, String>> list) {
    productService.importProduct(list);
  }

  @GetMapping("/exportSku/{fileName}")
  public void exportSku(@PathVariable("fileName") String fileName, ProductQuery filter) {
    productService.exportSku(fileName, filter);
  }

  @PostMapping("/priceImport")
  public void importVipPrice(@RequestBody List<Map<String, String>> list) {
    productService.importProductPrice(list);
  }
}

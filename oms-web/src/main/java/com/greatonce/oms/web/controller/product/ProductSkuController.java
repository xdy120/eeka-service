package com.greatonce.oms.web.controller.product;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.product.ProductSku;
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
import javax.annotation.Resource;
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

@RestController
@CrossOrigin
@RequestMapping(value = "/product/sku")
public class ProductSkuController implements PageListController<ProductSku, ProductSkuQuery>,
    EnableController<ProductSku, ProductSkuQuery> {

  private Logger LOGGER = LoggerFactory.getLogger(ProductSkuController.class);
  @Resource
  private ProductSkuService productSkuService;
  @Autowired
  private HttpElasticsearchTemplate httpElasticsearchTemplate;

  @Override
  public BizService<ProductSku, ProductSkuQuery> getBizService() {
    return productSkuService;
  }

  /**
   * 展示商品规格列表.
   */
  @Override
  public PageList<ProductSku> listPage(ProductSkuQuery productSkuQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    return productSkuService.listPage(productSkuQuery, page, pageSize);
  }

  @GetMapping("/full")
  public PageList<ProductSku> listFullInfo(ProductSkuQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    if (!Assert.isEmpty(filter.getProductName()) || !Assert.isEmpty(filter.getSkuName())) {
      Field field1 = new Field();
      field1.setField("product_name");
      field1.setValue(filter.getProductName());
      field1.setOperator(Operator.FUZZY);
      field1.setType(FieldType.TEXT);
      field1.setBoolType(BoolType.MUST);
      Field field2 = new Field();
      field2.setField("sku_name");
      field2.setValue(filter.getSkuName());
      field2.setOperator(Operator.FUZZY);
      field2.setType(FieldType.TEXT);
      field2.setBoolType(BoolType.MUST);

      PageList<Map<String, Object>> pageList = null;
      try {
        pageList = httpElasticsearchTemplate
            .searchSource("product_sku", "doc", 1, 10000,
                new AdvanceQuery(Arrays.asList(field1, field2)),
                new String[]{"sku_id"}, new String[]{});
      } catch (IOException e) {
        LOGGER.error("es商品规格查询出错，堆栈信息：{}", e);
        throw new OmsException("查询es索引出错，请联系系统管理员");
      }
      if (!Assert.isEmpty(pageList.getData())) {
        Set<Long> set = pageList.getData().stream()
            .map(x -> Long.valueOf(String.valueOf(x.get("sku_id"))))
            .collect(Collectors.toSet());
        filter.setSkuIds(new ArrayList<>(set));
        filter.setProductName(null);
        filter.setSkuName(null);
      } else {
        return new PageList<>(pageSize, page, 0, null);
      }
    }
    return productSkuService.listFullInfo(filter, page, pageSize);
  }

  @GetMapping("/list/codes")
  public List<ProductSku> listByCodes(@RequestParam List<String> codes) {
    if (Assert.isEmpty(codes)) {
      throw new OmsException("传入编码不能为空！");
    }
    ProductSkuQuery query = new ProductSkuQuery();
    query.setSkuCodes(codes);
    return productSkuService.listFullInfo(query);
  }

  @GetMapping("/combination")
  public PageList<ProductSku> listCombination(ProductSkuQuery filter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    if (!Assert.isTrue(filter.isCombination())) {
      filter.setCombination(true);
    }
    return listPage(filter, page, pageSize);
  }

  @PostMapping("/combination")
  public void addCombination(@RequestBody ProductSku sku) {
    productSkuService.insertCombination(sku);
  }

  @PutMapping("/combination/{id}")
  public void updateCombination(@PathVariable("id") Long id, @RequestBody ProductSku sku) {
    sku.setSkuId(id);
    productSkuService.updateCombination(sku);
  }

  @PutMapping("/combination/{id}/disable")
  public void disableCombination(@PathVariable("id") Long id) {
    ProductSku sku = productSkuService.getByKey(id);
    productSkuService.disable(sku);
  }

  @PutMapping("/combination/{id}/enable")
  public void enableCombination(@PathVariable("id") Long id) {
    ProductSku sku = productSkuService.getByKey(id);
    productSkuService.enable(sku);
  }

  @PostMapping("/combination/import")
  public void importCombination(@RequestBody List<Map<String, String>> list) {
    productSkuService.importCombination(list);
  }

  @GetMapping("/{skuCode}/enable")
  public ProductSku getEnableSkuByCode(@PathVariable("skuCode") String skuCode) {
    return productSkuService.getEffectiveByCode(skuCode);
  }

  @GetMapping("/exportCombination/{fileName}")
  public void exportCombination(@PathVariable("fileName") String fileName, ProductSkuQuery filter) {
    productSkuService.exportCombination(fileName, filter);
  }

  @PutMapping("/prepackage/{id}/enable")
  public void enablePrepackage(@PathVariable("id") Long id) {
    ProductSku sku = productSkuService.getByKey(id);
    if (!Assert.isTrue(sku.isPrepackage())) {
      sku.setPrepackage(true);
    }
    productSkuService.modify(sku);
  }

  @PutMapping("/prepackage/{id}/disable")
  public void disablePrepackage(@PathVariable("id") Long id) {
    ProductSku sku = productSkuService.getByKey(id);
    if (Assert.isTrue(sku.isPrepackage())) {
      sku.setPrepackage(false);
    }
    productSkuService.modify(sku);
  }
}

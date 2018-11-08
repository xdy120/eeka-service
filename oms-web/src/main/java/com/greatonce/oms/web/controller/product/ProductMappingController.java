package com.greatonce.oms.web.controller.product;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.product.mall.mapping.ProductMappingDownload;
import com.greatonce.oms.bo.product.ProductMallMappingDownloadBO;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.query.product.ProductMallMappingQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product/mapping")
@CrossOrigin
public class ProductMappingController implements
    PageListController<ProductMallMapping, ProductMallMappingQuery> {

  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private ProductMappingDownload productMappingDownload;

  @Override
  public BizService<ProductMallMapping, ProductMallMappingQuery> getBizService() {
    return productMallMappingService;
  }

  @PostMapping(value = "/download")
  public void download(@RequestBody ProductMallMappingDownloadBO productMallMappingDownloadBO) {
    productMallMappingDownloadBO.setOperator(BizContext.getNickname());
    productMappingDownload.asyncDownload(productMallMappingDownloadBO);
  }

  @GetMapping("/export/{fileName}")
  public void exportSku(@PathVariable("fileName") String fileName, ProductMallMappingQuery filter) {
    productMallMappingService.export(fileName, filter);
  }

  @PutMapping("/setDropShipping/{id}")
  public void setDropShipping(@PathVariable Long id) {
    ProductMallMapping mapping = productMallMappingService.getByKey(id);
    productMallMappingService.setDropShipping(mapping);
  }

  @PutMapping("/cancelDropShipping/{id}")
  public void cancelDropShipping(@PathVariable Long id) {
    ProductMallMapping mapping = productMallMappingService.getByKey(id);
    productMallMappingService.cancelDropShipping(mapping);
  }

  @GetMapping("/getMallProductUrl/{productMallMappingId}")
  public String getMallProductUrl(@PathVariable Long productMallMappingId) {
    ProductMallMapping mapping = productMallMappingService.getByKey(productMallMappingId);
    return productMallMappingService.getMallProductUrl(mapping);
  }
}

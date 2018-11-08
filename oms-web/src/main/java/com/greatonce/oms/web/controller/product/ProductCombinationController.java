package com.greatonce.oms.web.controller.product;

import com.greatonce.oms.biz.product.ProductCombinationService;
import com.greatonce.oms.domain.product.ProductCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author buer
 * @version 2017-11-29 19:50
 */
@RestController
@RequestMapping(value = "/product/combination/{combinationId}/detail")
@CrossOrigin
public class ProductCombinationController {

  @Autowired
  private ProductCombinationService productCombinationService;

  @PostMapping
  public void addDetail(@PathVariable("combinationId") Long combinationId,
      @RequestBody List<ProductCombination> combinations) {
    combinations.forEach(x -> x.setCombinationId(combinationId));
    productCombinationService.batchCreate(combinations);
  }

  @DeleteMapping("/{detailId}")
  public void deleteDetail(@PathVariable("combinationId") Long combinationId,
      @PathVariable("detailId") Long detailId) {
    productCombinationService.remove(productCombinationService.getByKey(detailId));
  }

  @GetMapping
  public List<ProductCombination> list(@PathVariable("combinationId") Long combinationId) {
    return productCombinationService.listByCombination(combinationId);
  }

  @PostMapping(path = "/setMainSku")
  public void setMainSku(@PathVariable("combinationId") Long combinationId,@RequestBody ProductCombination productCombination){
    productCombinationService.setMainSku(productCombination);
  }
}

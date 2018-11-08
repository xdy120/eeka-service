package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaProductSkuQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaProductSkuQueryResponse;
import com.greatonce.oms.domain.product.ProductSku;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
@RestController
@EekaApiCondition
public class EekaProductSkuController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaProductSkuController.class);

  @Autowired
  private ProductSkuService productSkuService;
  @Resource
  private IdGenerator apiIdGenerator;

  /**
   * sku查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.sku.get")
  public EekaProductSkuQueryResponse productSkuQuery(HttpServletRequest servletRequest) {
    EekaProductSkuQueryRequest request = checkSign(servletRequest,
        EekaProductSkuQueryRequest.class);
    EekaProductSkuQueryResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getSkuCode())) {
        response = new EekaProductSkuQueryResponse(apiIdGenerator.next(), "商品编码不能为空");
        return response;
      }
      ProductSku productSku = productSkuService.getEffectiveByCode(request.getSkuCode());
      if (!Assert.isNull(productSku) && productSku.isEnable()) {
        response = new EekaProductSkuQueryResponse(apiIdGenerator.next());
        response.setProductSku(productSku);
      } else {
        response = new EekaProductSkuQueryResponse(apiIdGenerator.next(), "商品不存在或已禁用");
        return response;
      }
    }
    if (Assert.isNull(response)) {
      response = new EekaProductSkuQueryResponse(apiIdGenerator.next());
    }
    return response;
  }

}

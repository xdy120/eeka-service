package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaExpressQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaExpressQueryResponse;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.enums.ExpressUse;
import com.greatonce.oms.query.base.ExpressQuery;
import java.util.ArrayList;
import java.util.List;
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
public class EekaExpressController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaExpressController.class);

  @Autowired
  private ExpressService expressService;
  @Resource
  private IdGenerator apiIdGenerator;

  /**
   * 快递查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.express.list")
  public EekaExpressQueryResponse expressQuery(HttpServletRequest servletRequest) {
    EekaExpressQueryRequest request = checkSign(servletRequest,
        EekaExpressQueryRequest.class);
    EekaExpressQueryResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getExpressType())) {
        response = new EekaExpressQueryResponse(apiIdGenerator.next(), "快递类型不能为空");
        return response;
      }
      if (Assert.isNull(request.getExpressUse())) {
        response = new EekaExpressQueryResponse(apiIdGenerator.next(), "快递用途不能为空");
        return response;
      }
      ExpressQuery expressQuery = new ExpressQuery();
      List<ExpressUse> expressUses = new ArrayList<>();
      expressUses.add(request.getExpressUse());
      expressUses.add(ExpressUse.ALL);
      expressQuery.setExpressUses(expressUses);
      expressQuery.setExpressType(request.getExpressType());
      expressQuery.setEnable(true);
      List<Express> expresss = expressService.list(expressQuery);
      response = new EekaExpressQueryResponse(apiIdGenerator.next());
      response.setExpresses(expresss);
    }
    if (Assert.isNull(response)) {
      response = new EekaExpressQueryResponse(apiIdGenerator.next());
    }
    return response;
  }
}

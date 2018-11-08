package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaDataDictQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaDataDictQueryResponse;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.domain.base.DataDictItem;
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
public class EekaDataDictController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaDataDictController.class);

  @Autowired
  private DataDictItemService dataDictItemService;
  @Resource
  private IdGenerator apiIdGenerator;

  /**
   * 字典查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.datadict.item.list")
  public EekaDataDictQueryResponse dataDictQuery(HttpServletRequest servletRequest) {
    EekaDataDictQueryRequest request = checkSign(servletRequest, EekaDataDictQueryRequest.class);
    EekaDataDictQueryResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getDataDictId())) {
        response = new EekaDataDictQueryResponse(apiIdGenerator.next(), "字典id不能为空");
        return response;
      }
      List<DataDictItem> dataDictItems = dataDictItemService.listByDictId(request.getDataDictId());
      if (!Assert.isEmpty(dataDictItems)) {
        response = new EekaDataDictQueryResponse(apiIdGenerator.next());
        response.setDataDictItems(dataDictItems);
      }
    }
    if(Assert.isNull(response)){
      response = new EekaDataDictQueryResponse(apiIdGenerator.next());
    }
    return response;
  }

}

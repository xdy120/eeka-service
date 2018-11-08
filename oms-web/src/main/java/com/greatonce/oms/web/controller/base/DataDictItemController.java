package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.query.base.DataDictItemQuery;
import com.greatonce.oms.web.controller.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/datadict/item")
@CrossOrigin
public class DataDictItemController implements CommonController<DataDictItem, DataDictItemQuery> {

  @Autowired
  private DataDictItemService dataDictItemService;

  @Override
  public BizService<DataDictItem, DataDictItemQuery> getBizService() {
    return dataDictItemService;
  }
}

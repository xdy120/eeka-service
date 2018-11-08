package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.TreeBizService;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.DataDictService;
import com.greatonce.oms.domain.base.DataDict;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.query.base.DataDictQuery;
import com.greatonce.oms.web.controller.CommonController;
import com.greatonce.oms.web.controller.TreeController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/base/datadict")
@CrossOrigin
public class DataDictController implements CommonController<DataDict, DataDictQuery>,
    TreeController<DataDict> {

  @Resource
  private DataDictService dataDictService;
  @Resource
  private DataDictItemService dataDictItemService;

  @Override
  public BizService<DataDict, DataDictQuery> getBizService() {
    return dataDictService;
  }

  @Override
  public TreeBizService<DataDict> getTreeBizService() {
    return dataDictService;
  }

  /**
   * 获取字典的字典项
   *
   * @param id 字典ID
   */
  @GetMapping(path = "{id}/item")
  public List<DataDictItem> listItems(@PathVariable("id") Long id) {
    return dataDictItemService.listByDictId(id);
  }

}

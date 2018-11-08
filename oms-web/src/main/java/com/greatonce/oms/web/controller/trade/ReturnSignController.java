package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.trade.ReturnSignService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.ReturnSign;
import com.greatonce.oms.query.trade.ReturnSignQuery;
import com.greatonce.oms.web.controller.BatchController;
import com.greatonce.oms.web.controller.PageListController;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/12/8.
 */
@RestController
@RequestMapping(value = "/trade/return/sign")
@CrossOrigin
public class ReturnSignController implements PageListController<ReturnSign, ReturnSignQuery>,
    BatchController<ReturnSign, ReturnSignQuery> {

  @Resource
  ReturnSignService returnSignService;

  @Override
  public BatchBizService<ReturnSign, ReturnSignQuery> getBizService() {
    return returnSignService;
  }

  @RequestMapping(path = "{id}/invalid", method = RequestMethod.PUT)
  public void invalid(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    ReturnSign returnSign = returnSignService.getByKey(id);
    returnSignService.invalid(returnSign,bo);
  }

  /**
   * @return 根据快递的单号获取快递的信息
   */
  @RequestMapping("{expressNo}/express")
  public List<ReturnSign> getExpressName(@PathVariable("expressNo") String expressNo) {
    return returnSignService.getExpressSign(expressNo);
  }

  @GetMapping("exportSign/{fileName}")
  public void exportSign(@PathVariable("fileName") String fileName, ReturnSignQuery query){
    returnSignService.exportSign(fileName,query);
  }
}

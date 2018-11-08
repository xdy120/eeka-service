package com.greatonce.oms.web.controller.finance;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.finance.AlipayRecordService;
import com.greatonce.oms.bo.finance.AlipayRecordDownloadBO;
import com.greatonce.oms.domain.finance.AlipayRecord;
import com.greatonce.oms.query.finance.AlipayRecordQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/finance/alipay/record")
@CrossOrigin
public class AlipayRecordController implements PageListController<AlipayRecord, AlipayRecordQuery> {

  @Autowired
  private AlipayRecordService alipayRecordService;

  @Override
  public BizService<AlipayRecord, AlipayRecordQuery> getBizService() {
    return alipayRecordService;
  }

  @PostMapping(value = "/download")
  public void download(@RequestBody AlipayRecordDownloadBO downloadBO) {
    downloadBO.setOperator(BizContext.getNickname());
    alipayRecordService.asyncDownload(downloadBO);
  }

}

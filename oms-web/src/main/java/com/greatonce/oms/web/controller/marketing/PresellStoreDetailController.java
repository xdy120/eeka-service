package com.greatonce.oms.web.controller.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.marketing.PresellStoreDetailService;
import com.greatonce.oms.domain.marketing.PresellStoreDetail;
import com.greatonce.oms.query.marketing.PresellStoreDetailQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/12/5.
 */
@RestController
@RequestMapping(value = "/marketing/presell/{presellId}")
@CrossOrigin
public class PresellStoreDetailController {

  @Autowired
  private PresellStoreDetailService presellStoreDetailService;

  @GetMapping("/{storeId}/detail")
  public PageList<PresellStoreDetail> listPage(PresellStoreDetailQuery filter,
      @PathVariable("presellId") Long presellId, @PathVariable("storeId") Long storeId,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    if (filter == null) {
      filter = new PresellStoreDetailQuery();
    }
    filter.setPresellId(presellId);
    filter.setStoreId(storeId);
    return presellStoreDetailService.listPage(filter, page, pageSize);
  }
}

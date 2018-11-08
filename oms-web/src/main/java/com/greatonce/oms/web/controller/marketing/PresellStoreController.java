package com.greatonce.oms.web.controller.marketing;

import com.greatonce.oms.biz.marketing.PresellStoreService;
import com.greatonce.oms.domain.marketing.PresellStore;
import com.greatonce.oms.query.marketing.PresellStoreQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/12/5.
 */
@RestController
@RequestMapping(value = "/marketing/presell/{presellId}/store")
@CrossOrigin
public class PresellStoreController {

  @Autowired
  private PresellStoreService presellStoreService;


  @GetMapping
  public List<PresellStore> list(PresellStoreQuery filter,
      @PathVariable("presellId") Long presellId) {
    if (filter == null) {
      filter = new PresellStoreQuery();
    }
    filter.setPresellId(presellId);
    return presellStoreService.list(filter);
  }
}

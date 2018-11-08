package com.greatonce.oms.web.controller.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.admin.MenuOperationService;
import com.greatonce.oms.domain.admin.MenuOperation;
import com.greatonce.oms.query.admin.MenuOperationQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangc on 2017/11/2.
 */
@RestController
@RequestMapping("/admin/menuOperation")
@CrossOrigin
public class MenuOperationController implements
    FullListController<MenuOperation, MenuOperationQuery> {

  @Resource
  MenuOperationService menuOperationService;

  @Override
  public BizService<MenuOperation, MenuOperationQuery> getBizService() {
    return menuOperationService;
  }

  @GetMapping("/all")
  List<MenuOperation> listAll() {
    return menuOperationService.listAll();
  }
}

package com.greatonce.oms.web.controller;

import com.greatonce.oms.biz.TreeBizService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 树形数据Controller.
 *
 * @author buer
 * @version 2017-11-14 18:07
 */
public interface TreeController<T> {

  TreeBizService<T> getTreeBizService();

  @RequestMapping(method = RequestMethod.GET, path = "{parentId}/children")
  default List<T> listChildern(@PathVariable("parentId") Long parentId) {
    return getTreeBizService().listChildren(parentId);
  }
}

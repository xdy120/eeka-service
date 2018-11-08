package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.query.base.DataDictItemQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据字典项.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface DataDictItemService extends BizService<DataDictItem, DataDictItemQuery> {

  List<DataDictItem> listByDictId(Long dictId);

  Map<String, String> listMapByDictId(Long dictId);

  Set<String> listSetByDictId(Long dictId);
}
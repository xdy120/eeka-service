package com.greatonce.oms.search.service;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.query.stock.StockUploadLogQuery;

import java.util.Map;

/**
 * @author Shenzhen cca
 * @version 2018/9/12
 */
public interface StockUploadLogQueryService {

  PageList<Map<String, Object>> listLogs(StockUploadLogQuery query, int page, int pageSize);
}

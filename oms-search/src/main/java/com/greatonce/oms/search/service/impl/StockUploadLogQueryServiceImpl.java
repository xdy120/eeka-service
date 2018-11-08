package com.greatonce.oms.search.service.impl;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.query.stock.StockUploadLogQuery;
import com.greatonce.oms.search.HttpElasticsearchOperations;
import com.greatonce.oms.search.QueryUtil;
import com.greatonce.oms.search.service.StockUploadLogQueryService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author Shenzhen cca
 * @version 2018/9/12
 */
@Component
public class StockUploadLogQueryServiceImpl implements StockUploadLogQueryService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(StockUploadLogQueryServiceImpl.class);

  @Autowired
  HttpElasticsearchOperations httpElasticsearchOperations;

  @Override
  public PageList<Map<String, Object>> listLogs(StockUploadLogQuery query, int page, int pageSize) {
    PageList<Map<String, Object>> pageList = null;
    try {
      pageList = httpElasticsearchOperations
          .searchSource("stock-upload-*", "oms3", page, pageSize, getLogsQuery(query));
    } catch (IOException e) {
      LOGGER.info("查询库存上传日志失败,参数:{}", JsonUtil.toJson(query));
      LOGGER.info("查询库存上传日志失败,堆栈信息:", e);
    }
    return pageList;
  }

  private SearchSourceBuilder getLogsQuery(StockUploadLogQuery logQuery) {
    SearchSourceBuilder builder = new SearchSourceBuilder();
    BoolQueryBuilder query = QueryBuilders.boolQuery();
    BoolQueryBuilder filter = QueryBuilders.boolQuery();

    QueryUtil.setMatchQuery(logQuery, StockUploadLogQuery::getStoreId, filter, "storeId");
    QueryUtil.setMatchQuery(logQuery, StockUploadLogQuery::getBatchId, filter, "batchId");
    QueryUtil.setMatchQuery(logQuery, StockUploadLogQuery::getProductCode, filter, "productCode");
    QueryUtil.setMatchQuery(logQuery, StockUploadLogQuery::getSkuCode, filter, "skuCode");
    QueryUtil.setMatchQuery(logQuery, StockUploadLogQuery::getStatus, filter, "status");
    QueryUtil.setDateTimeRangeQuery(logQuery, StockUploadLogQuery::getBeginUploadTime,
        StockUploadLogQuery::getEndUploadTime, filter, "@timestamp");

    builder.query(query);
    builder.postFilter(filter);
    return builder;
  }
}

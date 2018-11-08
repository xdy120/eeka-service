package com.greatonce.oms.search;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.search.entity.AdvanceQuery;
import java.io.IOException;
import java.util.Map;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * es请求模板.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public interface HttpElasticsearchOperations {

  SearchResponse search(String index, String type, int page,
      int pageSize, SearchSourceBuilder queryBuilder)
      throws IOException;

  PageList<Map<String, Object>> searchSource(String index, String type, int page,
      int pageSize, SearchSourceBuilder queryBuilder) throws IOException;

  PageList<Map<String, Object>> searchSource(String index, String type, int page, int pageSize,
      AdvanceQuery advanceQuery, String[] includes, String[] excludes) throws IOException;

  PageList<Map<String, Object>> searchSource(String index, String type, int page, int pageSize,
      AdvanceQuery advanceQuery, String[] includes, String[] excludes, SortBuilder sortBuilder)
      throws IOException;
}

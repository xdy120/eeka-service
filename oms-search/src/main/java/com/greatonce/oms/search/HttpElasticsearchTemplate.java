package com.greatonce.oms.search;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.search.entity.AdvanceQuery;
import com.greatonce.oms.search.entity.AdvanceQuery.Field;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
@Component
public class HttpElasticsearchTemplate implements HttpElasticsearchOperations {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpElasticsearchTemplate.class);
  @Autowired
  private RestHighLevelClient restHighLevelClient;

  @Override
  public SearchResponse search(String index, String type, int page, int pageSize,
      SearchSourceBuilder sourceBuilder) throws IOException {
    sourceBuilder.from((page - 1) * pageSize).size(pageSize);
    SearchRequest request = new SearchRequest(index);
    request.types(type);
    request.source(sourceBuilder);
    return restHighLevelClient.search(request);
  }

  @Override
  public PageList<Map<String, Object>> searchSource(String index, String type, int page,
      int pageSize, SearchSourceBuilder sourceBuilder) throws IOException {
    sourceBuilder.from((page - 1) * pageSize).size(pageSize);
    SearchRequest request = new SearchRequest(index);
    request.types(type);
    request.source(sourceBuilder);
    final SearchResponse response = restHighLevelClient.search(request);
    final List<Map<String, Object>> list = Arrays.stream(response.getHits().getHits())
        .map(SearchHit::getSource)
        .collect(Collectors.toList());
    return new PageList<>(page, pageSize, (int) response.getHits().totalHits, list);
  }

  @Override
  public PageList<Map<String, Object>> searchSource(String index, String type, int page,
      int pageSize, AdvanceQuery advanceQuery, String[] includes, String[] excludes)
      throws IOException {
    return searchSource(index, type, page, pageSize, advanceQuery, includes, excludes, null);
  }

  @Override
  public PageList<Map<String, Object>> searchSource(String index, String type, int page,
      int pageSize, AdvanceQuery advanceQuery, String[] includes, String[] excludes,
      SortBuilder sortBuilder) throws IOException {
    if (Assert.isNull(advanceQuery.getFields())) {
      advanceQuery.setFields(new ArrayList<>());
    }
    SearchSourceBuilder builder = new SearchSourceBuilder();
    BoolQueryBuilder query = QueryBuilders.boolQuery();

    List<Field> father = new ArrayList<>();
    List<Field> son = new ArrayList<>();
    for (Field field : advanceQuery.getFields()) {
      if (Assert.isNull(field.getRelation())) {
        father.add(field);
      } else {
        son.add(field);
      }
    }

    for (Field field : father) {
      BoolQueryBuilder fatherQuery = QueryBuilders.boolQuery();
      BoolQueryBuilder fatherFilter = QueryBuilders.boolQuery();
      QueryUtil.setQueryCondition(fatherQuery, fatherFilter, field);
      if (fatherFilter.hasClauses()) {
        fatherQuery.filter(fatherFilter);
      }
      if (!fatherQuery.hasClauses()) {
        continue;
      }
      switch (field.getBoolType()) {
        case MUST:
          query.must(fatherQuery);
          break;
        case MUST_NOT:
          query.mustNot(fatherQuery);
          break;
        case SHOULD:
          query.should(fatherQuery);
          query.minimumShouldMatch(1);
          break;
      }
    }
    for (Field field : son) {
      BoolQueryBuilder sonQuery = QueryBuilders.boolQuery();
      BoolQueryBuilder sonFilter = QueryBuilders.boolQuery();
      QueryUtil.setQueryCondition(sonQuery, sonFilter, field);
      if (sonFilter.hasClauses()) {
        sonQuery.filter(sonFilter);
      }
      if (!sonQuery.hasClauses()) {
        continue;
      }
      switch (field.getBoolType()) {
        case MUST:
          query.must(JoinQueryBuilders.hasChildQuery(field.getRelation(), sonQuery, ScoreMode.None));
          break;
        case MUST_NOT:
          query.mustNot(JoinQueryBuilders.hasChildQuery(field.getRelation(), sonQuery, ScoreMode.None));
          break;
        case SHOULD:
          query.should(JoinQueryBuilders.hasChildQuery(field.getRelation(), sonQuery, ScoreMode.None));
          query.minimumShouldMatch(1);
          break;
      }
    }

    if (query.hasClauses()) {
      builder.query(query);
    }
    if (!Assert.isEmpty(includes) || !Assert.isEmpty(excludes)) {
      builder.fetchSource(includes, excludes);
    }
    if (!Assert.isNull(sortBuilder)) {
      builder.sort(sortBuilder);
    }
    return searchSource(index, type, page, pageSize, builder);
  }

}

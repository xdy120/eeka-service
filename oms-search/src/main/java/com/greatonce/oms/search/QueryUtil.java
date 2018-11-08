package com.greatonce.oms.search;

import com.greatonce.core.database.Query;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.search.entity.AdvanceQuery.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SKP
 */
public class QueryUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(QueryUtil.class);

  /**
   * 设置String term查询条件
   */
  public static <Q extends Query> void setStringTermQuery(Q query, Function<Q, String> getString,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(getString.apply(query))) {
      boolQueryBuilder.must(QueryBuilders.termQuery(term, getString.apply(query)));
    }
  }

  /**
   * 设置Boolean term查询条件
   */
  public static <Q extends Query> void setBooleanTermQuery(Q query, Function<Q, Boolean> getBoolean,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isNull(getBoolean.apply(query))) {
      boolQueryBuilder.must(QueryBuilders.termQuery(term, getBoolean.apply(query)));
    }
  }

  /**
   * 设置String terms查询条件
   */
  public static <Q extends Query> void setStringTermsQuery(Q query,
      Function<Q, List<String>> getStringList,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(getStringList.apply(query))) {
      boolQueryBuilder.must(QueryBuilders.termsQuery(term, getStringList.apply(query)));
    }
  }

  /**
   * 设置Long terms查询条件
   */
  public static <Q extends Query> void setLongTermsQuery(Q query,
      Function<Q, List<Long>> getLongList,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(getLongList.apply(query))) {
      boolQueryBuilder.must(QueryBuilders.termsQuery(term, getLongList.apply(query)));
    }
  }

  /**
   * 设置match查询条件
   */
  public static <Q extends Query> void setMatchQuery(Q query, Function<Q, String> getString,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(getString.apply(query))) {
      boolQueryBuilder
          .must(QueryBuilders.matchQuery(term, getString.apply(query)).operator(Operator.AND));
    }
  }

  /**
   * 设置时间范围datetime查询条件
   */
  public static <Q extends Query> void setDateTimeRangeQuery(Q query,
      Function<Q, LocalDateTime> getBegin,
      Function<Q, LocalDateTime> getEnd,
      BoolQueryBuilder boolQueryBuilder,
      String term) {
    RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(term);
    if (!Assert.isNull(getBegin.apply(query)) && !Assert.isNull(getEnd.apply(query))) {
      rangeQuery.gte(DateTimeUtil.format(getBegin.apply(query)))
          .lte(DateTimeUtil.format(getEnd.apply(query)));
    } else if (!Assert.isNull(getBegin.apply(query))) {
      rangeQuery.gte(DateTimeUtil.format(getBegin.apply(query)));
    } else if (!Assert.isNull(getEnd.apply(query))) {
      rangeQuery.lte(DateTimeUtil.format(getEnd.apply(query)));
    } else {
      rangeQuery = null;
    }
    if (!Assert.isNull(rangeQuery)) {
      boolQueryBuilder.must(rangeQuery);
    }
  }

  /**
   * 设置时间范围date查询条件
   */
  public static <Q extends Query> void setDateRangeQuery(Q query,
      Function<Q, LocalDate> getBegin,
      Function<Q, LocalDate> getEnd,
      BoolQueryBuilder boolQueryBuilder,
      String term) {
    RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(term);
    if (!Assert.isNull(getBegin.apply(query)) && !Assert.isNull(getEnd.apply(query))) {
      rangeQuery.gte(DateTimeUtil.format(getBegin.apply(query)))
          .lte(DateTimeUtil.format(getEnd.apply(query)));
    } else if (!Assert.isNull(getBegin.apply(query))) {
      rangeQuery.gte(DateTimeUtil.format(getBegin.apply(query)));
    } else if (!Assert.isNull(getEnd.apply(query))) {
      rangeQuery.lte(DateTimeUtil.format(getEnd.apply(query)));
    } else {
      rangeQuery = null;
    }
    if (!Assert.isNull(rangeQuery)) {
      boolQueryBuilder.must(rangeQuery);
    }
  }

  /**
   * 设置double范围查询条件
   */
  public static <Q extends Query> void setDoubleRangeQuery(Q query,
      Function<Q, Double> getBegin,
      Function<Q, Double> getEnd,
      BoolQueryBuilder boolQueryBuilder,
      String term) {
    RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(term);
    if (!Assert.isNull(getBegin.apply(query)) && !Assert.isNull(getEnd.apply(query))) {
      rangeQuery.gte(getBegin.apply(query)).lte(getEnd.apply(query));
    } else if (!Assert.isNull(getBegin.apply(query))) {
      rangeQuery.gte(getBegin.apply(query));
    } else if (!Assert.isNull(getEnd.apply(query))) {
      rangeQuery.lte(getEnd.apply(query));
    } else {
      rangeQuery = null;
    }
    if (!Assert.isNull(rangeQuery)) {
      boolQueryBuilder.must(rangeQuery);
    }
  }

  /**
   * 高级查询：设置String term精确查询条件
   */
  public static void setStringTermQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.must(QueryBuilders.termQuery(term + ".keyword", value));
    }
  }

  /**
   * 高级查询：设置match查询条件
   */
  public static void setMatchQuery(String value, BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.must(QueryBuilders.matchQuery(term, value).operator(Operator.AND));
    }
  }

  /**
   * 高级查询：设置not match查询条件
   */
  public static void setNotMatchQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.mustNot(QueryBuilders.matchQuery(term, value).operator(Operator.AND));
    }
  }

  /**
   * 高级查询：设置null term查询条件
   */
  public static void setExistsQuery(BoolQueryBuilder boolQueryBuilder, String term) {
    boolQueryBuilder.must(QueryBuilders.existsQuery(term));
  }

  /**
   * 高级查询：设置not null term查询条件
   */
  public static void setNotExistsQuery(BoolQueryBuilder boolQueryBuilder, String term) {
    boolQueryBuilder.mustNot(QueryBuilders.existsQuery(term));
  }

  /**
   * 高级查询：设置long等于查询条件
   */
  public static void setLongTermQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.must(QueryBuilders.termQuery(term, Long.valueOf(value)));
    }
  }

  /**
   * 高级查询：设置long不等于查询条件
   */
  public static void setLongTermNotEqualQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.mustNot(QueryBuilders.termQuery(term, Long.valueOf(value)));
    }
  }

  /**
   * 高级查询：设置long大于查询条件
   */
  public static void setLongTermGreaterThanQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.gt(Long.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置long大于等于查询条件
   */
  public static void setLongTermGreaterThanEqualQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.gte(Long.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置long小于查询条件
   */
  public static void setLongTermLessThanQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.lt(Long.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置long小于等于查询条件
   */
  public static void setLongTermLessThanEqualQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.lte(Long.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置float等于查询条件
   */
  public static void setFloatTermQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.must(QueryBuilders.termQuery(term, Float.valueOf(value)));
    }
  }

  /**
   * 高级查询：设置float不等于查询条件
   */
  public static void setFloatTermNotEqualQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder
          .mustNot(QueryBuilders.termQuery(term, Float.valueOf(value)));
    }
  }

  /**
   * 高级查询：设置float大于查询条件
   */
  public static void setFloatTermGreaterThanQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.gt(Float.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置float大于等于查询条件
   */
  public static void setFloatTermGreaterThanEqualQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.gte(Float.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置float小于查询条件
   */
  public static void setFloatTermLessThanQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.lt(Float.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置float小于等于查询条件
   */
  public static void setFloatTermLessThanEqualQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.lte(Float.valueOf(value));
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置Boolean term查询条件
   */
  public static void setBooleanTermQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isNull(value)) {
      boolQueryBuilder.must(QueryBuilders.termQuery(term, Boolean.valueOf(value)));
    }
  }

  /**
   * 高级查询：设置时间等于查询条件
   */
  public static void setDateTimeTermQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.must(QueryBuilders.termQuery(term, value));
    }
  }

  /**
   * 高级查询：设置时间不等于查询条件
   */
  public static void setDateTimeTermNotEqualQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.mustNot(QueryBuilders.termQuery(term, value));
    }
  }

  /**
   * 高级查询：设置时间大于查询条件
   */
  public static void setDateTimeTermGreaterThanQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.gt(value);
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置时间大于等于查询条件
   */
  public static void setDateTimeTermGreaterThanEqualQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.gte(value);
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置时间小于查询条件
   */
  public static void setDateTimeTermLessThanQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.lt(value);
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置时间小于等于查询条件
   */
  public static void setDateTimeTermLessThanEqualQuery(String value,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(term);
      rangeQueryBuilder.lte(value);
      boolQueryBuilder.must(rangeQueryBuilder);
    }
  }

  /**
   * 高级查询：设置integer terms查询条件
   */
  public static void setIntegerTermsQuery(List<Integer> integerList,
      BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(integerList)) {
      boolQueryBuilder.must(QueryBuilders.termsQuery(term, integerList));
    }
  }

  /**
   * 高级查询：设置long terms查询条件
   */
  public static void setLongTermsQuery(List<Long> longList, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(longList)) {
      boolQueryBuilder.must(QueryBuilders.termsQuery(term, longList));
    }
  }

  /**
   * 高级查询：设置String terms查询条件
   */
  public static void setStringTermsQuery(List<String> stringList, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(stringList)) {
      boolQueryBuilder.must(QueryBuilders.termsQuery(term + ".keyword", stringList));
    }
  }

  /**
   * 高级查询：设置String terms不在列表查询条件
   */
  public static void setStringTermsNotInQuery(List<String> stringList,
      BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(stringList)) {
      boolQueryBuilder.mustNot(QueryBuilders.termsQuery(term + ".keyword", stringList));
    }
  }

  /**
   * 高级查询：设置开始以查询条件
   */
  public static void setBeginWithQuery(String value, BoolQueryBuilder boolQueryBuilder,
      String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.must(QueryBuilders.wildcardQuery(term + ".keyword", value + "*"));
    }
  }

  /**
   * 高级查询：设置结束以查询条件
   */
  public static void setEndWithQuery(String value, BoolQueryBuilder boolQueryBuilder, String term) {
    if (!Assert.isEmpty(value)) {
      boolQueryBuilder.must(QueryBuilders.wildcardQuery(term + ".keyword", "*" + value));
    }
  }

  public static void setQueryCondition(BoolQueryBuilder query, BoolQueryBuilder filter,
      Field field) {
    switch (field.getType()) {
      case TEXT:
        switch (field.getOperator()) {
          case FUZZY:
            QueryUtil.setMatchQuery(field.getValue(), query, field.getField());
            break;
          case NOT_FUZZY:
            QueryUtil.setNotMatchQuery(field.getValue(), query, field.getField());
            break;
          case PRECISE:
            QueryUtil.setStringTermQuery(field.getValue(), filter, field.getField());
            break;
          case NULL:
            QueryUtil.setNotExistsQuery(filter, field.getField());
            break;
          case NOT_NULL:
            QueryUtil.setExistsQuery(filter, field.getField());
            break;
          case MULTI_EQUAL:
            QueryUtil.setStringTermsQuery(Arrays.asList(field.getValue().split(",")), filter,
                field.getField());
            break;
          case NOT_MULTI_EQUAL:
            QueryUtil.setStringTermsNotInQuery(Arrays.asList(field.getValue().split(",")), filter,
                field.getField());
            break;
          case BEGIN_WITH:
            QueryUtil.setBeginWithQuery(field.getValue(), query, field.getField());
            break;
          case END_WITH:
            QueryUtil.setEndWithQuery(field.getValue(), query, field.getField());
            break;
        }
        break;
      case LONG:
        switch (field.getOperator()) {
          case EQUAL:
            QueryUtil.setLongTermQuery(field.getValue(), filter, field.getField());
            break;
          case NOT_EQUAL:
            QueryUtil.setLongTermNotEqualQuery(field.getValue(), filter, field.getField());
            break;
          case GREATER_THAN:
            QueryUtil.setLongTermGreaterThanQuery(field.getValue(), filter, field.getField());
            break;
          case GREATER_THAN_EQUAL:
            QueryUtil.setLongTermGreaterThanEqualQuery(field.getValue(), filter, field.getField());
            break;
          case LESS_THAN:
            QueryUtil.setLongTermLessThanQuery(field.getValue(), filter, field.getField());
            break;
          case LESS_THAN_EQUAL:
            QueryUtil.setLongTermLessThanEqualQuery(field.getValue(), filter, field.getField());
            break;
        }
        break;
      case FLOAT:
        switch (field.getOperator()) {
          case EQUAL:
            QueryUtil.setFloatTermQuery(field.getValue(), filter, field.getField());
            break;
          case NOT_EQUAL:
            QueryUtil.setFloatTermNotEqualQuery(field.getValue(), filter, field.getField());
            break;
          case GREATER_THAN:
            QueryUtil.setFloatTermGreaterThanQuery(field.getValue(), filter, field.getField());
            break;
          case GREATER_THAN_EQUAL:
            QueryUtil.setFloatTermGreaterThanEqualQuery(field.getValue(), filter, field.getField());
            break;
          case LESS_THAN:
            QueryUtil.setFloatTermLessThanQuery(field.getValue(), filter, field.getField());
            break;
          case LESS_THAN_EQUAL:
            QueryUtil.setFloatTermLessThanEqualQuery(field.getValue(), filter, field.getField());
            break;
        }
        break;
      case BOOLEAN:
        QueryUtil.setBooleanTermQuery(field.getValue(), filter, field.getField());
        break;
      case DATETIME:
        switch (field.getOperator()) {
          case EQUAL:
            QueryUtil.setDateTimeTermQuery(field.getValue(), filter, field.getField());
            break;
          case NOT_EQUAL:
            QueryUtil.setDateTimeTermNotEqualQuery(field.getValue(), filter, field.getField());
            break;
          case GREATER_THAN:
            QueryUtil
                .setDateTimeTermGreaterThanQuery(field.getValue(), filter, field.getField());
            break;
          case GREATER_THAN_EQUAL:
            QueryUtil
                .setDateTimeTermGreaterThanEqualQuery(field.getValue(), filter, field.getField());
            break;
          case LESS_THAN:
            QueryUtil
                .setDateTimeTermLessThanQuery(field.getValue(), filter, field.getField());
            break;
          case LESS_THAN_EQUAL:
            QueryUtil.setDateTimeTermLessThanEqualQuery(field.getValue(), filter, field.getField());
            break;
        }
        break;
      case ENUM:
        List<Integer> in = Arrays.stream(field.getValue().split(","))
            .map(Integer::valueOf)
            .collect(Collectors.toList());
        QueryUtil.setIntegerTermsQuery(in, filter, field.getField());
        break;
      case STORE:
        if (Assert.isEmpty(field.getValue())) {
          break;
        }
        List<Long> storeIds = Arrays.stream(field.getValue().split(","))
            .map(Long::valueOf)
            .collect(Collectors.toList());
        QueryUtil.setLongTermsQuery(storeIds, filter, field.getField());
        break;
    }
    if ("detail".equals(field.getRelation())) {
      filter.must(QueryBuilders.termQuery("sales_order_detail_type", SalesOrderDetailType.NORMAL.value()));
      query.should(QueryBuilders.boolQuery().mustNot(QueryBuilders.existsQuery("is_deleted")));
      query.should(QueryBuilders.termQuery("is_deleted", false));
      query.minimumShouldMatch(1);
    }
  }
}

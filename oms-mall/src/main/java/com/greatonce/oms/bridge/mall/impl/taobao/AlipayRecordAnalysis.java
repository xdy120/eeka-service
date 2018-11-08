package com.greatonce.oms.bridge.mall.impl.taobao;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 * 支付宝账单解析器
 */
@Component
public class AlipayRecordAnalysis {

  private static String CHINESE_CHARACTER = "^[\\u4e00-\\u9fa5（\\-）]+";
  private static String MERCHANT_ORDER_NO = "(?<=T200P)\\d+";
  private static String NUMBERS = "\\d+";
  private static String NUMBERS_END = "\\d+$";

  private Pattern typeRulePattern;
  private Map<String, TradeIdRule> tradeIdRules = new HashMap<>(17);

  public AlipayRecordAnalysis() {
    // 配置解析规则
    this.tradeIdRules.put("花呗支付服务费", new TradeIdRule("花呗支付服务费", "服务费", "memo", MERCHANT_ORDER_NO));
    this.tradeIdRules.put("服务费", new TradeIdRule("服务费", "服务费", "memo", NUMBERS));
    this.tradeIdRules.put("花呗交易号", new TradeIdRule("花呗交易号", "销售", "merchantOrderNo", MERCHANT_ORDER_NO));
    this.tradeIdRules.put("佣金", new TradeIdRule("佣金", "佣金", "memo", NUMBERS));
    this.tradeIdRules.put("险", new TradeIdRule("险", "保险", "memo", NUMBERS));
    this.tradeIdRules.put("分销分账", new TradeIdRule("分销分账", "分销", "memo", NUMBERS));
    this.tradeIdRules.put("售后退款", new TradeIdRule("售后退款", "退款", "merchantOrderNo", MERCHANT_ORDER_NO));
    this.tradeIdRules.put("保证金", new TradeIdRule("保证金", "保证金", "memo", NUMBERS));
    this.tradeIdRules.put("红包回退", new TradeIdRule("红包回退", "退款", "merchantOrderNo", MERCHANT_ORDER_NO));
    this.tradeIdRules.put("红包支付", new TradeIdRule("红包支付", "销售", "merchantOrderNo", MERCHANT_ORDER_NO));
    this.tradeIdRules.put("商家保证金理赔", new TradeIdRule("商家保证金理赔", "保险", "memo", NUMBERS));
    this.tradeIdRules.put("退回积分", new TradeIdRule("退回积分", "退回积分", "memo", NUMBERS));
    this.tradeIdRules.put("返点积分", new TradeIdRule("返点积分", "退回积分", "memo", NUMBERS));
    this.tradeIdRules.put("提现", new TradeIdRule("提现", "提现", "memo", NUMBERS));
    this.tradeIdRules.put("扣款", new TradeIdRule("扣款", "扣款", "memo", NUMBERS));
    this.tradeIdRules.put("转账", new TradeIdRule("转账", "转账", "memo", NUMBERS));
    this.tradeIdRules.put("", new TradeIdRule("", "销售", "merchantOrderNo", NUMBERS_END));
  }

  public Pattern getTypeRulePattern() {
    if (null == typeRulePattern) {
      typeRulePattern = Pattern.compile(CHINESE_CHARACTER);
    }
    return typeRulePattern;
  }

  public void setTypeRulePattern(Pattern typeRuleRegex) {
    this.typeRulePattern = typeRuleRegex;
  }

  public Map<String, TradeIdRule> getTradeIdRules() {
    return tradeIdRules;
  }

  public void setTradeIdRules(Map<String, TradeIdRule> tradeIdRules) {
    this.tradeIdRules = tradeIdRules;
  }

  public class TradeIdRule {

    private String type;
    private String target;
    private String valueField;
    private String rule;
    private Pattern rulePattern;

    public TradeIdRule(String type, String target, String valueField, String rule) {
      this.type = type;
      this.target = target;
      this.valueField = valueField;
      this.rule = rule;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getTarget() {
      return target;
    }

    public void setTarget(String target) {
      this.target = target;
    }

    public String getValueField() {
      return valueField;
    }

    public void setValueField(String valueField) {
      this.valueField = valueField;
    }

    public String getRule() {
      return rule;
    }

    public void setRule(String rule) {
      this.rule = rule;
    }

    public Pattern getRulePattern() {
      if (null == rulePattern) {
        rulePattern = Pattern.compile(this.rule);
      }
      return rulePattern;
    }

    public void setRulePattern(Pattern ruleRegex) {
      this.rulePattern = ruleRegex;
    }
  }

}

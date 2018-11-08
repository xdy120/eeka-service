package com.greatonce.oms.domain.enums;

import com.greatonce.core.ValueEnum;

/**
 * @author buer
 * @version 2017-10-24 10:02
 */
public enum MallType implements ValueEnum {
  UNDEFINE(0, "其他"),
  TAOBAO(1001, "淘宝网站"),
  TAOBAO_FX(1002, "淘宝分销"),
  TAOBAO_JX(1003, "淘宝经销"),
  TMALL(1004, "天猫商城"),
  TMALL_HK(1005, "天猫国际"),
  ALIEXPRESS(1006, "速卖通"),
  ALIBABA(1007, "阿里巴巴"),
  JD(1101, "京东商城"),
  VIP(1201, "唯品会"),
  SUNING(1301, "苏宁易购"),
  SUNING_SELF(1302, "苏宁自营"),
  SUNING_SPECIAL(1303, "苏宁特卖"),
  AMAZON_CN(1401, "亚马逊国内"),
  AMAZON_COM(1402, "亚马逊国际"),
  NETEASE_KAO_LA(1501, "网易考拉"),
  BAIDU_MALL(1601, "百度MALL"),
  JU_MEI(1701, "聚美优品"),
  DANGDANG(1801, "当当网站"),
  YOU_ZAN(1901, "有赞"),
  GOME(2001, "国美"),
  SHOPEX(2101, "商派"),
  SHOPEX_FX(2102, "商派分销王"),
  MEI_LI_SHUO(2201, "美丽说"),
  MO_GU_JIE(2202, "蘑菇街"),
  ICBC(2301, "工行融E购"),
  WEIMOB(2401, "微盟"),
  QIAN_MI(2501, "千米网"),
  OK_BUY(2601, "好乐买"),
  QOO10(2701, "趣天"),
  MIA(2801, "蜜芽宝贝"),
  WEI_DIAN(2901, "微店商城"),
  HICHAO(3001, "明星衣橱"),
  BEI_BEI(3101, "贝贝网"),
  CHU_CHU_JIE(3201, "楚楚街"),
  PIN_DUO_DUO(3301, "拼多多"),
  BABY_TREE(3401, "宝宝树"),
  FEI_NIU(3501, "飞牛网"),
  ZHE800(3601, "折800"),
  HAI_ZI_WANG(3701, "孩子王"),
  DA_V_DIAN(3801, "大V店"),
  YUN_JI(3901, "云集"),
  JUANPI(4001, "卷皮"),
  XIAOHONGSHU(4101, "小红书");
  private final String caption;
  private final int value;

  MallType(int value, String caption) {
    this.caption = caption;
    this.value = value;
  }

  @Override
  public int value() {
    return this.value;
  }

  @Override
  public String caption() {
    return this.caption;
  }
}

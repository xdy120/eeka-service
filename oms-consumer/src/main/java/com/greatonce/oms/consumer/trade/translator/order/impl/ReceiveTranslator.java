package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.RegionService;
import com.greatonce.oms.bo.admin.RegionMatchBO;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.admin.MallRegionMapping;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 收货人信息转化.
 *
 * 国外地址直接拼接
 *
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @author lcc
 * @version 2018-09-08
 */
@Component("orderReceiveTranslator")
@TranslatorOrderCondition
public class ReceiveTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  private static final Long FOREIGN_COUNTRY_ID = 9000000001L;
  private static final Long FOREIGN_PROVINCE_ID = 9000000002L;
  private static final Long FOREIGN_CITY_ID = 9000000003L;
  private static final Long FOREIGN_DISTRICT_ID = 9000000004L;
  private static final String FOREIGN_NAME = "其他";
  private static final String RECEIVE_INFO_CACHE_NAME = "RECEIVER_INFO";

  @Autowired
  private RegionService regionService;
  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public void save(OrderTranslatableContext context) {
  }

  @Override
  public void rollback(OrderTranslatableContext context) {

  }

  /**
   * 收货人信息转化逻辑：
   * <p>
   * 只处理转化模式为新建和修改模式的订单
   * 封装收货人信息，并通过地址匹配将平台地址转为OMS地址
   */
  @Override
  public void translate(OrderTranslatableContext context) {
    if (context.getMode() == OrderTranslatableMode.CREATE) {
      MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
      SalesOrder order = context.getSalesOrder();
      SalesOrderSub orderSub = order.getSub();

      Map<String, String> map = (Map<String, String>) redisTemplate.opsForValue()
          .get(RECEIVE_INFO_CACHE_NAME + "::TRADE_ID_" + mallSalesOrderInfo.getTradeId());
      if (!Assert.isEmpty(map)) {
        order.setContact(map.get("consignee"));
        order.setMobile(map.get("mobile"));
        order.setAddress(map.get("address"));
        orderSub.setZipcode(map.get("postCode"));
        String country = map.get("country");
        String provinceName = map.get("provinceName");
        String cityName = map.get("cityName");
        String countyName = map.get("countyName");
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "修改地址：{},{},{},{}->{},{},{},{}",
            mallSalesOrderInfo.getCountry(), mallSalesOrderInfo.getProvince(),
            mallSalesOrderInfo.getCity(), mallSalesOrderInfo.getDistrict(), country, provinceName,
            cityName, countyName);
        mallSalesOrderInfo.setCountry(country);
        mallSalesOrderInfo.setProvince(provinceName);
        mallSalesOrderInfo.setCity(cityName);
        mallSalesOrderInfo.setDistrict(countyName);
      } else {
        order.setContact(mallSalesOrderInfo.getContact());
        order.setMobile(mallSalesOrderInfo.getMobile());
        order.setTelephone(mallSalesOrderInfo.getTelephone());
        order.setAddress(mallSalesOrderInfo.getAddress());
        orderSub.setBuyerEmail(mallSalesOrderInfo.getBuyerEmail());
        orderSub.setZipcode(mallSalesOrderInfo.getZipCode());
      }
      convertAddress(context, order);
    }
  }

  /**
   * 转化地址.
   * 1.判断是否国外地址，如果是国外地址直接组装信息
   * 2.中国地址按照去掉“省”、“市”、“区”的关键字匹配，匹配成功组装信息
   * 3.匹配失败则根据别名匹配，匹配成功组装信息
   * 4.如果仍然匹配失败，组装能匹配到的信息
   */
  private void convertAddress(OrderTranslatableContext context, SalesOrder order) {
    MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
    String country = mallSalesOrderInfo.getCountry();
    String province = mallSalesOrderInfo.getProvince().replace("市", "");
    String city = mallSalesOrderInfo.getCity();
    String district = mallSalesOrderInfo.getDistrict();
    LOGGER.info(context.getSerialNo(), context.getStore(),
        context.getMallSalesOrder().getTradeId(), "匹配地址：{},{},{},{}",
        country, province, city, district);

    if (mallSalesOrderInfo.isForeign()) {
      order.setCountryId(FOREIGN_COUNTRY_ID);
      order.setCountryName(FOREIGN_NAME);
      order.setProvinceId(FOREIGN_PROVINCE_ID);
      order.setProvinceName(FOREIGN_NAME);
      order.setCityId(FOREIGN_CITY_ID);
      order.setCityName(FOREIGN_NAME);
      order.setDistrictId(FOREIGN_DISTRICT_ID);
      order.setDistrictName(FOREIGN_NAME);
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "国外地址：{}", mallSalesOrderInfo.getAddress());
      return;
    }

    RegionMatchBO result = regionService.matchRegion(country, province, city, district);
    if (result.isMatch() && result.isMatchMapping()) {
      MallRegionMapping regionMapping = result.getMapping();
      //封装信息
      order.setCountryId(regionMapping.getCountryId());
      order.setProvinceId(regionMapping.getProvinceId());
      order.setCityId(regionMapping.getCityId());
      order.setDistrictId(regionMapping.getDistrictId());
      order.setCountryName(regionMapping.getCountryName());
      order.setProvinceName(regionMapping.getProvinceName());
      order.setCityName(regionMapping.getCityName());
      order.setDistrictName(regionMapping.getDistrictName());
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "商城区域匹配成功");
      return;
    }
    //封装地址结果
    order.setCountryId(result.getCountry().getRegionId());
    order.setCountryName(result.getCountry().getRegionName());
    if (result.getProvince() != null) {
      order.setProvinceId(result.getProvince().getRegionId());
      order.setProvinceName(result.getProvince().getRegionName());
      if (result.getCity() != null) {
        order.setCityId(result.getCity().getRegionId());
        order.setCityName(result.getCity().getRegionName());
        if (result.getDistrict() != null) {
          order.setDistrictId(result.getDistrict().getRegionId());
          order.setDistrictName(result.getDistrict().getRegionName());
        } else {
          context.getLogs().add(String.format("地址匹配失败，平台地址找不到，区：%s", district));
          context.setPassAudited(false);
          LOGGER.info(context.getSerialNo(), context.getStore(),
              context.getMallSalesOrder().getTradeId(), "地址匹配失败，区级：{}找不到", district);
        }
      } else {
        context.getLogs()
            .add(String.format("地址匹配失败，平台地址找不到，市：%s、区：%s", city, district));
        context.setPassAudited(false);
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "地址匹配失败，市级：{}找不到", city);
      }
    } else {
      context.getLogs()
          .add(String.format("地址匹配失败，平台地址找不到，省：%s、市：%s、区：%s", province, city, district));
      context.setPassAudited(false);
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "地址匹配失败，省级：{}找不到", province);
    }
  }
}

package com.greatonce.oms.custom.eeka.controller;

import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.service.StockChangeService;
import com.qimen.api.response.StockchangeReportResponse;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EekaApiCondition
@RequestMapping(value = "/eeka")
public class SynchronizeVipStockToFMSController {
  private static final Logger LOGGER = LoggerFactory.getLogger(
      SynchronizeVipStockToFMSController.class);

  private static final String LAST_DATE_TO_FMS = "lastDateToFMS";
  @Resource
  private StockChangeService stockChangeService;

  @Resource
  private RedisTemplate redisTemplate;

  @PostConstruct
  public void init() {
    redisTemplate.opsForValue().set(LAST_DATE_TO_FMS,null);
  }


  @GetMapping(value = "/synchronizeVipStockToFMS")
  public StockchangeReportResponse confirm(@Param("beginDate") String beginDate, @Param("endDate") String endDate){
    stockChangeService.getStockChange(beginDate, endDate);
    return new StockchangeReportResponse();
  }

}

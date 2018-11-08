package com.greatonce.oms.job.executor.report;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.WebUtil;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.domain.base.User;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author SKP
 */
@DisallowConcurrentExecution
public class SalesRefundExportJob implements Job {

  private Logger LOGGER = LoggerFactory.getLogger(SalesRefundExportJob.class);
  @Value("${service.web.url:http://127.0.0.1:8181/api}")
  private String webUrl;
  private static String  URL_SUFFIX = "/report/auto/exportSalesRefundForDay";
  @Resource
  private Scheduler globalScheduler;
  @Autowired
  private UserService userService;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    String loginName = jobExecutionContext.getJobDetail().getJobDataMap().getString("loginName");
    Long days = jobExecutionContext.getJobDetail().getJobDataMap().getLong("days");
    User user = new User();
    user.setLoginName(loginName);
    user = userService.getByExample(user);
    if (Assert.isNull(user)) {
      LOGGER.error("定时导出退货(仅退款)数据任务出错。原因：loginName不存在或错误！loginName:{}", loginName);
      JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
      try {
        globalScheduler.pauseJob(jobKey);
        LOGGER.error("已停止定时导出退货(仅退款)数据任务。");
      } catch (SchedulerException e) {
        LOGGER.error("停止定时导出退货(仅退款)数据任务出错。异常信息：{}", e);
      }
    }
    Map<String, String> map = new HashMap<>(4);
    String fileName = "退货(仅退款)数据 " + DateTimeUtil.format(LocalDate.now().minusDays(days));
    map.put("fileName", fileName);
    map.put("userId", String.valueOf(user.getUserId()));
    map.put("appliedTimeBegin", DateTimeUtil.format(
        LocalDateTime.of(LocalDate.now().minusDays(days), LocalTime.MIN)));
    map.put("appliedTimeEnd",
        DateTimeUtil.format(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX)));

    try {
      WebUtil.doGet(webUrl + URL_SUFFIX, map);
    } catch (IOException e) {
      LOGGER.error("定时发送请求导出excel失败！请求URL：{}；请求参数：{}", (webUrl + URL_SUFFIX),
          JsonUtil.toJson(map));
    }
  }
}

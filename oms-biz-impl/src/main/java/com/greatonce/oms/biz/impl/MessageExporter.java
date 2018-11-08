package com.greatonce.oms.biz.impl;

import com.greatonce.core.database.Query;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.ExportHelper;
import com.greatonce.oms.biz.PageFunction;
import com.greatonce.oms.biz.message.MessageService;
import com.greatonce.oms.domain.enums.message.MessageType;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.excel.ExcelExport;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import java.time.LocalDateTime;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 消息导出.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-29
 */
@Component
public class MessageExporter {

  @Autowired
  private MessageService messageService;
  @Autowired
  private AsyncTaskExecutor bizExecutor;

  /**
   * 导出excel.
   */
  public <T, Q extends Query> void exportExcel(BizService<T, Q> bizService,
      ExcelHeaderCollection<T> headers, Q query, String fileName) {
    exportExcel(bizService::listPage, headers, query, fileName);
  }

  /**
   * 导出excel.
   */
  public <T, Q extends Query> void exportExcel(PageFunction<Q, T> function,
      ExcelHeaderCollection<T> headers, Q query, String fileName) {
    Message message = new Message();
    message.setContent(fileName + "生成中...");
    message.setMessageType(MessageType.DATA_EXPORT);
    message.setUserId(BizContext.getUserId());
    message.setExpirationTime(LocalDateTime.now().plusMonths(1));
    messageService.create(message);

    ExcelExport<T> excelExport = new ExcelExport<>(String.valueOf(message.getMessageId()), headers);
    bizExecutor.execute(() -> {
      ExportHelper.export(function, excelExport, query);
      excelExport.writeFile();
      message.setMessageId(message.getMessageId());
      message.setContent(fileName);
      message.setUrl("/export/" + message.getMessageId() + ".xlsx");
      messageService.modify(message);
    });
  }

  public <T> void exportExcel(ExcelHeaderCollection<T> headers, Collection<T> data,
      String fileName) {
    Message message = new Message();
    message.setContent(fileName + "生成中...");
    message.setMessageType(MessageType.DATA_EXPORT);
    message.setUserId(BizContext.getUserId());
    message.setExpirationTime(LocalDateTime.now().plusMonths(1));
    messageService.create(message);

    ExcelExport<T> excelExport = new ExcelExport<>(String.valueOf(message.getMessageId()), headers);
    bizExecutor.execute(() -> {
      excelExport.writeDataToSheet(data);
      excelExport.writeFile();
      Message messageUpdate = new Message();
      messageUpdate.setMessageId(message.getMessageId());
      messageUpdate.setContent(fileName);
      messageUpdate.setUrl("/export/" + message.getMessageId() + ".xlsx");
      messageService.modify(messageUpdate);
    });
  }
}

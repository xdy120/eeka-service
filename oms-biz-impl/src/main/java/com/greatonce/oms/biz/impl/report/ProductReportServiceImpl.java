package com.greatonce.oms.biz.impl.report;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.ExportHelper;
import com.greatonce.oms.biz.message.MessageService;
import com.greatonce.oms.biz.report.ProductReportService;
import com.greatonce.oms.bo.report.ProductReturnBO;
import com.greatonce.oms.bo.report.ProductSalesBO;
import com.greatonce.oms.dao.report.ProductReportDao;
import com.greatonce.oms.domain.enums.message.MessageType;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.query.report.ProductReturnQuery;
import com.greatonce.oms.query.report.ProductSalesQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelExport;
import com.greatonce.oms.util.excel.ExcelExportConfig;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Supplier <br/> 供应商
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ProductReportServiceImpl implements ProductReportService {

  @Autowired
  private ProductReportDao dao;
  @Autowired
  private MessageService messageService;
  @Resource
  private AsyncTaskExecutor bizExecutor;

  @Override
  public PageList<ProductReturnBO> exportProductReturnList(ProductReturnQuery productReturnQuery,
      int page, int pageSize) {
    return dao.exportProductReturnList(productReturnQuery, page, pageSize);
  }

  @Override
  public PageList<ProductSalesBO> exportProductSalesList(ProductSalesQuery productSalesQuery,
      int page, int pageSize) {
    if(Assert.isTrue(productSalesQuery.isDeliveryOnly())){
      return dao.exportDeliveriedProductSalesList(productSalesQuery, page, pageSize);
    }else {
      return dao.exportProductSalesList(productSalesQuery, page, pageSize);
    }
  }

  @Override
  public void exportReturn(String fileName, ProductReturnQuery productReturnQuery) {
    ExcelExportConfig<ProductReturnBO> exportConfig = new ExcelExportConfig<>();
    //消息的id 就是文件的名称
    exportConfig.setFileName(String.valueOf(messageService.getIdGenerator().next()));
    exportConfig.setSheetName("book");
    ExcelHeaderCollection<ProductReturnBO> headers = new ExcelHeaderCollection<>();
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("规格名称", x -> x.getSkuName());
    headers.add("退货量", x -> Assert.isNull(x.getQuantity()) ? "" : String.valueOf(x.getQuantity()));
    headers.add("退款金额",
        x -> Assert.isNull(x.getRefundAmount()) ? "" : String.valueOf(x.getRefundAmount()));
    exportConfig.setHeader(headers);

    //保存消息
    Message message = new Message();
    message.setContent(fileName + "生成中...");
    message.setMessageType(MessageType.DATA_EXPORT);
    message.setUserId(BizContext.getUserId());
    message.setExpirationTime(LocalDateTime.now().plusMonths(1));
    message.setMessageId(Long.valueOf(exportConfig.getFileName()));
    messageService.create(message);

    ExcelExport<ProductReturnBO> export = new ExcelExport<>(exportConfig.getFileName(), headers);
    bizExecutor.execute(() -> {
      ExportHelper.export((query, page, pageSize) ->
          exportProductReturnList(query, page, pageSize), export, productReturnQuery);
      export.writeFile();
      //导出完,修改消息
      Message messageUpdate = new Message();
      messageUpdate.setMessageId(message.getMessageId());
      messageUpdate.setContent(fileName);
      messageUpdate.setUrl("/export/" + message.getMessageId() + ".xlsx");
      messageService.modify(messageUpdate);
    });
  }

  @Override
  public void exportSales(String fileName, ProductSalesQuery productSalesQuery) {
    ExcelExportConfig<ProductSalesBO> exportConfig = new ExcelExportConfig<>();
    exportConfig.setFileName(String.valueOf(messageService.getIdGenerator().next()));
    exportConfig.setSheetName("book");
    ExcelHeaderCollection<ProductSalesBO> headers = new ExcelHeaderCollection<>();
    headers.add("支付时间", x -> DateTimeUtil.format(x.getMallPaidTime()));
    headers.add("商品编码", x -> x.getProductCode());
    headers.add("商品名称", x -> x.getProductName());
    headers.add("规格编码", x -> x.getSkuCode());
    headers.add("规格名称", x -> x.getSkuName());
    headers.add("销量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("结算金额", x -> FormatUtil.formatDouble(x.getSettlementAmount()));
    headers.add("财务成本", x -> FormatUtil.formatDouble(x.getCostPrice()));

    //保存消息
    Message message = new Message();
    message.setContent(fileName + "生成中...");
    message.setMessageType(MessageType.DATA_EXPORT);
    message.setUserId(BizContext.getUserId());
    message.setExpirationTime(LocalDateTime.now().plusMonths(1));
    message.setMessageId(Long.valueOf(exportConfig.getFileName()));
    messageService.create(message);

    ExcelExport<ProductSalesBO> export = new ExcelExport<>(exportConfig.getFileName(), headers);
    bizExecutor.execute(() -> {
      ExportHelper.export((query, page, pageSize) ->
          exportProductSalesList(query, page, pageSize), export, productSalesQuery);
      export.writeFile();
    });

    Message messageUpdate = new Message();
    messageUpdate.setMessageId(message.getMessageId());
    messageUpdate.setContent(fileName);
    messageUpdate.setUrl("/export/" + message.getMessageId() + ".xlsx");
    messageService.modify(messageUpdate);
  }
}
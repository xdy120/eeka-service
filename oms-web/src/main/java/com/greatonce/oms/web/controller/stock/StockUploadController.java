package com.greatonce.oms.web.controller.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.stock.StockUploadService;
import com.greatonce.oms.bo.stock.AsyncStockUploadBO;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.query.stock.StockUploadLogQuery;
import com.greatonce.oms.search.service.StockUploadLogQueryService;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author buer
 * @version 2017-12-07 17:19
 */
@RestController
@RequestMapping(value = "/stock/upload")
@CrossOrigin
public class StockUploadController {

  @Resource
  IdGenerator idGenerator;
  @Autowired
  private StockUploadService stockUploadService;
  @Autowired
  private StockUploadLogQueryService stockUploadLogQueryService;
  @Autowired
  private MessageExporter messageExporter;


  @PostMapping(path = "{strategyId}")
  public String storeUpload(@PathVariable("strategyId") Long strategyId) {
    return String.valueOf(stockUploadService.storeUpload(strategyId));
  }

  @PostMapping
  public String uploadAll() {
    return String.valueOf(stockUploadService.allUpload());
  }

  @PostMapping(path = "/cover")
  public String coverUpload(@RequestBody AsyncStockUploadBO content) {
    Assert.notNull(content.getConfigId(), "上传配置不能为空");
    Assert.notEmpty(content.getSkus(), "SKU不能为空");
    String batchNo = String.valueOf(idGenerator.next());
    stockUploadService
        .asyncManualUpload(content, StockUploadType.COVER, true, batchNo, BizContext.getNickname(),
            "手工上传");
    return batchNo;
  }

  @PostMapping(path = "/increment")
  public String incrementUpload(@RequestBody AsyncStockUploadBO content) {
    Assert.notNull(content.getConfigId(), "上传配置不能为空");
    Assert.notEmpty(content.getSkus(), "SKU不能为空");
    String batchNo = String.valueOf(idGenerator.next());
    stockUploadService
        .asyncManualUpload(content, StockUploadType.INCREMENT, false, batchNo,
            BizContext.getNickname(),
            "手工增量上传");
    return batchNo;
  }

  @PostMapping(path = "/fixCover")
  public String fixCover(@RequestBody AsyncStockUploadBO content) {
    Assert.notNull(content.getConfigId(), "上传配置不能为空");
    Assert.notEmpty(content.getSkus(), "SKU不能为空");
    String batchNo = String.valueOf(idGenerator.next());
    stockUploadService
        .asyncManualUpload(content, StockUploadType.COVER, false, batchNo, BizContext.getNickname(),
            "手工覆盖上传");
    return batchNo;
  }

  @GetMapping(path = "/stockUploadLogs/{fileName}")
  public void listStockUploadLogs(@PathVariable("fileName") String fileName,
      StockUploadLogQuery query) {
    ExcelHeaderCollection<Map<String, Object>> headers = new ExcelHeaderCollection<>();
    headers.add("批次号", x -> x.get("batchId").toString());
    headers.add("店铺", x -> x.get("storeName").toString());
    headers.add("商品编码", x -> x.get("productCode").toString());
    headers.add("规格编码", x -> x.get("skuCode").toString());
    headers.add("上传数量", x -> x.get("quantity").toString());
    headers.add("状态", x -> x.get("status").toString());
    headers.add("操作人", x -> x.get("oper").toString());
    headers.add("内容", x -> x.get("message").toString());
//    headers.add("上传日期", x->x.get("@timestamp").toString());
    messageExporter.exportExcel(stockUploadLogQueryService::listLogs, headers, query, fileName);
  }

}

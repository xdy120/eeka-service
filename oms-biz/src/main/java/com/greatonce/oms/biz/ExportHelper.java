package com.greatonce.oms.biz;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.Query;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.util.excel.ExcelExport;

/**
 * 导出工具类
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-06-28
 */
public class ExportHelper {

  /**
   * 导出Excel.
   */
  public static <T, Q extends Query> void export(BizService<T, Q> bizService,
      ExcelExport<T> excelExport, Q q) {

    int pageSize = 500;
    PageList<T> pageList = bizService.listPage(q, 1, pageSize);
    if (!Assert.isEmpty(pageList.getData())) {
      excelExport.writeDataToSheet(pageList.getData());
      int pages = (pageList.getTotal() + pageSize - 1) / pageSize;
      if (pages > 1) {
        for (int i = 2; i <= pages; i++) {
          pageList = bizService.listPage(q, i, pageSize);
          excelExport.writeDataToSheet(pageList.getData());
        }
      }
    }
  }

  /**
   * 导出Excel.
   *
   * @param function 分页查询函数
   * @param excelExport 导出配置
   * @param q 过滤条件
   */
  public static <T, Q extends Query> void export(PageFunction<Q, T> function,
      ExcelExport<T> excelExport, Q q) {
    int pageSize = 500;
    PageList<T> pageList = function.listPage(q, 1, pageSize);
    if (!Assert.isEmpty(pageList.getData())) {
      excelExport.writeDataToSheet(pageList.getData());
      int pages = (pageList.getTotal() + pageSize - 1) / pageSize;
      if (pages > 1) {
        for (int i = 2; i <= pages; i++) {
          pageList = function.listPage(q, i, pageSize);
          excelExport.writeDataToSheet(pageList.getData());
        }
      }
    }
  }
}

package com.greatonce.oms.util.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.ResourceUtils;

/**
 * Excel导出.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta cca
 * @version 2018-07-24
 */
public class ExcelExport<T> {

  private final String fileName;
  private final SXSSFWorkbook workbook;
  private final ExcelHeaderCollection<T> headers;

  private int sheetIndex = 1;
  private Sheet sheet;

  public ExcelExport(String fileName, ExcelHeaderCollection<T> headers) {
    this.fileName = fileName;
    this.headers = headers;
    this.workbook = new SXSSFWorkbook();
    this.createSheet();
  }

  private void createSheet() {
    this.sheet = workbook.createSheet("sheet" + this.sheetIndex++);
    this.createHeaderRow();
  }

  private void createHeaderRow() {
    Row header = sheet.createRow(0);
    for (int i = 0; i < headers.size(); i++) {
      //创建头部的单元格
      Cell headerCell = header.createCell(i);
      //设置头的名称
      headerCell.setCellValue(headers.get(i).getHeaderName());
    }
  }

  public void writeDataToSheet(Collection<T> data) {
    Iterator<T> iterator = data.iterator();
    int index = sheet.getLastRowNum();
    if (index >= 1000000) {
      this.createSheet();
      index = sheet.getLastRowNum();
    }
    while (iterator.hasNext()) {
      Row row = sheet.createRow(++index);
      T obj = iterator.next();
      for (int i = 0; i < headers.size(); i++) {
        Cell cell = row.createCell(i);
        String textValue = headers.get(i).getFun().apply(obj);
        cell.setCellValue(textValue);
      }
    }
  }


  public void writeFile() {
    OutputStream outputStream = null;
    try {

      String path = ResourceUtils.getURL("").getPath();

      File file = new File(path + "/file/export");
      if (!file.exists() && !file.isDirectory()) {
        final boolean mkdirs = file.mkdirs();
      }

      outputStream = new FileOutputStream(path + "/file/export/" + fileName + ".xlsx");
      try {
        workbook.write(outputStream);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        if (outputStream != null) {
          outputStream.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

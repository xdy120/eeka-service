package com.greatonce.oms.util.excel;

import java.util.List;

/**
 * ExportConfig
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta cca
 * @version 2018-07-24
 */
public class ExcelExportConfig<T> {

  private String fileName;

  private String sheetName;

  private ExcelHeaderCollection<T> header;


  public String getSheetName() {
    return sheetName;
  }

  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }

  public List<ExcelHeader<T>> getHeader() {
    return header;
  }

  public void setHeader(ExcelHeaderCollection<T> header) {
    this.header = header;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

}


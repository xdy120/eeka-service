package com.greatonce.oms.util.excel;

import java.util.function.Function;

/**
 * Excel列配置
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta cca
 * @version 2018-07-24
 */
public class ExcelHeader<T> {

  private String headerName;

  private Function<T, String> fun;

  public Function<T, String> getFun() {
    return fun;
  }

  public void setFun(Function<T, String> fun) {
    this.fun = fun;
  }

  public ExcelHeader(String headerName, Function<T, String> fun) {
    this.headerName = headerName;
    this.fun = fun;
  }

  public ExcelHeader() {
  }

  public String getHeaderName() {
    return headerName;
  }

  public void setHeaderName(String headerName) {
    this.headerName = headerName;
  }


}

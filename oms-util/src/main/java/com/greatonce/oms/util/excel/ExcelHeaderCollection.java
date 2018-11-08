package com.greatonce.oms.util.excel;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Excel列集合.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-24
 */
public class ExcelHeaderCollection<T> extends ArrayList<ExcelHeader<T>> {

  public void add(String header, Function<T, String> function) {
    this.add(new ExcelHeader<T>(header, function));
  }
}

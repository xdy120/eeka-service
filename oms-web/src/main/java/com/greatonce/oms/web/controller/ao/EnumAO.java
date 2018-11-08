package com.greatonce.oms.web.controller.ao;

/**
 * @author buer
 * @version 2017-10-27 9:41
 */
public class EnumAO {

  private String title;
  private String caption;
  private Integer value;

  public EnumAO( String title, String caption, Integer value) {
    this.title = title;
    this.caption = caption;
    this.value = value;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }
}

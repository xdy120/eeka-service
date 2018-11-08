package com.greatonce.oms.custom.kingdee.request;

import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.response.K3CloudCustomerSaveResponse;

/**
 * 客户保存请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudCustomerSaveRequest extends K3CloudRequest<K3CloudCustomerSaveResponse> {

  @Override
  public String formid() {
    return "BD_Customer";
  }

  @Override
  public Class<K3CloudCustomerSaveResponse> getResponseClass() {
    return K3CloudCustomerSaveResponse.class;
  }

  @Override
  public Object content() {
    return this.customer;
  }

  private Customer customer;

  public void setCustomer(Customer customer){
    this.customer = customer;
  }

  public static class Customer{

    private FNumber FCreateOrgId;
    private String FNumber;
    private FNumber FUseOrgId;
    private String FName;
    private String FADDRESS;
    private String FZIP;
    private String FTEL;
    private String FFAX;

    public com.greatonce.oms.custom.kingdee.entity.FNumber getFCreateOrgId() {
      return FCreateOrgId;
    }

    public void setFCreateOrgId(com.greatonce.oms.custom.kingdee.entity.FNumber FCreateOrgId) {
      this.FCreateOrgId = FCreateOrgId;
    }

    public String getFNumber() {
      return FNumber;
    }

    public void setFNumber(String FNumber) {
      this.FNumber = FNumber;
    }

    public com.greatonce.oms.custom.kingdee.entity.FNumber getFUseOrgId() {
      return FUseOrgId;
    }

    public void setFUseOrgId(com.greatonce.oms.custom.kingdee.entity.FNumber FUseOrgId) {
      this.FUseOrgId = FUseOrgId;
    }

    public String getFName() {
      return FName;
    }

    public void setFName(String FName) {
      this.FName = FName;
    }

    public String getFADDRESS() {
      return FADDRESS;
    }

    public void setFADDRESS(String FADDRESS) {
      this.FADDRESS = FADDRESS;
    }

    public String getFZIP() {
      return FZIP;
    }

    public void setFZIP(String FZIP) {
      this.FZIP = FZIP;
    }

    public String getFTEL() {
      return FTEL;
    }

    public void setFTEL(String FTEL) {
      this.FTEL = FTEL;
    }

    public String getFFAX() {
      return FFAX;
    }

    public void setFFAX(String FFAX) {
      this.FFAX = FFAX;
    }
  }

}

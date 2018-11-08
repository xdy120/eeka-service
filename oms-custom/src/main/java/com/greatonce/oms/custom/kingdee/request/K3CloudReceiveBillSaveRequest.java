package com.greatonce.oms.custom.kingdee.request;

import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.response.K3CloudReceiveBillSaveResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 收款单保存请求.
 *
 * @author skp
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-20
 */
public class K3CloudReceiveBillSaveRequest extends K3CloudRequest<K3CloudReceiveBillSaveResponse> {

  @Override
  public String formid() {
    return "AR_RECEIVEBILL";
  }

  @Override
  public Class<K3CloudReceiveBillSaveResponse> getResponseClass() {
    return K3CloudReceiveBillSaveResponse.class;
  }

  @Override
  public Object content() {
    return receiveBill;
  }

  private ReceiveBill receiveBill;

  public void setReceiveBill( ReceiveBill receiveBill) {
    this.receiveBill = receiveBill;
  }

  public static class ReceiveBill {
    private FNumber FBillTypeID;
    private FNumber FSETTLECUR;
    private LocalDateTime FDATE;
    private String FCONTACTUNITTYPE;
    private FNumber FCONTACTUNIT;
    private String FPAYUNITTYPE;
    private FNumber FPAYUNIT;
    private FNumber FCURRENCYID;
    private FNumber FPAYORGID;
    private List<FRECEIVEBILLENTRY> FRECEIVEBILLENTRY;

    public FNumber getFBillTypeID() {
      return FBillTypeID;
    }

    public void setFBillTypeID(FNumber FBillTypeID) {
      this.FBillTypeID = FBillTypeID;
    }

    public FNumber getFSETTLECUR() {
      return FSETTLECUR;
    }

    public void setFSETTLECUR(FNumber FSETTLECUR) {
      this.FSETTLECUR = FSETTLECUR;
    }

    public LocalDateTime getFDATE() {
      return FDATE;
    }

    public void setFDATE(LocalDateTime FDATE) {
      this.FDATE = FDATE;
    }

    public String getFCONTACTUNITTYPE() {
      return FCONTACTUNITTYPE;
    }

    public void setFCONTACTUNITTYPE(String FCONTACTUNITTYPE) {
      this.FCONTACTUNITTYPE = FCONTACTUNITTYPE;
    }

    public FNumber getFCONTACTUNIT() {
      return FCONTACTUNIT;
    }

    public void setFCONTACTUNIT(FNumber FCONTACTUNIT) {
      this.FCONTACTUNIT = FCONTACTUNIT;
    }

    public String getFPAYUNITTYPE() {
      return FPAYUNITTYPE;
    }

    public void setFPAYUNITTYPE(String FPAYUNITTYPE) {
      this.FPAYUNITTYPE = FPAYUNITTYPE;
    }

    public FNumber getFPAYUNIT() {
      return FPAYUNIT;
    }

    public void setFPAYUNIT(FNumber FPAYUNIT) {
      this.FPAYUNIT = FPAYUNIT;
    }

    public FNumber getFCURRENCYID() {
      return FCURRENCYID;
    }

    public void setFCURRENCYID(FNumber FCURRENCYID) {
      this.FCURRENCYID = FCURRENCYID;
    }

    public FNumber getFPAYORGID() {
      return FPAYORGID;
    }

    public void setFPAYORGID(FNumber FPAYORGID) {
      this.FPAYORGID = FPAYORGID;
    }

    public List<K3CloudReceiveBillSaveRequest.FRECEIVEBILLENTRY> getFRECEIVEBILLENTRY() {
      return FRECEIVEBILLENTRY;
    }

    public void setFRECEIVEBILLENTRY(
        List<K3CloudReceiveBillSaveRequest.FRECEIVEBILLENTRY> FRECEIVEBILLENTRY) {
      this.FRECEIVEBILLENTRY = FRECEIVEBILLENTRY;
    }
  }

  public static class FRECEIVEBILLENTRY {
    private FNumber FSETTLETYPEID;
    private FNumber FPURPOSEID;
    private Double FRECTOTALAMOUNTFOR;
    private String F_PAEZ_Text1;
    private String F_PAEZ_Text2;
    private FNumber FACCOUNTID;

    public FNumber getFSETTLETYPEID() {
      return FSETTLETYPEID;
    }

    public void setFSETTLETYPEID(FNumber FSETTLETYPEID) {
      this.FSETTLETYPEID = FSETTLETYPEID;
    }

    public FNumber getFPURPOSEID() {
      return FPURPOSEID;
    }

    public void setFPURPOSEID(FNumber FPURPOSEID) {
      this.FPURPOSEID = FPURPOSEID;
    }

    public Double getFRECTOTALAMOUNTFOR() {
      return FRECTOTALAMOUNTFOR;
    }

    public void setFRECTOTALAMOUNTFOR(Double FRECTOTALAMOUNTFOR) {
      this.FRECTOTALAMOUNTFOR = FRECTOTALAMOUNTFOR;
    }

    public String getF_PAEZ_Text1() {
      return F_PAEZ_Text1;
    }

    public void setF_PAEZ_Text1(String f_PAEZ_Text1) {
      F_PAEZ_Text1 = f_PAEZ_Text1;
    }

    public String getF_PAEZ_Text2() {
      return F_PAEZ_Text2;
    }

    public void setF_PAEZ_Text2(String f_PAEZ_Text2) {
      F_PAEZ_Text2 = f_PAEZ_Text2;
    }

    public FNumber getFACCOUNTID() {
      return FACCOUNTID;
    }

    public void setFACCOUNTID(FNumber FACCOUNTID) {
      this.FACCOUNTID = FACCOUNTID;
    }
  }

}

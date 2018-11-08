package com.greatonce.oms.custom.kingdee.request;

import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.response.K3CloudStockOutSaveResponse;
import java.util.List;

/**
 * 出库单保存请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudStockOutSaveRequest extends K3CloudRequest<K3CloudStockOutSaveResponse> {

  @Override
  public String formid() {
    return "SAL_OUTSTOCK";
  }

  @Override
  public Class<K3CloudStockOutSaveResponse> getResponseClass() {
    return K3CloudStockOutSaveResponse.class;
  }

  @Override
  public Object content() {
    return salesStockOut;
  }

  private SalesStockOut salesStockOut;

  public void setSalesStockOut(SalesStockOut salesStockOut) {
    this.salesStockOut = salesStockOut;
  }

  public static class SalesStockOut {

    private FNumber FStockOrgId;
    private FNumber FCustomerID;
    private FNumber FBillTypeID;
    private FNumber FSettleCurrID;
    private FNumber FSaleDeptID;
    private FNumber F_PAEZ_Assistant1;
    private String F_PAEZ_Text;
    private String FCarriageNO;
    private FNumber F_PAEZ_Assistant;
    private SubHeadEntity SubHeadEntity;
    private List<Fentity> Fentity;

    public FNumber getFStockOrgId() {
      return FStockOrgId;
    }

    public void setFStockOrgId(FNumber FStockOrgId) {
      this.FStockOrgId = FStockOrgId;
    }

    public FNumber getFCustomerID() {
      return FCustomerID;
    }

    public void setFCustomerID(FNumber FCustomerID) {
      this.FCustomerID = FCustomerID;
    }

    public FNumber getFBillTypeID() {
      return FBillTypeID;
    }

    public void setFBillTypeID(FNumber FBillTypeID) {
      this.FBillTypeID = FBillTypeID;
    }

    public FNumber getFSettleCurrID() {
      return FSettleCurrID;
    }

    public void setFSettleCurrID(FNumber FSettleCurrID) {
      this.FSettleCurrID = FSettleCurrID;
    }

    public FNumber getFSaleDeptID() {
      return FSaleDeptID;
    }

    public void setFSaleDeptID(FNumber FSaleDeptID) {
      this.FSaleDeptID = FSaleDeptID;
    }

    public FNumber getF_PAEZ_Assistant1() {
      return F_PAEZ_Assistant1;
    }

    public void setF_PAEZ_Assistant1(FNumber f_PAEZ_Assistant1) {
      F_PAEZ_Assistant1 = f_PAEZ_Assistant1;
    }

    public String getFCarriageNO() {
      return FCarriageNO;
    }

    public void setFCarriageNO(String FCarriageNO) {
      this.FCarriageNO = FCarriageNO;
    }

    public FNumber getF_PAEZ_Assistant() {
      return F_PAEZ_Assistant;
    }

    public void setF_PAEZ_Assistant(FNumber f_PAEZ_Assistant) {
      F_PAEZ_Assistant = f_PAEZ_Assistant;
    }

    public String getF_PAEZ_Text() {
      return F_PAEZ_Text;
    }

    public void setF_PAEZ_Text(String f_PAEZ_Text) {
      F_PAEZ_Text = f_PAEZ_Text;
    }

    public K3CloudStockOutSaveRequest.SubHeadEntity getSubHeadEntity() {
      return SubHeadEntity;
    }

    public void setSubHeadEntity(
        K3CloudStockOutSaveRequest.SubHeadEntity subHeadEntity) {
      SubHeadEntity = subHeadEntity;
    }

    public List<K3CloudStockOutSaveRequest.Fentity> getFentity() {
      return Fentity;
    }

    public void setFentity(
        List<K3CloudStockOutSaveRequest.Fentity> fentity) {
      Fentity = fentity;
    }
  }

  public static class SubHeadEntity {

    private Integer FExchangeRate;

    public Integer getFExchangeRate() {
      return FExchangeRate;
    }

    public void setFExchangeRate(Integer FExchangeRate) {
      this.FExchangeRate = FExchangeRate;
    }
  }

  public static class Fentity {
    private FNumber FMATERIALID;
    private FNumber FStockID;
    private Double FTaxRate;
    private Double FTaxPrice;
    private Integer FRealQty;

    public FNumber getFMATERIALID() {
      return FMATERIALID;
    }

    public void setFMATERIALID(FNumber FMATERIALID) {
      this.FMATERIALID = FMATERIALID;
    }

    public FNumber getFStockID() {
      return FStockID;
    }

    public void setFStockID(FNumber FStockID) {
      this.FStockID = FStockID;
    }

    public Double getFTaxRate() {
      return FTaxRate;
    }

    public void setFTaxRate(Double FTaxRate) {
      this.FTaxRate = FTaxRate;
    }

    public Double getFTaxPrice() {
      return FTaxPrice;
    }

    public void setFTaxPrice(Double FTaxPrice) {
      this.FTaxPrice = FTaxPrice;
    }

    public Integer getFRealQty() {
      return FRealQty;
    }

    public void setFRealQty(Integer FRealQty) {
      this.FRealQty = FRealQty;
    }
  }

}

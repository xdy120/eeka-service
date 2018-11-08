package com.greatonce.oms.custom.kingdee.request;

import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.response.K3CloudPurchaseReturnSaveResponse;

import java.util.List;

/**
 * 采购退货单保存请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudPurchaseReturnSaveRequest extends
    K3CloudRequest<K3CloudPurchaseReturnSaveResponse> {

  @Override
  public String formid() {
    return "PUR_MRB";
  }

  @Override
  public Class<K3CloudPurchaseReturnSaveResponse> getResponseClass() {
    return K3CloudPurchaseReturnSaveResponse.class;
  }

  @Override
  public Object content() {
    return purchaseReturnOut;
  }

  private PurchaseReturnOut purchaseReturnOut;

  public void setPurchaseReturnOut(PurchaseReturnOut purchaseReturnOut) {
    this.purchaseReturnOut = purchaseReturnOut;
  }

  public static class PurchaseReturnOut {

    private FNumber FPurchaseOrgId;
    private FNumber FStockOrgId;
    private FNumber FBillTypeID;
    private FNumber FSupplierId;
    private SubHeadEntity SubHeadEntity;
    private List<FPURMRBENTRY> FPURMRBENTRY;

    public FNumber getFPurchaseOrgId() {
      return FPurchaseOrgId;
    }

    public void setFPurchaseOrgId(FNumber FPurchaseOrgId) {
      this.FPurchaseOrgId = FPurchaseOrgId;
    }

    public FNumber getFStockOrgId() {
      return FStockOrgId;
    }

    public void setFStockOrgId(FNumber FStockOrgId) {
      this.FStockOrgId = FStockOrgId;
    }

    public FNumber getFBillTypeID() {
      return FBillTypeID;
    }

    public void setFBillTypeID(FNumber FBillTypeID) {
      this.FBillTypeID = FBillTypeID;
    }

    public FNumber getFSupplierId() {
      return FSupplierId;
    }

    public void setFSupplierId(FNumber FSupplierId) {
      this.FSupplierId = FSupplierId;
    }

    public K3CloudPurchaseReturnSaveRequest.SubHeadEntity getSubHeadEntity() {
      return SubHeadEntity;
    }

    public void setSubHeadEntity(
        K3CloudPurchaseReturnSaveRequest.SubHeadEntity subHeadEntity) {
      SubHeadEntity = subHeadEntity;
    }

    public List<K3CloudPurchaseReturnSaveRequest.FPURMRBENTRY> getFPURMRBENTRY() {
      return FPURMRBENTRY;
    }

    public void setFPURMRBENTRY(
        List<K3CloudPurchaseReturnSaveRequest.FPURMRBENTRY> FPURMRBENTRY) {
      this.FPURMRBENTRY = FPURMRBENTRY;
    }
  }

  public static class SubHeadEntity {

    private Integer FExchangeRate;

    public SubHeadEntity(Integer FExchangeRate) {
      this.FExchangeRate = FExchangeRate;
    }

    public Integer getFExchangeRate() {
      return FExchangeRate;
    }

    public void setFExchangeRate(Integer FExchangeRate) {
      this.FExchangeRate = FExchangeRate;
    }
  }

  public static class FPURMRBENTRY {

    private FNumber FMATERIALID;
    private FNumber FStockID;
    private Integer FTaxRate;
    private Integer FRMREALQTY;
    private Double FTaxPrice;

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

    public Integer getFTaxRate() {
      return FTaxRate;
    }

    public void setFTaxRate(Integer FTaxRate) {
      this.FTaxRate = FTaxRate;
    }

    public Integer getFRMREALQTY() {
      return FRMREALQTY;
    }

    public void setFRMREALQTY(Integer FRMREALQTY) {
      this.FRMREALQTY = FRMREALQTY;
    }

    public Double getFTaxPrice() {
      return FTaxPrice;
    }

    public void setFTaxPrice(Double FTaxPrice) {
      this.FTaxPrice = FTaxPrice;
    }
  }

}

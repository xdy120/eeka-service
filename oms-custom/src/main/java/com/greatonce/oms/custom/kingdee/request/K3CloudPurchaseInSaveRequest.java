package com.greatonce.oms.custom.kingdee.request;

import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.response.K3CloudPurchaseInSaveResponse;

import java.util.List;

/**
 * 采购入库单保存请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudPurchaseInSaveRequest extends K3CloudRequest<K3CloudPurchaseInSaveResponse> {

  @Override
  public String formid() {
    return "STK_InStock";
  }

  @Override
  public Class<K3CloudPurchaseInSaveResponse> getResponseClass() {
    return K3CloudPurchaseInSaveResponse.class;
  }

  @Override
  public Object content() {
    return this.purchaseIn;
  }

  private PurchaseIn purchaseIn;

  public void setPurchaseIn(PurchaseIn purchaseIn) {
    this.purchaseIn = purchaseIn;
  }

  public static class PurchaseIn {

    private FNumber FPurchaseOrgId;
    private FNumber FStockOrgId;
    private FNumber FBillTypeID;
    private FNumber FSupplierId;
    private SubHeadEntity SubHeadEntity;
    private List<FInStockEntry> FInStockEntry;

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

    public K3CloudPurchaseInSaveRequest.SubHeadEntity getSubHeadEntity() {
      return SubHeadEntity;
    }

    public void setSubHeadEntity(
        K3CloudPurchaseInSaveRequest.SubHeadEntity subHeadEntity) {
      SubHeadEntity = subHeadEntity;
    }

    public List<K3CloudPurchaseInSaveRequest.FInStockEntry> getFInStockEntry() {
      return FInStockEntry;
    }

    public void setFInStockEntry(
        List<K3CloudPurchaseInSaveRequest.FInStockEntry> FInStockEntry) {
      this.FInStockEntry = FInStockEntry;
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

  public static class FInStockEntry {

    private FNumber FMATERIALID;
    private FNumber FStockID;
    private Integer FTaxRate;
    private Integer FRealQty;
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

    public Integer getFRealQty() {
      return FRealQty;
    }

    public void setFRealQty(Integer FRealQty) {
      this.FRealQty = FRealQty;
    }

    public Double getFTaxPrice() {
      return FTaxPrice;
    }

    public void setFTaxPrice(Double FTaxPrice) {
      this.FTaxPrice = FTaxPrice;
    }
  }
}

package com.greatonce.oms.custom.kingdee.request;

import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.response.K3CloudStockReturnSaveResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退货单保存请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudStockReturnSaveRequest extends K3CloudRequest<K3CloudStockReturnSaveResponse> {

  @Override
  public String formid() {
    return "SAL_RETURNSTOCK";
  }

  @Override
  public Class<K3CloudStockReturnSaveResponse> getResponseClass() {
    return K3CloudStockReturnSaveResponse.class;
  }

  @Override
  public Object content() {
    return this.salesStockReturn;
  }

  private SalesStockReturn salesStockReturn;

  public void setSalesStockReturn(SalesStockReturn salesStockReturn) {
    this.salesStockReturn = salesStockReturn;
  }

  public static class SalesStockReturn {
    private FNumber FSaleOrgId;
    private FNumber FRetcustId;
    private FNumber FBillTypeID;
    private FNumber FSettleCurrId;
    private String F_PAEZ_Text;
    private FNumber F_PAEZ_Assistant;
    private FNumber FStockOrgId;
    private SubHeadEntity SubHeadEntity;
    private List<Fentity> Fentity;

    public FNumber getFSaleOrgId() {
      return FSaleOrgId;
    }

    public void setFSaleOrgId(FNumber FSaleOrgId) {
      this.FSaleOrgId = FSaleOrgId;
    }

    public FNumber getFRetcustId() {
      return FRetcustId;
    }

    public void setFRetcustId(FNumber FRetcustId) {
      this.FRetcustId = FRetcustId;
    }

    public FNumber getFBillTypeID() {
      return FBillTypeID;
    }

    public void setFBillTypeID(FNumber FBillTypeID) {
      this.FBillTypeID = FBillTypeID;
    }

    public FNumber getFSettleCurrId() {
      return FSettleCurrId;
    }

    public void setFSettleCurrId(FNumber FSettleCurrId) {
      this.FSettleCurrId = FSettleCurrId;
    }

    public String getF_PAEZ_Text() {
      return F_PAEZ_Text;
    }

    public void setF_PAEZ_Text(String f_PAEZ_Text) {
      F_PAEZ_Text = f_PAEZ_Text;
    }

    public FNumber getF_PAEZ_Assistant() {
      return F_PAEZ_Assistant;
    }

    public void setF_PAEZ_Assistant(FNumber f_PAEZ_Assistant) {
      F_PAEZ_Assistant = f_PAEZ_Assistant;
    }

    public FNumber getFStockOrgId() {
      return FStockOrgId;
    }

    public void setFStockOrgId(FNumber FStockOrgId) {
      this.FStockOrgId = FStockOrgId;
    }

    public K3CloudStockReturnSaveRequest.SubHeadEntity getSubHeadEntity() {
      return SubHeadEntity;
    }

    public void setSubHeadEntity(
        K3CloudStockReturnSaveRequest.SubHeadEntity subHeadEntity) {
      SubHeadEntity = subHeadEntity;
    }

    public List<K3CloudStockReturnSaveRequest.Fentity> getFentity() {
      return Fentity;
    }

    public void setFentity(
        List<K3CloudStockReturnSaveRequest.Fentity> fentity) {
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
    private FNumber FMaterialID;
    private FNumber FStockID;
    private Double FTaxRate;
    private Double FTaxPrice;
    private Integer FRealQty;
    private FNumber FReturnType;
    private LocalDateTime FDeliveryDate;

    public FNumber getFMaterialID() {
      return FMaterialID;
    }

    public void setFMaterialID(FNumber FMaterialID) {
      this.FMaterialID = FMaterialID;
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

    public FNumber getFReturnType() {
      return FReturnType;
    }

    public void setFReturnType(FNumber FReturnType) {
      this.FReturnType = FReturnType;
    }

    public LocalDateTime getFDeliveryDate() {
      return FDeliveryDate;
    }

    public void setFDeliveryDate(LocalDateTime FDeliveryDate) {
      this.FDeliveryDate = FDeliveryDate;
    }
  }
}

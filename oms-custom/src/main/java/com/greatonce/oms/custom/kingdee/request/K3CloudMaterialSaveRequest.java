package com.greatonce.oms.custom.kingdee.request;

import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.response.K3CloudMaterialSaveResponse;

/**
 * 物料保存请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudMaterialSaveRequest extends K3CloudRequest<K3CloudMaterialSaveResponse> {

  @Override
  public String formid() {
    return "BD_MATERIAL";
  }

  @Override
  public Class<K3CloudMaterialSaveResponse> getResponseClass() {
    return K3CloudMaterialSaveResponse.class;
  }

  @Override
  public Object content() {
    return material;
  }

  private Material material;

  public void setMaterial(Material material) {
    this.material = material;
  }

  public static class Material {

    private FNumber FCreateOrgId;
    private FNumber FUseOrgId;
    private String FNumber;
    private String FName;
    private String FSpecification;
    private SubHeadEntity SubHeadEntity;

    public com.greatonce.oms.custom.kingdee.entity.FNumber getFCreateOrgId() {
      return FCreateOrgId;
    }

    public void setFCreateOrgId(com.greatonce.oms.custom.kingdee.entity.FNumber FCreateOrgId) {
      this.FCreateOrgId = FCreateOrgId;
    }

    public com.greatonce.oms.custom.kingdee.entity.FNumber getFUseOrgId() {
      return FUseOrgId;
    }

    public void setFUseOrgId(com.greatonce.oms.custom.kingdee.entity.FNumber FUseOrgId) {
      this.FUseOrgId = FUseOrgId;
    }

    public String getFNumber() {
      return FNumber;
    }

    public void setFNumber(String FNumber) {
      this.FNumber = FNumber;
    }

    public String getFName() {
      return FName;
    }

    public void setFName(String FName) {
      this.FName = FName;
    }

    public String getFSpecification() {
      return FSpecification;
    }

    public void setFSpecification(String FSpecification) {
      this.FSpecification = FSpecification;
    }

    public K3CloudMaterialSaveRequest.SubHeadEntity getSubHeadEntity() {
      return SubHeadEntity;
    }

    public void setSubHeadEntity(
        K3CloudMaterialSaveRequest.SubHeadEntity subHeadEntity) {
      SubHeadEntity = subHeadEntity;
    }
  }

  public static class SubHeadEntity {

    private String FErpClsID;
    private FNumber FCategoryID;
    private FNumber FBaseUnitId;
    private String FBARCODE;
    private FNumber FTaxRateId;
    private FNumber FTaxType;
    private Boolean FIsPurchase;
    private Boolean FIsInventory;
    private Boolean FIsSubContract;
    private Boolean FIsSale;
    private Boolean FIsProduce;
    private Boolean FIsAsset;

    public String getFErpClsID() {
      return FErpClsID;
    }

    public void setFErpClsID(String FErpClsID) {
      this.FErpClsID = FErpClsID;
    }

    public FNumber getFCategoryID() {
      return FCategoryID;
    }

    public void setFCategoryID(FNumber FCategoryID) {
      this.FCategoryID = FCategoryID;
    }

    public FNumber getFBaseUnitId() {
      return FBaseUnitId;
    }

    public void setFBaseUnitId(FNumber FBaseUnitId) {
      this.FBaseUnitId = FBaseUnitId;
    }

    public String getFBARCODE() {
      return FBARCODE;
    }

    public void setFBARCODE(String FBARCODE) {
      this.FBARCODE = FBARCODE;
    }

    public FNumber getFTaxRateId() {
      return FTaxRateId;
    }

    public void setFTaxRateId(FNumber FTaxRateId) {
      this.FTaxRateId = FTaxRateId;
    }

    public FNumber getFTaxType() {
      return FTaxType;
    }

    public void setFTaxType(FNumber FTaxType) {
      this.FTaxType = FTaxType;
    }

    public Boolean getFIsPurchase() {
      return FIsPurchase;
    }

    public void setFIsPurchase(Boolean FIsPurchase) {
      this.FIsPurchase = FIsPurchase;
    }

    public Boolean getFIsInventory() {
      return FIsInventory;
    }

    public void setFIsInventory(Boolean FIsInventory) {
      this.FIsInventory = FIsInventory;
    }

    public Boolean getFIsSubContract() {
      return FIsSubContract;
    }

    public void setFIsSubContract(Boolean FIsSubContract) {
      this.FIsSubContract = FIsSubContract;
    }

    public Boolean getFIsSale() {
      return FIsSale;
    }

    public void setFIsSale(Boolean FIsSale) {
      this.FIsSale = FIsSale;
    }

    public Boolean getFIsProduce() {
      return FIsProduce;
    }

    public void setFIsProduce(Boolean FIsProduce) {
      this.FIsProduce = FIsProduce;
    }

    public Boolean getFIsAsset() {
      return FIsAsset;
    }

    public void setFIsAsset(Boolean FIsAsset) {
      this.FIsAsset = FIsAsset;
    }
  }
}

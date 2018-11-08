package com.greatonce.oms.bo.vip;

import com.greatonce.oms.bo.VersionBO;

import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/18
 */
public class VipReturnScanBO extends VersionBO {

    private List<VipReturnScanDetail> details;

    public List<VipReturnScanDetail> getDetails() {
        return details;
    }

    public void setDetails(List<VipReturnScanDetail> details) {
        this.details = details;
    }

    public static class VipReturnScanDetail {
        private Long vipReturnDetailId;
        private String boxNo;
        private String skuCode;
        private Integer returnQuantity;
        private Integer scanQuantity;

        public Long getVipReturnDetailId() {
            return vipReturnDetailId;
        }

        public void setVipReturnDetailId(Long vipReturnDetailId) {
            this.vipReturnDetailId = vipReturnDetailId;
        }

        public String getBoxNo() {
            return boxNo;
        }

        public void setBoxNo(String boxNo) {
            this.boxNo = boxNo;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public Integer getReturnQuantity() {
            return returnQuantity;
        }

        public void setReturnQuantity(Integer returnQuantity) {
            this.returnQuantity = returnQuantity;
        }

        public Integer getScanQuantity() {
            return scanQuantity;
        }

        public void setScanQuantity(Integer scanQuantity) {
            this.scanQuantity = scanQuantity;
        }
    }
}

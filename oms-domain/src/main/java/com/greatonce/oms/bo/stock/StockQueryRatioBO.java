package com.greatonce.oms.bo.stock;

import com.greatonce.oms.bo.trade.ManualDispatchDetailBO;
import com.greatonce.oms.domain.trade.SalesOrder;

import java.util.List;

public class StockQueryRatioBO {

    /** 订单明细**/
    private List<ManualDispatchDetailBO> salesOrderDetails;
    /** 订单主信息*/
    private SalesOrder salesOrder;

    /** 动销比**/
    private List<StockRatio> stockRatios;

    public List<ManualDispatchDetailBO> getSalesOrderDetails() {
        return salesOrderDetails;
    }

    public void setSalesOrderDetails(List<ManualDispatchDetailBO> salesOrderDetails) {
        this.salesOrderDetails = salesOrderDetails;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public List<StockRatio> getStockRatios() {
        return stockRatios;
    }

    public void setStockRatios(List<StockRatio> stockRatios) {
        this.stockRatios = stockRatios;
    }
}

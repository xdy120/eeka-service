package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.trade.ExchangeApplyOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.biz.trade.SalesOrderSubService;
import com.greatonce.oms.bridge.mall.ExchangeBridge;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.request.ExchangeAgreeRequest;
import com.greatonce.oms.bridge.mall.request.ExchangeRefuseRequest;
import com.greatonce.oms.bridge.mall.response.ExchangeAgreeResponse;
import com.greatonce.oms.bridge.mall.response.ExchangeRefuseResponse;
import com.greatonce.oms.dao.trade.ExchangeApplyOrderDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.ExchangeApplyOrderStatus;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.query.trade.ExchangeApplyOrderQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 换货申请单.
 * ExchangeApplyOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-04-02
 */

@Service
public class ExchangeApplyOrderServiceImpl extends
    AbstractService<ExchangeApplyOrder, ExchangeApplyOrderQuery> implements
    ExchangeApplyOrderService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeApplyOrderServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.EXCHANGE_APPLY);
  @Autowired
  private ExchangeApplyOrderDao dao;
  @Resource
  private IdGenerator exchangeOrderIdGenerator;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private SalesOrderSubService salesOrderSubService;
  @Autowired
  private StockOccupancyService stockOccupancyService;

  @Override
  protected QueryDao<ExchangeApplyOrder, ExchangeApplyOrderQuery> getDAO() {
    return this.dao;
  }
  @Override
  public IdGenerator getIdGenerator() {
    return exchangeOrderIdGenerator;
  }

  /**
   * 拒绝换货.
   *
   * @param reasonCode 拒绝理由编码
   */
  @Override
  public void refuse(Long exchangeId, String reasonCode) {
    try {

      //更新淘宝的状态
      //先找到 申请单
      ExchangeApplyOrder exchangeApplyOrder = getByKey(exchangeId);

      // 通过 malldetailId 找到 mallSalesDetail
      SalesOrderDetail detail = new SalesOrderDetail();
      detail.setMallDetailId(exchangeApplyOrder.getMallDetailId());
      SalesOrderDetail salesOrderDetail = salesOrderDetailService.getByExample(detail);
      //再找到 salesOrder
      SalesOrder salesOrder = salesOrderService.getByKey(salesOrderDetail.getSalesOrderId());
      Long storeId = salesOrder.getStoreId();
      //都是为了找到 store
      Store store = storeService.getByKey(storeId);
      //请求天猫
      ExchangeBridge exchangeBridge = mallBridgeFactory.getExchangeBridge(store.getMallType());
      ExchangeRefuseRequest refuseRequest = new ExchangeRefuseRequest(store);
      refuseRequest.setDisputeId(Long.valueOf(exchangeApplyOrder.getMallExchangeId()));
      refuseRequest.setReasonId(Long.valueOf(reasonCode));
      ExchangeRefuseResponse response = exchangeBridge.refuse(refuseRequest);
      if (response.isSuccess()) {
        //更新数据库状态
        ExchangeApplyOrder applyOrder = new ExchangeApplyOrder();
        applyOrder.setExchangeApplyOrderId(exchangeId);
        applyOrder.setStatus(ExchangeApplyOrderStatus.DENYED);

        int update = modify(applyOrder);
      } else {
        throw new Exception("拒绝换货状态更新天猫失败");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void agree(Long exchangeId) {

    try {
      //更新淘宝的状态
      //先找到 申请单
      ExchangeApplyOrder exchangeApplyOrder = getByKey(exchangeId);

      // 通过 malldetailId 找到 mallSalesDetail
      SalesOrderDetail detail = new SalesOrderDetail();
      detail.setMallDetailId(exchangeApplyOrder.getMallDetailId());
      SalesOrderDetail salesOrderDetail = salesOrderDetailService.getByExample(detail);
      //再找到 salesOrder
      SalesOrder salesOrder = salesOrderService.getByKey(salesOrderDetail.getSalesOrderId());
      Long storeId = salesOrder.getStoreId();
      //都是为了找到 store
      Store store = storeService.getByKey(storeId);

      ExchangeBridge exchangeBridge = mallBridgeFactory.getExchangeBridge(store.getMallType());
      ExchangeAgreeRequest agreeRequest = new ExchangeAgreeRequest(store);
      agreeRequest.setAddressId(Long.valueOf(store.getSetting().getTaobaoReturnAddressId()));
      agreeRequest.setDisputeId(Long.valueOf(exchangeApplyOrder.getMallExchangeId()));
      ExchangeAgreeResponse agreeResponse = exchangeBridge.agree(agreeRequest);
      LOGGER.info("换货单自动同意，交易号：{}，换货单号：{}", exchangeApplyOrder.getTradeId(),
          exchangeApplyOrder.getMallExchangeId());
    } catch (Exception e) {
      LOGGER.error("换货单同意失败，换货单：{}", JsonUtil.toJson(exchangeId));
      LOGGER.error("换货单同意失败，换货单：", e);
      throw new OmsException("换货单同意失败！");
    }
  }

  @Override
  public void matchAbnormalApply(ExchangeApplyOrder applyOrder) {
    ExchangeApplyOrder updateExchange = new ExchangeApplyOrder();
    updateExchange.setExchangeApplyOrderId(applyOrder.getExchangeApplyOrderId());
    //匹配原单
    if (Assert.isNull(applyOrder.getTradeId())) {
      SalesOrderDetail orderDetailEg = new SalesOrderDetail();
      orderDetailEg.setMallDetailId(applyOrder.getMallDetailId());
      SalesOrderDetail salesOrderDetail = salesOrderDetailService.getByExample(orderDetailEg);

      if (!Assert.isNull(salesOrderDetail)) {
        SalesOrder salesOrderEg = new SalesOrder();
        salesOrderEg.setSalesOrderId(salesOrderDetail.getSalesOrderId());
        SalesOrder salesOrder = salesOrderService.getByExample(salesOrderEg);
        if (!Assert.isNull(salesOrder)) {
          SalesOrderSub salesOrderSub = salesOrderSubService.getByKey(salesOrder.getSalesOrderId());
          updateExchange.setSalesOrderId(salesOrder.getSalesOrderId());
          updateExchange.setTradeId(salesOrder.getTradeId());
          updateExchange.setTelephone(salesOrder.getTelephone());
          if (!Assert.isNull(salesOrderSub)) {
            updateExchange.setMemberId(salesOrder.getSub().getMemberId());
            updateExchange.setMemberName(salesOrder.getSub().getMemberName());
          }
        }
      }
    }
    //换出商品异常匹配
    if (Assert.isNull(applyOrder.getOutSkuId())) {
      //找出换出商品的铺货关系
      ProductMallMapping query = new ProductMallMapping();
      query.setStoreId(applyOrder.getStoreId());
      query.setMallSkuId(applyOrder.getOutMallSkuId());
      ProductMallMapping mapping = productMallMappingService.getByExample(query);

      if (!Assert.isNull(mapping)) {
        //更新换货申请
        updateExchange.setOutSkuId(mapping.getSkuId());
        updateExchange.setOutSkuName(mapping.getSkuName());
        updateExchange.setOutSkuCode(mapping.getSkuCode());
        updateExchange.setOutProductId(mapping.getProductId());
        updateExchange.setOutProductName(mapping.getProductName());
        updateExchange.setOutProductCode(mapping.getProductCode());
      }
    }

    modify(updateExchange);
  }

  @Override
  public List<ExchangeApplyOrder> getByExpressNo(String expressNo) {
    ExchangeApplyOrder exchangeApplyOrder = new ExchangeApplyOrder();
    exchangeApplyOrder.setInExpressNo(expressNo);
    return dao.listExample(exchangeApplyOrder);
  }

  @Override
  public void deleteOccupancy(Long exchangeApplyId) {
    try {
      stockOccupancyService.deleteOccupancy(exchangeApplyId,StockOccupancyType.RETURN_ORDER);
      BIZ_LOGGER.log(exchangeApplyId,"删除换货申请占用");
    }catch (Exception e){
      LOGGER.error("删除换货申请占用失败,参数:{}",exchangeApplyId);
      LOGGER.error("删除换货申请占用失败,堆栈信息:",e);
    }
  }


}

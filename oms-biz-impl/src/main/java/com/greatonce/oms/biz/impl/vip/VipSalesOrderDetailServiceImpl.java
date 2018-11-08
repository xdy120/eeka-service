package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.vip.VipSalesOrderDetailService;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipCancelOrderQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipCancelOrderQueryResponse.VipOrder;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse;
import com.greatonce.oms.dao.vip.VipSalesOrderDetailDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.vip.VipSalesOrderDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.vip.VipSalesOrderDetailQuery;
import com.greatonce.oms.util.BizContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 唯品销售单明细. VipSalesOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-14
 */

@Service
public class VipSalesOrderDetailServiceImpl extends AbstractService
    <VipSalesOrderDetail, VipSalesOrderDetailQuery> implements VipSalesOrderDetailService {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(VipSalesOrderDetailServiceImpl.class);
  @Autowired
  private VipSalesOrderDetailDao dao;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Resource
  private IdGenerator vipSalesOrderDetailIidGenerator;

  @Override
  protected QueryDao<VipSalesOrderDetail, VipSalesOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipSalesOrderDetailIidGenerator;
  }

  @Override
  public int create(VipSalesOrderDetail record) {
    StockOccupancy stockOccupancy = new StockOccupancy();
    stockOccupancy.setSkuId(record.getSkuId());
    stockOccupancy.setWarehouseId(record.getWarehouseId());
    stockOccupancy.setWarehouseName(record.getWarehouseName());
    stockOccupancy.setStockOccupancyType(StockOccupancyType.VIP_SALES);
    stockOccupancy.setDetailId(record.getVipSalesOrderDetailId());
    stockOccupancy.setMainId(record.getVipSalesOrderDetailId());
    stockOccupancy.setQuantity(record.getQuantity());
    stockOccupancy.setStatus(StockOccupancyStatus.LOCK);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        //添加库存占用
        stockOccupancyService.create(stockOccupancy);
        return insert(record);
      });
      getMqProducer().send(
          new StockChangedMessage(record.getTradeId(), stockOccupancy.getSkuId(),
              BizContext.getNickname(), "新增唯品销售单"));
      return count;
    } catch (Exception e) {
      LOGGER.error("唯品销售单占用新增失败,销售单占用信息: {}", JsonUtil.toJson(stockOccupancy));
      LOGGER.error("唯品销售单占用新增失败,堆栈信息: ", e);
      throw new OmsException("唯品销售单占用新增失败");
    }
  }

  @Override
  public int remove(VipSalesOrderDetail detail) {
    return delete(detail.getVipSalesOrderDetailId());
  }

  @Override
  public void deleteByDispatch(Store store, List<String> tradeIds) {
    //获取所有配货单对应的销售单
    VipSalesOrderDetailQuery query = new VipSalesOrderDetailQuery();
    query.setStoreId(store.getStoreId());
    query.setTradeIds(tradeIds);
    List<VipSalesOrderDetail> details = list(query);
    if (!Assert.isEmpty(details)) {
      try {
        getTransactionTemplate().execute(() -> {
          for (VipSalesOrderDetail detail : details) {
            delete(detail.getVipSalesOrderDetailId());
            stockOccupancyService.deleteOccupancy(detail.getVipSalesOrderDetailId(),
                StockOccupancyType.VIP_SALES);
          }
        });
        LOGGER.info("删除唯品销售单：{}", StringUtil.join(tradeIds));
      } catch (Exception e) {
        LOGGER.error("删除唯品销售单失败,销售单占用信息: {}", StringUtil.join(tradeIds));
        LOGGER.error("删除唯品销售单失败,堆栈信息: ", e);
        throw new OmsException("删除唯品销售单失败");
      }
    }
  }

  @Override
  public void cancelByBuyer(Store store, List<VipCancelOrderQueryResponse.VipOrder> orders) {
    List<String> tradeIds = orders.stream().map(VipOrder::getOrderNo).collect(Collectors.toList());
    VipSalesOrderDetailQuery query = new VipSalesOrderDetailQuery();
    query.setStoreId(store.getStoreId());
    query.setTradeIds(tradeIds);
    List<VipSalesOrderDetail> details = list(query);
    if (!Assert.isEmpty(details)) {
      try {
        getTransactionTemplate().execute(() -> {
          for (VipSalesOrderDetail detail : details) {
            delete(detail.getVipSalesOrderDetailId());
            stockOccupancyService.deleteOccupancy(detail.getVipSalesOrderDetailId(),
                StockOccupancyType.VIP_SALES);
          }
        });
        LOGGER.info("删除唯品销售单：{}", StringUtil.join(tradeIds));
      } catch (Exception e) {
        LOGGER.error("用户取消唯品销售单失败,销售单占用信息: {}", JsonUtil.toJson(orders));
        LOGGER.error("用户取消唯品销售单失败,堆栈信息: ", e);
        throw new OmsException("用户取消唯品销售单失败");
      }
    }
  }

  /**
   * 自动创建唯品销售单. 1、增加唯品销售占用
   */
  @Override
  public void autoCreate(List<VipSalesOrderDetail> detailList) {
    List<StockOccupancy> stockOccupancies = new ArrayList<>(detailList.size());
    detailList.forEach(detail -> {
      initDefaultValue(detail);
      if (!Assert.isNull(detail.getSkuId())) {
        StockOccupancy stockOccupancy = new StockOccupancy();
        stockOccupancy.setCreatedTime(LocalDateTime.now());
        stockOccupancy.setModifiedTime(LocalDateTime.now());
        stockOccupancy.setSortTime(detail.getCreatedTime());
        stockOccupancy.setQuantity(detail.getQuantity());
        stockOccupancy.setStockOccupancyType(StockOccupancyType.VIP_SALES);
        stockOccupancy.setSkuId(detail.getSkuId());
        stockOccupancy.setSkuCode(detail.getSkuCode());
        stockOccupancy.setMainId(detail.getVipSalesOrderDetailId());
        stockOccupancy.setDetailId(detail.getVipSalesOrderDetailId());
        stockOccupancy.setWarehouseId(detail.getWarehouseId());
        stockOccupancy.setWarehouseName(detail.getWarehouseName());
        stockOccupancy.setVirtualWarehouseId(detail.getVirtualWarehouseId());
        stockOccupancy.setVirtualWarehouseName(detail.getVirtualWarehouseName());
        stockOccupancy.setStatus(StockOccupancyStatus.LOCK);
        stockOccupancies.add(stockOccupancy);
      }
    });
    try {
      getTransactionTemplate().execute(() -> {
        //添加销售单明细
        batchCreate(detailList);
        //添加销售单占用
        stockOccupancyService.batchCreate(stockOccupancies);
      });
      for (VipSalesOrderDetail vipSalesOrderDetail : detailList) {
        if (!Assert.isNull(vipSalesOrderDetail.getSkuId())) {
          getMqProducer().send(
              new StockChangedMessage(vipSalesOrderDetail.getTradeId(),
                  vipSalesOrderDetail.getSkuId(),
                  BizContext.getNickname(), "唯品销售单创建"));
        }
      }
    } catch (Exception e) {
      LOGGER.error("唯品销售单新增失败,销售单信息: {}", JsonUtil.toJson(detailList));
      LOGGER.error("唯品销售单新增失败,堆栈信息: ", e);
      throw new OmsException("唯品销售单新增失败");
    }
  }

  @Override
  public void filterExistingOrder(Long storeId, List<VipOrderQueryResponse.VipOrder> orders) {
    List<String> tradeIds = dao.listTradeIdsByStoreId(storeId);
    orders.removeIf(x -> tradeIds.contains(x.getOrderNo()));
  }

  @Override
  public int batchCreate(Collection<? extends VipSalesOrderDetail> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends VipSalesOrderDetail> collection) {
    return updateBatch(collection);
  }
}
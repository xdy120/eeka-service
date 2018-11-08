package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockOutOrderDetailService;
import com.greatonce.oms.biz.stock.StockOutOrderService;
import com.greatonce.oms.dao.stock.StockOutOrderDetailDao;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.query.stock.StockOutOrderDetailQuery;
import com.greatonce.oms.query.stock.StockOutOrderQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出库单明细. StockOutOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class StockOutOrderDetailServiceImpl extends
    AbstractDetailService<StockOutOrder, StockOutOrderDetail, StockOutOrderDetailQuery> implements
    StockOutOrderDetailService {

  @Autowired
  private StockOutOrderDetailDao dao;
  @Autowired
  private StockOutOrderService stockOutOrderService;
  @Resource
  private IdGenerator stockOutDetailIdGenerator;

  @Override
  protected QueryDao<StockOutOrderDetail, StockOutOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<StockOutOrderDetail> getDetails(StockOutOrder stockOutOrder) {
    return stockOutOrder.getDetails();
  }

  @Override
  protected BizService<StockOutOrder, ? extends Query> getMainService() {
    return stockOutOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockOutDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockOutOrderDetail detail) {
    super.initDefaultValue(detail);
    detail.setOutQuantity(0);
    detail.setNoticeQuantity(0);
  }

  @Override
  public int batchCreate(Collection<? extends StockOutOrderDetail> list) {
    List<String> skuCodes = new ArrayList<>();
    StockOutOrderDetailQuery filter = new StockOutOrderDetailQuery();
    list.forEach(e -> {
      initDefaultValue(e);
      skuCodes.add(e.getSkuCode());
      filter.setStockOutOrderId(e.getStockOutOrderId());
    });
    filter.setSystemQueryFields("stock_out_order_detail_id");
    filter.setSkuCodes(skuCodes);
    List<StockOutOrderDetail> oldList = dao.listField("stock_out_order_detail_id", filter);
    if (oldList.size() > 0) {
      throw SysExceptions.DATA_DUPLICATION;
    } else {
      return insertBatch(list);
    }
  }

  @Override
  public List<StockOutOrderDetail> listAvailable(Long stockOutOrderId) {
    return dao.listAvailable(stockOutOrderId);
  }

  @Override
  public List<StockOutOrderDetail> listSaleable(Long stockOutOrderId) {
    return dao.listSaleable(stockOutOrderId);
  }

  @Override
  public List<StockOutOrderDetail> listDetails(Long stockOutOrderId) {
    StockOutOrderDetail eg = new StockOutOrderDetail();
    eg.setStockOutOrderId(stockOutOrderId);
    return listExample(eg);
  }
}
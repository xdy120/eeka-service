package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockVirtualAllotDetailService;
import com.greatonce.oms.biz.stock.StockVirtualAllotService;
import com.greatonce.oms.dao.stock.StockVirtualAllotDetailDao;
import com.greatonce.oms.domain.stock.StockVirtualAllot;
import com.greatonce.oms.domain.stock.StockVirtualAllotDetail;
import com.greatonce.oms.query.stock.StockVirtualAllotDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 虚拟调拨明细.
 * StockVirtualAllotDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-01-17
 */

@Service
public class StockVirtualAllotDetailServiceImpl extends
    AbstractDetailService<StockVirtualAllot, StockVirtualAllotDetail, StockVirtualAllotDetailQuery> implements
    StockVirtualAllotDetailService {

  @Autowired
  private StockVirtualAllotDetailDao dao;
  @Autowired
  private StockVirtualAllotService stockVirtualAllotService;
  @Resource
  private IdGenerator stockVirtualAllotDetailIdGenerator;

  @Override
  protected QueryDao<StockVirtualAllotDetail, StockVirtualAllotDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<StockVirtualAllotDetail> getDetails(StockVirtualAllot stockVirtualAllot) {
    return stockVirtualAllot.getDetails();
  }

  @Override
  protected BizService<StockVirtualAllot, ? extends Query> getMainService() {
    return stockVirtualAllotService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockVirtualAllotDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockVirtualAllotDetail entity) {
    super.initDefaultValue(entity);
    entity.setQuantity(0);
  }

  @Override
  public List<StockVirtualAllotDetail> listAvailable(Long stockVirtualAllotId) {
    return dao.listAvailable(stockVirtualAllotId);
  }

  @Override
  public List<StockVirtualAllotDetail> listSaleable(Long stockVirtualAllotId) {
    return dao.listSaleable(stockVirtualAllotId);
  }
}
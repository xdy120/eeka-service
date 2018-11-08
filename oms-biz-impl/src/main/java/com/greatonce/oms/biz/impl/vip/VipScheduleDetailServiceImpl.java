package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.vip.VipScheduleDetailService;
import com.greatonce.oms.biz.vip.VipScheduleService;
import com.greatonce.oms.dao.vip.VipScheduleDetailDao;
import com.greatonce.oms.domain.enums.vip.VipScheduleDetailStatus;
import com.greatonce.oms.domain.vip.VipSchedule;
import com.greatonce.oms.domain.vip.VipScheduleDetail;
import com.greatonce.oms.query.vip.VipScheduleDetailQuery;
import com.greatonce.oms.query.vip.VipScheduleQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 唯品档期明细. VipScheduleDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipScheduleDetailServiceImpl extends
    AbstractDetailService<VipSchedule, VipScheduleDetail, VipScheduleDetailQuery> implements
    VipScheduleDetailService {

  @Resource
  VipScheduleService vipScheduleService;
  @Resource
  VipScheduleDetailDao dao;


  @Override
  protected BizService<VipSchedule, VipScheduleQuery> getMainService() {
    return vipScheduleService;
  }

  @Override
  protected QueryDao<VipScheduleDetail, VipScheduleDetailQuery> getDAO() {
    return dao;
  }

  @Override
  protected List<VipScheduleDetail> getDetails(VipSchedule vipSchedule) {
    return vipSchedule.getDetails();
  }

  @Override
  protected void initDefaultValue(VipScheduleDetail detail) {
    super.initDefaultValue(detail);
    detail.setStatus(VipScheduleDetailStatus.NO_UPLOAD);
    detail.setOutQuantity(0);
    detail.setLockQuantity(0);
    detail.setDispatchOccupancyQuantity(0);
  }

  @Override
  public int batchCreate(Collection<? extends VipScheduleDetail> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public List<VipScheduleDetail> listInSales(String storeId, Collection<String> skuCodes) {
    return null;
  }

  @Override
  public List<VipScheduleDetail> listAvailable(Long vipScheduleId) {
    return dao.listAvailable(vipScheduleId);
  }

  @Override
  public List<VipScheduleDetail> listSaleable(Long vipScheduleId) {
    return dao.listSaleable(vipScheduleId);
  }
}
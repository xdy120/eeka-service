package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.vip.VipAdjustDetailService;
import com.greatonce.oms.biz.vip.VipAdjustService;
import com.greatonce.oms.dao.vip.VipAdjustDetailDao;
import com.greatonce.oms.domain.enums.vip.VipAdjustDetailStatus;
import com.greatonce.oms.domain.enums.vip.VipScheduleUploadType;
import com.greatonce.oms.domain.vip.VipAdjust;
import com.greatonce.oms.domain.vip.VipAdjustDetail;
import com.greatonce.oms.query.vip.VipAdjustDetailQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 唯品调整单明细. VipAdjustDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-15
 */

@Service
public class VipAdjustDetailServiceImpl extends AbstractDetailService
    <VipAdjust, VipAdjustDetail, VipAdjustDetailQuery> implements VipAdjustDetailService {

  @Autowired
  VipAdjustDetailDao dao;
  @Autowired
  VipAdjustService vipAdjustService;
  @Resource
  IdGenerator vipAdjustDetailIdGenerator;


  @Override
  protected QueryDao<VipAdjustDetail, VipAdjustDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<VipAdjustDetail> getDetails(VipAdjust vipAdjust) {
    return vipAdjust.getDetails();
  }


  @Override
  protected BizService<VipAdjust, ? extends Query>
  getMainService() {
    return vipAdjustService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipAdjustDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(VipAdjustDetail detail) {
    super.initDefaultValue(detail);
    detail.setLockQuantity(0);
    detail.setUploadQuantity(0);
    detail.setStatus(VipAdjustDetailStatus.NO_UPLOAD);
    detail.setUploadType(VipScheduleUploadType.FULL);
  }

  /**
   * 通过判定来确定是全量还是增量.todo
   */

  @Override
  public int batchCreate(Collection<? extends VipAdjustDetail> list) {
    list.forEach(detail -> {
      initDefaultValue(detail);
    });
    return super.insertBatch(list);
  }
}
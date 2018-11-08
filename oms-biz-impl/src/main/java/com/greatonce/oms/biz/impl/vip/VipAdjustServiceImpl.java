package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.vip.VipAdjustDetailService;
import com.greatonce.oms.biz.vip.VipAdjustService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.dao.vip.VipAdjustDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.vip.VipAdjustStatus;
import com.greatonce.oms.domain.vip.VipAdjust;
import com.greatonce.oms.query.vip.VipAdjustQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 唯品调整单. VipAdjust <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipAdjustServiceImpl extends AbstractVersionService<VipAdjust, VipAdjustQuery>
    implements VipAdjustService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.VIP_SCHEDULE_ADJUST);
  private static final Logger LOGGER = LoggerFactory.getLogger(VipAdjustServiceImpl.class);
  @Resource
  IdGenerator vipAdjustIdGenerator;
  @Resource
  CodeGenerator vipAdjustCodeGenerator;
  @Resource
  VipAdjustDao dao;
  @Resource
  VipAdjustDetailService vipAdjustDetailService;

  @Override
  protected QueryDao<VipAdjust, VipAdjustQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipAdjustIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(VipAdjust entity) {
    super.initDefaultValue(entity);
    entity.setVipAdjustCode(vipAdjustCodeGenerator.next());
    entity.setStatus(VipAdjustStatus.CREATED);
    entity.setCreator(BizContext.getNickname());
    entity.setVersion(0);
    if (entity.isUpload() == null) {
      entity.setUpload(false);
    }
    entity.getDetails().forEach(detail -> {
      detail.setVipAdjustId(entity.getVipAdjustId());
    });
  }

  @Override
  public int create(VipAdjust record) {
    initDefaultValue(record);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        vipAdjustDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getVipAdjustId(), BizLogger.ADD, "唯品调整单新增成功");
      return count;
    } catch (Exception e) {
      LOGGER.error("新增唯品调整单失败,调整单信息:{}", JsonUtil.toJson(record));
      LOGGER.error("新增唯品调整单失败,堆栈信息:", e);
      throw new OmsException("新增唯品调整单失败");
    }
  }

  @Override
  public void audit(VipAdjust vipAdjust, VersionBO bo) {
    if (vipAdjust.getStatus() != VipAdjustStatus.CREATED) {
      throw new OmsException("只有新建的调整单才能审核");
    }
    vipAdjust.setStatus(VipAdjustStatus.AUDITED);
    vipAdjust.setVersion(bo.getVersion());
    modify(vipAdjust);
  }

  @Override
  public void end(VipAdjust vipAdjust) {

  }
}
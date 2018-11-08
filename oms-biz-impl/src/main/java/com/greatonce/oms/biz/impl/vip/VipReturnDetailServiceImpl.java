package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.vip.VipReturnDetailService;
import com.greatonce.oms.biz.vip.VipReturnService;
import com.greatonce.oms.dao.vip.VipReturnDetailDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.vip.VipReturnStatus;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.domain.vip.VipReturnDetail;
import com.greatonce.oms.query.vip.VipReturnDetailQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 唯品退货单明细. VipReturnDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipReturnDetailServiceImpl extends
    AbstractDetailService<VipReturn, VipReturnDetail, VipReturnDetailQuery> implements
    VipReturnDetailService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.VIP_RETURN);
  private static final Logger LOGGER = LoggerFactory.getLogger(VipReturnDetailServiceImpl.class);

  @Autowired
  private VipReturnDetailDao dao;
  @Resource
  private IdGenerator vipReturnDetailIdGenerator;
  @Autowired
  private VipReturnService vipReturnService;

  @Override
  protected QueryDao<VipReturnDetail, VipReturnDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<VipReturnDetail> getDetails(VipReturn vipReturn) {
    return vipReturn.getDetails();
  }

  @Override
  protected BizService<VipReturn, ? extends Query>
  getMainService() {
    return vipReturnService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipReturnDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(VipReturnDetail detail) {
    detail.setVipReturnDetailId(vipReturnDetailIdGenerator.next());
    detail.setNoticeQuantity(0);
    detail.setScanQuantity(0);
    if (Assert.isNull(detail.getVipPrice()) || detail.getVipPrice() <= 0) {
      detail.setVipPrice(0D);
      detail.setVipAmount(0D);
      detail.setVipPriceAbnormal(true);
    } else {
      detail.setVipAmount(detail.getVipPrice() * detail.getReturnQuantity());
      detail.setVipPriceAbnormal(false);
    }
    if (Assert.isNull(detail.isAbnormal())) {
      detail.setAbnormal(false);
    }
    if (Assert.isNull(detail.getVipBarcode())) {
      detail.setVipBarcode(detail.getSkuCode());
    }
  }


  @Override
  public int createDetail(VipReturn record) {
    if (VipReturnStatus.CREATED != record.getStatus()) {
      throw new OmsException("只有新建状态的退货单才能增加明细");
    }
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        batchCreate(record.getDetails());
        adjustQuantity(record);
        return getMainService().modify(record);
      });
      BIZ_LOGGER.log(record.getVipReturnId(), BizLogger.ADD, "新增唯品退货单明细成功");
      return count;
    } catch (Exception e) {
      LOGGER.error("新增唯品退货单明细失败,退货单明细信息: {}", JsonUtil.toJson(record.getDetails()));
      LOGGER.error("新增唯品退货单明细失败,堆栈信息: ", e);
      throw new OmsException("新增唯品退货单明细失败");
    }

  }

  @Override
  public int modifyDetail(VipReturn record) {
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        updateBatch(record.getDetails());
        adjustQuantity(record);
        return getMainService().modify(record);
      });
      BIZ_LOGGER.log(record.getVipReturnId(), BizLogger.UPDATE, "修改唯品退货单明细成功");
      return count;
    } catch (Exception e) {
      LOGGER.error("修改唯品退货单明细失败,退货单明细信息: {}", JsonUtil.toJson(record.getDetails()));
      LOGGER.error("修改唯品退货单明细失败,堆栈信息: ", e);
      throw new OmsException("修改唯品退货单明细失败");
    }
  }

  @Override
  public int removeDetail(VipReturn record) {
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        record.getDetails().forEach(d -> {
          delete(d.getVipReturnDetailId());
        });
        adjustQuantity(record);
        return getMainService().modify(record);
      });
      BIZ_LOGGER.log(record.getVipReturnId(), BizLogger.DELETE, "删除唯品退货单明细成功");
      return count;
    } catch (Exception e) {
      LOGGER.error("删除唯品退货单明细失败,拣货单明细信息: {}", JsonUtil.toJson(record.getDetails()));
      LOGGER.error("删除唯品退货单明细失败,堆栈信息: ", e);
      throw new OmsException("删除唯品退货单明细失败");
    }
  }

  @Override
  public List<VipReturnDetail> listCanNotice(Long vipReturnId) {
    return dao.listCanNotice(vipReturnId);
  }

  /**
   * 当退货明细发生改变时，更新主单退货数量，箱数，规格数量和退货金额
   */
  public void adjustQuantity(VipReturn record) {
    VipReturnDetail vipReturnDetailEg = new VipReturnDetail();
    vipReturnDetailEg.setVipReturnId(record.getVipReturnId());
    int returnQuantity = 0;
    double vipReturnAmount = 0;
    HashSet<String> set = new HashSet<>();
    List<VipReturnDetail> vipReturnDetails = listExample(vipReturnDetailEg);
    for (VipReturnDetail detail : vipReturnDetails) {
      detail.setVipAmount(detail.getVipPrice() * detail.getReturnQuantity());
      returnQuantity += detail.getReturnQuantity();
      vipReturnAmount += detail.getVipAmount();
      set.add(detail.getBoxNumber());
      if (detail.isVipPriceAbnormal()) {
        record.setVipPriceAbnormal(true);
      }
    }
    record.setSkuQuantity(vipReturnDetails.size());
    record.setVipAmount(vipReturnAmount);
    record.setQuantity(returnQuantity);
    record.setBoxQuantity(set.size());
    batchModify(vipReturnDetails);
  }
}
package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.vip.VipReturnNoticeDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.dao.vip.VipReturnNoticeDetailDao;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.query.vip.VipReturnNoticeDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 唯品退货通知单明细. VipReturnNoticeDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-20
 */

@Service
public class VipReturnNoticeDetailServiceImpl extends
    AbstractDetailService<VipReturnNotice, VipReturnNoticeDetail, VipReturnNoticeDetailQuery>
    implements VipReturnNoticeDetailService {

  @Autowired
  private VipReturnNoticeDetailDao dao;
  @Resource
  private IdGenerator vipReturnNoticeDetailIdGenerator;
  @Autowired
  private VipReturnNoticeService vipReturnNoticeService;
  @Autowired
  private ProductSkuService productSkuService;

  @Override
  protected QueryDao<VipReturnNoticeDetail, VipReturnNoticeDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<VipReturnNoticeDetail> getDetails(VipReturnNotice vipReturnNotice) {
    return vipReturnNotice.getDetails();
  }

  @Override
  protected BizService<VipReturnNotice, ? extends Query>
  getMainService() {
    return vipReturnNoticeService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipReturnNoticeDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(VipReturnNoticeDetail detail) {
    super.initDefaultValue(detail);
    detail.setInQuantity(0);
    detail.setInSubstandardQuantity(0);
    detail.setVipAmount(0D);
    if (Assert.isNull(detail.isVipPriceAbnormal())) {
      detail.setVipPriceAbnormal(false);
    }
  }

  @Override
  public List<VipReturnNoticeDetail> listByReturnNoticeId(Long vipReturnNoticeId) {
    VipReturnNoticeDetail eg = new VipReturnNoticeDetail();
    eg.setVipReturnNoticeId(vipReturnNoticeId);
    return listExample(eg);
  }
}
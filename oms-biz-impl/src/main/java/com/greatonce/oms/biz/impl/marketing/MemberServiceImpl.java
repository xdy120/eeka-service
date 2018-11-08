package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.marketing.MemberService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.dao.marketing.MemberDao;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.query.marketing.MemberQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Member <br/> 会员
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class MemberServiceImpl extends AbstractService<Member, MemberQuery> implements
    MemberService {

  @Autowired
  private MemberDao dao;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  @Override
  protected QueryDao<Member, MemberQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return super.getIdGenerator();
  }

  @Override
  public boolean checkExist(String memberName, Long storeId) {
    Member eg = new Member();
    eg.setMemberName(memberName);
    eg.setStoreId(storeId);
    return dao.checkExist(eg) != 0;
  }

  @Override
  protected void initDefaultValue(Member record) {
    super.initDefaultValue(record);
    if (record.isUrgent() == null) {
      record.setUrgent(false);
    }
  }

  @Override
  public Member getByName(Long storeId, String name) {
    Member eg = new Member();
    eg.setStoreId(storeId);
    eg.setMemberName(name);
    return getByExample(eg);
  }

  @Override
  public int create(Member entity) {
    int result = super.create(entity);
    getMqProducer().send(new GeneralMessage(OmsModule.MARKETING_MEMBER,entity.getMemberId(),GeneralMessage.EventType.CREATED));
    return result;
  }

  /**
   * 解密会员信息返回.
   */
  @Override
  public Member decrypt(Member member) {
    Store store = storeService.getByKey(member.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    member.setContact(
        securityBridge.decrypt(store, member.getContact(), DataType.NAME));
    member.setMobile(
        securityBridge.decrypt(store, member.getMobile(), DataType.MOBILE));
    member.setTelephone(securityBridge
        .decrypt(store, member.getTelephone(), DataType.TELEPHONE));
    return member;
  }
}
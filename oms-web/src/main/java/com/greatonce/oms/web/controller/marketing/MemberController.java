package com.greatonce.oms.web.controller.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.marketing.MemberService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.query.marketing.MemberQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import com.greatonce.oms.web.controller.trade.TradeConstants;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketing/member")
@CrossOrigin
public class MemberController implements PageListController<Member, MemberQuery> {

  @Autowired
  private MemberService memberService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<Member, MemberQuery> getBizService() {
    return memberService;
  }

  @Override
  @PostMapping
  public Member insert(@RequestBody Member member) {
    boolean exist = memberService.checkExist(member.getMemberName(), member.getStoreId());
    if(exist){
      throw new OmsException("会员已存在，不可重复添加！");
    }
    Store store = storeService.getByKey(member.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    member.setContact(
        securityBridge.encrypt(store, member.getContact(), DataType.NAME));
    member.setMobile(
        securityBridge.encrypt(store, member.getMobile(), DataType.MOBILE));
    member.setTelephone(
        securityBridge.encrypt(store, member.getTelephone(), DataType.TELEPHONE));
    memberService.create(member);
    return member;
  }

  /**
   * 展示会员列表. 如果没有选中店铺，需要将敏感信息用所有店铺加密一次。 否则只需要用选中的店铺来进行加密即可。
   */
  @Override
  @GetMapping
  public PageList<Member> listPage(MemberQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil
        .addUserStoreIds(filter, MemberQuery::getStoreIds, filter::setStoreIds);
    if (!Assert.isEmpty(filter.getMobile())) {
      if (!Pattern.matches(TradeConstants.MOBILE_PATTERN, filter.getMobile())) {
        throw new OmsException("请输入正确的手机号");
      }
      List<String> mobiles = controllerUtil
          .encryptKeyByStoreId(filter.getMobile(), filter.getStoreIds(),
              DataType.MOBILE);
      mobiles.add(filter.getMobile());
      filter.setMobiles(mobiles);
      filter.setMobile(null);
    }
    if (!Assert.isEmpty(filter.getContacts())) {
      List<String> contacts = controllerUtil
          .encryptKeysByStoreId(filter.getContacts(), filter.getStoreIds(),
              DataType.NAME);
      filter.getContacts().addAll(contacts);
    }
    return memberService.listPage(filter, page, pageSize);
  }

  /**
   * 返回完全解密的会员信息
   *
   * @param id 主键ID
   */
  @Override
  @GetMapping("/{id}")
  public Member get(@PathVariable("id") Long id) {
    return memberService.decrypt(memberService.getByKey(id));
  }
}

package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.query.base.StoreQuery;
import java.util.List;

/**
 * 店铺信息表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-18
 */
public interface StoreService extends BizService<Store, StoreQuery>, EnableBizService<Store> {

  /**
   * 编码是否存在.
   */
  boolean exists(String storeCode);

  /**
   * 获取授权URL.
   */
  String authUrl(Store store);

  /**
   * 授权.
   */
  void assessToken(Store store, String code);

  /**
   * 刷新令牌.
   */
  void refreshToken(Store store);

  /**
   * 获取用户授权店铺.
   */
  List<Store> listUserStore(Long userId);

  /**
   * 获取所有启用的店铺
   */
  List<Store> listEnableStore();
}
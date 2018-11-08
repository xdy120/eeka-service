package com.greatonce.oms.api.utils;

import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.Warehouse;
import com.qimen.api.QimenResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * QimenUtils.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/8
 */
@Component
public class QimenUtils {

  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;


  public Warehouse checkQimenRequest(String warehouseCode, String ownerCode) {
    return warehouseService.getEffectiveByCode(warehouseCode);
  }

  /**
   * 根据店铺对某一字段加密.
   * <p/>
   * 根据传入的每个店铺id查找店铺，且仅当appkey存在时对key进行加密，每个appkey只加密一次，返回集合不包括传入字段本身。
   *
   * @param key 被加密字段
   * @param storeIds 店铺id集合
   * @param dataType 加密字段类型
   */
  @SuppressWarnings("Duplicates")
  public List<String> encryptKeyByStoreId(String key, List<Long> storeIds, DataType dataType) {
    Map<String, String> encryptMap = new HashMap<>(storeIds.size());
    for (Long storeId : storeIds) {
      Store storeFullInfo = storeService.getByKey(storeId);
      if (storeFullInfo.getMallApp().getAppKey() == null) {
        continue;
      }
      //相同appkey不需重复加密
      if (!encryptMap.containsKey(storeFullInfo.getMallApp().getAppKey())) {
        SecurityBridge securityBridge = mallBridgeFactory
            .getSecurityBridge(storeFullInfo.getMallType());
        String encryptResult = securityBridge.encrypt(storeFullInfo, key, dataType);
        encryptMap.put(storeFullInfo.getMallApp().getAppKey(), encryptResult);
      }
    }
    return encryptMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
  }
}

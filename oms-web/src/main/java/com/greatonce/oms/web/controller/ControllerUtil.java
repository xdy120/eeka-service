package com.greatonce.oms.web.controller;

import com.greatonce.core.database.Query;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.base.PrivilegeService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.search.entity.AdvanceQuery.Field;
import com.greatonce.oms.search.entity.BoolType;
import com.greatonce.oms.search.entity.FieldType;
import com.greatonce.oms.search.entity.Operator;
import com.greatonce.oms.util.BizContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller工具类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-02
 */
@Component
public class ControllerUtil {

  @Autowired
  private PrivilegeService privilegeService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  /**
   * 为QUERY增加用户店铺，如果已经有店铺ID了就不设置.
   *
   * @param query 查询对象
   * @param getStoreIds 获取店铺id的方法
   * @param setStoreIds 设置店铺id的方法
   * @param <Q> 查询对象类型
   */
  public <Q extends Query> void addUserStoreIds(Q query,
      Function<Q, List<Long>> getStoreIds,
      Consumer<List<Long>> setStoreIds) {
    List<Long> storeIds = privilegeService.listItems(BizContext.getUserId(), PrivilegeType.STORE);
    Assert.notEmpty(storeIds, "用户没有店铺权限！");
    Collection<Long> ids = getStoreIds.apply(query);
    if (Assert.isEmpty(ids)) {
      setStoreIds.accept(storeIds);
    } else {
      ids.removeIf(x -> !storeIds.contains(x));
    }
  }

  /**
   * es查询，店铺权限
   *
   * @param fieldList es查询字段
   */
  public void addUserStoreIdsAndEncryptKey(List<Field> fieldList) {
    List<Long> storeIds = privilegeService.listItems(BizContext.getUserId(), PrivilegeType.STORE);
    Assert.notEmpty(storeIds, "用户没有店铺权限！");
    List<Long> validStoreIdList = null;
    for (Field field : fieldList) {
      if ("store_id".equals(field.getField())) {
        String[] split = field.getValue().split(",");
        validStoreIdList = Arrays.stream(split)
            .map(x -> Long.valueOf(x))
            .filter(x -> storeIds.contains(x))
            .collect(Collectors.toList());
        StringJoiner sj = new StringJoiner(",");
        validStoreIdList.forEach(x -> sj.add(String.valueOf(x)));
        field.setValue(sj.toString());
        break;
      }
    }
    if (Assert.isNull(validStoreIdList)) {
      validStoreIdList = storeIds;
      StringJoiner sj = new StringJoiner(",");
      storeIds.forEach(x -> sj.add(String.valueOf(x)));
      Field field = new Field();
      field.setField("store_id");
      field.setOperator(Operator.MULTI_EQUAL);
      field.setType(FieldType.STORE);
      field.setValue(sj.toString());
      field.setBoolType(BoolType.MUST);
      fieldList.add(field);
    }

    // 加密加密字段
    for (Field field : fieldList) {
      if ("mobile".equals(field.getField())
          && (field.getOperator() != Operator.NULL || field.getOperator() != Operator.NOT_NULL)) {
        List<String> mobiles = encryptKeyByStoreId(field.getValue(), validStoreIdList,
            DataType.MOBILE);
        field.setValue(StringUtil.join(mobiles, ","));
        if (field.getOperator() == Operator.NOT_FUZZY) {
          field.setOperator(Operator.NOT_MULTI_EQUAL);
        } else {
          field.setOperator(Operator.MULTI_EQUAL);
        }
      } else if ("telephone".equals(field.getField()) &&
          (field.getOperator() != Operator.NULL || field.getOperator() != Operator.NOT_NULL)) {
        List<String> telephones = encryptKeyByStoreId(field.getValue(), validStoreIdList,
            DataType.TELEPHONE);
        field.setValue(StringUtil.join(telephones, ","));
        if (field.getOperator() == Operator.NOT_FUZZY) {
          field.setOperator(Operator.NOT_MULTI_EQUAL);
        } else {
          field.setOperator(Operator.MULTI_EQUAL);
        }
      } else if ("contact".equals(field.getField())
          && (field.getOperator() != Operator.NULL || field.getOperator() != Operator.NOT_NULL)) {
        List<String> contacts = encryptKeyByStoreId(field.getValue(), validStoreIdList,
            DataType.NAME);
        field.setValue(StringUtil.join(contacts, ","));
        if (field.getOperator() == Operator.NOT_FUZZY) {
          field.setOperator(Operator.NOT_MULTI_EQUAL);
        } else {
          field.setOperator(Operator.MULTI_EQUAL);
        }
      }
    }
  }

  /**
   * 为QUERY增加用户仓库，如果已经有仓库ID了就不设置.
   *
   * @param query 查询对象
   * @param getVirtualWarehouseIds 获取仓库id的方法
   * @param setVirtualWarehouseIds 设置仓库id的方法
   * @param <Q> 查询对象类型
   */
  public <Q extends Query> void addUserVirtualWarehouseIds(Q query,
      Function<Q, List<Long>> getVirtualWarehouseIds,
      Consumer<List<Long>> setVirtualWarehouseIds) {
    List<Long> virtualWarehouseId = privilegeService
        .listItems(BizContext.getUserId(), PrivilegeType.WAREHOUSE);
    Assert.notEmpty(virtualWarehouseId, "用户没有仓库权限！");
    Collection<Long> ids = getVirtualWarehouseIds.apply(query);
    if (Assert.isEmpty(ids)) {
      setVirtualWarehouseIds.accept(virtualWarehouseId);
    } else {
      ids.removeIf(x -> !virtualWarehouseId.contains(x));
    }
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
      if (Assert.isNull(storeFullInfo) && Assert.isNull(storeFullInfo.getMallApp())) {
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

  /**
   * 根据店铺对集合中所有字段加密.
   *
   * @param keys 被加密字段集合
   * @param storeIds 店铺id集合
   * @param dataType 加密字段类型
   */
  public List<String> encryptKeysByStoreId(Collection<String> keys, List<Long> storeIds,
      DataType dataType) {
    Map<String, String> encryptMap = new HashMap<>(storeIds.size() * keys.size());
    for (Long storeId : storeIds) {
      Store storeFullInfo = storeService.getByKey(storeId);
      if (Assert.isNull(storeFullInfo.getMallApp())) {
        continue;
      }
      for (String key : keys) {
        String encryptMapKey = storeFullInfo.getMallApp().getAppKey() + key;
        //相同appkey不需重复加密
        if (!encryptMap.containsKey(encryptMapKey)) {
          SecurityBridge securityBridge = mallBridgeFactory
              .getSecurityBridge(storeFullInfo.getMallType());
          String encryptResult = securityBridge.encrypt(storeFullInfo, key, dataType);
          encryptMap.put(encryptMapKey, encryptResult);
        }
      }
    }
    return encryptMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
  }
}

package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.query.base.VirtualWarehouseQuery;
import java.util.List;

/**
 * 虚拟仓库.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VirtualWarehouseService extends
    BizService<VirtualWarehouse, VirtualWarehouseQuery>, EnableBizService<VirtualWarehouse> {

  /**
   * 获取实体仓下的共享仓.
   */
  VirtualWarehouse getShareWarehouse(Long warehouseId);

  /**
   * 获取次品仓.
   */
  VirtualWarehouse getSubstandardWarehouse(Long warehouseId);

  /**
   * 取到实体仓启用的独立仓.
   */
  List<VirtualWarehouse> listExclusive(Long warehouseId);

  /**
   * 获取用户授权虚拟仓库.
   */
  List<VirtualWarehouse> listUserVirtualWarehouse(Long userId);

  /**
   * 获取所有共享仓.
   */
  List<VirtualWarehouse> listAllShared();

  /**
   * 获取所有独立仓.
   */
  List<VirtualWarehouse> listAllExclusive();

  /**
   * 获取实体仓下的所有虚拟仓.
   */
  List<VirtualWarehouse> listAllVirtualWarehouse(Long warehouseId);

  /**
   * 获取仓库.
   */
  VirtualWarehouse getEffectiveByCode(String code);
}
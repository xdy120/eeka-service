package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.PrivilegeService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.dao.base.VirtualWarehouseDao;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.domain.enums.VirtualWarehouseType;
import com.greatonce.oms.query.base.VirtualWarehouseQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * VirtualWarehouse <br/> 虚拟仓库.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-13
 */
@Service
public class VirtualWarehouseServiceImpl extends
    AbstractEnableService<VirtualWarehouse, VirtualWarehouseQuery> implements
    VirtualWarehouseService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_VIRTUAL_WAREHOUSE);

  private static final String CACHE_NAME = "VIRTUAL_WAREHOUSE";

  @Autowired
  private VirtualWarehouseDao dao;
  @Autowired
  private PrivilegeService privilegeService;

  @Override
  protected QueryDao<VirtualWarehouse, VirtualWarehouseQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(VirtualWarehouse entity) {
    super.initDefaultValue(entity);
    entity.setEnable(false);
  }

  @Override
  protected void validateCreate(VirtualWarehouse entity) {
    super.validateCreate(entity);
    Assert.notTrue(getEffectiveByCode(entity.getVirtualWarehouseCode()) == null,
        "虚拟仓编码已存在");
  }

  @Override
  protected void validateModify(VirtualWarehouse entity) {
    final VirtualWarehouse exists = getEffectiveByCode(entity.getVirtualWarehouseCode());
    Assert.notTrue(
        exists == null || exists.getVirtualWarehouseId().equals(entity.getVirtualWarehouseId()),
        "虚拟仓编码已存在");
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'WID_'+#warehouseId+'_SHARE'")
  public VirtualWarehouse getShareWarehouse(Long warehouseId) {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setWarehouseId(warehouseId);
    eg.setEnable(true);
    eg.setVirtualWarehouseType(VirtualWarehouseType.SHARE);
    return dao.getByExample(eg);
  }

  @Override
  public VirtualWarehouse getSubstandardWarehouse(Long warehouseId) {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setWarehouseId(warehouseId);
    eg.setEnable(true);
    eg.setVirtualWarehouseType(VirtualWarehouseType.SUBSTANDARD);
    return dao.getByExample(eg);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'WID_'+#warehouseId+'_EXCLUSIVE'")
  public List<VirtualWarehouse> listExclusive(Long warehouseId) {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setWarehouseId(warehouseId);
    eg.setEnable(true);
    eg.setVirtualWarehouseType(VirtualWarehouseType.EXCLUSIVE);
    return dao.listExample(eg);
  }

  @Override
  public List<VirtualWarehouse> listUserVirtualWarehouse(Long userId) {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setEnable(true);
    List<VirtualWarehouse> warehouses = listExample(eg);
    privilegeService.filter(userId, warehouses, VirtualWarehouse::getVirtualWarehouseId,
        PrivilegeType.WAREHOUSE);
    return warehouses;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SHARE_ALL'")
  public List<VirtualWarehouse> listAllShared() {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setEnable(true);
    eg.setVirtualWarehouseType(VirtualWarehouseType.SHARE);
    return listExample(eg);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'EXCLUSIVE_ALL'")
  public List<VirtualWarehouse> listAllExclusive() {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setEnable(true);
    eg.setVirtualWarehouseType(VirtualWarehouseType.EXCLUSIVE);
    return listExample(eg);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'WID_'+#warehouseId")
  public List<VirtualWarehouse> listAllVirtualWarehouse(Long warehouseId) {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setEnable(true);
    eg.setWarehouseId(warehouseId);
    return listExample(eg);
  }

  /**
   * 启用.
   *
   * @return 影响行数
   */
  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int enable(VirtualWarehouse virtualWarehouse) {
    return super.enable(virtualWarehouse);
  }

  /**
   * 禁用.
   *
   * @return 影响行数
   */
  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int disable(VirtualWarehouse virtualWarehouse) {
    return super.disable(virtualWarehouse);
  }

  @CacheableNotNull(value = CACHE_NAME, key = "'VWID_'+#id")
  @Override
  public VirtualWarehouse getByKey(Long id) {
    return super.getByKey(id);
  }

  /**
   * 获取虚拟仓.
   *
   * @param virtualWarehouseCode not null
   * @return VirtualWarehouse
   */
  @CacheableNotNull(value = CACHE_NAME, key = "'VWCODE_'+#virtualWarehouseCode")
  public VirtualWarehouse getEffectiveByCode(String virtualWarehouseCode) {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setVirtualWarehouseCode(virtualWarehouseCode);
    eg.setEnable(true);
    return getByExample(eg);
  }
}
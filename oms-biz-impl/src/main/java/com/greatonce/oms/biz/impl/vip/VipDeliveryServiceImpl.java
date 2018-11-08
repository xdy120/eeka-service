package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.vip.VipDeliveryService;
import com.greatonce.oms.biz.vip.VipDispatchDetailService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipDispatchOrderBO;
import com.greatonce.oms.bridge.mall.impl.vip.VipDeliveryOrderBridge;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryCreateRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryImportRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryImportRequest.VipDeliveryDetail;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipDeliveryModifyRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipDeliveryCreateResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipDeliveryImportResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipDeliveryModifyResponse;
import com.greatonce.oms.dao.vip.VipDeliveryDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.vip.VipDeliveryStatus;
import com.greatonce.oms.domain.enums.vip.VipDispatchStatus;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.query.vip.VipDeliveryQuery;
import com.greatonce.oms.query.vip.VipDispatchQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 唯品出仓单. VipDelivery <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipDeliveryServiceImpl extends
    AbstractVersionService<VipDelivery, VipDeliveryQuery> implements VipDeliveryService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.VIP_DELIVERY);
  @Resource
  private IdGenerator vipDeliveryIdGenerator;
  @Resource
  private CodeGenerator vipDeliveryCodeGenerator;
  @Resource
  private VipDeliveryDao dao;
  @Resource
  private VipDispatchService vipDispatchService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private VipDeliveryOrderBridge vipDeliveryOrderBridge;
  @Autowired
  private MessageExporter messageExporter;
  @Autowired
  private VipDispatchDetailService vipDispatchDetailService;

  @Override
  protected QueryDao<VipDelivery, VipDeliveryQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipDeliveryIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(VipDelivery entity) {
    super.initDefaultValue(entity);
    entity.setVipDeliveryCode(vipDeliveryCodeGenerator.next());
    entity.setStatus(VipDeliveryStatus.CREATED);
    entity.setCreator(BizContext.getNickname());
  }

  @Override
  public int create(VipDelivery vipDelivery) {
    Store store = storeService.getByKey(vipDelivery.getStoreId());
    if (Assert.isEmpty(vipDelivery.getWaybillNumber())) {
      vipDelivery.setWaybillNumber("VIP888888");
    }

    VipDeliveryCreateRequest request = new VipDeliveryCreateRequest(store);
    request.setVipWarehouseCode(vipDelivery.getVipWarehouseCode());
    request.setArrivalDate(vipDelivery.getArrivalDate());
    request.setArrivalTime(vipDelivery.getArrivalTime());
    request.setDeliveryDate(vipDelivery.getDeliveryDate());
    request.setDeliveryTime(vipDelivery.getDeliveryTime());
    request.setBrandCode(vipDelivery.getBrandCode());
    request.setBrandName(vipDelivery.getBrandName());
    request.setCarrierCode(vipDelivery.getCarrierCode());
    request.setDeliveryMethod(vipDelivery.getDeliveryMethodCode());
    request.setPoCode(vipDelivery.getPoCode());
    request.setWaybillNumber(vipDelivery.getWaybillNumber());
    request.setJitType(vipDelivery.getJitType());

    VipDeliveryCreateResponse response = vipDeliveryOrderBridge.createDelivery(request);
    if (response.isSuccess()) {
      vipDelivery.setStorageNo(response.getDeliveryNo());
      initDefaultValue(vipDelivery);
      int count = insert(vipDelivery);
      BIZ_LOGGER.log(vipDelivery.getVipDeliveryId(), BizLogger.ADD, "新增唯品出仓单");
      return count;
    } else {
      throw new OmsException("创建唯品出仓单失败！" + response.getResult());
    }
  }

  @Override
  public int modify(VipDelivery vipDelivery) {
    Store store = storeService.getByKey(vipDelivery.getStoreId());
    VipDeliveryModifyRequest request = new VipDeliveryModifyRequest(store);
    request.setVipWarehouseCode(vipDelivery.getVipWarehouseCode());
    request.setArrivalDate(vipDelivery.getArrivalDate());
    request.setArrivalTime(vipDelivery.getArrivalTime());
    request.setDeliveryDate(vipDelivery.getDeliveryDate());
    request.setDeliveryTime(vipDelivery.getDeliveryTime());
    request.setBrandCode(vipDelivery.getBrandCode());
    request.setBrandName(vipDelivery.getBrandName());
    request.setCarrierCode(vipDelivery.getCarrierCode());
    request.setDeliveryMethod(vipDelivery.getDeliveryMethodCode());
    request.setPoCode(vipDelivery.getPoCode());
    request.setWaybillNumber(vipDelivery.getWaybillNumber());
    request.setStorageNo(vipDelivery.getStorageNo());
    VipDeliveryModifyResponse response = vipDeliveryOrderBridge.modifyDelivery(request);
    if (response.isSuccess()) {
      int count = update(vipDelivery);
      BIZ_LOGGER.log(vipDelivery.getVipDeliveryId(), BizLogger.UPDATE, "修改唯品出仓单");
      return count;
    } else {
      throw new OmsException("修改唯品出仓单失败！" + response.getResult());
    }
  }


  @Override
  public void delivery(VipDelivery vipDelivery, VersionBO bo) {
    if (VipDeliveryStatus.AUDITED != vipDelivery.getStatus()) {
      throw new OmsException("只有已审核的出仓单才能发货");
    }
    VipDispatch eg = new VipDispatch();
    eg.setVipDeliveryId(vipDelivery.getVipDeliveryId());
    List<VipDispatch> vipDispatches = vipDispatchService.listExample(eg);
    vipDispatches.removeIf(x -> x.getStatus() == VipDispatchStatus.CANCELED);
    if (Assert.isEmpty(vipDispatches)) {
      throw new OmsException("出仓单没有关联有效的拣货单");
    }
    if (vipDispatches.stream().anyMatch(
        x -> x.getStatus() != VipDispatchStatus.FINISH && x.getOutStatus() != OutStatus.ALL_OUT)) {
      throw new OmsException("只有全部出库或已完结的拣货单才能进行出仓单发货");
    }

    Store store = storeService.getByKey(vipDelivery.getStoreId());
    VipDeliveryImportRequest request = new VipDeliveryImportRequest(store);
    request.setPoCode(vipDelivery.getPoCode());
    request.setStorageNo(vipDelivery.getStorageNo());
    List<VipDeliveryDetail> deliveryDetails = new ArrayList<>();
    //组装request的明细数据
    for (VipDispatch vipDispatch : vipDispatches) {
      if (vipDispatch.getOutStatus() != OutStatus.NO_OUT) {
        List<VipDispatchDetail> vipDispatchDetails = vipDispatchDetailService
            .listDetails(vipDispatch.getVipDispatchId());
        for (VipDispatchDetail vipDispatchDetail : vipDispatchDetails) {
          if (vipDispatchDetail.getOutQuantity() > 0) {
            VipDeliveryDetail detail = new VipDeliveryDetail();
            detail.setQuantity(vipDispatchDetail.getOutQuantity());
            detail.setPoCode(vipDispatchDetail.getPoCode());
            detail.setPickNo(vipDispatch.getPickingCode());
            detail.setVipSkuCode(vipDispatchDetail.getVipBarcode());
            detail.setSkuCode(vipDispatchDetail.getSkuCode());
            deliveryDetails.add(detail);
          }
        }
      }
    }
    request.setDetails(deliveryDetails);
    VipDeliveryImportResponse response = vipDeliveryOrderBridge.delivery(request);
    if (response.isSuccess()) {
      VipDelivery updateInfo = new VipDelivery();
      updateInfo.setVipDeliveryId(vipDelivery.getVipDeliveryId());
      updateInfo.setStatus(VipDeliveryStatus.DELIVERED);
      updateInfo.setVersion(bo.getVersion());
      update(updateInfo);
      BIZ_LOGGER.log(vipDelivery.getVipDeliveryId(), BizLogger.DELIVERY, "仓库发货");
    } else {
      BIZ_LOGGER
          .log(vipDelivery.getVipDeliveryId(), BizLogger.DELIVERY, "仓库发货失败：{}",
              response.getResult());
      throw new OmsException("发货失败：" + response.getResult());
    }
  }

  @Override
  public void audit(VipDelivery vipDelivery, VersionBO bo) {
    if (vipDelivery.getStatus() != VipDeliveryStatus.CREATED) {
      throw new OmsException("只有新建的出仓单才能审核");
    }
    VipDelivery updateInfo = new VipDelivery();
    updateInfo.setVipDeliveryId(vipDelivery.getVipDeliveryId());
    updateInfo.setStatus(VipDeliveryStatus.AUDITED);
    updateInfo.setVersion(bo.getVersion());
    update(updateInfo);
    BIZ_LOGGER.log(vipDelivery.getVipDeliveryId(), BizLogger.AUDIT);
  }

  @Override
  public void invalid(VipDelivery vipDelivery, VersionBO bo) {
    if (vipDelivery.getStatus() != VipDeliveryStatus.CREATED) {
      throw new OmsException("只有新建的出仓单才能作废");
    }
    VipDelivery updateInfo = new VipDelivery();
    updateInfo.setVipDeliveryId(vipDelivery.getVipDeliveryId());
    updateInfo.setStatus(VipDeliveryStatus.INVALID);
    updateInfo.setVersion(bo.getVersion());
    update(updateInfo);
    BIZ_LOGGER.log(vipDelivery.getVipDeliveryId(), BizLogger.INVALID);
  }

  @Override
  public List<VipDispatchOrderBO> queryDispatch(VipDeliveryQuery filter) {
    return vipDispatchService.queryDispatch(filter.getVipDeliveryId());
  }

  @Override
  public void cancel(VipDelivery vipDelivery, VersionBO bo) {
    if (VipDeliveryStatus.DELIVERED == vipDelivery.getStatus()) {
      throw new OmsException("当前出仓单已发货，不能取消");
    }
    VipDispatchQuery vipDispatchFilter = new VipDispatchQuery();
    vipDispatchFilter.setVipDeliveryId(vipDelivery.getVipDeliveryId());
    List<VipDispatch> vipDispatches = vipDispatchService.list(vipDispatchFilter);
    if (vipDispatches.size() > 0) {
      for (VipDispatch vipDispatch : vipDispatches) {
        if (VipDispatchStatus.CANCELED != vipDispatch.getStatus()) {
          throw new OmsException("当前出仓单有绑定的拣货单未取消!");
        }
      }
    }
    VipDelivery updateInfo = new VipDelivery();
    updateInfo.setVipDeliveryId(vipDelivery.getVipDeliveryId());
    updateInfo.setStatus(VipDeliveryStatus.CANCELED);
    updateInfo.setVersion(bo.getVersion());
    update(updateInfo);
    BIZ_LOGGER.log(updateInfo.getVipDeliveryId(), BizLogger.CANCEL, "取消唯品");
  }

  @Override
  public void exportDelivery(String fileName, VipDeliveryQuery vipDeliveryQuery) {
    ExcelHeaderCollection<VipDelivery> headers = new ExcelHeaderCollection<>();
    headers.add("出仓单号", VipDelivery::getVipDeliveryCode);
    headers.add("状态", x -> FormatUtil.formatEnum(x.getStatus()));
    headers.add("店铺名称", VipDelivery::getStoreName);
    headers.add("PO编码", VipDelivery::getPoCode);
    headers.add("入库编号", VipDelivery::getStorageNo);
    headers.add("收货仓库", VipDelivery::getVipWarehouseName);
    headers.add("承运商", VipDelivery::getCarrierName);
    headers.add("运单号", VipDelivery::getWaybillNumber);
    headers.add("品牌编码", VipDelivery::getBrandCode);
    headers.add("品牌名称", VipDelivery::getBrandName);
    headers.add("送货方式", VipDelivery::getDeliveryMethodName);
    headers.add("要求到货时间", x -> x.getArrivalDate() + " " + x.getArrivalTime());
    headers.add("送货批次", x -> x.getDeliveryDate() + " " + x.getDeliveryTime());
    headers.add("制单人", VipDelivery::getCreator);
    headers.add("制单时间", x -> DateTimeUtil.format(x.getCreatedTime()));
    messageExporter.exportExcel(this, headers, vipDeliveryQuery, fileName);
  }

  @Override
  public void manualDelivery(VipDelivery vipDelivery, VersionBO bo) {
    VipDispatch eg = new VipDispatch();
    eg.setVipDeliveryId(vipDelivery.getVipDeliveryId());
    List<VipDispatch> vipDispatches = vipDispatchService.listExample(eg);
    vipDispatches.removeIf(x -> x.getStatus() == VipDispatchStatus.CANCELED);
    if (Assert.isEmpty(vipDispatches)) {
      throw new OmsException("出仓单没有关联有效的拣货单");
    }
    vipDispatches.forEach(dispatch -> {
      if (dispatch.getStatus() != VipDispatchStatus.FINISH
          && dispatch.getOutStatus() != OutStatus.ALL_OUT) {
        throw new OmsException(String.format("拣货单：%s，未全部出库或者未完结！",dispatch.getVipDispatchCode()));
      }
    });
    VipDelivery update = new VipDelivery();
    update.setVipDeliveryId(vipDelivery.getVipDeliveryId());
    update.setVersion(bo.getVersion());
    update.setStatus(VipDeliveryStatus.DELIVERED);
    super.update(update);
  }
}
package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.MathUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.vip.VipReturnDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.biz.vip.VipReturnService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipRefundOrderDownloadBO;
import com.greatonce.oms.bo.vip.VipReturnExportBO;
import com.greatonce.oms.bo.vip.VipReturnScanBO;
import com.greatonce.oms.bo.vip.VipReturnSignBO;
import com.greatonce.oms.bridge.mall.impl.vip.VipReturnBridge;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipReturnQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipReturnQueryResponse;
import com.greatonce.oms.dao.vip.VipReturnDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.setting.IoBillSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.vip.VipReturnStatus;
import com.greatonce.oms.domain.enums.vip.VipSignStatus;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.domain.vip.VipReturnDetail;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.message.vip.VipReturnNoticeCreateMessage;
import com.greatonce.oms.query.vip.VipReturnDetailQuery;
import com.greatonce.oms.query.vip.VipReturnQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vipapis.vreturn.ReturnDeliveryDetail;
import vipapis.vreturn.ReturnDeliveryInfo;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 唯品退货单. VipReturn <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipReturnServiceImpl extends
    AbstractVersionService<VipReturn, VipReturnQuery> implements
    VipReturnService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.VIP_RETURN);
  private static final Logger LOGGER = LoggerFactory.getLogger(VipReturnServiceImpl.class);
  @Autowired
  private VipReturnDao dao;
  @Resource
  private IdGenerator vipReturnIdGenerator;
  @Resource
  private CodeGenerator vipReturnCodeGenerator;
  @Resource
  private VipReturnDetailService vipReturnDetailService;
  @Resource
  private VirtualWarehouseService virtualWarehouseService;

  @Resource
  private VipReturnNoticeService vipReturnNoticeService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private MessageExporter messageExporter;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private VipReturnBridge vipReturnBridge;
  @Autowired
  private VipReturnService vipReturnService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private DataDictItemService dataDictItemService;

  @Override
  protected QueryDao<VipReturn, VipReturnQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipReturnIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(VipReturn vipReturn) {
    super.initDefaultValue(vipReturn);
    vipReturn.setVipReturnCode(vipReturnCodeGenerator.next());
    vipReturn.setVipSignStatus(VipSignStatus.NO_SIGN);
    vipReturn.setVipPriceAbnormal(false);
    vipReturn.setScanQuantity(0);
    if (Assert.isNull(vipReturn.isAbnormal())) {
      vipReturn.setStatus(VipReturnStatus.CREATED);
      vipReturn.setAbnormal(false);
    }
    if (vipReturn.getCreator() == null) {
      vipReturn.setCreator(BizContext.getNickname());
    }
    HashSet<String> boxSet = new HashSet<>();
    int quantity = 0;
    double totalAmount = 0;
    for (VipReturnDetail detail : vipReturn.getDetails()) {
      detail.setVipReturnId(vipReturn.getVipReturnId());
      boxSet.add(detail.getBoxNumber());
      quantity += detail.getReturnQuantity();
      if (!Assert.isNull(detail.getVipPrice()) && detail.getVipPrice() > 0) {
        detail.setVipPriceAbnormal(false);
        totalAmount += MathUtil.multiply(detail.getVipPrice(),detail.getReturnQuantity());
      } else {
        detail.setVipPrice(0D);
        detail.setVipAmount(0D);
        vipReturn.setVipPriceAbnormal(true);
        detail.setVipPriceAbnormal(true);
      }
    }
    vipReturn.setVipAmount(totalAmount);
    vipReturn.setBoxQuantity(boxSet.size());
    vipReturn.setQuantity(quantity);
    vipReturn.setSkuQuantity(vipReturn.getDetails().size());

  }

  /**
   * 唯品退货单新增.
   *
   * @param record not null
   */
  @Override
  public int create(VipReturn record) {
    Assert.notNull(record.getDetails(), "退货单没有退货商品");
    try {
      initDefaultValue(record);
      int count = getTransactionTemplate().executeWithResult(() -> {
        vipReturnDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getVipReturnId(), BizLogger.ADD, "手工生成唯品退货单");
      return count;
    } catch (Exception e) {
      LOGGER.error("新增唯品退货单失败,退货单信息:{}", JsonUtil.toJson(record));
      LOGGER.error("新增唯品退货单失败,堆栈信息:", e);
      throw new OmsException("新增唯品退货单失败");
    }
  }

  @Override
  public void createNotice(VipReturn record, VersionBO bo) {
    VipReturnNotice vipReturnNotice = new VipReturnNotice();
    IoBillSetting setting = settingService.getIoBillSetting();
    List<VipReturnDetail> returnDetails;
    List<VipReturnNoticeDetail> noticeDetails;
    int noticeQuantity = 0;
    if (setting.getVipReturnNoticeOrderNum()
        == IoBillSetting.NoticeOrderNumGranularity.RETURN_NUMBER) {
      VipReturnDetail eg = new VipReturnDetail();
      eg.setVipReturnId(record.getVipReturnId());
      returnDetails = vipReturnDetailService.listExample(eg);
      noticeDetails = new ArrayList<>(returnDetails.size());
      for (VipReturnDetail detail : returnDetails) {
        VipReturnNoticeDetail noticeDetail = new VipReturnNoticeDetail();
        noticeDetail.setVipReturnId(record.getVipReturnId());
        noticeDetail.setVipReturnCode(record.getVipReturnCode());
        noticeDetail.setVipReturnDetailId(detail.getVipReturnDetailId());
        noticeDetail.setProductId(detail.getProductId());
        noticeDetail.setProductCode(detail.getProductCode());
        noticeDetail.setProductName(detail.getProductName());
        noticeDetail.setSkuId(detail.getSkuId());
        noticeDetail.setSkuCode(detail.getSkuCode());
        noticeDetail.setSkuName(detail.getSkuName());
        noticeDetail.setNoticeQuantity(detail.getReturnQuantity());
        noticeDetail.setVipPrice(detail.getVipPrice());
        noticeDetail.setVipAmount(0D);
        noticeDetail.setVipPriceAbnormal(detail.isVipPriceAbnormal());
        if (Assert.isTrue(detail.isVipPriceAbnormal())) {
          vipReturnNotice.setVipPriceAbnormal(true);
        }
        detail.setNoticeQuantity(detail.getReturnQuantity());
        noticeDetails.add(noticeDetail);
        noticeQuantity += noticeDetail.getNoticeQuantity();
      }
    } else {
      if (record.getVipSignStatus() == VipSignStatus.NO_SIGN) {
        throw new OmsException("单据未签收");
      }
      returnDetails = vipReturnDetailService.listCanNotice(record.getVipReturnId());
      Assert.notEmpty(returnDetails, "没有可通知商品！");
      noticeDetails = new ArrayList<>(returnDetails.size());
      for (VipReturnDetail detail : returnDetails) {
        VipReturnNoticeDetail noticeDetail = new VipReturnNoticeDetail();
        noticeDetail.setVipReturnId(record.getVipReturnId());
        noticeDetail.setVipReturnCode(record.getVipReturnCode());
        noticeDetail.setVipReturnDetailId(detail.getVipReturnDetailId());
        noticeDetail.setProductId(detail.getProductId());
        noticeDetail.setProductCode(detail.getProductCode());
        noticeDetail.setProductName(detail.getProductName());
        noticeDetail.setSkuId(detail.getSkuId());
        noticeDetail.setSkuCode(detail.getSkuCode());
        noticeDetail.setSkuName(detail.getSkuName());
        noticeDetail.setNoticeQuantity(detail.getScanQuantity() - detail.getNoticeQuantity());
        noticeDetail.setVipPrice(detail.getVipPrice());
        noticeDetail.setVipAmount(0D);
        noticeDetail.setVipPriceAbnormal(detail.isVipPriceAbnormal());
        if (Assert.isTrue(detail.isVipPriceAbnormal())) {
          vipReturnNotice.setVipPriceAbnormal(true);
        }
        noticeQuantity += noticeDetail.getNoticeQuantity();
        noticeDetails.add(noticeDetail);
        detail.setNoticeQuantity(detail.getScanQuantity());
      }
    }

    VirtualWarehouse warehouse = virtualWarehouseService.getByKey(record.getVirtualWarehouseId());
    vipReturnNotice.setStoreId(record.getStoreId());
    vipReturnNotice.setStoreName(record.getStoreName());
    vipReturnNotice.setWarehouseId(warehouse.getWarehouseId());
    vipReturnNotice.setWarehouseName(warehouse.getWarehouseName());
    vipReturnNotice.setNoticeQuantity(noticeQuantity);
    if (!Assert.isNull(record.getCreator())) {
      vipReturnNotice.setCreator(record.getCreator());
    }
    vipReturnNotice.setDetails(noticeDetails);

    VipReturn updateInfo = new VipReturn();
    updateInfo.setVipReturnId(record.getVipReturnId());
    updateInfo.setVersion(bo.getVersion());

    try {
      getTransactionTemplate().execute(() -> {
        //添加唯品退货通知单
        vipReturnNoticeService.create(vipReturnNotice);
        //更新唯品退货单明细
        vipReturnDetailService.batchModify(returnDetails);
        update(updateInfo);
      });
      BIZ_LOGGER.log(vipReturnNotice.getVipReturnNoticeId(), BizLogger.ADD, "创建唯品退货通知单成功");
      getMqProducer()
          .send(new VipReturnNoticeCreateMessage(vipReturnNotice.getVipReturnNoticeId()));
    } catch (Exception e) {
      LOGGER.error("创建唯品退货通知单失败,退货单信息:{}", JsonUtil.toJson(record));
      LOGGER.error("创建唯品退货通知单失败,堆栈信息:", e);
      throw new OmsException("创建唯品退货通知单失败");
    }
  }

  @Override
  public void audit(VipReturn vipReturn, VersionBO bo) {
    if (vipReturn.getStatus() != VipReturnStatus.CREATED) {
      throw new OmsException("只有新建的单据才能审核");
    }
    VipReturn updateInfo = new VipReturn();
    updateInfo.setVipReturnId(vipReturn.getVipReturnId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(VipReturnStatus.AUDITED);
    update(updateInfo);
  }

  @Override
  public VipReturn getByOuterCode(String outerCode) {
    VipReturn eg = new VipReturn();
    eg.setOuterCode(outerCode);
    return getByExample(eg);

  }

  @Override
  public void autoCreate(VipReturn vipReturn) {
    //封装明细数据
    matchProduct(vipReturn);
    vipReturn.setStatus(VipReturnStatus.AUDITED);
    try {
      initDefaultValue(vipReturn);
      getTransactionTemplate().execute(() -> {
        vipReturnDetailService.batchCreate(vipReturn.getDetails());
        insert(vipReturn);
      });
      BIZ_LOGGER.log(vipReturn.getVipReturnId(), BizLogger.ADD, "自动下载生成唯品退货单");
    } catch (Exception e) {
      LOGGER.error("新增唯品退货单失败,退货单信息:{}", JsonUtil.toJson(vipReturn));
      LOGGER.error("新增唯品退货单失败,堆栈信息:", e);
      throw new OmsException("新增唯品退货单失败");
    }
  }


  @Override
  public void sign(VipReturn vipReturn, VipReturnSignBO bo) {
    if (vipReturn.getStatus() != VipReturnStatus.AUDITED) {
      throw new OmsException("只有已审核的单据才能签收");
    }
    IoBillSetting ioBillSetting = settingService.getIoBillSetting();
    if (Assert.isTrue(ioBillSetting.isVipPriceAbnormalIntercept())
        && Assert.isTrue(vipReturn.isVipPriceAbnormal())){
      throw new OmsException("请先维护唯品价后再签收");
    }
    VipReturn updateInfo = new VipReturn();
    updateInfo.setVipReturnId(vipReturn.getVipReturnId());
    updateInfo.setVipReturnType(bo.getVipReturnType());
    updateInfo.setVipSignStatus(bo.getVipSignStatus());
    updateInfo.setVirtualWarehouseId(bo.getVirtualWarehouseId());
    updateInfo.setVirtualWarehouseName(bo.getVirtualWarehouseName());
    if (Assert.isNull(bo.getOperator())) {
      updateInfo.setSigner(BizContext.getNickname());
    } else {
      updateInfo.setSigner(bo.getOperator());
    }
    updateInfo.setSignTime(LocalDateTime.now());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setRemark(bo.getRemark());
    update(updateInfo);

    BIZ_LOGGER.log(vipReturn.getVipReturnId(), BizLogger.SIGN);
  }

  @Override
  public void scan(VipReturn vipReturn, VipReturnScanBO bo) {
    if (vipReturn.getStatus() != VipReturnStatus.AUDITED) {
      throw new OmsException("只有已审核的单据才能扫描");
    }
    if (vipReturn.getVipSignStatus() == VipSignStatus.NO_SIGN) {
      throw new OmsException("单据未签收");
    }
    List<VipReturnDetail> details = new ArrayList<>(bo.getDetails().size());
    int scanningQuantity = 0;
    for (VipReturnScanBO.VipReturnScanDetail vipReturnScanDetail : bo.getDetails()) {
      VipReturnDetail detail = new VipReturnDetail();
      detail.setVipReturnDetailId(vipReturnScanDetail.getVipReturnDetailId());
      detail.setScanQuantity(vipReturnScanDetail.getScanQuantity());
      VipReturnDetail vipReturnDetail = vipReturnDetailService
          .getByKey(vipReturnScanDetail.getVipReturnDetailId());
      scanningQuantity += (vipReturnScanDetail.getScanQuantity() - vipReturnDetail
          .getScanQuantity());
      details.add(detail);
    }
    VipReturn updateInfo = new VipReturn();
    updateInfo.setScanQuantity(vipReturn.getScanQuantity() + scanningQuantity);
    updateInfo.setVipReturnId(vipReturn.getVipReturnId());
    if (Assert.isNull(vipReturn.getCreator())) {
      updateInfo.setSigner(BizContext.getNickname());
    } else {
      updateInfo.setSigner(vipReturn.getCreator());
    }
    updateInfo.setSignTime(LocalDateTime.now());
    updateInfo.setVersion(bo.getVersion());
    try {
      getTransactionTemplate().execute(() -> {
        //更新唯品退货单明细
        vipReturnDetailService.batchModify(details);
        update(updateInfo);
      });
      BIZ_LOGGER.log(vipReturn.getVipReturnId(), BizLogger.SCAN);
    } catch (Exception e) {
      LOGGER.error("扫描唯品退货单失败,退货单信息:{}", JsonUtil.toJson(vipReturn));
      LOGGER.error("扫描唯品退货单失败,堆栈信息:", e);
      throw new OmsException("扫描唯品退货单失败");
    }
  }

  /**
   * 封装唯品退货单信息
   */
  public void matchProduct(VipReturn vipReturn) {
    //根据平台获取到的唯品规格编码找铺货关系，找不到标记为异常订单
    vipReturn.setAbnormal(false);
    vipReturn.getDetails().forEach(vipReturnDetail -> {
      vipReturnDetail.setAbnormal(false);
      ProductMallMapping mallMapping = productMallMappingService
          .getByMallSkuOutCode(vipReturnDetail.getVipBarcode(), vipReturn.getStoreId());
      ProductSku sku;
      if (!Assert.isNull(mallMapping) && Assert.isTrue(mallMapping.isMatched())) {
        sku = productSkuService.getEffectiveById(mallMapping.getSkuId());
      } else {
        sku = productSkuService.getEffectiveByCode(vipReturnDetail.getVipBarcode());
      }
      if (!Assert.isNull(sku) && !Assert.isNull(sku.getSkuCode())) {
        vipReturnDetail.setProductCode(sku.getProductCode());
        vipReturnDetail.setProductId(sku.getProductId());
        vipReturnDetail.setProductName(sku.getProductName());
        vipReturnDetail.setSkuCode(sku.getSkuCode());
        vipReturnDetail.setSkuId(sku.getSkuId());
        vipReturnDetail.setSkuName(sku.getSkuName());
        vipReturnDetail.setVipPrice(sku.getVipPrice());
      } else {
        //设为异常订单
        vipReturnDetail.setAbnormal(true);
        vipReturn.setAbnormal(true);
      }
      if (Assert.isNull(vipReturnDetail.getVipPrice()) || vipReturnDetail.getVipPrice() <= 0) {
        vipReturnDetail.setVipPriceAbnormal(false);
        vipReturnDetail.setVipAmount(0D);
        vipReturn.setVipPriceAbnormal(false);
        vipReturn.setVipAmount(0D);
      }
    });
  }

  /**
   * 再次匹配铺货关系和系统商品
   */
  @Override
  public void match(VipReturn record) {
    VipReturnDetailQuery vipReturnDetailFilter = new VipReturnDetailQuery();
    vipReturnDetailFilter.setAbnormal(true);
    vipReturnDetailFilter.setVipReturnId(record.getVipReturnId());
    List<VipReturnDetail> vipReturnDetails = vipReturnDetailService
        .list(vipReturnDetailFilter);
    record.setDetails(vipReturnDetails);
    matchProduct(record);
    record.getDetails().forEach(x -> {
      ProductSku sku = productSkuService.getEffectiveByCode(x.getSkuCode());
      if (!Assert.isNull(sku.getVipPrice())) {
        x.setVipPrice(sku.getVipPrice());
      }
    });
    try {
      getTransactionTemplate().execute(() -> {
        vipReturnDetailService.batchModify(vipReturnDetails);
        update(record);
      });
      BIZ_LOGGER.log(record.getVipReturnId(), BizLogger.ADD, "唯品退货单匹配异常成功");
    } catch (Exception e) {
      LOGGER.error("唯品退货单匹配异常失败,拣货单信息: {}", JsonUtil.toJson(record));
      LOGGER.error("唯品退货单匹配异常失败,堆栈信息: ", e);
      throw new OmsException("唯品退货单匹配异常失败");
    }
  }

  @Override
  public void exportReturn(String fileName, VipReturnQuery vipReturnQuery) {
    ExcelHeaderCollection<VipReturnExportBO> headers = new ExcelHeaderCollection<>();
    headers.add("退货单号", VipReturnExportBO::getVipReturnCode);
    headers.add("唯品退供单号", VipReturnExportBO::getOuterCode);
    headers.add("状态", x -> Assert.isNull(x.getStatus()) ? "" : x.getStatus().caption);
    headers
        .add("签收状态", x -> Assert.isNull(x.getVipSignStatus()) ? "" : x.getVipSignStatus().caption);
    headers.add("退货类型", x -> x.getVipReturnType());
    headers.add("店铺", VipReturnExportBO::getStoreName);
    headers.add("唯品仓库", VipReturnExportBO::getVipWarehouseName);
    headers.add("退入仓库", VipReturnExportBO::getVirtualWarehouseName);
    headers.add("箱数", x -> FormatUtil.formatInteger(x.getBoxQuantity()));
    headers.add("唯品箱码", VipReturnExportBO::getBoxNumber);
    headers.add("PO", VipReturnExportBO::getPoCode);
    headers.add("规格编码（SKU）", VipReturnExportBO::getSkuCode);
    headers.add("规格名称", VipReturnExportBO::getSkuName);
    headers.add("唯品价", x -> FormatUtil.formatDouble(x.getVipPrice()));
    headers.add("退货数量", x -> FormatUtil.formatInteger(x.getReturnQuantity()));
    headers.add("扫描数量", x -> FormatUtil.formatInteger(x.getScanQuantity()));
    headers.add("通知数量", x -> FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("唯品金额", x -> FormatUtil.formatDouble(x.getVipAmount()));
    headers.add("制单人", VipReturnExportBO::getCreator);
    headers.add("制单时间", x -> FormatUtil.formatLocalDateTime(x.getCreatedTime()));
    headers.add("扫描人", VipReturnExportBO::getSigner);
    headers.add("扫描时间", x -> FormatUtil.formatLocalDateTime(x.getSignTime()));
//    headers.add("扫描人",VipReturnExportBO);
//    headers.add("扫描时间",VipReturnExportBO);
    messageExporter.exportExcel(this::exportListVipReturn, headers, vipReturnQuery, fileName);
  }

  @Override
  public PageList<VipReturnExportBO> exportListVipReturn(VipReturnQuery query, Integer page,
      Integer pageSize) {
    return dao.exportListVipReturn(query, page, pageSize);
  }

  /**
   * 重新计算唯品退货单下明细的金额，如果当前明细金额为空，则重新进行计算
   */
  @Override
  public void recalculateAmount(VipReturn vipReturn, VersionBO bo) {
    VipReturnDetailQuery query = new VipReturnDetailQuery();
    query.setVipReturnId(vipReturn.getVipReturnId());
    query.setVipPriceAbnormal(true);
    List<VipReturnDetail> vipReturnDetails = vipReturnDetailService.list(query);
    if (!Assert.isEmpty(vipReturnDetails)) {
      VipReturn updateInfo = new VipReturn();
      updateInfo.setVipReturnId(vipReturn.getVipReturnId());
      updateInfo.setVipPriceAbnormal(false);
      updateInfo.setVersion(bo.getVersion());
      double totalAmount = 0D;
      ProductSku sku;
      for (VipReturnDetail detail : vipReturnDetails) {
        if (Assert.isNull(detail.getSkuId())) {
          updateInfo.setVipPriceAbnormal(true);
        } else {
          sku = productSkuService.getEffectiveById(detail.getSkuId());
          if (!Assert.isNull(sku.getVipPrice()) && sku.getVipPrice() > 0) {
            detail.setVipPrice(sku.getVipPrice());
            detail.setVipAmount(sku.getVipPrice() * detail.getReturnQuantity());
            detail.setVipPriceAbnormal(false);
            totalAmount += detail.getVipAmount();
          } else {
            updateInfo.setVipPriceAbnormal(true);
          }
        }
      }
      if (totalAmount > 0) {
        updateInfo.setVipAmount(vipReturn.getVipAmount() + totalAmount);
      }
      try {
        getTransactionTemplate().execute(() -> {
          vipReturnDetailService.batchModify(vipReturnDetails);
          update(updateInfo);
        });
        BIZ_LOGGER.log(vipReturn.getVipReturnId(), BizLogger.UPDATE, "重新计算唯品金额");
      } catch (Exception e) {
        LOGGER.error("重新计算失败,退货通知单明细信息: {}", JsonUtil.toJson(vipReturnDetails));
        LOGGER.error("重新计算失败,堆栈信息: ", e);
        throw new OmsException("重新计算失败");
      }
    }
  }

  /**
   * 根据退供单号和生成时间来下载唯品退货单
   */
  @Override
  public void automaticDownload(VipRefundOrderDownloadBO bo) {
    Store store = storeService.getByKey(bo.getStoreId());
    VipReturnQueryRequest request = new VipReturnQueryRequest(store);
    if (Assert.isNull(bo.getOuterCode())) {
      request.setBeginTime(bo.getCreatedTimeBegin());
      request.setEndTime(bo.getCreatedTimeEnd());
    } else {
      request.setOuterCode(StringUtil.join(StringUtil.words(bo.getOuterCode())));
    }
    List<DataDictItem> items = dataDictItemService.listByDictId(10804L);
    request.setPage(1);
    for (DataDictItem item : items) {
      request.setVipWarehouseCode(item.getDataDictItemCode());
      vipReturnService.download(request, store, item);
    }
  }

  @Override
  public void download(VipReturnQueryRequest request, Store store, DataDictItem warehouse) {
    VipReturnQueryResponse response = vipReturnBridge
        .queryReturnDetail(request);
    LOGGER.info("下载【{}】店铺{}~{}仓库【{}】第{}页退供单共{}条数据...", store.getStoreName(), request.getBeginTime(),
        request.getEndTime(), warehouse.getDataDictItemName(), request.getPage(),
        response.getCount());
    if (!Assert.isEmpty(response.getDeliveries())) {
      VipReturn vipReturn;
      for (ReturnDeliveryInfo returnDeliveryInfo : response.getDeliveries()) {
        vipReturn = getByOuterCode(returnDeliveryInfo.getReturn_sn());
        if (Assert.isNull(vipReturn) || vipReturn.getStatus() == VipReturnStatus.INVALID) {
          vipReturn = parseVipReturn(store, warehouse, returnDeliveryInfo);
          autoCreate(vipReturn);
        } else {
          LOGGER.info("订单【{}】已存在，跳过处理", returnDeliveryInfo.getReturn_sn());
        }
      }
    }
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      download(request, store, warehouse);
    }
  }

  @Override
  public VipReturn parseVipReturn(Store store, DataDictItem warehouse,
      ReturnDeliveryInfo returnDeliveryInfo) {
    VipReturn vipReturn = new VipReturn();
    vipReturn.setOuterCode(returnDeliveryInfo.getReturn_sn());
    vipReturn.setReturnTime(DateTimeUtil.parserLocalDateTime(returnDeliveryInfo.getOut_time()));
    vipReturn.setStoreId(store.getStoreId());
    vipReturn.setStoreName(store.getStoreName());
    vipReturn.setVipReturnType("三退");
    vipReturn.setVipWarehouseCode(warehouse.getDataDictItemCode());
    vipReturn.setVipWarehouseName(warehouse.getDataDictItemName());
    vipReturn.setVirtualWarehouseId(store.getSetting().getDefaultReturnWarehouse());
    vipReturn.setVirtualWarehouseName(store.getSetting().getDefaultReturnWarehouseName());
    vipReturn.setQuantity(returnDeliveryInfo.getTotal_qtys().intValue());
    vipReturn.setSkuQuantity(returnDeliveryInfo.getTotal_skus().intValue());
    vipReturn.setBoxQuantity(returnDeliveryInfo.getTotal_cases().intValue());
    parseVipReturnDetail(vipReturn, returnDeliveryInfo);
    return vipReturn;

  }

  @Override
  public void parseVipReturnDetail(VipReturn vipReturn, ReturnDeliveryInfo returnDeliveryInfo) {
    List<VipReturnDetail> vipReturnDetails = new ArrayList<>(
        returnDeliveryInfo.getDelivery_list().size());
    for (ReturnDeliveryDetail detail : returnDeliveryInfo.getDelivery_list()) {
      VipReturnDetail vipReturnDetail = new VipReturnDetail();
      vipReturnDetail.setBoxNumber(detail.getBox_no());
      vipReturnDetail.setPoCode(detail.getPo_no());
      vipReturnDetail.setVipBarcode(detail.getBarcode());
      vipReturnDetail.setReturnQuantity(detail.getQty().intValue());
      vipReturnDetails.add(vipReturnDetail);
    }
    vipReturn.setDetails(vipReturnDetails);
  }

}
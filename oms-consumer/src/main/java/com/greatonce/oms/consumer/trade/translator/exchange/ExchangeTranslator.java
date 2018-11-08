package com.greatonce.oms.consumer.trade.translator.exchange;

import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.admin.MallRegionMappingService;
import com.greatonce.oms.biz.admin.RegionService;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.trade.ExchangeApplyOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.mall.MallExchangeOrderInfo;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.domain.admin.MallRegionMapping;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.mall.MallExchangeStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.ExchangeApplyOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateExchangeLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Shenzhen cca
 * @version 2018/7/21
 */
@Component
@TranslatorExchangeCondition
public class ExchangeTranslator {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeTranslator.class);
  private static final TranslateExchangeLogger TRANSLATE_EXCHANGE_LOGGER = OmsLoggerFactory
      .getTranslateExchangeLogger();

  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private StoreService storeService;
  @Autowired
  ExchangeApplyOrderService exchangeApplyOrderService;
  @Autowired
  ProductMallMappingService productMallMappingService;
  @Autowired
  MallRegionMappingService mallRegionMappingService;
  @Autowired
  RegionService regionService;
  @Autowired
  MallBridgeFactory mallBridgeFactory;
  @Autowired
  DataDictItemService dataDictItemService;
  @Autowired
  StockOccupancyService stockOccupancyService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private CodeGenerator exchangeOrderCodeGenerator;
  @Autowired
  private MqProducer mqProducer;

  /**
   * 换货单转货主方法
   */
  public void translate(ExchangeTranslatableContext context) {
    MallExchangeOrderInfo mallExchangeOrderInfo = context.getMallExchangeOrderInfo();
    Long storeId = context.getMallExchangeOrder().getStoreId();
    Store store = storeService.getByKey(storeId);
    context.setStore(store);

    boolean isCross = validate(context);
    ExchangeApplyOrder exchangeApplyOrderEg = new ExchangeApplyOrder();
    exchangeApplyOrderEg.setMallDetailId(mallExchangeOrderInfo.getOid());
    ExchangeApplyOrder exchangeApplyOrder = exchangeApplyOrderService.getByExample(exchangeApplyOrderEg);

    if (Assert.isNull(exchangeApplyOrder)) {
      create(context,isCross);
      writeLog(context, "新增换货申请");
    } else {
      context.setExchangeApplyOrder(exchangeApplyOrder);
      writeLog(context, "修改换货申请,原换货申请单id:{}", exchangeApplyOrder.getExchangeApplyOrderId());
      modify(context,isCross);
    }
  }

  /**
   * 检查该有的信息得有
   * 检查状态
   */
  private boolean validate(ExchangeTranslatableContext context) {
    MallExchangeOrderInfo mallExchangeInfo = context.getMallExchangeOrderInfo();
    MallExchangeOrder mallExchangeOrder = context.getMallExchangeOrder();
    Store store = storeService.getByKey(mallExchangeOrder.getStoreId());
    context.setStore(store);

    ProductMallMapping outProductMappingEg = new ProductMallMapping();
    outProductMappingEg.setMallSkuId(mallExchangeInfo.getOutMallSkuId());
    outProductMappingEg.setStoreId(mallExchangeOrder.getStoreId());
    //换出商品的 铺货关系
    ProductMallMapping outProductMapping = productMallMappingService.getByExample(outProductMappingEg);
    if (Assert.isNull(outProductMapping)) {
      LOGGER.info("找不到换出商品的铺货关系,平台skuId:{}", mallExchangeInfo.getOutMallSkuId());
      return false;
    }else {
      context.setOutProductMapping(outProductMapping);
    }
    //退入商品的铺货关系
    ProductMallMapping inProductMappingEg = new ProductMallMapping();
    inProductMappingEg.setMallSkuId(mallExchangeInfo.getInMallSkuId());
    inProductMappingEg.setStoreId(mallExchangeOrder.getStoreId());
    ProductMallMapping inProductMapping = productMallMappingService.getByExample(inProductMappingEg);
    if (Assert.isNull(inProductMapping)){
      LOGGER.info("找不到退入商品的铺货关系,平台skuId:{}", mallExchangeInfo.getInMallSkuId());
      return false;
    }else {
      context.setInProductMappping(inProductMapping);
    }

    SalesOrderDetail detailEg = new SalesOrderDetail();
    detailEg.setMallDetailId(mallExchangeInfo.getOid());
    //获取销售单明细
    SalesOrderDetail salesOrderDetail = salesOrderDetailService.getByExample(detailEg);
    if (Assert.isNull(salesOrderDetail)) {
      LOGGER.info("退入商品找不到销售单明细");
      return false;
    }
    context.setSalesOrderDetail(salesOrderDetail);
    //获取销售单
    SalesOrder salesOrder = salesOrderService.getByKey(salesOrderDetail.getSalesOrderId());
    if (Assert.isNull(salesOrder)) {
      LOGGER.info("OMS中未找到订单");
      return false;
    }
    if (salesOrder.getStatus() == SalesOrderStatus.INVALID) {
      LOGGER.info("OMS中订单已作废");
      return false;
    }
    context.setSalesOrder(salesOrder);
    return true;
  }

  private void create(ExchangeTranslatableContext context,boolean isCross) {
    createExchangeApply(context);
    if (isCross) {
      //1.修改主单的明细 需要退货
      needReturnGoods(context);
      //2.自动同意换货  待处理的申请单  和    换货理由 符合数据字典  自动同意
      autoAgree(context);
    }else {
      writeLog(context,"没有找到销售单,只进行保存");
    }
  }

  private void modify(ExchangeTranslatableContext context,boolean isCross) {
    modifyExchangeApply(context);
    if (isCross) {
      //删除库存占用 删除指定状态
      deleteOccupancy(context);
    }else {
        writeLog(context,"没有找到销售单,只进行修改");
    }
  }


  /**
   * 修改换货申请
   */
  private void modifyExchangeApply(ExchangeTranslatableContext context) {
    ExchangeApplyOrder exchangeApplyOrder = buildModifyApply(context);
    exchangeApplyOrderService.modify(exchangeApplyOrder);
  }

  /**
   * 创建修改申请
   */
  private ExchangeApplyOrder buildModifyApply(ExchangeTranslatableContext context) {
    MallExchangeOrder mallExchangeOrder = context.getMallExchangeOrder();
    MallExchangeOrderInfo mallExchangeInfo = context.getMallExchangeOrderInfo();
    ExchangeApplyOrder applyOrder = context.getExchangeApplyOrder();

    MallExchangeStatus mallExchangeStatus = mallExchangeOrder.getMallExchangeStatus();
    //换货状态
    applyOrder.setMallExchangeStatus(mallExchangeStatus);
    //退入快递单名称  也就是买家 退货的 快递名称
    applyOrder.setInExpressName(mallExchangeInfo.getBuyerExpressName());
    //退入快递单号
    applyOrder.setInExpressNo(mallExchangeInfo.getBuyerExpressNo());
    //商城换货单版本
    applyOrder.setMallExchangeVersion(mallExchangeInfo.getVersion());

    writeLog(context, "换货申请修改,平台换货状态:{}", mallExchangeStatus.caption());
    return applyOrder;
  }


  /**
   * 删掉库存占用
   */
  private void deleteOccupancy(ExchangeTranslatableContext context) {
    ExchangeApplyOrder applyOrder = context.getExchangeApplyOrder();
    try {
      //用户取消换货 , 删除之前的库存占用
      if (applyOrder.getMallExchangeStatus() == MallExchangeStatus.EXCHANGE_CLOSE
          || applyOrder.getMallExchangeStatus() == MallExchangeStatus.EXCHANGE_SUCCESS) {
        //删除库存占用
        stockOccupancyService
            .deleteOccupancy(applyOrder.getExchangeApplyOrderId(), StockOccupancyType.RETURN_ORDER);
        mqProducer.send(
            new StockChangedMessage(applyOrder.getExchangeApplyOrderCode(), applyOrder.getOutSkuId(),
                BizContext.getNickname(), "取消换货申请"));
        writeLog(context, "换货结束,删除库存占用,mainId:{}", applyOrder.getExchangeApplyOrderId());
      }
    }catch (Exception e){
      LOGGER.info("删除库存占用失败,堆栈信息",e);
      writeLog(context, "换货结束,删除库存占用失败,mainId:{},原因:{}", applyOrder.getExchangeApplyOrderId(),e.getMessage());
    }
  }

  private void writeLog(ExchangeTranslatableContext context, String message, Object... args) {
    TRANSLATE_EXCHANGE_LOGGER.log(context.getSerialNumber(), context.getStore(),
        context.getExchangeApplyOrder().getTradeId(),
        context.getExchangeApplyOrder().getMallExchangeId(), message, args);
  }


  /**
   * 新增退换货单时,修改订单明细需要退货状态
   */
  private void needReturnGoods(ExchangeTranslatableContext context) {
    //将退入的 商品标记为需要退货
    SalesOrder salesOrder = context.getSalesOrder();
    SalesOrderDetail salesOrderDetail = context.getSalesOrderDetail();
    VersionBO<SalesOrderDetail> versionBO = new VersionBO<>();
    versionBO.setDomain(salesOrderDetail);
    versionBO.setVersion(salesOrder.getVersion());
    salesOrderService.needReturnGood(salesOrder, versionBO);
    writeLog(context, "修改订单换入商品为需要退货,订单明细id:{}", salesOrderDetail.getSalesOrderDetailId());
  }

  /**
   * 待处理的申请单 和 换货理由 符合数据字典  自动同意
   */
  private void autoAgree(ExchangeTranslatableContext context) {
    ExchangeApplyOrder applyOrder = context.getExchangeApplyOrder();
    if (Assert.isTrue(context.getStore().getSetting().isTaobaoAutoAuditExchange())) {
      //待处理 状态
      if (applyOrder.getMallExchangeStatus() == MallExchangeStatus.WAIT_DEAL) {
        Set<String> reasons = dataDictItemService.listSetByDictId(10306L);
        String reason = applyOrder.getReason();
        if (!Assert.isEmpty(reasons) && reasons.contains(reason)) {
          //自动同意换货
          exchangeApplyOrderService.agree(applyOrder.getExchangeApplyOrderId());
          writeLog(context, "换货申请新建,自动同意换货,换货原因:{}", reason);
        }
        writeLog(context, "换货申请新建,换货原因不满足,无法自动同意换货,换货原因:{}", reason);
      }
      LOGGER.info("换货申请:{},状态:{},无法自动同意换货", applyOrder.getExchangeApplyOrderId(),
          applyOrder.getMallExchangeStatus().caption());
    }
  }


  /**
   * 新增时为换出商品增加库存占用
   */
  private void addOccupancy(ExchangeTranslatableContext context) {
    ExchangeApplyOrder exchangeApplyOrder = context.getExchangeApplyOrder();
    if (Assert.isNull(exchangeApplyOrder.getOutSkuId())){
      writeLog(context,"换出商品为空,无法加换出商品的库存占用");
      return;
    }
    Store store = context.getStore();
    //为换出商品的添加库存占用
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(store.getSetting().getDefaultWarehouse());

    StockOccupancy stockOccupancy = new StockOccupancy();
    stockOccupancy.setVirtualWarehouseId(virtualWarehouse.getVirtualWarehouseId());
    stockOccupancy.setVirtualWarehouseName(virtualWarehouse.getVirtualWarehouseName());
    stockOccupancy.setWarehouseId(virtualWarehouse.getWarehouseId());
    stockOccupancy.setWarehouseName(virtualWarehouse.getWarehouseName());
    stockOccupancy.setSkuId(exchangeApplyOrder.getOutSkuId());
    stockOccupancy.setSkuCode(exchangeApplyOrder.getOutSkuCode());
    stockOccupancy.setQuantity(exchangeApplyOrder.getQuantity());
    stockOccupancy.setStockOccupancyType(StockOccupancyType.RETURN_ORDER);
    stockOccupancy.setMainId(exchangeApplyOrder.getExchangeApplyOrderId());
    stockOccupancy.setDetailId(exchangeApplyOrder.getExchangeApplyOrderId());
    stockOccupancy.setStatus(StockOccupancyStatus.LOCK);
    stockOccupancyService.create(stockOccupancy);
    mqProducer.send(
        new StockChangedMessage(exchangeApplyOrder.getExchangeApplyOrderCode(),
            exchangeApplyOrder.getOutSkuId(),
            BizContext.getNickname(), "新建换货申请"));
    writeLog(context, "新增时为换出商品增加库存占用,skuId:{}", exchangeApplyOrder.getOutSkuId());
  }

  /**
   * 保存换货申请
   */
  private void createExchangeApply(ExchangeTranslatableContext context) {
    ExchangeApplyOrder exchange = buildNewApply(context);
    exchangeApplyOrderService.create(exchange);
    boolean isTrue = exchange.getMallExchangeStatus() == MallExchangeStatus.WAIT_DEAL
                      || exchange.getMallExchangeStatus() == MallExchangeStatus.WAIT_BUYER_RETURN
                      || exchange.getMallExchangeStatus() == MallExchangeStatus.BUYER_RETURNED;
    Store store = context.getStore();
    if (!Assert.isNull(context.getSalesOrder()) && isTrue && Assert.isTrue(store.getSetting().isTaobaoExchangeBeforeOccupancy())){
      addOccupancy(context);
    }else {
      writeLog(context,"找不到原单,不进行换出商品的库存占用");
    }
  }

  /**
   * 创建新的换货申请
   */
  private ExchangeApplyOrder buildNewApply(ExchangeTranslatableContext context) {

    MallExchangeOrderInfo mallExchangeInfo = context.getMallExchangeOrderInfo();
    ProductMallMapping outProductMapping = context.getOutProductMapping();
    ProductMallMapping inProductMappping = context.getInProductMappping();
    SalesOrder salesOrder = context.getSalesOrder();
    Store store = context.getStore();
    MallExchangeStatus mallExchangeStatus = context.getMallExchangeOrder().getMallExchangeStatus();

    ExchangeApplyOrder applyOrder = new ExchangeApplyOrder();
    context.setExchangeApplyOrder(applyOrder);
    applyOrder.setExchangeApplyOrderCode(exchangeOrderCodeGenerator.next());
    //店铺id
    applyOrder.setStoreId(store.getStoreId());
    //店铺名称
    applyOrder.setStoreName(store.getStoreName());
    applyOrder.setMallType(store.getMallType());
    //地址的处理
    convertAddress(context);
    //联系人
    applyOrder.setContact(mallExchangeInfo.getContact());
    //换货理由
    applyOrder.setReason(mallExchangeInfo.getReason());
    //换货状态
    applyOrder.setMallExchangeStatus(mallExchangeStatus);
    //退入快递单名称  也就是买家 退货的 快递名称
    applyOrder.setInExpressName(mallExchangeInfo.getBuyerExpressName());
    //退入快递单号
    applyOrder.setInExpressNo(mallExchangeInfo.getBuyerExpressNo());
    //-----退入的----
    if (!Assert.isNull(inProductMappping)) {
      //退入商品的编码
      applyOrder.setInProductCode(inProductMappping.getProductCode());
      //退入商品的id
      applyOrder.setInProductId(inProductMappping.getProductId());
      //退入商品的名称
      applyOrder.setInProductName(inProductMappping.getProductName());
      //退入商品的规格编码
      applyOrder.setInSkuCode(inProductMappping.getSkuCode());
      //退入商品的规格id
      applyOrder.setInSkuId(inProductMappping.getSkuId());
      //退入商品的规格名称
      applyOrder.setInSkuName(inProductMappping.getSkuName());

    }
    //商城明细id
    applyOrder.setMallDetailId(mallExchangeInfo.getOid());
    //商城换货单id
    applyOrder.setMallExchangeId(mallExchangeInfo.getExchangeId());
    //商城换货单版本
    applyOrder.setMallExchangeVersion(mallExchangeInfo.getVersion());
    //手机
    applyOrder.setMobile(mallExchangeInfo.getMobile());

    if (!Assert.isNull(outProductMapping)) {
      //换出商品的编码
      applyOrder.setOutProductCode(outProductMapping.getProductCode());
      //换出商品的id
      applyOrder.setOutProductId(outProductMapping.getProductId());
      //换出商品的名称
      applyOrder.setOutProductName(outProductMapping.getProductName());
      //换出商品的规格编码
      applyOrder.setOutSkuCode(outProductMapping.getSkuCode());
      //换出商品的规格id
      applyOrder.setOutSkuId(outProductMapping.getSkuId());
      //换出商品的规格名称
      applyOrder.setOutSkuName(outProductMapping.getSkuName());
    }
    applyOrder.setOutMallSkuId(mallExchangeInfo.getOutMallSkuId());
    //数量
    applyOrder.setQuantity(mallExchangeInfo.getQuantity());
    //换货这个单据的状态
    applyOrder.setStatus(ExchangeApplyOrderStatus.CREATED);
    //销售单id
    if (!Assert.isNull(salesOrder)) {
      applyOrder.setSalesOrderId(salesOrder.getSalesOrderId());
      //电话
      applyOrder.setTelephone(salesOrder.getTelephone());
      applyOrder.setMemberId(salesOrder.getSub().getMemberId());
      applyOrder.setMemberName(salesOrder.getSub().getMemberName());
      //交易号
      applyOrder.setTradeId(salesOrder.getTradeId());
    }

    //将获得的明文信息 转为密文
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    String mobile = securityBridge
        .encrypt(store, applyOrder.getMobile(), DataType.MOBILE);
    String name = securityBridge
        .encrypt(store, applyOrder.getContact(), DataType.NAME);
    applyOrder.setMobile(mobile);
    applyOrder.setContact(name);
    writeLog(context, "换货申请创建,平台换货状态:{}", mallExchangeStatus.caption());
    return applyOrder;
  }

  /**
   * @param context 地址的转换
   */
  private void convertAddress(ExchangeTranslatableContext context) {
    MallExchangeOrderInfo exchangeOrder = context.getMallExchangeOrderInfo();
    String country = "中国";
    String provinceName = exchangeOrder.getProvince().replace("市", "");
    String cityName = exchangeOrder.getCity();
    String districtName = exchangeOrder.getDistrict();
    String address = exchangeOrder.getAddress();

    ExchangeApplyOrder applyOrder = context.getExchangeApplyOrder();

    String tradeId = "";
    if (!Assert.isNull(context.getSalesOrder())) {
       tradeId = context.getSalesOrder().getTradeId();
    }
    TRANSLATE_EXCHANGE_LOGGER
        .log(context.getSerialNumber(), context.getStore(), tradeId,
            context.getMallExchangeOrderInfo().getExchangeId(), "匹配地址");
    //根据地名通过匹配方法找到系统中对应的region
    Region regionCountry = matchRegion(null, context, country, null, 1);
    if (regionCountry != null) {
      //将regionId封装到SalesOrder中
      applyOrder.setCountryId(regionCountry.getRegionId());
    }
    provinceName = provinceName.replace("市", "");
    //全部在 region 表里面找一遍
    Region regionProvince = matchRegion(regionCountry, context, provinceName, "省", 2);
    Region regionCity = matchRegion(regionProvince, context, cityName, "市", 3);
    Region regionCounty = matchRegion(regionCity, context, districtName, "区", 4);
    //如果这里面有没有找到的 那就找映射里的信息
    if (Assert.isNull(regionProvince) || Assert.isNull(regionCity) || Assert.isNull(regionCounty)) {

      //使用 省市区 封装别名  查询 mapping 表
      String alias = provinceName.concat(">".concat(cityName.concat(">".concat(districtName))));
      List<MallRegionMapping> mappers = new ArrayList<>(2);
      //查询mapping
      MallRegionMapping regionMapping = getRegionMapping(context, mappers, alias);
      if (regionMapping != null) {
        applyOrder.setProvinceId(regionMapping.getProvinceId());
        applyOrder.setCityId(regionMapping.getCityId());
        applyOrder.setDistrictId(regionMapping.getDistrictId());
      } else {
        if (regionProvince != null) {
          applyOrder.setProvinceId(regionProvince.getRegionId());
        }
        if (regionCity != null) {
          applyOrder.setCityId(regionCity.getRegionId());
        }
        if (regionCounty != null) {
          applyOrder.setDistrictId(regionCounty.getRegionId());
        }
      }

    } else {
      applyOrder.setProvinceId(regionProvince.getRegionId());
      applyOrder.setCityId(regionCity.getRegionId());
      applyOrder.setDistrictId(regionCounty.getRegionId());
    }
    applyOrder.setCountryName(country);
    applyOrder.setProvinceName(provinceName);
    applyOrder.setCityName(cityName);
    applyOrder.setDistrictName(districtName);
    applyOrder.setAddress(address);
  }

  /**
   * 通过name匹配，返回国省市区的id
   *
   * @return region
   */
  private Region matchRegion(Region parentReagion, ExchangeTranslatableContext context, String name,
      String suffix, Integer level) {
    if (Assert.isEmpty(name.trim())) {
      return null;
    }
    if (Assert.isNull(parentReagion) && !("中国".equals(name))) {
      return null;
    }
    //直接通过 名称 和 level 获取
    //  parentReagion 为空  level 为 1 ,那一定是中国
    Long reagionId;
    if (Assert.isNull(parentReagion) && level.equals(1)) {
      reagionId = 0L;
    } else {
      reagionId = parentReagion.getRegionId();
    }
    Region region = regionService.getByNameAndLevel(reagionId, name, level);
    if (region == null) {
      String tradeId = "";
      if (!Assert.isNull(context.getSalesOrder())) {
        tradeId = context.getSalesOrder().getTradeId();
      }
      TRANSLATE_EXCHANGE_LOGGER
          .log(context.getSerialNumber(), context.getStore(), tradeId,
              context.getMallExchangeOrderInfo().getExchangeId(), "区域1未找到");
      //如果名称不是以 省 市 等结尾  那就 加上这些再查
      if (!Assert.isEmpty(suffix) && !name.endsWith(suffix)) {
        region = regionService
            .getByNameAndLevel(parentReagion.getRegionId(), name.concat(suffix), level);
      }
    }
    return region;
  }

  /**
   * 通过名称和level 在region表里面找不到，
   * 就在此处  在mallRegionMapping 表里面找
   */
  private MallRegionMapping getRegionMapping(ExchangeTranslatableContext context,
      List<MallRegionMapping> mappers, String aliasPath) {
    String tradeId = "";
    if (!Assert.isNull(context.getSalesOrder())) {
      tradeId = context.getSalesOrder().getTradeId();
    }
    TRANSLATE_EXCHANGE_LOGGER
        .log(context.getSerialNumber(), context.getStore(), tradeId,
            context.getMallExchangeOrderInfo().getExchangeId(), "区域2未找到");
    if (Assert.isEmpty(aliasPath)) {
      return null;
    }
    MallRegionMapping regionMappingExample = new MallRegionMapping();
    regionMappingExample.setMallRegionAlias(aliasPath);
    MallRegionMapping regionMapping = mallRegionMappingService.getByExample(regionMappingExample);
    mappers.add(regionMapping);
    return regionMapping;
  }
}

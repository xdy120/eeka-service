package com.greatonce.oms.custom.eeka.service;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.stock.StockVirtualAllotDetailService;
import com.greatonce.oms.biz.stock.StockVirtualAllotService;
import com.greatonce.oms.biz.vip.VipDeliveryService;
import com.greatonce.oms.biz.vip.VipDispatchDetailService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.impl.qimen.QimenWms;
import com.greatonce.oms.custom.eeka.EekaCondition;
import com.greatonce.oms.custom.eeka.bo.StockChange;
import com.greatonce.oms.custom.eeka.dao.StockChangeDao;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.base.setting.IoBillSetting;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.stock.StockVirtualAllotStatus;
import com.greatonce.oms.domain.enums.vip.VipDeliveryStatus;
import com.greatonce.oms.domain.stock.StockVirtualAllot;
import com.greatonce.oms.domain.stock.StockVirtualAllotDetail;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.query.stock.StockVirtualAllotQuery;
import com.greatonce.oms.query.vip.VipDeliveryQuery;
import com.greatonce.oms.query.vip.VipDispatchQuery;
import com.qimen.api.request.StockchangeReportRequest;
import com.qimen.api.response.StockchangeReportResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EekaCondition
public class StockChangeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(StockChangeService.class);
  @Autowired
  private VipDeliveryService vipDeliveryService;
  @Autowired
  private VipDispatchService vipDispatchService;
  @Autowired
  private VipDispatchDetailService vipDispatchDetailService;
  @Autowired
  private StockVirtualAllotService stockVirtualAllotService;
  @Autowired
  private StockVirtualAllotDetailService stockVirtualAllotDetailService;
  @Autowired
  private StockChangeDao stockChangeDao;
  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private SettingService settingService;
  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  protected QimenWms qimenWms;

  public void getStockChange(String beginDate, String endDate) {
    if (Assert.isNull(beginDate)) {
      LOGGER.info("唯品库存同步FMS的开始时间不能为空");
    }
    if (Assert.isNull(endDate)) {
      LOGGER.info("唯品库存同步FMS的结束时间不能为空");
    }
    List<String> skuCodes = new ArrayList<>();
    //唯品出库SKU
    VipDeliveryQuery vipDeliveryQuery = new VipDeliveryQuery();
    vipDeliveryQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(beginDate));
    vipDeliveryQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(endDate));
    vipDeliveryQuery.setStatus(VipDeliveryStatus.DELIVERED);
    List<VipDelivery> vipDeliverys = vipDeliveryService.list(vipDeliveryQuery);
    if (!Assert.isEmpty(vipDeliverys)) {
      vipDeliverys.forEach(x -> {
        VipDispatchQuery vipDispatchQuery = new VipDispatchQuery();
        vipDispatchQuery.setVipDeliveryId(x.getVipDeliveryId());
        List<OutStatus> outStatuses = new ArrayList<>(2);
        outStatuses.add(OutStatus.ALL_OUT);
        outStatuses.add(OutStatus.PART_OUT);
        vipDispatchQuery.setOutStatuses(outStatuses);
        List<VipDispatch> vipDispatchs = vipDispatchService.list(vipDispatchQuery);
        if (!Assert.isEmpty(vipDispatchs)) {
          vipDispatchs.forEach(y -> {
            List<VipDispatchDetail> vipDispatchDetails = vipDispatchDetailService
                .listDetails(y.getVipDispatchId());
            if (!Assert.isEmpty(vipDispatchDetails)) {
              vipDispatchDetails = vipDispatchDetails.stream()
                  .filter(detail -> detail.getOutQuantity() > 0).collect(
                      Collectors.toList());
              if (!Assert.isEmpty(vipDispatchDetails)) {
                List<String> skus = vipDispatchDetails.stream().map(d -> d.getSkuCode()).distinct()
                    .collect(
                        Collectors.toList());
                skuCodes.addAll(skus);
              }
            }
          });
        } else {
          LOGGER.info("开始时间【" + beginDate + "】，结束时间【" + endDate + "】查询唯品拣货单数据为空");
        }
      });
      if (Assert.isEmpty(skuCodes)) {
        LOGGER.info("开始时间【" + beginDate + "】，结束时间【" + endDate + "】查询唯品拣货单明细数据为空");
      }
    } else {
      LOGGER.info("开始时间【" + beginDate + "】，结束时间【" + endDate + "】查询唯品出仓数据为空");
    }
    //虚拟调拨唯品异动的SKU
    StockVirtualAllotQuery stockVirtualAllotQuery = new StockVirtualAllotQuery();
    stockVirtualAllotQuery.setAuditedTimeBegin(DateTimeUtil.parserLocalDateTime(beginDate));
    stockVirtualAllotQuery.setAuditedTimeEnd(DateTimeUtil.parserLocalDateTime(endDate));
    stockVirtualAllotQuery.setStatus(StockVirtualAllotStatus.AUDITED);
    List<StockVirtualAllot> stockVirtualAllots = stockVirtualAllotService
        .list(stockVirtualAllotQuery);
    if (!Assert.isEmpty(stockVirtualAllots)) {
      stockVirtualAllots.forEach(x -> {
        StockVirtualAllotDetail stockVirtualAllotDetail = new StockVirtualAllotDetail();
        stockVirtualAllotDetail.setStockVirtualAllotId(x.getStockVirtualAllotId());
        List<StockVirtualAllotDetail> stockVirtualAllotDetails = stockVirtualAllotDetailService
            .listExample(stockVirtualAllotDetail);
        if (!Assert.isEmpty(stockVirtualAllotDetails)) {
          stockVirtualAllotDetails = stockVirtualAllotDetails.stream()
              .filter(detail -> detail.getQuantity() > 0).collect(Collectors.toList());
          if (!Assert.isEmpty(stockVirtualAllotDetails)) {
            List<String> skus = stockVirtualAllotDetails.stream().map(d -> d.getSkuCode())
                .distinct().collect(
                    Collectors.toList());
            skuCodes.addAll(skus);
          }
        }
      });
      if (Assert.isEmpty(skuCodes)) {
        LOGGER.info("开始时间【" + beginDate + "】，结束时间【" + endDate + "】查询虚拟调拨数据为空");
      }
    } else {
      LOGGER.info("开始时间【" + beginDate + "】，结束时间【" + endDate + "】查询虚拟调拨数据为空");
    }
    if (!Assert.isEmpty(skuCodes)) {
      List<String> skus = skuCodes.stream().distinct().collect(Collectors.toList());
      //查询OMS库存
      Map<String, Object> params = new HashMap<>(1);
      params.put("skuCodes", skus);
      List<StockChange> stockChanges = stockChangeDao.stockChangeList(params);
      if (!Assert.isEmpty(stockChanges)) {
        StockchangeReportRequest request = new StockchangeReportRequest();
        try {
          List<StockchangeReportRequest.Item> qimenItems = new ArrayList<>();
          stockChanges.forEach(x -> {
            StockchangeReportRequest.Item item = new StockchangeReportRequest.Item();
            //仓库编码
            item.setWarehouseCode(x.getWarehouseCode());
            //商品编码
            item.setItemCode(x.getSkuCode());
            //商品变化量
            item.setQuantity(
                (Assert.isEmpty(x.getQuantity()) || Integer.parseInt(x.getQuantity()) < 0) ? 0
                    : Long.valueOf(x.getQuantity()));
            qimenItems.add(item);
          });
          request.setItems(qimenItems);
          Map<String, String> extendProps = new HashMap<>();
          extendProps.put("beginDate", beginDate);
          extendProps.put("endDate", endDate);
          extendProps.put("omsOrderNo", String.valueOf(apiIdGenerator.next()));
          request.setExtendProps(extendProps);
          Warehouse warehouse = new Warehouse();
          IoBillSetting ioBillSetting = settingService.getIoBillSetting();
          warehouse
              .setWmsApp(
                  wmsAppService.getByKey(Long.valueOf(ioBillSetting.getOfflineDeliveryAppId())));
          StockchangeReportResponse rsp = qimenWms.call(warehouse, request);
          if (rsp.isSuccess()) {
            LOGGER.info("同步FMS唯品库存成功");
          } else {
            LOGGER.info("同步FMS唯品库存失败：" + rsp.getMessage());
          }
        } catch (WmsException e) {
          LOGGER.error("同步FMS唯品库存失败：" + e.getMessage());
        }
      }
    }
  }
}

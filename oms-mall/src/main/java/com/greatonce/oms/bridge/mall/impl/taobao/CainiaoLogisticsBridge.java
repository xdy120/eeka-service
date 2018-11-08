package com.greatonce.oms.bridge.mall.impl.taobao;

import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractLogisticsBridge;
import com.greatonce.oms.bridge.mall.request.WaybillCancelRequest;
import com.greatonce.oms.bridge.mall.request.WaybillGetRequest;
import com.greatonce.oms.bridge.mall.request.WaybillUpdateRequest;
import com.greatonce.oms.bridge.mall.response.WaybillCancelResponse;
import com.greatonce.oms.bridge.mall.response.WaybillGetResponse;
import com.greatonce.oms.bridge.mall.response.WaybillUpdateResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.WaybillSetting;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.taobao.api.request.CainiaoWaybillIiGetRequest;
import com.taobao.api.request.CainiaoWaybillIiGetRequest.AddressDto;
import com.taobao.api.request.CainiaoWaybillIiGetRequest.Item;
import com.taobao.api.request.CainiaoWaybillIiGetRequest.OrderInfoDto;
import com.taobao.api.request.CainiaoWaybillIiGetRequest.PackageInfoDto;
import com.taobao.api.request.CainiaoWaybillIiGetRequest.TradeOrderInfoDto;
import com.taobao.api.request.CainiaoWaybillIiGetRequest.UserInfoDto;
import com.taobao.api.request.CainiaoWaybillIiGetRequest.WaybillCloudPrintApplyNewRequest;
import com.taobao.api.response.CainiaoWaybillIiGetResponse;
import com.taobao.api.response.CainiaoWaybillIiGetResponse.WaybillCloudPrintResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/6
 */
@Component
public class CainiaoLogisticsBridge extends AbstractLogisticsBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(CainiaoLogisticsBridge.class);
  @Autowired
  private TaobaoMall taobaoMall;
  private static final String templateUtl = "http://cloudprint.cainiao.com/template/standard/82710/15";
  private static final MallType[] supportMallType =
      {MallType.ALIBABA, MallType.TAOBAO, MallType.TAOBAO_FX, MallType.TAOBAO_JX, MallType.TMALL};
  private static Map<String, String> templateMap = new HashMap<>(32);

  static {
    templateMap.put("GTO", "http://cloudprint.cainiao.com/template/standard/1101/106");
    templateMap.put("5000000007756", "http://cloudprint.cainiao.com/template/standard/82710/21");
    templateMap.put("DBKD", "http://cloudprint.cainiao.com/template/standard/1301/102");
    templateMap.put("QFKD", "http://cloudprint.cainiao.com/template/standard/1401/117");
    templateMap.put("2460304407_385", "http://cloudprint.cainiao.com/template/standard/190302/6");
    templateMap.put("UC", "http://cloudprint.cainiao.com/template/standard/1001/111");
    templateMap.put("STO", "http://cloudprint.cainiao.com/template/standard/201/172");
    templateMap.put("EYB", "http://cloudprint.cainiao.com/template/standard/1601/120");
    templateMap.put("SF", "http://cloudprint.cainiao.com/template/standard/1501/65");
    templateMap.put("ZTO", "http://cloudprint.cainiao.com/template/standard/301/192");
    templateMap.put("YTO", "http://cloudprint.cainiao.com/template/standard/101/587");
    templateMap.put("TTKDEX", "http://cloudprint.cainiao.com/template/standard/601/147");
    templateMap.put("CN7000001017817", "http://cloudprint.cainiao.com/template/standard/238398/1");
    templateMap.put("HTKY", "http://cloudprint.cainiao.com/template/standard/501/141");
    templateMap.put("100004928", "http://cloudprint.cainiao.com/template/standard/34725/44");
    templateMap.put("CN7000001009020", "http://cloudprint.cainiao.com/template/standard/194434/5");
    templateMap.put("YUNDA", "http://cloudprint.cainiao.com/template/standard/401/167");
    templateMap.put("ZJS", "http://cloudprint.cainiao.com/template/standard/901/118");
    templateMap.put("3108002701_1011", "http://cloudprint.cainiao.com/template/standard/191833/8");
    templateMap.put("BESTQJT", "http://cloudprint.cainiao.com/template/standard/83910/75");
    templateMap.put("CN7000001000869", "http://cloudprint.cainiao.com/template/standard/251315/4");
    templateMap.put("2744832184_543", "http://cloudprint.cainiao.com/template/standard/251413/1");
    templateMap.put("EMS", "http://cloudprint.cainiao.com/template/standard/701/126");
    templateMap.put("XFWL", "http://cloudprint.cainiao.com/template/standard/249128/1");
    templateMap.put("2383545689_32", "http://cloudprint.cainiao.com/template/standard/188729/6");
    templateMap.put("POSTB", "http://cloudprint.cainiao.com/template/standard/801/131");
    templateMap.put("CN7000001021040", "http://cloudprint.cainiao.com/template/standard/256622/3");
    templateMap.put("SURE", "http://cloudprint.cainiao.com/template/standard/133204/11");
    templateMap.put("CN7000001003751", "http://cloudprint.cainiao.com/template/standard/181603/7");
    templateMap.put("100007887", "http://cloudprint.cainiao.com/template/standard/248932/1");
    templateMap.put("2608021499_235", "http://cloudprint.cainiao.com/template/standard/183020/7");
    templateMap.put("FAST", "http://cloudprint.cainiao.com/template/standard/1201/105");
  }

  /**
   * 获取淘宝电子面单
   */
  @Override
  protected WaybillGetResponse doGetWaybill(WaybillGetRequest request) {
    CainiaoWaybillIiGetRequest mallRequest = getCainiaoWaybillIiGetRequest(request);
    if (mallRequest == null) {
      return new WaybillGetResponse(request, false, "请求参数不全");
    }
    int retryTimes = 0;
    String msg = null;
    while (retryTimes++ < 3) {
      try {
        CainiaoWaybillIiGetResponse mallResponse =
            taobaoMall.call(request.getStore(), mallRequest, false);
        if (!mallResponse.isSuccess()) {
          if (Assert.isEmpty(mallResponse.getSubMsg()) && Assert.isEmpty(mallResponse.getMsg())) {
            throw new MallException("淘宝错误：" + mallResponse.getBody());
          }
          if (mallResponse.getSubCode().equals("waybill account not enough")) {
            return new WaybillGetResponse(request, false, "电子面单余额不足");
          } else {
            throw new MallException("尝试重试，错误信息：" + mallResponse.getSubMsg());
          }
        }
        WaybillGetResponse response = new WaybillGetResponse(request);
        WaybillCloudPrintResponse mallWaybillResponse = mallResponse.getModules().get(0);
        JSONObject jsonObject = JsonUtil.toJSONObject(mallWaybillResponse.getPrintData());
        JSONObject routingInfo = jsonObject.getJSONObject("data").getJSONObject("routingInfo");
        response.setWaybillCode(mallWaybillResponse.getWaybillCode());
        response
            .setPackageCenterCode(routingInfo.getJSONObject("consolidation").getString("code"));
        response
            .setPackageCenterName(routingInfo.getJSONObject("consolidation").getString("name"));
        response.setBigShortName(routingInfo.getJSONObject("sortation").getString("name"));
        response.setSecondSectionCode(null);
        response.setThirdSectionCode(routingInfo.getString("routeCode"));
        return response;
      } catch (Exception e) {
        msg = e.getMessage();
        LOGGER.error("交易号{}获取面单失败，重试第{}次", request.getTradeId(), retryTimes);
        LOGGER.error("交易号{}获取面单失败，异常" + e);
      }
    }
    return new WaybillGetResponse(request, false, msg);
  }

  @Override
  //TODO
  protected WaybillUpdateResponse doUpdateWaybill(WaybillUpdateRequest request) {
    return null;
  }

  @Override
  //TODO
  protected WaybillCancelResponse doCancelWaybill(WaybillCancelRequest request) {
    return null;
  }

  @Override
  public MallType[] supports() {
    return supportMallType;
  }

  /**
   * 封装淘宝接口.
   * 1.必填参数不全返回null，在上一层进行处理
   * 2.只请求一张面单
   */
  private CainiaoWaybillIiGetRequest getCainiaoWaybillIiGetRequest(WaybillGetRequest request) {
    String globalExpressCode = request.getGlobalExpressCode();
    Store expressStore = request.getStore();
    Store orderStore = request.getOrderStore();

    WaybillSetting setting = request.getExpress().getSetting();

    CainiaoWaybillIiGetRequest req = new CainiaoWaybillIiGetRequest();
    WaybillCloudPrintApplyNewRequest waybillCloudPrintApply = new WaybillCloudPrintApplyNewRequest();
    waybillCloudPrintApply.setCpCode(globalExpressCode);

    //发件人信息
    UserInfoDto sellerInfo = new UserInfoDto();
    AddressDto sellerAddress = new AddressDto();
    //发件信息必填参数
    if (!Assert.isEmpty(setting.getProvinceName()) && !Assert.isEmpty(setting.getSenderAddress()) &&
        !Assert.isEmpty(setting.getSenderContact())) {
      sellerInfo.setName(setting.getSenderContact());
      sellerAddress.setProvince(setting.getProvinceName());
      sellerAddress.setDetail(setting.getSenderAddress());
      sellerAddress.setTown("");
      sellerAddress.setCity(setting.getCityName());
      sellerAddress.setDistrict(setting.getDistrictName());
    } else {
      LOGGER.error("获取淘宝电子面单的发件人信息不完整，省：{}，发件地址：{}，发件人：{}",
          setting.getProvinceName(), setting.getSenderAddress(), setting.getSenderContact());
      return null;
    }
    sellerInfo.setAddress(sellerAddress);
    sellerInfo.setMobile(setting.getSenderMobile());
    sellerInfo.setPhone(setting.getSenderTelephone());
    waybillCloudPrintApply.setSender(sellerInfo);

    //请求面单信息
    List<TradeOrderInfoDto> listTradeOrderInfo = new ArrayList<>(1);
    TradeOrderInfoDto tradeOrderInfo = new TradeOrderInfoDto();
    // 仅顺丰货到付款处理
    DispatchOrder dispatchOrder = request.getDispatchOrder();
    if (dispatchOrder.isCod() && globalExpressCode.equals("SF")) {
      JSONObject jsonServices = new JSONObject();
      JSONObject jsonValue = new JSONObject();
      jsonValue.put("value", dispatchOrder.getCodAmount());
      jsonServices.put("SVC-COD", jsonValue.toString());
      tradeOrderInfo.setLogisticsServices(jsonServices.toString());
    }
    tradeOrderInfo.setObjectId(UUID.randomUUID().toString());

    //订单信息
    OrderInfoDto orderInfo = new OrderInfoDto();
    orderInfo.setOrderChannelsType(convertMallType(orderStore.getMallType()));
    List<String> orderIds = Collections.singletonList(request.getTradeId());
    orderInfo.setTradeOrderList(orderIds);
    tradeOrderInfo.setOrderInfo(orderInfo);

    //包裹信息（包含商品信息Item）
    PackageInfoDto packageInfo = new PackageInfoDto();
    packageInfo.setId(UUID.randomUUID().toString());
    List<Item> listItem = new ArrayList<>();
    Item item = new Item();
    listItem.add(item);
    item.setCount(1L);
    item.setName(dispatchOrder.getDetails().get(0).getProductName());
    packageInfo.setItems(listItem);
    packageInfo.setVolume(0L);
    packageInfo.setWeight(0L);
    tradeOrderInfo.setPackageInfo(packageInfo);

    //收件人信息
    UserInfoDto buyerInfo = new UserInfoDto();
    AddressDto buyerAddress = new AddressDto();
    buyerAddress.setProvince(dispatchOrder.getProvinceName());
    buyerAddress.setCity(dispatchOrder.getCityName());
    buyerAddress.setDistrict(dispatchOrder.getDistrictName());
    buyerAddress.setDetail(dispatchOrder.getAddress());
    buyerAddress.setTown("");
    buyerInfo.setAddress(buyerAddress);
    buyerInfo.setMobile(dispatchOrder.getMobile());
    buyerInfo.setName(dispatchOrder.getContact());
    buyerInfo.setPhone(dispatchOrder.getTelephone());
    tradeOrderInfo.setRecipient(buyerInfo);
    //其他信息
    tradeOrderInfo.setTemplateUrl(Assert.isEmpty(templateMap.get(globalExpressCode)) ?
        templateUtl : templateMap.get(globalExpressCode));
    tradeOrderInfo.setUserId(Long.parseLong(expressStore.getMallApp().getAppKey()));
    listTradeOrderInfo.add(tradeOrderInfo);

    waybillCloudPrintApply.setTradeOrderInfoDtos(listTradeOrderInfo);
    req.setParamWaybillCloudPrintApplyNewRequest(waybillCloudPrintApply);
    return req;
  }

  /**
   * 渠道编码转换
   */
  private String convertMallType(MallType mallType) {
    switch (mallType) {
      case TAOBAO:
        return "TB";
      case TMALL:
        return "TM";
      case JD:
        return "JD";
      case VIP:
        return "WPH";
      case DANGDANG:
        return "DD";
      case AMAZON_CN:
        return "AMAZON";
      case AMAZON_COM:
        return "AMAZON";
      case SUNING:
        return "SN";
      case SUNING_SELF:
        return "SN";
      case SUNING_SPECIAL:
        return "SN";
      case GOME:
        return "GM";
      case JU_MEI:
        return "JM";
      case MO_GU_JIE:
        return "MGJ";
      case ALIBABA:
        return "1688";
      default:
        return "OTHERS";
    }
  }
}

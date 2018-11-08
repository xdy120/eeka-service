package com.greatonce.oms.bridge.mall.impl.jd;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.mall.impl.AbstractLogisticsBridge;
import com.greatonce.oms.bridge.mall.impl.jd.entity.JdResponseConverter;
import com.greatonce.oms.bridge.mall.impl.jd.entity.JdWayBillContentConverter;
import com.greatonce.oms.bridge.mall.impl.jd.entity.JdWayBillContentConverter.FromAddress;
import com.greatonce.oms.bridge.mall.impl.jd.entity.JdWayBillContentConverter.ToAddress;
import com.greatonce.oms.bridge.mall.request.WaybillCancelRequest;
import com.greatonce.oms.bridge.mall.request.WaybillGetRequest;
import com.greatonce.oms.bridge.mall.request.WaybillUpdateRequest;
import com.greatonce.oms.bridge.mall.response.WaybillCancelResponse;
import com.greatonce.oms.bridge.mall.response.WaybillGetResponse;
import com.greatonce.oms.bridge.mall.response.WaybillUpdateResponse;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.WaybillSetting;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.jd.open.api.sdk.domain.kdgjapi.WaybillBigShotApi.BigShotDTO;
import com.jd.open.api.sdk.request.kdgjapi.LdopAlphaVendorBigshotQueryRequest;
import com.jd.open.api.sdk.request.kdgjapi.LdopAlphaWaybillReceiveRequest;
import com.jd.open.api.sdk.response.kdgjapi.LdopAlphaVendorBigshotQueryResponse;
import com.jd.open.api.sdk.response.kdgjapi.LdopAlphaWaybillReceiveResponse;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/8/13
 */
@Component
public class JdLogisticsBridge extends AbstractLogisticsBridge {

  private final MallType[] supportMallType = {MallType.JD};
  private static final Logger LOGGER = LoggerFactory.getLogger(JdLogisticsBridge.class);
  @Autowired
  private JdMall jdMall;

  @Override
  public WaybillGetResponse doGetWaybill(WaybillGetRequest request) {
    WaybillGetResponse response;
    //封装报文
    JdWayBillContentConverter content = getContentJson(request);
    String contentJson = JsonUtil.toJson(content);
    LOGGER.info("请求报文：配货单号:{},Content:{}", request.getDispatchOrder().getDispatchOrderCode(),
        contentJson);
    LdopAlphaWaybillReceiveRequest req = new LdopAlphaWaybillReceiveRequest();
    req.setContent(contentJson);
    //获取面单号
    LdopAlphaWaybillReceiveResponse jdResp = jdMall.call(request.getStore(), req);
    JdResponseConverter resp = JsonUtil.toObject(jdResp.getMsg(), JdResponseConverter.class);
    if (resp.getStatusCode() == 0) {
      String waybillCode = resp.getData().getWaybillCodeList().get(0);
      LdopAlphaVendorBigshotQueryRequest waybillQueryRequest =
          new LdopAlphaVendorBigshotQueryRequest();
      waybillQueryRequest.setWaybillCode(waybillCode);
      waybillQueryRequest.setProviderCode(content.getProviderCode());
      //获取大头笔信息
      LOGGER.info("查询大头笔报文：配货单号:{},Content:{}", request.getDispatchOrder().getDispatchOrderCode(),
          JsonUtil.toJson(waybillQueryRequest));
      LdopAlphaVendorBigshotQueryResponse waybillQueryResponse =
          jdMall.call(request.getStore(), waybillQueryRequest);
      if (waybillQueryResponse.getResultInfo().getStatusCode() == 0) {
        BigShotDTO bigShotDTO = waybillQueryResponse.getResultInfo().getData().get(0);
        response = new WaybillGetResponse(request);
        response.setWaybillCode(waybillCode);
        response.setPackageCenterCode(bigShotDTO.getGatherCenterCode());
        response.setPackageCenterName(bigShotDTO.getGatherCenterName());
        response.setBigShortName(bigShotDTO.getBigShotName());
        response.setSecondSectionCode(bigShotDTO.getSecondSectionCode());
        response.setThirdSectionCode(
            bigShotDTO.getSecondSectionCode() + "-" + bigShotDTO.getThirdSectionCode());
        LOGGER.info("配货单号:{},运单号:{}", request.getDispatchOrder().getDispatchOrderCode(),
            waybillCode);
      } else {
        response = new WaybillGetResponse(request, false,
            waybillQueryResponse.getResultInfo().getStatusMessage());
      }
    } else {
      response = new WaybillGetResponse(request, false, jdResp.getResultInfo().getStatusMessage());
    }
    return response;
  }

  /**
   * 封装报文json.
   */
  private JdWayBillContentConverter getContentJson(WaybillGetRequest request) {
    WaybillSetting setting = request.getExpress().getSetting();
    Store expressStore = request.getStore();
    Store orderStore = request.getOrderStore();
    DispatchOrder dispatchOrder = request.getDispatchOrder();

    FromAddress fromAddress = new FromAddress();
    if (!Assert.isEmpty(setting.getProvinceName()) && !Assert.isEmpty(setting.getSenderAddress()) &&
        !Assert.isEmpty(setting.getSenderContact())) {
      fromAddress.setProvinceName(setting.getProvinceName());
      fromAddress.setCityName(setting.getCityName());
      fromAddress.setCountryName(setting.getCountryName());
      fromAddress.setAddress(setting.getSenderAddress());
      fromAddress.setContact(setting.getSenderContact());
      fromAddress.setPhone(Assert.isEmpty(setting.getSenderTelephone()) ? setting.getSenderMobile()
          : setting.getSenderTelephone());
      fromAddress.setMobile(Assert.isEmpty(setting.getSenderMobile()) ? setting.getSenderTelephone()
          : setting.getSenderMobile());
    } else {
      LOGGER.error("获取京东电子面单的发件人信息不完整，仓库省：{}，仓库地址：{}，仓库联系人：{}",
          setting.getProvinceName(), setting.getSenderAddress(), setting.getSenderContact());
      throw new OmsException("获取京东电子面单的发件人信息不完整");
    }

    ToAddress toAddress = new ToAddress();
    toAddress.setProvinceName(dispatchOrder.getProvinceName());
    toAddress.setCityName(dispatchOrder.getCityName());
    toAddress.setCountryName(dispatchOrder.getCountryName());
    toAddress.setAddress(dispatchOrder.getAddress());
    toAddress.setContact(dispatchOrder.getContact());
    toAddress.setPhone(Assert.isEmpty(dispatchOrder.getTelephone()) ? dispatchOrder.getMobile()
        : dispatchOrder.getTelephone());
    toAddress.setMobile(Assert.isEmpty(dispatchOrder.getMobile()) ? dispatchOrder.getTelephone()
        : dispatchOrder.getMobile());

    JdWayBillContentConverter content = new JdWayBillContentConverter();
    content.setWaybillType(1);
    content.setWaybillCount(1);
    content.setVendorCode(expressStore.getSetting().getVendorCode());
    content.setVendorName(expressStore.getSetting().getVendorName());
    content.setProviderCode(request.getGlobalExpressCode());
    content.setBranchCode(setting.getBranchCode());
    content.setSettlementCode(setting.getSettlementCode());
    content.setPlatformOrderNo(request.getTradeId());
    content.setSalePlatform(salesPlaformConverter(orderStore.getMallType()));
    content.setVendorOrderCode(dispatchOrder.getDispatchOrderCode());

    content.setWeight(new BigDecimal(0));
    content.setVolume(new BigDecimal(0));
    if (Assert.isTrue(dispatchOrder.isCod())) {
      content.setPayType(1);
      content.setShouldPayMoney(
          new BigDecimal(dispatchOrder.getCodAmount()));
    } else {
      content.setPayType(0);
      content.setShouldPayMoney(new BigDecimal(0));
    }
    content.setNeedGuarantee(false);
    content.setGuaranteeMoney(new BigDecimal(0));
    content.setReceiveTimeType(0);
    content.setPromiseTimeType(0);
    content.setFromAddress(fromAddress);
    content.setToAddress(toAddress);

    return content;
  }

  /**
   * 转换平台编码.
   */
  private String salesPlaformConverter(MallType mallType) {
    if (mallType == MallType.JD) {
      return "0010001";
    } else if (mallType == MallType.TAOBAO || mallType == MallType.TMALL
        || mallType == MallType.TAOBAO_FX || mallType == MallType.TAOBAO_JX) {
      return "0010002";
    } else {
      return "0030001";
    }
  }

  @Override
  protected WaybillUpdateResponse doUpdateWaybill(WaybillUpdateRequest request) {
    return null;
  }

  @Override
  protected WaybillCancelResponse doCancelWaybill(WaybillCancelRequest request) {
    return null;
  }

  @Override
  public MallType[] supports() {
    return supportMallType;
  }
}

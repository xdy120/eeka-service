package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.biz.vip.VipReturnDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeDetailService;
import com.greatonce.oms.biz.vip.VipReturnService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipReturnScanBO;
import com.greatonce.oms.bo.vip.VipReturnSignBO;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipReturnNoticeCreatRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipReturnQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipReturnScanRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipReturnSignRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipReturnNoticeCreatResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipReturnQueryResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipReturnScanResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipReturnSignResponse;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.enums.vip.VipReturnStatus;
import com.greatonce.oms.domain.enums.vip.VipSignStatus;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.domain.vip.VipReturnDetail;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 唯品售后. ReturnOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */
@RestController
@EekaApiCondition
public class EekaVipReturnController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaVipReturnController.class);

  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private VipReturnService vipReturnService;
  @Autowired
  private VipReturnDetailService vipReturnDetailService;
  @Autowired
  private ManualTransactionTemplate transactionTemplate;
  @Autowired
  private VipReturnNoticeDetailService vipReturnNoticeDetailService;

  @Autowired
  private UserService userService;

  /**
   * 唯品退供单查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.vip.return.list")
  public EekaVipReturnQueryResponse vipReturnQuery(HttpServletRequest servletRequest) {
    EekaVipReturnQueryRequest request = checkSign(servletRequest,
        EekaVipReturnQueryRequest.class);
    EekaVipReturnQueryResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getOuterCode()) && Assert.isNull(request.getBoxNumber())) {
        response = new EekaVipReturnQueryResponse(apiIdGenerator.next(), "请求参数不能为空");
        return response;
      }
      if (!Assert.isNull(request.getOuterCode())) {
        VipReturn vipReturn = vipReturnService.getByOuterCode(request.getOuterCode());
        if (!Assert.isNull(vipReturn)) {
          VipReturnDetail vipReturnDetailEg = new VipReturnDetail();
          vipReturnDetailEg.setVipReturnId(vipReturn.getVipReturnId());
          List<VipReturnDetail> vipReturnDetails = vipReturnDetailService
              .listExample(vipReturnDetailEg);
          //根据退供单id查询所有的箱码
          if (!Assert.isNull(vipReturnDetails)) {
            vipReturn.setDetails(vipReturnDetails);
            response = new EekaVipReturnQueryResponse(apiIdGenerator.next());
            response.setVipReturn(vipReturn);
          }
        }
      } else if (!Assert.isNull(request.getBoxNumber())) {
        VipReturnDetail vipReturnDetailEg = new VipReturnDetail();
        vipReturnDetailEg.setBoxNumber(request.getBoxNumber());
        List<VipReturnDetail> vipReturnDetails = vipReturnDetailService
            .listExample(vipReturnDetailEg);
        if (!Assert.isEmpty(vipReturnDetails)) {
          VipReturn vipReturn = vipReturnService.getByKey(vipReturnDetails.get(0).getVipReturnId());
          if (!Assert.isNull(vipReturn)) {
            vipReturn.setDetails(vipReturnDetails);
            response = new EekaVipReturnQueryResponse(apiIdGenerator.next());
            response.setVipReturn(vipReturn);
          }
        }
      }
    } else {
      response = new EekaVipReturnQueryResponse(apiIdGenerator.next(), "请求参数为空");
      return response;
    }
    if (Assert.isNull(response)) {
      response = new EekaVipReturnQueryResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 获取单个唯品退供单
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.vip.return.get")
  public EekaVipReturnQueryResponse vipReturn(HttpServletRequest servletRequest) {
    EekaVipReturnQueryRequest request = checkSign(servletRequest,
        EekaVipReturnQueryRequest.class);
    EekaVipReturnQueryResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getOuterCode())) {
        response = new EekaVipReturnQueryResponse(apiIdGenerator.next(), "退供单号不能为空");
        return response;
      }
      VipReturn vipReturn = vipReturnService.getByOuterCode(request.getOuterCode());
      if (!Assert.isNull(vipReturn)) {
        VipReturnNoticeDetail VipReturnNoticeDetailEg = new VipReturnNoticeDetail();
        VipReturnNoticeDetailEg.setVipReturnId(vipReturn.getVipReturnId());
        List<VipReturnNoticeDetail> vipReturnNoticeDetails = vipReturnNoticeDetailService
            .listExample(VipReturnNoticeDetailEg);
        response = new EekaVipReturnQueryResponse(apiIdGenerator.next());
        response.setVipReturn(vipReturn);
        Integer quantity = 0;
        if (!Assert.isEmpty(vipReturnNoticeDetails)) {
          quantity = vipReturnNoticeDetails.stream().map(x -> x.getNoticeQuantity())
              .reduce((a, b) -> a + b).get().intValue();
          response.setNoticeQuantity(quantity);
        }
      }
    } else {
      response = new EekaVipReturnQueryResponse(apiIdGenerator.next(), "请求参数为空");
      return response;
    }
    if (Assert.isNull(response)) {
      response = new EekaVipReturnQueryResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 唯品退供单签收
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.vip.return.sign")
  public EekaVipReturnSignResponse vipReturnSign(HttpServletRequest servletRequest) {
    EekaVipReturnSignRequest request = checkSign(servletRequest,
        EekaVipReturnSignRequest.class);
    EekaVipReturnSignResponse response = null;
    if (!Assert.isNull(request)) {

      if (Assert.isNull(request.getVirtualWarehouseId())) {
        response = new EekaVipReturnSignResponse(apiIdGenerator.next(), "收货仓库不能为空");
        return response;
      }

      if (Assert.isNull(request.getVipSignStatus())) {
        response = new EekaVipReturnSignResponse(apiIdGenerator.next(), "签收状态不能为空");
        return response;
      }
      VipReturn vipReturn = vipReturnService.getByKey(request.getVipReturnId());
      vipReturn.setVipReturnId(request.getVipReturnId());
      vipReturn.setStatus(VipReturnStatus.AUDITED);
      VipReturnSignBO vipReturnSignBO = new VipReturnSignBO();
      vipReturnSignBO.setRemark(request.getRemark());
      vipReturnSignBO.setVipReturnType(vipReturn.getVipReturnType());
      vipReturnSignBO.setVipSignStatus(convertSignStatus(request.getVipSignStatus()));
      vipReturnSignBO.setVirtualWarehouseId(request.getVirtualWarehouseId());
      vipReturnSignBO.setVirtualWarehouseName(request.getVirtualWarehouseName());
      vipReturnSignBO.setVersion(vipReturn.getVersion());
      if (!Assert.isNull(request.getOperator())) {
        User user = getUser(request.getOperator());
        if (!Assert.isNull(user)) {
          vipReturnSignBO.setOperator(user.getNickname());
        }
      }
      try {
        vipReturnService.sign(vipReturn, vipReturnSignBO);
        response = new EekaVipReturnSignResponse(apiIdGenerator.next());
      } catch (Exception e) {
        LOGGER.error("唯品退货签收失败,堆栈信息:", e);
        return new EekaVipReturnSignResponse(apiIdGenerator.next(), e.getMessage());
      }
    } else {
      response = new EekaVipReturnSignResponse(apiIdGenerator.next(), "请求参数为空");
      return response;
    }
    if (Assert.isNull(response)) {
      response = new EekaVipReturnSignResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 唯品退供单扫描
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.vip.return.scan")
  public EekaVipReturnScanResponse vipReturnScan(HttpServletRequest servletRequest) {
    EekaVipReturnScanRequest request = checkSign(servletRequest,
        EekaVipReturnScanRequest.class);
    EekaVipReturnScanResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isEmpty(request.getDetails())) {
        response = new EekaVipReturnScanResponse(apiIdGenerator.next(), "请求数据不能为空");
        return response;
      }

      if (Assert.isNull(request.getOperator())) {
        response = new EekaVipReturnScanResponse(apiIdGenerator.next(), "用户不能为空");
        return response;
      }
      User user = getUser(request.getOperator());
      try {
        VipReturn vipReturn = vipReturnService.getByKey(request.getVipReturnId());
        if (!Assert.isNull(vipReturn)) {
          transactionTemplate.execute(() -> {
            VipReturnScanBO vipReturnScanBO = new VipReturnScanBO();
            vipReturnScanBO.setVersion(vipReturn.getVersion());
            if (!Assert.isNull(user)) {
              vipReturn.setCreator(user.getNickname());
            }
            List<VipReturnScanBO.VipReturnScanDetail> details = new ArrayList<>();
            request.getDetails().forEach(y -> {
              VipReturnScanBO.VipReturnScanDetail vipReturnScanDetail = new VipReturnScanBO.VipReturnScanDetail();
              vipReturnScanDetail.setBoxNo(y.getBoxNo());
              vipReturnScanDetail.setScanQuantity(
                  y.getScanQuantity() == null ? 0 : Integer.valueOf(y.getScanQuantity()));
              vipReturnScanDetail.setSkuCode(y.getSkuCode());
              vipReturnScanDetail.setVipReturnDetailId(
                  y.getVipReturnDetailId() == null ? 0 : Long.valueOf(y.getVipReturnDetailId()));
              details.add(vipReturnScanDetail);
            });
            vipReturnScanBO.setDetails(details);
            vipReturnService.scan(vipReturn, vipReturnScanBO);
          });
          response = new EekaVipReturnScanResponse(apiIdGenerator.next());
        }
      } catch (Exception e) {
        LOGGER.error("唯品退货扫描失败,堆栈信息:", e);
        return new EekaVipReturnScanResponse(apiIdGenerator.next(), e.getMessage());
      }
    } else {
      response = new EekaVipReturnScanResponse(apiIdGenerator.next(), "请求参数为空");
      return response;
    }
    if (Assert.isNull(response)) {
      response = new EekaVipReturnScanResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 唯品退供生成通知单
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.vip.return.notice.create")
  public EekaVipReturnNoticeCreatResponse vipReturnNotice(HttpServletRequest servletRequest) {
    EekaVipReturnNoticeCreatRequest request = checkSign(servletRequest,
        EekaVipReturnNoticeCreatRequest.class);
    EekaVipReturnNoticeCreatResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isNull(request.getOuterCode())) {
        response = new EekaVipReturnNoticeCreatResponse(apiIdGenerator.next(), "退供单号不能为空");
        return response;
      }

      if (Assert.isNull(request.getOperator())) {
        response = new EekaVipReturnNoticeCreatResponse(apiIdGenerator.next(), "用户不能为空");
        return response;
      }
      User user = getUser(request.getOperator());
      try {
        VipReturn vipReturn = vipReturnService.getByKey(
            request.getVipReturnId() == null ? 0 : Long.valueOf(request.getVipReturnId()));
        if (!Assert.isNull(vipReturn)) {
          VipReturnScanBO vipReturnScanBO = new VipReturnScanBO();
          vipReturnScanBO.setVersion(vipReturn.getVersion());
          if (!Assert.isNull(user)) {
            vipReturn.setCreator(user.getNickname());
          }
          VersionBO bo = new VersionBO();
          bo.setVersion(vipReturn.getVersion());
          vipReturnService.createNotice(vipReturn, bo);
          response = new EekaVipReturnNoticeCreatResponse(apiIdGenerator.next());
        }
      } catch (Exception e) {
        LOGGER.error("唯品退供生成通知单,堆栈信息:", e);
        return new EekaVipReturnNoticeCreatResponse(apiIdGenerator.next(), e.getMessage());
      }
    } else {
      response = new EekaVipReturnNoticeCreatResponse(apiIdGenerator.next(), "请求参数为空");
      return response;
    }
    if (Assert.isNull(response)) {
      response = new EekaVipReturnNoticeCreatResponse(apiIdGenerator.next());
    }
    return response;
  }

  private User getUser(String loginName) {
    User userEg = new User();
    userEg.setLoginName(loginName);
    userEg.setEnable(true);
    return userService.getByExample(userEg);
  }

  public static VipSignStatus convertSignStatus(String status) {
    switch (status) {
      case "1":
        return VipSignStatus.NO_SIGN;
      case "2":
        return VipSignStatus.NORMAL;
      case "3":
        return VipSignStatus.ABNORMAL;
      default:
        return VipSignStatus.NO_SIGN;
    }
  }

  public static VipReturnStatus convertReturnStatus(String status) {
    switch (status) {
      case "1":
        return VipReturnStatus.CREATED;
      case "2":
        return VipReturnStatus.AUDITED;
      case "3":
        return VipReturnStatus.INVALID;
      default:
        return VipReturnStatus.CREATED;
    }
  }

}

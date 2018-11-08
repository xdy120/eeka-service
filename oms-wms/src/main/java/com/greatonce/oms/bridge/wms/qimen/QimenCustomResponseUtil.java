package com.greatonce.oms.bridge.wms.qimen;

import com.qimen.api.QimenResponse;

/**
 * 奇门自定义接口响应工具类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-10
 */
public abstract class QimenCustomResponseUtil {

  public static <T extends QimenResponse> T resultSuccessResponse(T response, String code) {
    response.setFlag("success");
    response.setCode("0");
    response.setMessage("成功");
    return response;
  }

  public static <T extends QimenResponse> T resultFailureResponse(T response, String message) {
    response.setFlag("failure");
    response.setCode("1");
    response.setMessage(message);
    return response;
  }

}

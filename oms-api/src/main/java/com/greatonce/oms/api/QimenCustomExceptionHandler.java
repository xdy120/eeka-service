package com.greatonce.oms.api;

import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.bridge.wms.qimen.SignException;
import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 奇门自定义接口异常处理.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
@RestController
@ControllerAdvice(assignableTypes = QimenCustomController.class)
public class QimenCustomExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(QimenCustomExceptionHandler.class);

  @ExceptionHandler(value = SignException.class)
  @ResponseBody
  public OmsQimenCustomResponse signExceptionHandler(SignException e) {
    LOGGER.debug("signExceptionHandler");
    return new OmsQimenCustomResponse(0, "sign-check-failure", "Illegal request");
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public OmsQimenCustomResponse exceptionHandler(Exception e) {
    return new OmsQimenCustomResponse(0, e.getMessage());
  }
}

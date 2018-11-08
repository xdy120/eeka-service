package com.greatonce.oms.web;

import com.greatonce.oms.domain.OmsException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/26
 */

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public BaseResponse webExceptionHandler(Exception e, HttpServletRequest request) {
    if (request != null) {
      LOGGER.error("异常URL:{}", request.getRequestURI());
    }

    LOGGER.error("系统异常", e);
    return new BaseResponse(-1, "系统异常：" + e.getMessage());
  }

  @ExceptionHandler(value = OmsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public BaseResponse omsExceptionHandler(OmsException e) {
    return new BaseResponse(e.getCode(), e.getMessage());
  }
}

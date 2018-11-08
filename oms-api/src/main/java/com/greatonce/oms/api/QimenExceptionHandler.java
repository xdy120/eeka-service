package com.greatonce.oms.api;

import com.greatonce.oms.api.qimen.QimenController;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * CREATED by buer on 2017/4/15.
 */
@RestController
@ControllerAdvice(assignableTypes = QimenController.class)
public class QimenExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(QimenExceptionHandler.class);

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping(produces = "application/xml")
  public OmsQimenResponse exceptionHandler(Exception e) {
    return QimenCustomResponseUtil.resultFailureResponse(new OmsQimenResponse(), e.getMessage());
  }
}

package com.greatonce.oms.web;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 返回值统一封装
 *
 * @author buer
 * @version 2017-10-21 12:28
 */
public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {

  private final HandlerMethodReturnValueHandler delegate;

  public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
    this.delegate = delegate;
  }

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return delegate.supportsReturnType(returnType);
  }

  @Override
  public void handleReturnValue(Object returnValue, MethodParameter returnType,
      ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
    BaseResponse response = new BaseResponse(returnValue);
    delegate.handleReturnValue(response, returnType, mavContainer, webRequest);
  }
}
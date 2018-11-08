package com.greatonce.oms.web;

import com.greatonce.core.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author buer
 * @version 2017-12-14 12:01
 */
public class JsonArgumentsResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(JsonArgument.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    Object param = webRequest.getParameter(parameter.getParameterName());
    return JsonUtil.toObject(param.toString(), parameter.getParameterType());
  }
}

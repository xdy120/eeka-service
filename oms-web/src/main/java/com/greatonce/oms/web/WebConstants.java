package com.greatonce.oms.web;

/**
 * @author buer
 * @version 2018-01-17 14:53
 */
public final class WebConstants {

  public static final String PRIVATE_TOKEN_KEY = "JEOMS-TOKEN-SECRET";
  public static final String DOMAIN_HEADER = "X-OMS-ORGAN";
  public static final String TOKEN_HEADER = "Authorization";
  public static final String TOKEN_PREFIX = "BEARER:";
  public static final String CORS_REQUEST_METHOD = "OPTIONS";
  public static final String LOGIN_URL = "/auth/login";
  public static final String LOGOUT_URL = "/auth/logout";
  public static final String AUTO_EXPORT_URL = "/report/auto";
  public static final String HEAD_CORS_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
  public static final String HEAD_CORS_ALLOW_METHODS = "Access-Control-Allow-Methods";
  public static final String HEAD_CORS_ALLOW_HEADERS = "Access-Control-Allow-Headers";
  public static final String HEAD_CORS_MAX_AGE = "Access-Control-Max-Age";
  public static final String HEAD_CORS_ALLOW_ORIGIN_VALUE = "*";
  public static final String HEAD_CORS_ALLOW_METHODS_VALUE = "*";
  public static final String HEAD_CORS_ALLOW_HEADERS_VALUE = "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-OMS-ORGAN";
  public static final String HEAD_CORS_MAX_AGE_VALUE = "3600";
  public static final String JWT_HEADER_USER_ID = "uid";
  public static final String JWT_HEADER_USER_NAME = "uname";

}

package com.greatonce.oms.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greatonce.core.Constants;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.web.Long2StringJsonFilter;
import com.greatonce.oms.web.WebConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author buer
 * @version 2018-01-17 14:42
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

  private static final Long2StringJsonFilter STRING_JSON_FILTER = new Long2StringJsonFilter();
  private AuthenticationManager authenticationManager;

  public LoginFilter(String url, AuthenticationManager authenticationManager) {
    super(new AntPathRequestMatcher(url));
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    try {
      User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
      Authentication authentication = new UsernamePasswordAuthenticationToken(user.getLoginName(),
          user.getLoginPassword(), Collections.emptyList());
      return authenticationManager.authenticate(authentication);
    } catch (IOException ex) {
      throw new UsernameNotFoundException("请求内容未找到");
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) throws IOException {
    UsernamePasswordAuthenticationToken authenticationToken
        = (UsernamePasswordAuthenticationToken) authResult;
    User user = (User) authenticationToken.getDetails();
    String token = Jwts.builder()
        .setSubject(authResult.getPrincipal().toString())
        .setExpiration(DateTimeUtil.localDateTimeToDate(LocalDateTime.now().plusWeeks(1)))
        .setHeaderParam(WebConstants.JWT_HEADER_USER_ID, user.getUserId())
        .setHeaderParam(WebConstants.JWT_HEADER_USER_NAME, user.getNickname())
        .signWith(SignatureAlgorithm.HS512, WebConstants.PRIVATE_TOKEN_KEY)
        .compact();
    response.addHeader(WebConstants.TOKEN_HEADER, WebConstants.TOKEN_PREFIX + token);
    response.setHeader(WebConstants.HEAD_CORS_ALLOW_HEADERS,
        WebConstants.HEAD_CORS_ALLOW_HEADERS_VALUE);
    response.setHeader(WebConstants.HEAD_CORS_ALLOW_METHODS,
        WebConstants.HEAD_CORS_ALLOW_METHODS_VALUE);
    response
        .setHeader(WebConstants.HEAD_CORS_ALLOW_ORIGIN, WebConstants.HEAD_CORS_ALLOW_ORIGIN_VALUE);
    response.setHeader(WebConstants.HEAD_CORS_MAX_AGE, WebConstants.HEAD_CORS_MAX_AGE_VALUE);

    JSONObject userInfo = new JSONObject();
    userInfo.put("userId", user.getUserId());
    userInfo.put("userName", user.getUserName());
    userInfo.put("nickname", user.getNickname());
    userInfo.put("headUrl", user.getHeadUrl());
    userInfo.put("departmentName", user.getDepartmentName());

    JSONObject json = new JSONObject();
    json.put("user", userInfo);
    json.put("token", WebConstants.TOKEN_PREFIX + token);
    response.getOutputStream()
        .write(JSON.toJSONString(json, STRING_JSON_FILTER).getBytes(Constants.CHARSET_UTF8));
  }
}

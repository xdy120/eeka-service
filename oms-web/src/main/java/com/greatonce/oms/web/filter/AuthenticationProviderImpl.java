package com.greatonce.oms.web.filter;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.SecurityUtil;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.security.PrivateKey;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 登录校验类.
 *
 * @author buer
 * @version 2018-01-17 15:18
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

  private static final BizLogger LOGIN_LOGGER = OmsLoggerFactory.getLogger(OmsModule.USER_LOGIN);

  @Autowired
  private UserService userService;
  @Resource
  private PrivateKey loginPrivateKey;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String loginNameRSA = authentication.getName();
    String loginPasswordRSA = authentication.getCredentials().toString();
    String loginName;
    String loginPassword;
    try {
      loginName = SecurityUtil.decryptRSA(loginNameRSA, loginPrivateKey);
      loginPassword = SecurityUtil.decryptRSA(loginPasswordRSA, loginPrivateKey);
    } catch (Exception e) {
      throw new BadCredentialsException("密码不合法！");
    }
    User user = userService.login(loginName, loginPassword);
    if (user == null) {
      throw new BadCredentialsException("用户名或密码错误!");
    }
    if (!Assert.isTrue(user.isEnable())) {
      throw new BadCredentialsException("此用户已禁用!");
    }
    LOGIN_LOGGER.log(user.getUserId(), "登录");
    UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
        loginName,
        loginPassword);
    result.setDetails(user);
    return result;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}

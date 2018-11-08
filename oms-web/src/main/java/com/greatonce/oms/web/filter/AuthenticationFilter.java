package com.greatonce.oms.web.filter;

import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.web.WebConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author buer
 * @version 2018-01-17 14:51
 */
public class AuthenticationFilter extends BasicAuthenticationFilter {

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    String header = request.getHeader(WebConstants.TOKEN_HEADER);
    if (header == null || !header.startsWith(WebConstants.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }

    try {
      Jws<Claims> jws = Jwts.parser()
          .setSigningKey(WebConstants.PRIVATE_TOKEN_KEY)
          .parseClaimsJws(header.substring(WebConstants.TOKEN_PREFIX.length()));
      Long userId = Long.parseLong(jws.getHeader().get(WebConstants.JWT_HEADER_USER_ID).toString());
      String nickname = String.valueOf(jws.getHeader().get(WebConstants.JWT_HEADER_USER_NAME));
      BizContext.setUserId(userId);
      BizContext.setNickname(nickname);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          nickname, null, new ArrayList<>());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      this.onSuccessfulAuthentication(request, response, authentication);
      chain.doFilter(request, response);
    } catch (JwtException e) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}

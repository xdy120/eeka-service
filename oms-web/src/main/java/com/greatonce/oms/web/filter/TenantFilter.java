package com.greatonce.oms.web.filter;

import com.greatonce.core.TenantContext;
import com.greatonce.oms.web.WebConstants;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author buer
 * @version 2018-01-19 14:14
 */
public class TenantFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws IOException, ServletException {
    TenantContext.setTenantId(request.getHeader(WebConstants.DOMAIN_HEADER));
    filterChain.doFilter(request, response);
  }
}

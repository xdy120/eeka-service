package com.greatonce.oms.web.filter;

import com.greatonce.core.Constants;
import com.greatonce.oms.web.WebConstants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author buer
 * @version 2018-01-17 18:43
 */
public class OptionsFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (WebConstants.CORS_REQUEST_METHOD.equalsIgnoreCase(request.getMethod())) {
      PrintWriter printWriter = response.getWriter();
      try {
        response.setCharacterEncoding(Constants.CHARSET_UTF8);
        response.setHeader(WebConstants.HEAD_CORS_ALLOW_HEADERS,
            WebConstants.HEAD_CORS_ALLOW_HEADERS_VALUE);
        response.setHeader(WebConstants.HEAD_CORS_ALLOW_METHODS,
            WebConstants.HEAD_CORS_ALLOW_METHODS_VALUE);
        response.setHeader(WebConstants.HEAD_CORS_ALLOW_ORIGIN,
            WebConstants.HEAD_CORS_ALLOW_ORIGIN_VALUE);
        response.setHeader(WebConstants.HEAD_CORS_MAX_AGE, WebConstants.HEAD_CORS_MAX_AGE_VALUE);
      } finally {
        printWriter.close();
      }
    } else {
      filterChain.doFilter(request, response);
    }
  }
}

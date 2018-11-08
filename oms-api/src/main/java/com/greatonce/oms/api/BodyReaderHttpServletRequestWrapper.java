package com.greatonce.oms.api;

import com.greatonce.core.Constants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Body重复读取包装.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/24
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

  private final byte[] body;

  public BodyReaderHttpServletRequestWrapper(HttpServletRequest request, String body)
      throws IOException {
    super(request);
    this.body = body.getBytes(StandardCharsets.UTF_8);
  }

  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body);
    byteArrayInputStream.reset();
    return new ServletInputStream() {

      @Override
      public int read() {
        return byteArrayInputStream.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }
    };
  }

  @Override
  public String getHeader(String name) {
    return super.getHeader(name);
  }

  @Override
  public Enumeration<String> getHeaderNames() {
    return super.getHeaderNames();
  }

  @Override
  public Enumeration<String> getHeaders(String name) {
    return super.getHeaders(name);
  }
}
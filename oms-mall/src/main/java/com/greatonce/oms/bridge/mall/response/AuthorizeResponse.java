package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.AuthorizeRequest;
import java.time.LocalDateTime;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/27
 * remark:
 */
public class AuthorizeResponse extends MallResponse<AuthorizeRequest> {

  private String accessToken;
  private LocalDateTime accessExpire;
  private String refreshToken;
  private LocalDateTime refreshExpire;

  public AuthorizeResponse(AuthorizeRequest request) {
    super(request);
  }

  public AuthorizeResponse(AuthorizeRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public LocalDateTime getAccessExpire() {
    return accessExpire;
  }

  public void setAccessExpire(LocalDateTime accessExpire) {
    this.accessExpire = accessExpire;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public LocalDateTime getRefreshExpire() {
    return refreshExpire;
  }

  public void setRefreshExpire(LocalDateTime refreshExpire) {
    this.refreshExpire = refreshExpire;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AuthorizeResponse{");
    sb.append("accessToken='").append(accessToken).append('\'');
    sb.append(", accessExpire=").append(accessExpire);
    sb.append(", refreshToken='").append(refreshToken).append('\'');
    sb.append(", refreshExpire=").append(refreshExpire);
    sb.append('}');
    return sb.toString();
  }
}

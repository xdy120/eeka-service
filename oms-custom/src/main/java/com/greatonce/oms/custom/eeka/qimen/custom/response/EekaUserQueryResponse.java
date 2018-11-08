package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import com.greatonce.oms.domain.base.User;
import java.util.List;

public class EekaUserQueryResponse extends OmsQimenCustomResponse {

  public EekaUserQueryResponse(Long requestId) {
    super(requestId);
  }

  public EekaUserQueryResponse(Long requestId, String message) {
    super(requestId, message);
  }

  private List<User> users;

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}

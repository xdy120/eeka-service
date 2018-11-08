package com.greatonce.oms.web.controller;

import com.greatonce.oms.domain.enums.PrivilegeType;

import java.util.ArrayList;
import java.util.List;


/**
 * @author buer
 * @version 2017-12-04 13:50
 */
public interface AuthController<T> {

  default List<Long> getAuth(PrivilegeType privilegeType) {
    return new ArrayList<>();
  }
}

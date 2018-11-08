package com.greatonce.oms.consumer.base.store;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.enums.MallType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 店铺初始化工厂.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
@Component
public class StoreInitializationFactory {

  @Autowired
  private StoreInitialization storeInitialization;
  private final Map<MallType, StoreInitialization> map;

  public StoreInitializationFactory(
      List<StoreInitialization> initializations) {
    this.map = new HashMap<>(initializations.size());
    for (StoreInitialization initialization : initializations) {
      if (!Assert.isEmpty(initialization.supports())) {
        for (MallType mallType : initialization.supports()) {
          this.map.put(mallType, initialization);
        }
      }
    }
  }

  public StoreInitialization getInitializer(MallType mallType) {
    return this.map.getOrDefault(mallType, storeInitialization);
  }

}

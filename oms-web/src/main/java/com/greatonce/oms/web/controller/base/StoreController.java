package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.StoreBridge;
import com.greatonce.oms.bridge.mall.request.StoreAddressQueryRequest;
import com.greatonce.oms.bridge.mall.response.StoreAddressQueryResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.query.base.StoreQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.FullListController;
import com.taobao.api.domain.AddressResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-10-21 13:54
 */
@RestController
@RequestMapping("/base/store")
@CrossOrigin
public class StoreController implements FullListController<Store, StoreQuery>,
    EnableController<Store, StoreQuery> {

  @Autowired
  private StoreService storeService;

  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  @Override
  public BizService<Store, StoreQuery> getBizService() {
    return storeService;
  }

  @GetMapping("/effective")
  public List<Store> listEffective() {
    Store eg = new Store();
    eg.setEnable(true);
    return storeService.listExample(eg);
  }


  @GetMapping("/my")
  public List<Store> listUserStore() {
    return storeService.listUserStore(BizContext.getUserId());
  }

  @GetMapping("/auth/url/{storeId}")
  public String getAuthUrl(@PathVariable("storeId") Long storeId) {
    Store store = storeService.getByKey(storeId);
    return storeService.authUrl(store);
  }

  @PostMapping("/auth/token/access/{storeId}/{code}")
  public void accessToken(@PathVariable("storeId") Long storeId,
      @PathVariable("code") String code) {
    Store store = storeService.getByKey(storeId);
    storeService.assessToken(store, code);
  }

  @PostMapping("/auth/token/refresh/{storeId}")
  public void refreshToken(@PathVariable("storeId") Long storeId) {
    Store store = storeService.getByKey(storeId);
    storeService.refreshToken(store);
  }


  @PostMapping("/returnAddress")
  public List<AddressResult> setStoreAddress(@RequestBody Store store) {

    StoreBridge storeBridge = mallBridgeFactory.getStoreBridge(store.getMallType());
    StoreAddressQueryRequest request = new StoreAddressQueryRequest(store);
    StoreAddressQueryResponse response = storeBridge.queryAddress(request);
    response.getAddress()
        .forEach(x -> x.setAddr(x.getProvince() + x.getCity() + x.getCountry() + x.getAddr()));
    return response.getAddress();
  }
}

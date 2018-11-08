package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.biz.trade.ReturnSignService;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaReturnSignRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaReturnSignResponse;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.trade.ReturnSign;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EekaApiCondition
public class EekaReturnSignController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaReturnSignController.class);

  @Autowired
  private ReturnSignService returnSignService;
  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private UserService userService;

  @Autowired
  private ManualTransactionTemplate transactionTemplate;

  /**
   * 快递批量签收
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.return.sign.batch.create")
  public EekaReturnSignResponse returnSign(HttpServletRequest servletRequest) {
    EekaReturnSignRequest request = checkSign(servletRequest, EekaReturnSignRequest.class);
    EekaReturnSignResponse response = null;
    if (!Assert.isNull(request)) {
      if (Assert.isEmpty(request.getReturnSigns())) {
        response = new EekaReturnSignResponse(apiIdGenerator.next(), "请求参数不能为空");
        return response;
      }
      //获取用户昵称
      List<ReturnSign> returnSigns = request.getReturnSigns();
      if (!Assert.isEmpty(returnSigns)) {
        ReturnSign returnSign = returnSigns.get(0);
        User userEg = new User();
        userEg.setLoginName(returnSign.getCreator());
        userEg.setEnable(true);
        User user = userService.getByExample(userEg);
        returnSigns.forEach(x -> {
          if (!Assert.isNull(user)) {
            x.setCreator(user.getNickname());
          }
        });
        transactionTemplate.execute(() -> returnSignService.batchCreate(returnSigns));
        response = new EekaReturnSignResponse(apiIdGenerator.next());
      }
    }
    if (Assert.isNull(response)) {
      response = new EekaReturnSignResponse(apiIdGenerator.next());
    }
    return response;
  }

}

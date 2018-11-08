package com.greatonce.oms.custom;

import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.wms.qimen.SignException;
import com.taobao.api.internal.spi.CheckResult;
import com.taobao.api.internal.spi.SpiUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 奇门自定义接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/25
 */
@RequestMapping("/custom")
public abstract class QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(QimenCustomController.class);
  private static final String APP_SECRET = "bb31941ed1dc205371281af38e04082e";

  protected void checkSign(HttpServletRequest request) {
    try {
      CheckResult checkResult = SpiUtils.checkSign(request, APP_SECRET);
      LOGGER.info("checkSign：{},{},sign：{}", checkResult.getRequestBody(), checkResult.isSuccess(),
          request.getParameter("sign"));
      if (!checkResult.isSuccess()) {
        throw new SignException();
      }
    } catch (IOException e) {
      throw new SignException();
    }
  }

  protected <T> T checkSign(HttpServletRequest request, Class<T> clazz) {
    try {
      CheckResult checkResult = SpiUtils.checkSign(request, APP_SECRET);
      LOGGER.info("checkSign：{},{},sign：{}", checkResult.getRequestBody(), checkResult.isSuccess(),
          request.getParameter("sign"));
      if (checkResult.isSuccess()) {
        return JsonUtil.toObject(checkResult.getRequestBody(), clazz);
      } else {
        throw new SignException();
      }
    } catch (IOException e) {
      throw new SignException();
    }
  }

}

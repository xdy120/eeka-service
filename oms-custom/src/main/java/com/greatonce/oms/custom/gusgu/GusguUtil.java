package com.greatonce.oms.custom.gusgu;

import com.greatonce.core.util.Assert;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "oms.consumer.custom")
public class GusguUtil {

  private Map<String, Long> storeMapping;

  public Map<String, Long> getStoreMapping() {
    return storeMapping;
  }

  public void setStoreMapping(Map<String, Long> storeMapping) {
    this.storeMapping = storeMapping;
  }

  public String convertToOrgId(Long storeId) {
    if (!Assert.isNull(storeId)) {
      if (storeId.equals(storeMapping.get("qikemoke"))) {
        // "奇克摩克旗舰店"不推送数据
        return null;
      } else if (storeId.equals(storeMapping.get("deerma"))) {
        // "deerma德尔玛盛开专卖店"
        return "200";
      } else if (storeId.equals(storeMapping.get("lingchenchepin"))) {
        // "领臣车品旗舰店"
        return "300";
      }
    }
    // 其他店铺
    return "100";
  }

}

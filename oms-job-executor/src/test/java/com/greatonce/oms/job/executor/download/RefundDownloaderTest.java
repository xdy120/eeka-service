package com.greatonce.oms.job.executor.download;

import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.domain.product.ProductSku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xdy
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/31
 */
@ActiveProfiles("dev-eeka")
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RefundDownloaderTest {

  @Autowired
  ProductSkuService productSkuService;
/*  public static void main(String[] args) {
    SpringApplication.run(RefundDownloaderTest.class);
  }*/
  @Test
  public void doDownload() {
    final ProductSku sku = productSkuService.getEffectiveByCode("ABCD");
    System.out.println(sku);
  }
}
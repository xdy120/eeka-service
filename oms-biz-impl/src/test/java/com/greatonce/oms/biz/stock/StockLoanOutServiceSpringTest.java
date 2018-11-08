package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.SpringTest;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.stock.StockLoanOut;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-31
 */
@ActiveProfiles("dev-gusgu-ssh")
public class StockLoanOutServiceSpringTest extends SpringTest {

  @Autowired
  StockLoanOutService stockLoanOutService;
  @Autowired
  StockLoanInService stockLoanInService;

  @Test
  public void returnBack() {
    final StockLoanIn stockLoanIn = stockLoanInService.getByKey(13890345829417984L);
    final StockLoanOut stockloanOut = stockLoanOutService
        .getByCode(stockLoanIn.getStockLoanOutCode());
    stockLoanOutService.returnBack(stockloanOut, stockLoanIn);
    System.out.println(stockLoanIn);
  }
}
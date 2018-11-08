import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.admin.GlobalExpressMallMappingService;
import com.greatonce.oms.biz.admin.GlobalExpressService;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.trade.SalesOrderUtil;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.trade.MallExpressResetBo;
import com.greatonce.oms.bo.trade.SalesOrderExpressNoticeBo;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.dao.trade.SalesOrderDao;
import com.greatonce.oms.domain.admin.GlobalExpress;
import com.greatonce.oms.domain.admin.GlobalExpressMallMapping;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.message.trade.DispatchOrderDeliveryMessage;
import com.greatonce.oms.message.trade.SalesOrderDeliveryMessage;
import com.greatonce.oms.query.trade.DispatchOrderDetailQuery;
import com.greatonce.oms.web.Application;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/7/18
 */
@ActiveProfiles("dev-eeka-ssh")
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class WebTest {

  @Autowired
  SalesOrderService salesOrderService;
  @Autowired
  SalesOrderDetailService salesOrderDetailService;
  @Autowired
  SalesOrderDao salesOrderDao;
  @Autowired
  StoreService storeService;
  @Autowired
  MallBridgeFactory mallBridgeFactory;
  @Autowired
  DispatchOrderService dispatchOrderService;
  @Autowired
  DispatchOrderDetailService dispatchOrderDetailService;
  @Autowired
  ExpressService expressService;
  @Autowired
  GlobalExpressMallMappingService globalExpressMallMappingService;
  @Autowired
  GlobalExpressService globalExpressService;
  @Autowired
  MqProducer mqProducer;

  private final SalesOrder salesOrder = new SalesOrder();
  List<SalesOrderDetail> salesOrderDetails = salesOrder.getDetails();


  @Before
  public void before() {
    List<SalesOrderDetail> salesOrderDetails = new ArrayList<>(3);
    salesOrder.setDetails(salesOrderDetails);
    for (int i = 0; i < 3; i++) {
      SalesOrderDetail detail = new SalesOrderDetail();
      detail.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
      salesOrderDetails.add(detail);
    }
  }

  /**
   * 平台发货测试
   */
  @Test
  public void deliveryTest() {
    DispatchOrder dispatchOrder = dispatchOrderService.getByCode("DO54253275446800");
    DispatchOrderDetailQuery query = new DispatchOrderDetailQuery();
    query.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    List<DispatchOrderDetail> details = dispatchOrderDetailService.list(query);
    SalesOrder salesOrder = salesOrderService.getFullInfo(details.get(0).getSalesOrderId());
    Store store = storeService.getByKey(dispatchOrder.getStoreId());
    Express express = expressService.getByKey(dispatchOrder.getSuggestExpressId());
    GlobalExpressMallMapping mapping = globalExpressMallMappingService
        .getMallExpressMapping(express.getGlobalExpressId(), store.getMallType());
    SalesOrderExpressNoticeBo bo = new SalesOrderExpressNoticeBo();
    bo.setMallExpressCode(mapping.getOuterId());
    bo.setMallExpressName(mapping.getOuterName());
    bo.setMallExpressId(mapping.getOuterId());
    bo.setExpressName(dispatchOrder.getSuggestExpressName());
    bo.setExpressNo(dispatchOrder.getSuggestExpressNo());
    bo.setVersion(salesOrder.getVersion());
    salesOrderService.mallExpressNotice(salesOrder, bo);
  }

  /**
   * 平台修改快递测试
   */
  @Test
  public void mallResetExpressTest() {
    DispatchOrder dispatchOrder = dispatchOrderService.getByCode("DO54253290329936");
    DispatchOrderDetailQuery query = new DispatchOrderDetailQuery();
    query.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    List<DispatchOrderDetail> details = dispatchOrderDetailService.list(query);
    SalesOrder salesOrder = salesOrderService.getFullInfo(details.get(0).getSalesOrderId());
    Store store = storeService.getByKey(dispatchOrder.getStoreId());
    Express express = expressService.getByKey(dispatchOrder.getSuggestExpressId());
    GlobalExpressMallMapping mapping = globalExpressMallMappingService
        .getMallExpressMapping(express.getGlobalExpressId(), store.getMallType());
    MallExpressResetBo bo = new MallExpressResetBo();
    bo.setResetExpressNo("541063094636");
    bo.setMallExpressId(mapping.getOuterId());
    bo.setMallExpressCode(mapping.getOuterCode());
    bo.setMallExpressName(mapping.getOuterName());
    bo.setVersion(salesOrder.getVersion());
    salesOrderService.mallResetExpress(salesOrder, bo);
  }

  @Test
  public void mallExpressNotice() {
    DispatchOrder dispatchOrder = dispatchOrderService.getByCode("DO54286152802704");
    DispatchOrderDetailQuery query = new DispatchOrderDetailQuery();
    query.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    List<DispatchOrderDetail> details = dispatchOrderDetailService.list(query);
    details.forEach(x -> {
      SalesOrder salesOrder = salesOrderService.getFullInfo(x.getSalesOrderId());
      Store store = storeService.getByKey(dispatchOrder.getStoreId());
      Express express = expressService.getByKey(dispatchOrder.getSuggestExpressId());
      GlobalExpressMallMapping mapping = globalExpressMallMappingService
          .getMallExpressMapping(express.getGlobalExpressId(), store.getMallType());
      SalesOrderExpressNoticeBo bo = new SalesOrderExpressNoticeBo();
      bo.setMallExpressId(mapping.getOuterId());
      bo.setMallExpressCode(mapping.getOuterCode());
      bo.setMallExpressName(mapping.getOuterName());
      bo.setVersion(salesOrder.getVersion());
      bo.setExpressNo(dispatchOrder.getSuggestExpressNo());
      salesOrderService.mallExpressNotice(salesOrder, bo);
    });
  }

  /**
   * 补发回传平台消息.
   */
  @Test
  public void resendMallDeliveryMessage() {
    List<SalesOrder> salesOrders = salesOrderDao.listMallDeliveryAbnormalOrders();
    for (SalesOrder salesOrder : salesOrders) {
      System.out.printf("交易号：%s，店铺名：%s", salesOrder.getTradeId(), salesOrder.getStoreName());
      System.out.println();
      mqProducer.send(new SalesOrderDeliveryMessage(salesOrder.getSalesOrderId(), false));
      System.out.println("消息已发出");
    }
    System.out.printf("总条数：%s", salesOrders.size());
  }

  @Test
  public void resendWmsDeliveryMessage() {
    String b = "DO54091799001616,DO54091799015184,DO54091799038608,DO54091799078096,DO54091799295952,DO54091799295952,DO54091799295952,DO54103600700304,DO54103600706000,DO54103600917200,DO54105978799248,DO54105978799248,DO54109638443920,DO54110439374160,DO54110439374160,DO54118382892112,DO54118382892112,DO54119327957136,DO54119327957136,DO54119327957136,DO54120133456784,DO54120133720720,DO54120135527504,DO54120279540240,DO54121361970896,DO54121609513616,DO54121878310096,DO54122051404560,DO54122249098640,DO54122249484304,DO54127123749264,DO54127373350672,DO54131255706704,DO54131255706704,DO54131255706704,DO54132856776464,DO54132856776464,DO54132856776464,DO54132856776464,DO54138313508432,DO54138663681552,DO54138712421584,DO54138774308304,DO54138889509648,DO54142281509264,DO54142281509264,DO54142281521232,DO54142281521232,DO54150912997520,DO54150912997520,DO54159181590096,DO54159648576016,DO54160116320080,DO54160116320080,DO54160117635344,DO54160117635344,DO54160117635344,DO54160974215440,DO54161192904272";
    String[] split = b.split(",");
    for (String code : split) {
      DispatchOrder dispatchOrder = dispatchOrderService.getByCode(code);
      mqProducer.send(new DispatchOrderDeliveryMessage(dispatchOrder.getDispatchOrderId()));
    }
  }

  private void lccPrint(SalesOrder salesOrder, List<SalesOrderDetail> salesOrderDetails) {
    SalesOrderUtil.recountStatus(salesOrder, salesOrderDetails);
    System.out.printf("订单主状态：%s,配货状态：%s，发货状态：%s，退款状态：%s",
        salesOrder.getStatus().caption(),
        salesOrder.getDispatchStatus().caption(),
        salesOrder.getDeliveryStatus().caption(),
        salesOrder.getRefundStatus().caption());
    System.out.println();
  }

  /**
   * 测试1.
   *
   * 内容：
   * 2条明细：
   * A：已发货，未退款
   * B：已发货，未退款
   */
  @Test
  public void testStatus1() {

    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.DELIVERED);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    });

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试2.
   *
   * 内容：
   * 2条明细：
   * A：已发货，已申请退款
   * B：已发货，已申请退款
   */
  @Test
  public void testStatus2() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.DELIVERED);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.APPLY);
    });

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试3.
   *
   * 内容：
   * 2条明细：
   * A：已发货，已退款
   * B：已发货，已退款
   */
  @Test
  public void testStatus3() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.DELIVERED);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    });

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试4.
   *
   * 内容：
   * 2条明细：
   * A：已配货，未退款
   * B：已配货，未退款
   */
  @Test
  public void testStatus4() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.DISPATCHED);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    });

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试5.
   *
   * 内容：
   * 2条明细：
   * A：已配货，已申请退款
   * B：已配货，已申请退款
   */
  @Test
  public void testStatus5() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.DISPATCHED);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.APPLY);
    });

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试6.
   *
   * 内容：
   * 2条明细：
   * A：已配货，已退款
   * B：已配货，已退款
   */
  @Test
  public void testStatus6() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.DISPATCHED);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    });

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试7.
   *
   * 内容：
   * 2条明细：
   * A：已发货，已退款
   * B：未发货，未退款
   */
  @Test
  public void testStatus7() {
    salesOrderDetails.get(0).setStatus(SalesOrderDetailStatus.DELIVERED);
    salesOrderDetails.get(0).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    salesOrderDetails.get(1).setStatus(SalesOrderDetailStatus.WAITING);
    salesOrderDetails.get(1).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试7.
   *
   * 内容：
   * 2条明细：
   * A：已发货，已退款
   * B：未发货，已退款
   */
  @Test
  public void testStatus8() {
    salesOrderDetails.get(0).setStatus(SalesOrderDetailStatus.DELIVERED);
    salesOrderDetails.get(0).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    salesOrderDetails.get(1).setStatus(SalesOrderDetailStatus.WAITING);
    salesOrderDetails.get(1).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);

    lccPrint(salesOrder, salesOrderDetails);
  }


  /**
   * 测试9.
   *
   * 内容：
   * 2条明细：
   * A：已发货，已退款
   * B：已配货，未退款
   */
  @Test
  public void testStatus9() {
    salesOrderDetails.get(0).setStatus(SalesOrderDetailStatus.DELIVERED);
    salesOrderDetails.get(0).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    salesOrderDetails.get(1).setStatus(SalesOrderDetailStatus.DISPATCHED);
    salesOrderDetails.get(1).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试10.
   *
   * 内容：
   * 2条明细：
   * A：已发货，已退款
   * B：已配货，已退款
   */
  @Test
  public void testStatus10() {
    salesOrderDetails.get(0).setStatus(SalesOrderDetailStatus.DELIVERED);
    salesOrderDetails.get(0).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    salesOrderDetails.get(1).setStatus(SalesOrderDetailStatus.DISPATCHED);
    salesOrderDetails.get(1).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试11.
   *
   * 内容：
   * 2条明细：
   * A：未配货，已退款
   * B：未配货，已退款
   */
  @Test
  public void testStatus11() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.WAITING);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    });

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试12.
   *
   * 内容：
   * 3条明细：
   * A：未配货，已退款
   * B：未配货，已退款
   * C：已作废，未退款
   */
  @Test
  public void testStatus12() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.WAITING);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    });
    SalesOrderDetail detail = new SalesOrderDetail();
    detail.setStatus(SalesOrderDetailStatus.INVALID);
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    salesOrderDetails.add(detail);

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试13.
   *
   * 内容：
   * 3条明细：
   * A：已发货，已退款
   * B：已发货，已退款
   * C：已作废，未退款
   */
  @Test
  public void testStatus13() {
    salesOrderDetails.forEach(x -> {
      x.setStatus(SalesOrderDetailStatus.DELIVERED);
      x.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    });
    SalesOrderDetail detail = new SalesOrderDetail();
    detail.setStatus(SalesOrderDetailStatus.INVALID);
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    salesOrderDetails.add(detail);

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试14.
   *
   * 内容：
   * 3条明细：
   * A：已发货，已退款
   * B：已配货，未退款
   * C：已作废，未退款
   */
  @Test
  public void testStatus14() {
    salesOrderDetails.get(0).setStatus(SalesOrderDetailStatus.DELIVERED);
    salesOrderDetails.get(0).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    salesOrderDetails.get(1).setStatus(SalesOrderDetailStatus.DISPATCHED);
    salesOrderDetails.get(1).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    SalesOrderDetail detail = new SalesOrderDetail();
    detail.setStatus(SalesOrderDetailStatus.INVALID);
    detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    salesOrderDetails.add(detail);

    lccPrint(salesOrder, salesOrderDetails);
  }

  /**
   * 测试15.
   *
   * 内容：
   * 3条明细：
   * A：已发货，已退款
   * B：已配货，未退款
   * C：异常，未退款
   */
  @Test
  public void testStatus15() {
    salesOrderDetails.get(0).setStatus(SalesOrderDetailStatus.DELIVERED);
    salesOrderDetails.get(0).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.REFUND);
    salesOrderDetails.get(1).setStatus(SalesOrderDetailStatus.DISPATCHED);
    salesOrderDetails.get(1).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    salesOrderDetails.get(2).setStatus(SalesOrderDetailStatus.WAITING);
    salesOrderDetails.get(2).setProductAbnormal(true);
    salesOrderDetails.get(2).setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    SalesOrderUtil.recountStatus(salesOrder, salesOrder.getDetails());
    lccPrint(salesOrder, salesOrderDetails);
  }

}

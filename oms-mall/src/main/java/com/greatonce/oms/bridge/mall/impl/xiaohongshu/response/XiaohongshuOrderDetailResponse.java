package com.greatonce.oms.bridge.mall.impl.xiaohongshu.response;

import java.util.List;

public class XiaohongshuOrderDetailResponse extends BaseXiaohongshuBo {


  private Data data;

  public Data getData() {
    return data;
  }


  public void setData(Data data) {
    this.data = data;
  }


  public static class Data {

    //订单ID
    private String package_id;
    //当前包裹的物流模式
    private String logistics;
    //用户下单时间,Unix-Time时间戳
    private Integer time;
    //用户支付时间,Unix-Time时间戳
    private Integer pay_time;
    //	订单确认时间,Unix-Time时间戳
    private Integer confirm_time;
    //	订单使用的快递公司编码
    private String express_company_code;
    //	订单使用的快递公司名称
    private String express_company_name;
    //订单使用的快递号码
    private String express_no;
    //订单状态，waiting待打包和打包中(APP端显示为待配货和配货中),shipped已发货,received收件人已签收
    private String status;
    private String receiver_name;
    private String receiver_phone;
    private String receiver_address;
    //省
    private String province;
    //市
    private String city;
    //区
    private String district;
    //订单商品总净重
    private Double total_net_weight;
    //订单支付金额
    private Double pay_amount;
    //国际快件号，该字段仅限于小包转运模式
    private String Integerernational_express_no;
    //国内快递送货时间，该字段仅限于小包转运模式
    private String delivery_time_preference;
    //订单申报金额，该字段仅限于小包转运模式
    private String order_declared_amount;
    //大头笔，该字段仅限于小包转运模式
    private String paInteger_marker;
    //集包地，该字段仅限于小包转运模式
    private String express_extend_1;
    //三段码，该字段仅限于小包转运模式
    private String express_extend_2;
    private List<Item> item_list;


    public String getPackage_id() {
      return package_id;
    }


    public void setPackage_id(String package_id) {
      this.package_id = package_id;
    }


    public String getLogistics() {
      return logistics;
    }


    public void setLogistics(String logistics) {
      this.logistics = logistics;
    }


    public Integer getTime() {
      return time;
    }


    public void setTime(Integer time) {
      this.time = time;
    }


    public Integer getPay_time() {
      return pay_time;
    }


    public void setPay_time(Integer pay_time) {
      this.pay_time = pay_time;
    }


    public Integer getConfirm_time() {
      return confirm_time;
    }


    public void setConfirm_time(Integer confirm_time) {
      this.confirm_time = confirm_time;
    }

    public Double getTotal_net_weight() {
      return total_net_weight;
    }


    public void setTotal_net_weight(Double total_net_weight) {
      this.total_net_weight = total_net_weight;
    }


    public Double getPay_amount() {
      return pay_amount;
    }


    public void setPay_amount(Double pay_amount) {
      this.pay_amount = pay_amount;
    }


    public String getExpress_company_code() {
      return express_company_code;
    }


    public void setExpress_company_code(String express_company_code) {
      this.express_company_code = express_company_code;
    }


    public String getExpress_company_name() {
      return express_company_name;
    }


    public void setExpress_company_name(String express_company_name) {
      this.express_company_name = express_company_name;
    }


    public String getExpress_no() {
      return express_no;
    }


    public void setExpress_no(String express_no) {
      this.express_no = express_no;
    }


    public String getStatus() {
      return status;
    }


    public void setStatus(String status) {
      this.status = status;
    }


    public String getReceiver_name() {
      return receiver_name;
    }


    public void setReceiver_name(String receiver_name) {
      this.receiver_name = receiver_name;
    }


    public String getReceiver_phone() {
      return receiver_phone;
    }


    public void setReceiver_phone(String receiver_phone) {
      this.receiver_phone = receiver_phone;
    }


    public String getReceiver_address() {
      return receiver_address;
    }


    public void setReceiver_address(String receiver_address) {
      this.receiver_address = receiver_address;
    }


    public String getProvince() {
      return province;
    }


    public void setProvince(String province) {
      this.province = province;
    }


    public String getCity() {
      return city;
    }


    public void setCity(String city) {
      this.city = city;
    }


    public String getDistrict() {
      return district;
    }


    public void setDistrict(String district) {
      this.district = district;
    }


    public String getIntegerernational_express_no() {
      return Integerernational_express_no;
    }


    public void setIntegerernational_express_no(String Integerernational_express_no) {
      this.Integerernational_express_no = Integerernational_express_no;
    }


    public String getDelivery_time_preference() {
      return delivery_time_preference;
    }


    public void setDelivery_time_preference(String delivery_time_preference) {
      this.delivery_time_preference = delivery_time_preference;
    }


    public String getOrder_declared_amount() {
      return order_declared_amount;
    }


    public void setOrder_declared_amount(String order_declared_amount) {
      this.order_declared_amount = order_declared_amount;
    }


    public String getPaInteger_marker() {
      return paInteger_marker;
    }


    public void setPaInteger_marker(String paInteger_marker) {
      this.paInteger_marker = paInteger_marker;
    }


    public String getExpress_extend_1() {
      return express_extend_1;
    }


    public void setExpress_extend_1(String express_extend_1) {
      this.express_extend_1 = express_extend_1;
    }


    public String getExpress_extend_2() {
      return express_extend_2;
    }


    public void setExpress_extend_2(String express_extend_2) {
      this.express_extend_2 = express_extend_2;
    }


    public List<Item> getItem_list() {
      return item_list;
    }


    public void setItem_list(List<Item> item_list) {
      this.item_list = item_list;
    }


    public static class Item {

      //单件商品商家承担的优惠，一般包括商家配置的薯券和商家设置的促销，例如限时特价，满减和任选等。
      private String merchant_discount;
      //单件商品小红书承担的优惠，一般为全场通用薯券，例如新人薯券，全场通用券等
      private String red_discount;
      //商品条码
      private String barcode;
      //小红书编码
      private String skucode;
      //商品名称
      private String item_name;
      //商品规格
      private String specification;
      //购买数量
      private Integer qty;
      //商品售价
      private String price;
      //实际支付价格
      private String pay_price;
      //商品净重
      private Double net_weight;
      //备案名，该字段仅限于小包转运模式
      private String register_name;

      public String getMerchant_discount() {
        return merchant_discount;
      }

      public void setMerchant_discount(String merchant_discount) {
        this.merchant_discount = merchant_discount;
      }

      public String getRed_discount() {
        return red_discount;
      }

      public void setRed_discount(String red_discount) {
        this.red_discount = red_discount;
      }

      public String getBarcode() {
        return barcode;
      }

      public void setBarcode(String barcode) {
        this.barcode = barcode;
      }

      public String getSkucode() {
        return skucode;
      }

      public void setSkucode(String skucode) {
        this.skucode = skucode;
      }

      public String getItem_name() {
        return item_name;
      }

      public void setItem_name(String item_name) {
        this.item_name = item_name;
      }

      public String getSpecification() {
        return specification;
      }

      public void setSpecification(String specification) {
        this.specification = specification;
      }

      public String getPrice() {
        return price;
      }

      public void setPrice(String price) {
        this.price = price;
      }

      public String getPay_price() {
        return pay_price;
      }

      public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
      }

      public String getRegister_name() {
        return register_name;
      }

      public void setRegister_name(String register_name) {
        this.register_name = register_name;
      }

      public Integer getQty() {
        return qty;
      }

      public void setQty(Integer qty) {
        this.qty = qty;
      }

      public Double getNet_weight() {
        return net_weight;
      }

      public void setNet_weight(Double net_weight) {
        this.net_weight = net_weight;
      }


    }
  }
}

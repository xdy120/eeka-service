package com.greatonce.oms.bridge.mall.impl.xiaohongshu.response;

import java.util.List;

public class XiaohongshuOrderQueryResponse extends BaseXiaohongshuBo {


  private Data data;

  public Data getData() {
    return data;
  }


  public void setData(Data data) {
    this.data = data;
  }


  public static class Data {

    private int total_number;
    private int current_page;
    private int total_page;
    private int page_size;
    private List<Package> package_list;


    public int getTotal_number() {
      return total_number;
    }


    public void setTotal_number(int total_number) {
      this.total_number = total_number;
    }


    public int getCurrent_page() {
      return current_page;
    }


    public void setCurrent_page(int current_page) {
      this.current_page = current_page;
    }


    public int getTotal_page() {
      return total_page;
    }


    public void setTotal_page(int total_page) {
      this.total_page = total_page;
    }


    public int getPage_size() {
      return page_size;
    }


    public void setPage_size(int page_size) {
      this.page_size = page_size;
    }


    public List<Package> getPackage_list() {
      return package_list;
    }


    public void setPackage_list(List<Package> package_list) {
      this.package_list = package_list;
    }


    public static class Package {

      private String package_id;
      private Long time;
      private Long confirm_time;
      private String express_company_code;
      private String express_company_name;
      private String express_no;
      private String logistics;
      private String status;
      private int item_number;

      public String getPackage_id() {
        return package_id;
      }

      public void setPackage_id(String package_id) {
        this.package_id = package_id;
      }

      public Long getTime() {
        return time;
      }

      public void setTime(Long time) {
        this.time = time;
      }

      public Long getConfirm_time() {
        return confirm_time;
      }

      public void setConfirm_time(Long confirm_time) {
        this.confirm_time = confirm_time;
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

      public String getLogistics() {
        return logistics;
      }

      public void setLogistics(String logistics) {
        this.logistics = logistics;
      }

      public String getStatus() {
        return status;
      }

      public void setStatus(String status) {
        this.status = status;
      }

      public int getItem_number() {
        return item_number;
      }

      public void setItem_number(int item_number) {
        this.item_number = item_number;
      }


    }
  }
}

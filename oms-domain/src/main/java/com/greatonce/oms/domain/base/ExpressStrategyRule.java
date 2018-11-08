package com.greatonce.oms.domain.base;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cca
 * Date: 2018-03-15
 * Time: 22:06
 */
public class ExpressStrategyRule implements Serializable {

  private List<StrategyExpress> expresses;

  public List<StrategyExpress> getExpresses() {
    return expresses;
  }

  public void setExpresses(List<StrategyExpress> expresses) {
    this.expresses = expresses;
  }

  public static class StrategyExpress implements Serializable, Comparable<StrategyExpress> {

    private Long expressId;
    private String expressName;
    private Integer priorityNo;

    public Long getExpressId() {
      return expressId;
    }

    public void setExpressId(Long expressId) {
      this.expressId = expressId;
    }

    public String getExpressName() {
      return expressName;
    }

    public void setExpressName(String expressName) {
      this.expressName = expressName;
    }

    public Integer getPriorityNo() {
      return priorityNo;
    }

    public void setPriorityNo(Integer priorityNo) {
      this.priorityNo = priorityNo;
    }


    @Override
    public int compareTo(StrategyExpress o) {
      return this.priorityNo.compareTo(o.priorityNo);
    }
  }
}

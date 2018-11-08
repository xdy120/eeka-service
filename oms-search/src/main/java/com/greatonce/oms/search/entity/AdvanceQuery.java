package com.greatonce.oms.search.entity;

import java.util.List;

/**
 * 高级查询实体
 */
public class AdvanceQuery {

  private List<Field> fields;

  public List<Field> getFields() {
    return fields;
  }

  public AdvanceQuery(List<Field> fields) {
    this.fields = fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  public static class Field {
    private String field;
    private FieldType type;
    private String value;
    private Operator operator;
    private String relation;
    private String enumName;
    private BoolType boolType;

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }

    public FieldType getType() {
      return type;
    }

    public void setType(FieldType type) {
      this.type = type;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public Operator getOperator() {
      return operator;
    }

    public void setOperator(Operator operator) {
      this.operator = operator;
    }

    public String getRelation() {
      return relation;
    }

    public void setRelation(String relation) {
      this.relation = relation;
    }

    public String getEnumName() {
      return enumName;
    }

    public void setEnumName(String enumName) {
      this.enumName = enumName;
    }

    public BoolType getBoolType() {
      return boolType;
    }

    public void setBoolType(BoolType boolType) {
      this.boolType = boolType;
    }
  }

}

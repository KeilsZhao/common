package com.bzfar.handle;

import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.StringValue;


public class BzcyStringValue extends StringValue {

    private String value = "";

    public BzcyStringValue(String escapedValue) {
        super(escapedValue);
        this.value = escapedValue;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getNotExcapedValue() {
        StringBuilder buffer = new StringBuilder(this.value);
        return buffer.toString();
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "'" + this.value + "'";
    }
}

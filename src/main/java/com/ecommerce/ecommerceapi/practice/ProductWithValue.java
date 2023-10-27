package com.ecommerce.ecommerceapi.practice;

import java.util.List;

public class ProductWithValue {
    String name;
    String productCode;
    List<AttributeValue> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<AttributeValue> getValues() {
        return values;
    }

    public void setValues(List<AttributeValue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ProductWithValue{" +
                "name='" + name + '\'' +
                ", productCode='" + productCode + '\'' +
                ", values=" + values +
                '}';
    }
}

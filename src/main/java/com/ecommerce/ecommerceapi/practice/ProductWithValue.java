package com.ecommerce.ecommerceapi.practice;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductWithValue {
    String name;
    String productCode;
    List<AttributeValue> values;

    @Override
    public String toString() {
        return "ProductWithValue{" +
                "name='" + name + '\'' +
                ", productCode='" + productCode + '\'' +
                ", values=" + values +
                '}';
    }
}

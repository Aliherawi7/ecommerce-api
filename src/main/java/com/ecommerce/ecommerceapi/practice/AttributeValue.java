package com.ecommerce.ecommerceapi.practice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeValue {
    String name;
    String value;

    @Override
    public String toString() {
        return "AttributeValue{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

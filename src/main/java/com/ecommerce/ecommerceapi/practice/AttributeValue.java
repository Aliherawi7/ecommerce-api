package com.ecommerce.ecommerceapi.practice;

public class AttributeValue {
    String name;
    String value;

    public AttributeValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "AttributeValue{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

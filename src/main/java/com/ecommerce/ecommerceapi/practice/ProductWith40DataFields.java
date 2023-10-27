package com.ecommerce.ecommerceapi.practice;

public class ProductWith40DataFields {
    private String name;
    private String code;
    private String address;
    private Integer id;

    public ProductWith40DataFields() {
    }

    public ProductWith40DataFields(String name, String code, String address, Integer id) {
        this.name = name;
        this.code = code;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

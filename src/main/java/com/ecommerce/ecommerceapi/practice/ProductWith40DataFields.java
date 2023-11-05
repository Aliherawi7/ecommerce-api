package com.ecommerce.ecommerceapi.practice;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProductWith40DataFields {
    private String name;
    private String code;
    private String address;
    private Integer id;
    private int year;
    private LocalDate createdAt;
    private Boolean isEnabled;
    private String brandName;
    private String model;
    private BigDecimal price;
    private double discount;
    private String color;
    private String description;
    private Integer rate;
    private String width;
    private String height;
    private Double tax;
    private String tags;
    private List<Integer> relatedProduct;
    private LocalDate updated;
    private String category;
    private Double Weight;
    private String availability;
    

}

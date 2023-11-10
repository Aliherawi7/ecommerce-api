package com.ecommerce.ecommerceapi.projection;

import com.ecommerce.ecommerceapi.entity.ProductAttributeValue;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductProjection {
    private String name;
    private String code;
    private List<ProductAttributeValue> attributeValueList = new ArrayList<>();
    public ProductProjection(String name, String code){
        this.name = name;
        this.code = code;
    }

}

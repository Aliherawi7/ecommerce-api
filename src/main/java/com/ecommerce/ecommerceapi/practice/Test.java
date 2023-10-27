package com.ecommerce.ecommerceapi.practice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        ProductWith40DataFields productWith40DataFields = new ProductWith40DataFields("mobile", "pr-code", "mo address", 1);
        ProductWithValue v = convertToProductWithValue(productWith40DataFields);
        System.out.println(v);
    }


    public static ProductWithValue convertToProductWithValue(ProductWith40DataFields dto) throws IllegalAccessException {
        // get all the declared fields in ProductWith40DataField class
        Field[] fields = ProductWith40DataFields.class.getDeclaredFields();
        // this array is used to store the attributes with values
        List<AttributeValue> attributeValueList = new ArrayList<>();
        // the object with product data fields and the attribute values list
        ProductWithValue productWithValue = new ProductWithValue();
        // retrieving data field with value from the dto object
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equalsIgnoreCase("name")) {
                productWithValue.setName(String.valueOf(field.get(dto)));
            } else if (field.getName().equalsIgnoreCase("code")) {
                productWithValue.setProductCode(String.valueOf(field.get(dto)));
            } else {
                attributeValueList.add(
                        new AttributeValue(productWithValue.getName(), String.valueOf(field.get(dto)))
                );
            }
            System.out.println(field.get(dto));
        }
        productWithValue.setValues(attributeValueList);
        return productWithValue;
    }
}

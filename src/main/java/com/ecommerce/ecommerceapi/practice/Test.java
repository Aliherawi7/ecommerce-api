package com.ecommerce.ecommerceapi.practice;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IllegalAccessException {
        ProductWith40DataFields productWith40DataFields = new ProductWith40DataFields();
        productWith40DataFields.setName("pro-name");
        productWith40DataFields.setColor("red");
        productWith40DataFields.setCode("pro-code");
        productWith40DataFields.setCategory("pro-category");
        productWith40DataFields.setAvailability("pro-availability");
        productWith40DataFields.setTags("pro-tags");
        productWith40DataFields.setAddress("pro-address");
        productWith40DataFields.setBrandName("pro-brand");
        productWith40DataFields.setDescription("pro-description");
        productWith40DataFields.setCreatedAt(LocalDate.now());
        productWith40DataFields.setUpdated(LocalDate.now());
        productWith40DataFields.setAddress("pro-address");
        productWith40DataFields.setUpdated(LocalDate.now());
        productWith40DataFields.setModel("pro-model");
        productWith40DataFields.setDiscount(5);
        productWith40DataFields.setId(1);
        productWith40DataFields.setHeight("pro-height");
        productWith40DataFields.setRate(4);
        productWith40DataFields.setYear(2023);
        productWith40DataFields.setWeight(4.6);
        productWith40DataFields.setWidth("pr-width");
        productWith40DataFields.setRelatedProduct(List.of());
        productWith40DataFields.setPrice(BigDecimal.valueOf(1000));
        productWith40DataFields.setTax(23.4);
        productWith40DataFields.setIsEnabled(true);

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
                AttributeValue attributeValue = new AttributeValue();
                attributeValue.setName(productWithValue.getName());
                attributeValue.setValue(String.valueOf(field.get(dto)));

                attributeValueList.add(attributeValue);
            }
            System.out.println(field.getName() + " : "+field.get(dto));
        }
        productWithValue.setValues(attributeValueList);
        return productWithValue;
    }
}

package com.ecommerce.ecommerceapi.dto;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductAttributeValueRequestDTO {
    private String attributeCode;
    private String value;
}

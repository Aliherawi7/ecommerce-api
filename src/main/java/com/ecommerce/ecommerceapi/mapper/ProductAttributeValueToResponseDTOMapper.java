package com.ecommerce.ecommerceapi.mapper;

import com.ecommerce.ecommerceapi.dto.ProductAttributeValueResponseDTO;
import com.ecommerce.ecommerceapi.entity.ProductAttributeValue;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductAttributeValueToResponseDTOMapper implements Function<ProductAttributeValue, ProductAttributeValueResponseDTO> {
    @Override
    public ProductAttributeValueResponseDTO apply(ProductAttributeValue productAttributeValue) {
        return ProductAttributeValueResponseDTO.builder()
                .value(productAttributeValue.getValue())
                .id(productAttributeValue.getId())
                .type(productAttributeValue.getType())
                .attribute(productAttributeValue.getAttribute())
                .name(productAttributeValue.getName())
                .localeCode(productAttributeValue.getLocaleCode())
                .code(productAttributeValue.getCode())
                .build();
    }
}

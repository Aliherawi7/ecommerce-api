package com.ecommerce.ecommerceapi.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductAttribute(
        String context,
        String tempId,
        String tempType,
        String code,
        String type,
        List<String> configuration,
        String storageType,
        Integer position,
        Boolean translatable,
        Translations translations
) {
}
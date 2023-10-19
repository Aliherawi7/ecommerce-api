package com.ecommerce.ecommerceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductImage(
        @JsonProperty("@context")
        String context,
        @JsonProperty("@id")
        String imageId,
        Long id,
        String type,
        String path
) {
}

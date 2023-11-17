package com.ecommerce.ecommerceapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
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

package com.ecommerce.ecommerceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record ProductAttributeValueResponseDTO(
        @JsonProperty("@id")
        String tempId,
        @JsonProperty("@type")
        String tempType,
        Long id,
        String attribute,
        String localeCode,
        String name,
        String type,
        String code,
        String value
        ) {
}

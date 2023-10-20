package com.ecommerce.ecommerceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NotFoundErrorMessageDTO(
    @JsonProperty("@context")
    String context,
    @JsonProperty("@type")
    String type,
    @JsonProperty("hydra:title")
    String title,
    @JsonProperty("hydra:description")
    String description
) {
}

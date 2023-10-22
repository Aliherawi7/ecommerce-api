package com.ecommerce.ecommerceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Translations(
        @JsonProperty("en_US")
        Translation en_US
) {
}

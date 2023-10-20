package com.ecommerce.ecommerceapi.dto;

import lombok.Builder;

@Builder
public record AdminTokenRequestDTO(
        String email,
        String password
) {
}

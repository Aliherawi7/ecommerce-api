package com.ecommerce.ecommerceapi.dto;

import lombok.Builder;

@Builder
public record AdminTokenResponseDTO(
        String token,
        String adminUser
) {
}

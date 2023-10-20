package com.ecommerce.ecommerceapi.dto;

import lombok.Builder;

@Builder
public record AuthenticationTokenResponseDTO(
        String token,
        String customer
) {
}

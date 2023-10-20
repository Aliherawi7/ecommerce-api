package com.ecommerce.ecommerceapi.dto;

import lombok.Builder;

@Builder
public record AuthenticationTokenRequestDTO(
        String email,
        String password
) {
}

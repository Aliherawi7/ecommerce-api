package com.ecommerce.ecommerceapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record UnAuthorizedMessage(
        Integer code,
        String message
) {
}

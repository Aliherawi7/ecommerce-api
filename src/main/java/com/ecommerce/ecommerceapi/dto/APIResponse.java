package com.ecommerce.ecommerceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Builder
@Data
@AllArgsConstructor
public class APIResponse {
    private ZonedDateTime zonedDateTime;
    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
}

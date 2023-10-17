package com.ecommerce.ecommerceapi.dto;


import lombok.*;

@Builder
public record CustomerRegistrationDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        Boolean subscribedToNewsletter
) {

}

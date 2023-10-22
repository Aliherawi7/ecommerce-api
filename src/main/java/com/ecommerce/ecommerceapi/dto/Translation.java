package com.ecommerce.ecommerceapi.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Translation {
    private String name;
    private String slug;
    private String locale;
}


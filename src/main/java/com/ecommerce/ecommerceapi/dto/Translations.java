package com.ecommerce.ecommerceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Translations {
        @JsonProperty("en_US")
        Translation en_US;
}

package com.ecommerce.ecommerceapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductAttributeValueResponseDTO {
    @JsonProperty("@id")
    private String tempId;
    @JsonProperty("@type")
    private String tempType;
    private Long id;
    private String attribute;
    private String localeCode;
    private String name;
    private String type;
    private String code;
    private String value;
}

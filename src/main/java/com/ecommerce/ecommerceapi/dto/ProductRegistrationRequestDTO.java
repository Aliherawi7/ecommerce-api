package com.ecommerce.ecommerceapi.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRegistrationRequestDTO{
        private List<String> productTaxons;
        private List<String> images;
        private String mainTaxon;
        private String code;
        private List<String> options;
        private Boolean enabled;
        private Translations translations;
        private List<ProductAttributeValueRequestDTO> productAttributeValues;
}



package com.ecommerce.ecommerceapi.dto;

import java.util.List;

public record ProductRegistrationRequestDTO(
        List<String> productTaxons,
        List<String> images,
        String mainTaxon,
        String code,
        List<String> options,
        Boolean enabled,
        Translations translations

        ) {
}

record Translations(
        Translation en_US
) {
}
record Translation(
        String name,
        String slug,
        String locale
) {

}

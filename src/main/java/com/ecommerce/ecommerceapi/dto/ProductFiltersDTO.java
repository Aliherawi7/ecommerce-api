package com.ecommerce.ecommerceapi.dto;

import java.time.LocalDate;
import java.util.List;

public record ProductFiltersDTO(
        List<String> languages,
        List<String> versions,
        List<LocalDate> dates,
        int pageSize,
        int page
) {
}

package com.ecommerce.ecommerceapi.dto;

import java.time.LocalDate;

public record DateRangeDTO(
        LocalDate startDate,
        LocalDate endDate
) {
}

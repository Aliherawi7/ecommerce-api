package com.ecommerce.ecommerceapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ProductInfoDTO(
        @JsonProperty("@id")
        String productId,
        Long id,
        @JsonProperty("@type")
        String type,
        @JsonProperty("@context")
        String context,
        String name,
        List<String> productTaxons,
        String mainTaxon,
        List<String> reviews,
        Double averageRating,
        List<ProductImage> images,
        String code,
        List<String> variants,
        List<String> options,
        List<String> associations,
        String createdAt,
        String updatedAt,
        String shortDescription,
        String description,
        String slug,
        String defaultVariant
) {
}

/*
*
Product resource

Media type

application/ld+json
Controls Accept header.
Example Value
Schema
{
  "@id": "string",
  "@type": "string",
  "@context": "string",
  "productTaxons": [
    "string"
  ],
  "mainTaxon": "string",
  "reviews": [
    "string"
  ],
  "averageRating": 0,
  "images": [
    {
      "@context": "string",
      "@id": "string",
      "@type": "string",
      "id": 0,
      "type": "string",
      "path": "string"
    }
  ],
  "id": 0,
  "code": "string",
  "variants": [
    "string"
  ],
  "options": [
    "string"
  ],
  "associations": [
    "string"
  ],
  "createdAt": "2023-10-19T06:26:24.958Z",
  "updatedAt": "2023-10-19T06:26:24.958Z",
  "shortDescription": "string",
  "name": "string",
  "description": "string",
  "slug": "string",
  "defaultVariant": "string"
}
* */
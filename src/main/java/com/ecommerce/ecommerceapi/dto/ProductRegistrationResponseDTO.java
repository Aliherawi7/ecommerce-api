package com.ecommerce.ecommerceapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRegistrationResponseDTO{
    @JsonProperty("@id")
    private String productId;
    private Long id;
    @JsonProperty("@type")
    private String type;
    @JsonProperty("@context")
    private String context;
    private String name;
    private List<String> productTaxons;
    private String mainTaxon;
    private List<String> reviews;
    private Double averageRating;
    private List<String> images;
    private String code;
    private List<String> variants;
    private List<String> options;
    private List<String> associations;
    private String createdAt;
    private String updatedAt;
    private String shortDescription;
    private String description;
    private String slug;
    private String defaultVariant;
    private List<ProductAttributeValueResponseDTO> productAttributeValues;
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
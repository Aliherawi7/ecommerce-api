package com.ecommerce.ecommerceapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInfoDTO {
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
    private List<ProductImage> images;
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
    private List<ProductAttributeValueRequestDTO> productAttributeValues;
}


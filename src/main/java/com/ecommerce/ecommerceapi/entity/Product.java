package com.ecommerce.ecommerceapi.entity;


import com.ecommerce.ecommerceapi.dto.ProductImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    private String productId;
    @JsonProperty("@type")
    private String type;
    @JsonProperty("@context")
    private String context;
    private String name;
    private String mainTaxon;
    private Double averageRating;
    private String code;
    private String createdAt;
    private String updatedAt;
    private String shortDescription;
    private String description;
    private String slug;
    private String defaultVariant;
}

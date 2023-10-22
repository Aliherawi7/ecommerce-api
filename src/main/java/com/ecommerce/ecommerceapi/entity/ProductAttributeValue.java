package com.ecommerce.ecommerceapi.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductAttributeValue {
    @Id
    @SequenceGenerator(name = "attribute_value_id_generator",  sequenceName = "attribute_value_id_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attribute_value_id_generator")
    private Long id;
    private String attribute;
    private String productCode;
    private String localeCode;
    private String name;
    private String type;
    private String code;
    @Column(name = "`value`")
    private String value;
}

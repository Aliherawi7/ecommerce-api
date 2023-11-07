package com.ecommerce.ecommerceapi.repository;

import com.ecommerce.ecommerceapi.entity.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAttributeValueRepo extends JpaRepository<ProductAttributeValue, Long> {
    List<ProductAttributeValue> findAllByAttributeAndProductCode(String code, String productCode);
    List<ProductAttributeValue> findAllByProductCode(String productCode);
}

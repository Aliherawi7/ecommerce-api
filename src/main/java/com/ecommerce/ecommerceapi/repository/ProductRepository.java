package com.ecommerce.ecommerceapi.repository;

import com.ecommerce.ecommerceapi.entity.Product;
import com.ecommerce.ecommerceapi.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> {
    @Query("select p.name, p.code, p.type from Product p where p.id = :userId")
    Page<ProductProjection> findProjectedByProductId(Long userId, Pageable pageable);

}
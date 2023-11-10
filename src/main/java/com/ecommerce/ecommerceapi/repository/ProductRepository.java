package com.ecommerce.ecommerceapi.repository;

import com.ecommerce.ecommerceapi.entity.Product;
import com.ecommerce.ecommerceapi.projection.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, Long> {
    @Query("select new com.ecommerce.ecommerceapi.projection.ProductProjection(p.name, p.productId) from Product p where p.productId = :productId")
    List<ProductProjection> findProjectedByProductId(String productId, Pageable pageable);



}
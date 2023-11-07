package com.ecommerce.ecommerceapi.controller;


import com.ecommerce.ecommerceapi.dto.ProductInfoDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationRequestDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationResponseDTO;
import com.ecommerce.ecommerceapi.projection.ProductProjection;
import com.ecommerce.ecommerceapi.service.ProductAttributeValueService;
import com.ecommerce.ecommerceapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductAttributeValueService productAttributeValueService;

    @PostMapping
    public Mono<ProductRegistrationResponseDTO> addProduct(@RequestBody ProductRegistrationRequestDTO productRegistrationRequestDTO, @RequestHeader("Authorization")String token){
        return productService.addProduct(productRegistrationRequestDTO, token);
    }

    @GetMapping("/{code}")
    public Mono<ProductInfoDTO> getProduct(@PathVariable String code){
        return productService.getProduct(code);
    }
    @GetMapping("/")
    public List<ProductProjection> getProductProjection(@RequestParam(name = "productId") String productId,
                                                              @RequestParam(name = "pageNumber") Integer pageNumber,
                                                              @RequestParam(name = "pageSize") Integer pageSize){
        return productService.getProductProjection(productId, pageNumber, pageSize);
    }



}

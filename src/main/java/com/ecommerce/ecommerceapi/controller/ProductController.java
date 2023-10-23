package com.ecommerce.ecommerceapi.controller;


import com.ecommerce.ecommerceapi.dto.ProductInfoDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationRequestDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationResponseDTO;
import com.ecommerce.ecommerceapi.service.ProductAttributeValueService;
import com.ecommerce.ecommerceapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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



}

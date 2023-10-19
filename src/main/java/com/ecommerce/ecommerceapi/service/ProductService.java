package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.ProductInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final WebClient webClient;

    public Mono<ProductInfoDTO> getProduct(String productCode){
        return webClient.get()
                .uri(APIEndpoints.productInfo + productCode)
                .retrieve()
                .bodyToMono(ProductInfoDTO.class);
    }


}

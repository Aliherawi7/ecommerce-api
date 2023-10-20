package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.ProductInfoDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationRequestDTO;
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

    public Mono<ProductInfoDTO> addProduct(ProductRegistrationRequestDTO productRegistrationRequestDTO, String request) {
        return webClient.post().uri(APIEndpoints.ADD_PRODUCT)
                .header("Authorization", request)
                .bodyValue(productRegistrationRequestDTO)
                .retrieve()
                .bodyToMono(ProductInfoDTO.class)
                .doOnError(error -> {
                    //System.out.println("error message: ==> " + error.getMessage());
                });
    }
    public Mono<ProductInfoDTO> getProduct(String productCode) {
        return webClient.get()
                .uri(APIEndpoints.PRODUCT_INFO + productCode)
                .retrieve()
                .bodyToMono(ProductInfoDTO.class);
    }


}

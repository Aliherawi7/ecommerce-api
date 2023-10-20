package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.ProductInfoDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationRequestDTO;
import com.ecommerce.ecommerceapi.dto.UnAuthorizedMessage;
import com.ecommerce.ecommerceapi.exception.IllegalArgumentException;
import com.ecommerce.ecommerceapi.exception.InvalidInputException;
import com.ecommerce.ecommerceapi.exception.ResourceNotFoundException;
import com.ecommerce.ecommerceapi.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final WebClient webClient;

    public Mono<ProductInfoDTO> addProduct(ProductRegistrationRequestDTO productRegistrationRequestDTO, String request) {
        return webClient.post().uri(APIEndpoints.addProduct)
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
                .uri(APIEndpoints.productInfo + productCode)
                .retrieve()
                .bodyToMono(ProductInfoDTO.class);
    }


}

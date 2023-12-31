package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.APIResponse;
import com.ecommerce.ecommerceapi.dto.CustomerRegistrationDTO;
import com.ecommerce.ecommerceapi.exception.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final WebClient webClient;

    public Mono<APIResponse> registerCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        System.err.println(customerRegistrationDTO.toString());
        return webClient
                .post()
                .uri(APIEndpoints.SHOP_CUSTOMERS)
                .bodyValue(customerRegistrationDTO)
                .retrieve()
                .bodyToMono(APIResponse.class)
                .doOnError(error -> {
                    System.out.println(error.toString());
                    error.printStackTrace();
                    throw new IllegalArgumentException(error.getMessage());
                })
                .doOnSuccess(response -> APIResponse.builder()
                        .message("Customer successfully added")
                        .statusCode(HttpStatus.CREATED.value())
                        .httpStatus(HttpStatus.CREATED)
                        .zonedDateTime(ZonedDateTime.now())
                        .build());

    }
}

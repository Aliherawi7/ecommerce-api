package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.APIResponse;
import com.ecommerce.ecommerceapi.dto.CustomerRegistrationDTO;
import com.ecommerce.ecommerceapi.exception.IllegalArgumentException;
import com.ecommerce.ecommerceapi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final WebClient webClient;

    public Mono<Void> registerCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        System.err.println(customerRegistrationDTO.toString());

        Mono<Void> responseMono = webClient
                .post()
                .uri(APIEndpoints.baseUrl + APIEndpoints.customerShop)
                .bodyValue(customerRegistrationDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(error -> {
                    throw new IllegalArgumentException(error.getMessage());
                });


        log.info("res to string :=> " + responseMono);

        return responseMono;
    }
}

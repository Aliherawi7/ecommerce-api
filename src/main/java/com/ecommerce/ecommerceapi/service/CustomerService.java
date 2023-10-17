package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.APIResponse;
import com.ecommerce.ecommerceapi.dto.CustomerRegistrationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Mono<APIResponse> registerCustomer(CustomerRegistrationDTO customerRegistrationDTO) {
        System.err.println(customerRegistrationDTO.toString());

        Mono<APIResponse> responseMono = webClient
                .post()
                .uri(APIEndpoints.baseUrl + APIEndpoints.customerShop)
                .bodyValue(customerRegistrationDTO)
                .exchangeToMono(response -> {
                    HttpStatusCode status = response.statusCode();
                    if (status.is2xxSuccessful()) {
                        // Handle successful response
                        System.err.println(response.statusCode());
                        return response.bodyToMono(ClientResponse.class)
                                .map(res -> {
                                    return APIResponse.builder()
                                            .message("successfully registered")
                                            .statusCode(response.statusCode().value())
                                            .zonedDateTime(ZonedDateTime.now())
                                            .build();
                                });
                    } else {
                        // Handle error response
                        return response.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    // Handle error body and return appropriate error response
                                    System.err.println("Error body: " + errorBody);
                                    return Mono.error(new RuntimeException("API request failed with status: " + status));
                                });
                    }
                });


        log.info("res to string :=> " + responseMono.toString());
        log.info("res -> " + responseMono.log());
        return responseMono;
    }
}

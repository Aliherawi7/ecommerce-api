package com.ecommerce.ecommerceapi.service;


import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.AuthenticationTokenRequestDTO;
import com.ecommerce.ecommerceapi.dto.AuthenticationTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final WebClient webClient;
    public Mono<AuthenticationTokenResponseDTO> getAdminToken(AuthenticationTokenRequestDTO authenticationTokenRequestDTO){
        return webClient.post()
                .uri(APIEndpoints.ADMIN_TOKEN)
                .bodyValue(authenticationTokenRequestDTO)
                .retrieve()
                .bodyToMono(AuthenticationTokenResponseDTO.class);
    }
    public Mono<AuthenticationTokenResponseDTO> getShopCustomerToken(AuthenticationTokenRequestDTO authenticationTokenRequestDTO){
        return webClient.post()
                .uri(APIEndpoints.SHOP_CUSTOMER_TOKEN)
                .bodyValue(authenticationTokenRequestDTO)
                .retrieve()
                .bodyToMono(AuthenticationTokenResponseDTO.class);
    }

}

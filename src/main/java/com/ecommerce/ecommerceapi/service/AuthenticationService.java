package com.ecommerce.ecommerceapi.service;


import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.AdminTokenRequestDTO;
import com.ecommerce.ecommerceapi.dto.AdminTokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final WebClient webClient;
    public Mono<AdminTokenResponseDTO> getAdminToken(AdminTokenRequestDTO adminTokenRequestDTO){
        return webClient.post()
                .uri(APIEndpoints.ADMIN_TOKEN)
                .bodyValue(adminTokenRequestDTO)
                .retrieve()
                .bodyToMono(AdminTokenResponseDTO.class);
    }
}

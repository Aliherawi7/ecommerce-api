package com.ecommerce.ecommerceapi.controller;


import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.AdminTokenRequestDTO;
import com.ecommerce.ecommerceapi.dto.AdminTokenResponseDTO;
import com.ecommerce.ecommerceapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("admin/authentication-token")
    public Mono<AdminTokenResponseDTO> getAdminToken(@RequestBody AdminTokenRequestDTO adminTokenRequestDTO){
        return authenticationService.getAdminToken(adminTokenRequestDTO);
    }





}

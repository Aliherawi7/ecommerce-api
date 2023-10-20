package com.ecommerce.ecommerceapi.controller;


import com.ecommerce.ecommerceapi.dto.AuthenticationTokenRequestDTO;
import com.ecommerce.ecommerceapi.dto.AuthenticationTokenResponseDTO;
import com.ecommerce.ecommerceapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("admin/authentication-token")
    public Mono<AuthenticationTokenResponseDTO> getAdminToken(@RequestBody AuthenticationTokenRequestDTO authenticationTokenRequestDTO){
        return authenticationService.getAdminToken(authenticationTokenRequestDTO);
    }

    @PostMapping("shop-customer/authentication-token")
    public Mono<AuthenticationTokenResponseDTO> getShopCustomerToken(@RequestBody AuthenticationTokenRequestDTO authenticationTokenRequestDTO){
        return authenticationService.getShopCustomerToken(authenticationTokenRequestDTO);
    }


}

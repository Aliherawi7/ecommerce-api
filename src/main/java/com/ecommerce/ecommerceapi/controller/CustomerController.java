package com.ecommerce.ecommerceapi.controller;


import com.ecommerce.ecommerceapi.dto.APIResponse;
import com.ecommerce.ecommerceapi.dto.CustomerRegistrationDTO;
import com.ecommerce.ecommerceapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public Mono<Void> registerCustomer(@RequestBody CustomerRegistrationDTO customerRegistrationDTO){
        return customerService.registerCustomer(customerRegistrationDTO);
    }


}

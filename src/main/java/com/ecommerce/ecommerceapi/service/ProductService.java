package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.ProductAttributeValueResponseDTO;
import com.ecommerce.ecommerceapi.dto.ProductInfoDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationRequestDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final WebClient webClient;
    private final ProductAttributeValueService productAttributeValueService;

    public Mono<ProductRegistrationResponseDTO> addProduct(ProductRegistrationRequestDTO productRegistrationRequestDTO, String request) {
        /* first store all product attribute values in db */
        productRegistrationRequestDTO
                .getProductAttributeValues()
                .forEach(value -> {
                    System.out.println(value.getAttributeCode() +":"+value.getValue());
                    productAttributeValueService.addProductAttributeValue(value,productRegistrationRequestDTO.getCode(), request);
                });

        /* send the product information to the Sylius API */
        return webClient.post().uri(APIEndpoints.ADD_PRODUCT)
                .header("Authorization", request)
                .bodyValue(productRegistrationRequestDTO)
                .retrieve()
                .bodyToMono(ProductRegistrationResponseDTO.class)
                .doOnSuccess(productRegistrationResponseDTO -> {
                    // if the response is ok then get the all the attribute values from database
                    // and put into the product-Attributes-values list
                    List<ProductAttributeValueResponseDTO> values = new ArrayList<>();
                    productRegistrationRequestDTO.getProductAttributeValues().forEach(value -> {
                        values.addAll(productAttributeValueService
                                .getProductAttributeValuesResponseDTOByAttributeCodeAndProductCode(value.getAttributeCode(), productRegistrationRequestDTO.getCode()));
                    });
                    productRegistrationResponseDTO
                            .setProductAttributeValues(values);

                });
    }

    public Mono<ProductInfoDTO> getProduct(String productCode) {
        return webClient.get()
                .uri(APIEndpoints.PRODUCT_INFO + productCode)
                .retrieve()
                .bodyToMono(ProductInfoDTO.class);
    }


}

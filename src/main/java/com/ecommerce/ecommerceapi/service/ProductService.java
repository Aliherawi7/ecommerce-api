package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.ProductAttributeValueResponseDTO;
import com.ecommerce.ecommerceapi.dto.ProductInfoDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationRequestDTO;
import com.ecommerce.ecommerceapi.dto.ProductRegistrationResponseDTO;
import com.ecommerce.ecommerceapi.entity.Product;
import com.ecommerce.ecommerceapi.projection.ProductProjection;
import com.ecommerce.ecommerceapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final ProductRepository productRepository;

    public Mono<ProductRegistrationResponseDTO> addProduct(ProductRegistrationRequestDTO productRegistrationRequestDTO, String request) {
        /* first store all product attribute values in db */
        productRegistrationRequestDTO
                .getProductAttributeValues()
                .forEach(value -> {
                    System.out.println(value.getAttributeCode() + ":" + value.getValue());
                    productAttributeValueService.addProductAttributeValue(value, productRegistrationRequestDTO.getCode(), request);
                });

        // this is for test purpose
        productRepository.save(Product.builder()
                .productId(productRegistrationRequestDTO.getCode())
                .name(productRegistrationRequestDTO.getMainTaxon())
                .build());

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

    /*
    * in this scenario we use class based sql-projection instead of interface
    * and the reason behind this decision is that we cant set the list within interface
    * because its already null and if we do so we will get NullPointerException
    * */
    public List<ProductProjection> getProductProjection(String productId, Integer pageNumber, Integer pageSize) {
        List<ProductProjection> productProjections = productRepository
                .findProjectedByProductId(productId, PageRequest.of(pageNumber, pageSize));
        productProjections.forEach(pr -> {
            pr.setAttributeValueList(
                    productAttributeValueService.getProductAttributeValuesByProductId(pr.getCode())
            );
        });
        return productProjections;
    }


}

package com.ecommerce.ecommerceapi.service;


import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.ProductAttribute;
import com.ecommerce.ecommerceapi.dto.ProductAttributeValueRequestDTO;
import com.ecommerce.ecommerceapi.dto.ProductAttributeValueResponseDTO;
import com.ecommerce.ecommerceapi.entity.ProductAttributeValue;
import com.ecommerce.ecommerceapi.mapper.ProductAttributeValueToResponseDTOMapper;
import com.ecommerce.ecommerceapi.repository.ProductAttributeValueRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAttributeValueService {
    private final ProductAttributeValueRepo productAttributeValueRepo;
    private final WebClient webClient;
    private final ProductAttributeValueToResponseDTOMapper productAttributeValueToResponseDTOMapper;


    public void addProductAttributeValue(ProductAttributeValueRequestDTO productAttributeValueRequestDTO, String productCode, String request) {
        webClient.get()
                .uri(APIEndpoints.PRODUCT_ATTRIBUTE + "/" + productAttributeValueRequestDTO.getAttributeCode())
                .header("Authorization", request)
                .retrieve()
                .bodyToMono(ProductAttribute.class)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(productAttribute -> {
                    assert productAttribute.translations() != null;
                    ProductAttributeValue productAttributeValue = ProductAttributeValue.builder()
                            .name(productAttribute.translations().getEn_US().getName())
                            .attribute(productAttribute.code())
                            .type(productAttribute.type())
                            .productCode(productCode)
                            .localeCode(productAttribute.translations().getEn_US().getLocale())
                            .code(productAttribute.code())
                            .value(productAttributeValueRequestDTO.getValue())
                            .build();
                    System.out.println("saving the entity");
                     productAttributeValueRepo.save(productAttributeValue);
                    return Mono.empty();
                }).subscribe();
    }

    public List<ProductAttributeValue> getProductAttributeValuesByAttributeCodeAndProductCode(String attributeCode, String productCode){
        return productAttributeValueRepo.findAllByAttributeAndProductCode(attributeCode, productCode);
    }

    public List<ProductAttributeValueResponseDTO> getProductAttributeValuesResponseDTOByAttributeCodeAndProductCode(String attributeCode, String productCode){
        return productAttributeValueRepo
                .findAllByAttributeAndProductCode(attributeCode, productCode).stream().map(item -> {
                    ProductAttributeValueResponseDTO dto = productAttributeValueToResponseDTOMapper.apply(item);
                    dto.setTempId("/api/v2/admin/product-attributes/" + attributeCode);
                    return dto;
                }).toList();
    }

    public List<ProductAttributeValue> getProductAttributeValuesByProductId(String productId){
        return productAttributeValueRepo.findAllByProductCode(productId);
    }
}

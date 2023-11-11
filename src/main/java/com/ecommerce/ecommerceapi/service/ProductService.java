package com.ecommerce.ecommerceapi.service;

import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.dto.*;
import com.ecommerce.ecommerceapi.entity.Product;
import com.ecommerce.ecommerceapi.projection.BookProjection;
import com.ecommerce.ecommerceapi.projection.ProductProjection;
import com.ecommerce.ecommerceapi.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;




@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final WebClient webClient;
    private final ProductAttributeValueService productAttributeValueService;
    private final ProductRepository productRepository;
    private final EntityManager entityManager;

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



    /*
    *  to avoid a large number of if-else statements for constructing the query dynamically,
    *  you can use the Criteria API provided by JPA. The Criteria API allows you to build
    *  queries programmatically using a fluent and type-safe approach. Here's an example of
    *  how you can use the Criteria API to handle the scenario with optional filter parameters:
    *
    * */
    public List<Product> getProductsByFilters(List<String> languages, List<String> versions, List<LocalDate> dates, int page, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (languages != null && !languages.isEmpty()) {
            predicates.add(root.get("language").in(languages));
        }
        if (versions != null && !versions.isEmpty()) {
            predicates.add(root.get("version").in(versions));
        }
        if (dates != null && !dates.isEmpty()) {
            predicates.add(root.get("date").in(dates));
        }
        /*
        * The predicates.toArray(new Predicate[0]) part converts the list of
        * predicates into an array of Predicate objects. The toArray method is
        * called on the predicates list, and it takes an empty array of Predicate
        * as an argument. This is done to ensure that the resulting array has the correct type.
        * */
        query.where(predicates.toArray(new Predicate[0]));

        // Apply pagination
        int offset = (page - 1) * pageSize;
        entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(pageSize);
        return entityManager.createQuery(query).getResultList();
    }

    public List<BookProjection> getProductProjectionsFilters(List<String> languages, List<String> versions, List<DateRangeDTO> dateRanges ){
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT b.bookId AS bookId, b.title AS title, b.author AS author, b.date AS date ");
            queryBuilder.append("FROM Book b ");
            queryBuilder.append("JOIN b.language l ");
            queryBuilder.append("JOIN b.version v ");
            queryBuilder.append("JOIN b.bookDates bd ");
            queryBuilder.append("WHERE l.languageName IN (:languages) ");
            queryBuilder.append("AND v.versionName IN (:versions) ");

            if (dateRanges != null && !dateRanges.isEmpty()) {
                queryBuilder.append("AND (");

                for (int i = 0; i < dateRanges.size(); i++) {
                    queryBuilder.append("(bd.startDate >= :startDate").append(i).append(" AND bd.endDate <= :endDate").append(i).append(")");

                    if (i < dateRanges.size() - 1) {
                        queryBuilder.append(" OR ");
                    }
                }
                queryBuilder.append(")");
            }

            TypedQuery<BookProjection> query = entityManager.createQuery(queryBuilder.toString(), BookProjection.class);
            query.setParameter("languages", languages);
            query.setParameter("versions", versions);

            if (dateRanges != null && !dateRanges.isEmpty()) {
                for (int i = 0; i < dateRanges.size(); i++) {
                    DateRangeDTO dateRange = dateRanges.get(i);
                    query.setParameter("startDate" + i, dateRange.startDate());
                    query.setParameter("endDate" + i, dateRange.endDate());
                }
            }

            return query.getResultList();
    }
}

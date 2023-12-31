package com.ecommerce.ecommerceapi.configuration;


import com.ecommerce.ecommerceapi.constant.APIEndpoints;
import com.ecommerce.ecommerceapi.filter.ExchangeFilterFunctionConfigurationForWebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient(){
        return WebClient
                .builder()
                .baseUrl(APIEndpoints.BASE_URL)
                .filter(ExchangeFilterFunctionConfigurationForWebClient.errorHandler())
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}

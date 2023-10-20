package com.ecommerce.ecommerceapi.filter;

import com.ecommerce.ecommerceapi.dto.NotFoundErrorMessageDTO;
import com.ecommerce.ecommerceapi.dto.UnAuthorizedMessage;
import com.ecommerce.ecommerceapi.exception.InvalidInputException;
import com.ecommerce.ecommerceapi.exception.ResourceNotFoundException;
import com.ecommerce.ecommerceapi.exception.UnAuthorizedException;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

public class ExchangeFilterFunctionConfigurationForWebClient {
    public static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().value() == 401) {
                return clientResponse.bodyToMono(UnAuthorizedMessage.class)
                        .flatMap(errorBody -> Mono.error(new
                                UnAuthorizedException(errorBody.message())));
            } else if (clientResponse.statusCode().value() == 404) {
                return clientResponse.bodyToMono(NotFoundErrorMessageDTO.class)
                        .flatMap(errorBody -> Mono.error(new
                                ResourceNotFoundException(errorBody.description())));
            }else if(clientResponse.statusCode().value() == 400){
                return clientResponse.bodyToMono(NotFoundErrorMessageDTO.class)
                        .flatMap(errorBody -> Mono.error(new
                                InvalidInputException(errorBody.description())));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
}

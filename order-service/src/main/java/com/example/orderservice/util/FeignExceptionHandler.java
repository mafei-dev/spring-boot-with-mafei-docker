/*
package com.example.orderservice.util;

import com.example.orderservice.exception.UserNoActiveException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Component
public class FeignExceptionHandler implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        System.out.println("FeignExceptionHandler.decode");
        if (NOT_ACCEPTABLE.value() == response.status()) {
            return new UserNoActiveException("the user is not active", "");
        } else {
            return new Exception("some error " + response.status());
        }
    }
}
*/

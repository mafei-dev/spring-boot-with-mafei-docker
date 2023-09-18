package com.example.orderservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class HttpStatusException extends Exception {
    private final HttpStatus httpStatus;

    public boolean checkEqualsTo(HttpStatus... httpStatus) {
        for (HttpStatus status : httpStatus) {
            if (this.httpStatus.equals(status)) {
                return true;
            }
        }
        return false;
    }
}

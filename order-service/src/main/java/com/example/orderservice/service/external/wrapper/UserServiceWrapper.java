package com.example.orderservice.service.external.wrapper;

import com.example.orderservice.dto.UserDetailDto;
import com.example.orderservice.exception.UserNoActiveException;
import com.example.orderservice.service.external.UserServiceOF;
import feign.FeignException;
import feign.RetryableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
public class UserServiceWrapper implements UserServiceOF {

    private final HttpServletRequest request;
    private final UserServiceOF userServiceOF;

    @Override
    @CircuitBreaker(name = "getUserDetail")
    public ResponseEntity<UserDetailDto> getUserDetail(String username) {
        System.out.println("UserServiceWrapper.getUserDetail:" + this);
        System.out.println("username = " + username);
        System.out.println("request.getRequestURI() = " + request.getRequestURL());
        return this.userServiceOF.getUserDetail(username);
    }

    @Override
    @CircuitBreaker(name = "checkUserIsActive")
    @Retry(name = "checkUserIsActive", fallbackMethod = "checkUserIsActiveRetryFallbackMethod")
    public void checkUserIsActive(String username) {
        System.out.println("UserServiceWrapper.checkUserIsActive:" + this);
        System.out.println("username = " + username);
        try {
            //01>instance-1
            //02>instance-2
            //03>instance-3
            this.userServiceOF.checkUserIsActive(username);
        } catch (FeignException e) {
            e.printStackTrace();
            if (e instanceof RetryableException) {
                //timeout
                throw e;
            } else {
                if (e.status() == 406) {
                    throw new UserNoActiveException(username + " is not in active mode.", username);
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void checkUserIsActiveRetryFallbackMethod(String username, Exception exception) {
        exception.printStackTrace();
        System.out.println("UserServiceWrapper.checkUserIsActiveRetryFallbackMethod:" + this);
    }


}

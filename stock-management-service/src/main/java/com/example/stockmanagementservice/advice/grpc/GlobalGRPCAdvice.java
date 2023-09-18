package com.example.stockmanagementservice.advice.grpc;

import io.grpc.Status;
import io.grpc.StatusException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@GrpcAdvice
@Slf4j
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class GlobalGRPCAdvice {
    /**
     * common exception for all the services.
     *
     * @param exception
     * @return
     */
    @GrpcExceptionHandler({RuntimeException.class, Exception.class})
    public StatusException handleException(Exception exception) {
        log.error(exception.getMessage());
        Status status = Status.INTERNAL
                .withDescription(exception.getMessage())
                .withCause(exception);
        return status.asException();
    }
}

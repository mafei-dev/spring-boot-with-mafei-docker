package com.example.stockmanagementservice.advice.grpc;

import com.example.stockmanagementservice.exception.ItemNotFoundException;
import com.example.stockmanagementservice.exception.NoCartItemException;
import com.example.stockmanagementservice.exception.StockNotSufficientException;
import com.example.stockmanagementservice.grpc.GetCartItemDetailErrorResponse;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.protobuf.ProtoUtils;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

/**
 * all the item related exceptions will be handled here.
 */
@GrpcAdvice
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ItemGRPCAdvice {

    @GrpcExceptionHandler(ItemNotFoundException.class)
    public StatusException handleItemNotFoundException(ItemNotFoundException exception) {
        log.error("ItemNotFoundException:{}", exception.getMessage());
        Status status = Status.NOT_FOUND
                .withDescription(exception.getMessage())
                .withCause(exception);
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("itemCode", ASCII_STRING_MARSHALLER), exception.getItemCode());
        return status.asException(metadata);
    }

    @GrpcExceptionHandler(StockNotSufficientException.class)
    public StatusException handleStockNotSufficientException(StockNotSufficientException exception) {
        log.error("StockNotSufficientException:{}", exception.getMessage());
        Status status = Status.OUT_OF_RANGE
                .withDescription(exception.getMessage())
                .withCause(exception);
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("itemCode", ASCII_STRING_MARSHALLER), exception.getItemCode());
        return status.asException(metadata);
    }

    @GrpcExceptionHandler(NoCartItemException.class)
    public StatusException handleNoCartItemException(NoCartItemException exception) {
        log.error("NoCartItemException:{}", exception.getMessage());
        Status status = Status.OUT_OF_RANGE
                .withDescription(exception.getMessage())
                .withCause(exception);
        Metadata metadata = new Metadata();
        Metadata.Key<GetCartItemDetailErrorResponse> errorResponseKey =
                ProtoUtils.keyForProto(GetCartItemDetailErrorResponse.getDefaultInstance());
        metadata.put(
                errorResponseKey,
                GetCartItemDetailErrorResponse.newBuilder()
                        .setUsername(exception.getUsername())
                        .setMsg(exception.getMessage())
                        .setErrorCode(1)
                        .build()
        );
        return status.asException(metadata);
    }


}

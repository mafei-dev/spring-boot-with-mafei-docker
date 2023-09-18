package com.example.orderservice.exception;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public final class GRPCStatusExceptionUtil {

    private GRPCStatusExceptionUtil() {

    }

    public static boolean checkEqualsTo(StatusRuntimeException exception, Status... statuses) {
        for (Status status : statuses) {
            if (exception.getStatus().equals(status)) {
                return true;
            }
        }
        return false;
    }
}

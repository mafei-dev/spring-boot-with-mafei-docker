package com.example.orderservice.service.external;

import com.example.stockmanagementservice.grpc.CartServiceGrpc;
import com.example.stockmanagementservice.grpc.GetCartItemDetailRequest;
import com.example.stockmanagementservice.grpc.GetCartItemDetailResponse;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartServiceGRPC {
    @GrpcClient("stock-management-service")
    private CartServiceGrpc.CartServiceBlockingStub serviceBlockingStub;

    /**
     * call the stock management service as a GRPC-client.
     *
     * @param username
     * @return {@link GetCartItemDetailResponse}
     */
    public GetCartItemDetailResponse getItemDetails(String username) {
        log.debug("Trying to get the cart details from the GRPC server.");
        return this.serviceBlockingStub.getCartDetail(
                GetCartItemDetailRequest.newBuilder()
                        .setUsername(username)
                        .build()
        );
    }
}

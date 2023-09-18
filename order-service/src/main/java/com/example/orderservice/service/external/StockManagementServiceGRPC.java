package com.example.orderservice.service.external;

import com.example.stockmanagementservice.grpc.StockServiceGrpc;
import com.example.stockmanagementservice.grpc.UpdateItemRequest;
import com.example.stockmanagementservice.grpc.UpdateItemResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockManagementServiceGRPC {


    @GrpcClient("stock-management-service")
    private StockServiceGrpc.StockServiceBlockingStub stockServiceBlockingStub;


    public void updateItem(UpdateItemRequest request) {
        UpdateItemResponse response = this.stockServiceBlockingStub.updateItem(request);
        if (!response.getIsSuccess()) {
            throw new RuntimeException("update item process is failed.");
        }
    }
}

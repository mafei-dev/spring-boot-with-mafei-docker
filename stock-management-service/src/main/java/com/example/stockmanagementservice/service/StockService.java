package com.example.stockmanagementservice.service;

import com.example.stockmanagementservice.entity.ItemEntity;
import com.example.stockmanagementservice.exception.ItemNotFoundException;
import com.example.stockmanagementservice.exception.StockNotSufficientException;
import com.example.stockmanagementservice.grpc.*;
import com.example.stockmanagementservice.repository.ItemRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.transaction.annotation.Transactional;

@GrpcService
@AllArgsConstructor
public class StockService extends StockServiceGrpc.StockServiceImplBase {

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public void addItem(AddItemRequest request, StreamObserver<AddItemResponse> responseObserver) {
        //get the item repo and add the new item.
        ItemEntity itemEntity = this.itemRepository.save(
                ItemEntity.builder()
                        .itemName(request.getItemName())
                        .currentStock(request.getCurrentStock())
                        .unitPrice(request.getUnitPrice())
                        .build()
        );
        responseObserver.onNext(
                AddItemResponse
                        .newBuilder()
                        .setItemCode(itemEntity.getItemCode())
                        .build()
        );
        responseObserver.onCompleted();
    }

    /**
     * errors: ItemNotFoundException,StockNotSufficientException,RuntimeException
     *
     * @param request
     * @param responseObserver
     */
    @Override
    @Transactional
    public void updateItem(UpdateItemRequest request, StreamObserver<UpdateItemResponse> responseObserver) {
        //get the items from the request and update each and every item by given qty.
        int totalUpdatedItems = request.getItemListList()
                .stream()
                .map(itemDto -> {
                    //update the item.
                    int updatedCount = this.itemRepository.updateStock(
                            itemDto.getItemCode(),
                            request.getStockUpdateType().equals(StockUpdateType.INCREASED)
                                    ? /*INCREASED*/itemDto.getQty()
                                    : /*INCREASED*/itemDto.getQty() * -1
                    );
                    //check the updated value is less than zero. and if it is less than zero,
                    //the update can not be processed. (the stock QTY can not be less than zero.)
                    Boolean isLessThanZero = this.itemRepository.findById(itemDto.getItemCode())
                            .map(itemEntity -> {
                                //updated row (buffer area. because the transaction has not been committed yet.)
                                return itemEntity.getCurrentStock() > 0;
                            })
                            .orElseThrow(() -> {
                                //the item is not found.
                                return new ItemNotFoundException(
                                        "The item is not found. itemCode:" + itemDto.getItemCode(),
                                        itemDto.getItemCode()
                                );
                            });
                    if (isLessThanZero) {
                        return updatedCount;
                    } else {
                        //the process cannot be processed anymore.
                        throw new StockNotSufficientException(
                                "The item has no enough amount to be updated. itemCode:" + itemDto.getItemCode(),
                                itemDto.getItemCode()
                        );
                    }
                })
                .mapToInt(Integer::intValue)
                .sum();
        if (totalUpdatedItems == request.getItemListList().size()) {
            //all the items have been updated successfully.
            responseObserver.onNext(
                    UpdateItemResponse
                            .newBuilder()
                            .setIsSuccess(true)
                            .build()
            );
            responseObserver.onCompleted();
        } else {
            //some items are failed.
            throw new RuntimeException("stock update process is failed.");
        }
    }
}

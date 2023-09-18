package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.req.DeliveryDetailRequestBody;
import com.example.deliveryservice.dto.res.DeliveryDetailResponse;
import com.example.deliveryservice.entity.DeliveryDetailEntity;
import com.example.deliveryservice.exception.DeliveryDetailNotFoundException;
import com.example.deliveryservice.repository.DeliveryDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeliveryDetailService {

    private final DeliveryDetailRepository deliveryDetailRepository;

    /**
     * @param requestBody
     * @return deliveryDetailId
     */
    public Mono<Long> addNewDetail(DeliveryDetailRequestBody requestBody) {
        //save the entity on the db.
        return this.deliveryDetailRepository.save(
                        DeliveryDetailEntity.builder()
                                .deliveryDetailId(null)
                                .username(requestBody.username())
                                .address(requestBody.address())
                                .tel(requestBody.tel())
                                .email(requestBody.email())
                                .orderId(requestBody.orderId())
                                .transactionId(requestBody.transactionId())
                                .build()
                )
                .map(DeliveryDetailEntity::getDeliveryDetailId);
    }

    public Mono<DeliveryDetailResponse> getOrderDetail(String orderId) {
        return this.deliveryDetailRepository
                .findFirstByOrderId(orderId)
                .switchIfEmpty(Mono.error(
                        new DeliveryDetailNotFoundException(
                                "There is no delivery detail for the given order-id.",
                                orderId
                        )
                ))
                .map(deliveryDetailEntity -> DeliveryDetailResponse.builder()
                        .deliveryDetailId(deliveryDetailEntity.getDeliveryDetailId())
                        .username(deliveryDetailEntity.getUsername())
                        .address(deliveryDetailEntity.getAddress())
                        .tel(deliveryDetailEntity.getTel())
                        .email(deliveryDetailEntity.getEmail())
                        .orderId(deliveryDetailEntity.getOrderId())
                        .transactionId(deliveryDetailEntity.getTransactionId())
                        .build()
                );
    }
}

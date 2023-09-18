package com.example.deliveryservice.repository;

import com.example.deliveryservice.entity.DeliveryDetailEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface DeliveryDetailRepository extends ReactiveCrudRepository<DeliveryDetailEntity, Long> {
    Mono<DeliveryDetailEntity> findFirstByOrderId(String orderId);
}

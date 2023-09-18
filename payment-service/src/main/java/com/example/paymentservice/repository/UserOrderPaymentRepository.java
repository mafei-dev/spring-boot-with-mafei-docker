package com.example.paymentservice.repository;

import com.example.paymentservice.collection.UserOrderPayment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderPaymentRepository extends ReactiveMongoRepository<UserOrderPayment, String> {

}

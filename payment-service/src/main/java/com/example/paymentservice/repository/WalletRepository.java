package com.example.paymentservice.repository;

import com.example.paymentservice.collection.UserWallet;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WalletRepository extends ReactiveMongoRepository<UserWallet, String> {
    /**
     * @param username the username.
     * @param amount   the deduction value.
     * @return is updated or not. (if it was updated:1?0)
     */
    @Query("{'username' :  ?0}")
    @Update("{'$inc' : {'amount': ?1} }")
    Mono<Long> deductAmount(String username, Double amount);

    /**
     * @param username the username.
     * @return the document of the matching user.
     */
    Mono<UserWallet> findFirstByUsername(String username);
}

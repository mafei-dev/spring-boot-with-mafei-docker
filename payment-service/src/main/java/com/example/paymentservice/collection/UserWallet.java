package com.example.paymentservice.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @param _id      pk
 * @param username username
 * @param amount   current balance of the wallet
 */
@Document(value = "user_wallet")
public record UserWallet(@Id String _id, String username, Double amount) {
}

package com.example.stockmanagementservice.repository;

import com.example.stockmanagementservice.entity.CartEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, String> {
    Optional<CartEntity> findByUsername(String username);
}

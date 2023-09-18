package com.example.stockmanagementservice.repository;


import com.example.stockmanagementservice.entity.CartItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, String> {
    List<CartItemEntity> findAllByCartId(String cartId);

    void deleteAllByCartId(String cartId);
}

package com.example.stockmanagementservice.repository;

import com.example.stockmanagementservice.entity.ItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, String> {
    /**
     * update the current stock. [it can be + or -]
     *
     * @param itemCode the pk of the item that is going to be updated.
     * @param qty      the qty.
     */
    @Modifying
    @Query("update ItemEntity item set item.currentStock = (item.currentStock + :qty) where item.itemCode = :itemCode")
    int updateStock(String itemCode, int qty);
}

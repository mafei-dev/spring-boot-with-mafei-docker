package com.example.stockmanagementservice.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "item")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemEntity {
    /**
     * the item code of the item.
     * uuid:pk
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String itemCode;
    /**
     * the name of the item.
     */
    private String itemName;
    /**
     * the stock of the item currently has in the store.
     */
    private int currentStock;
    /**
     * the price for one unit.
     */
    private double unitPrice;
}

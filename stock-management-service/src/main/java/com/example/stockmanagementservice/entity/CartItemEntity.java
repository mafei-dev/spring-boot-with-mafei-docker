package com.example.stockmanagementservice.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cart_item")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String cartItemId;
    private String itemCode;
    private String itemName;
    private int qty;
    /**
     * the total will be calculated qty * unitPrice
     */
    private Double total;

    /**
     * FK from {@link CartEntity}
     */
    private String cartId;
}

package com.example.deliveryservice.dto.res;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDetailResponse {
    //data
    private Long deliveryDetailId;
    private String username;
    private String address;
    private String tel;
    private String email;
    private String orderId;
    private String transactionId;
}

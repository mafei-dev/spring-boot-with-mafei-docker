package com.example.orderservice.dto;


import lombok.*;

/**
 * the basic details of the user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDetailDto {
    private Long userId;
    private String username;
    private int isActive;
    private String email;
    private String tel;
    private String address;
}

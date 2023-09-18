package com.example.userservice.dto;

import lombok.*;

/**
 * the basic details of the user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDto {
    private Long userId;
    private String username;
    private int isActive;
    private String email;
    private String tel;
    private String address;
}

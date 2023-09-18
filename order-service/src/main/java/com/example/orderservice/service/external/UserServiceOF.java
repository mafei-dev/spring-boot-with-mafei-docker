package com.example.orderservice.service.external;

import com.example.orderservice.dto.UserDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceOF {
    @GetMapping(path = "/user")
    ResponseEntity<UserDetailDto> getUserDetail(@RequestParam("username") String username);

    @GetMapping(path = "/user/check")
    void checkUserIsActive(@RequestParam("username") String username);
}

package com.example.orderservice.service.external;

import com.example.orderservice.dto.UserDetailDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final RestTemplate restTemplate;

    public UserDetailDto getUserDetail(String username) {
        //call the user-service
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        ResponseEntity<UserDetailDto> userDetailDtoResponseEntity =
                restTemplate.getForEntity(
                        "http://USER-SERVICE/user?username={username}",
                        UserDetailDto.class,
                        params
                );
        log.debug("X-server-port : {}", userDetailDtoResponseEntity.getHeaders().get("X-server-port"));
        log.debug("UserDetailDto : {}", userDetailDtoResponseEntity.getBody());
        return userDetailDtoResponseEntity.getBody();
    }
}

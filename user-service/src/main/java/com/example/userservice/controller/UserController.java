package com.example.userservice.controller;

import com.example.userservice.dto.UserDetailDto;
import com.example.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stacksaga.common.communication.BinaryTransformerEntity;
import org.stacksaga.common.communication.SagaInstanceInfo;
import org.stacksaga.common.communication.ServiceInstanceInfo;
import org.stacksaga.exception.execution.BinaryFileSavingException;
import org.stacksaga.service.BinaryFileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final ServiceInstanceInfo serviceInstanceInfo;
    private final BinaryFileService binaryFileService;

    //add-new user (/user:POST)
    @PostMapping
    public ResponseEntity<?> addNewUser(@RequestBody UserDetailDto userDetailDto) {
        Long userId = this.userService.addNewUser(userDetailDto);
        return ResponseEntity.ok(Collections.singletonMap("user-id", userId));
    }

    //get user by username (/user:GET)

    @GetMapping
    public ResponseEntity<?> getUserByUsername(@RequestParam("username") String username,
                                               HttpServletRequest request,
                                               HttpServletResponse response
    ) throws InterruptedException {

        Enumeration<String> headerNames = request.getHeaderNames();

        log.info("X-Gateway-In time : {}", request.getHeader("X-Gateway-In"));
//        Thread.sleep(2_000);
        /*if (true) {
            return ResponseEntity.internalServerError().build();
        }*/

        int serverPort = request.getServerPort();
        response.addHeader("X-server-port", String.valueOf(serverPort));
        response.addHeader("X-Gateway-In", request.getHeader("X-Gateway-In"));
        Optional<UserDetailDto> userByUsername = this.userService.getUserByUsername(username);
        if (userByUsername.isPresent()) {
            //200
            return ResponseEntity.ok(userByUsername.get());
        } else {
            //404
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/check")
    public ResponseEntity<Void> checkUserIsActive(@RequestParam("username") String username
    ) throws InterruptedException {
//        Thread.sleep(6_000);
        System.out.println("checkUserIsActive : after 1s");
//        if (true) {
//            return ResponseEntity.internalServerError().build();
//        }
        Optional<UserDetailDto> userByUsername = this.userService.getUserByUsername(username);
        if (userByUsername.isPresent()) {
            //200
            if (userByUsername.get().getIsActive() == 1) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        } else {
            //404
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/file")
    public void file() {
        try {
            System.out.println("file saving started");
            SagaInstanceInfo sagaInstanceInfo = serviceInstanceInfo.getSagaInstanceInfo();
            File file = this.binaryFileService.saveAsFile(
                    BinaryTransformerEntity.builder()
                            .chunkExecutionUid(UUID.randomUUID().toString())
                            .serviceGroup(sagaInstanceInfo.getAppName())
                            .region(sagaInstanceInfo.getRegion())
                            .zone(sagaInstanceInfo.getZone())
                            .instanceId(sagaInstanceInfo.getInstanceId())
                            .build());
            System.out.println("file saving finished : " + file.toString());
        } catch (BinaryFileSavingException e) {
            throw new RuntimeException(e);
        }
    }
}

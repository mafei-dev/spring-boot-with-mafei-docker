package com.example.userservice.service;

import com.example.userservice.dto.UserDetailDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    //method-save new user
    public Long addNewUser(UserDetailDto userDetailDto) {
        UserEntity userEntity = UserEntity.builder()
                .userId(null)//auto
                .username(userDetailDto.getUsername())
                .isActive(userDetailDto.getIsActive())
                .email(userDetailDto.getEmail())
                .tel(userDetailDto.getTel())
                .address(userDetailDto.getAddress())
                .build();
        UserEntity savedUserEntity = this.userRepository.save(userEntity);
        return savedUserEntity.getUserId();
    }


    //method-get user by username.
    public Optional<UserDetailDto> getUserByUsername(String username) {
        Optional<UserEntity> user = this.userRepository.findByUsername(username);
        //transfer the entity to UserDetailDto
        return user
                .map(userEntity -> UserDetailDto.builder()
                        .userId(userEntity.getUserId())
                        .username(userEntity.getUsername())
                        .isActive(userEntity.getIsActive())
                        .email(userEntity.getEmail())
                        .tel(userEntity.getTel())
                        .address(userEntity.getAddress())
                        .build()
                );
    }


}

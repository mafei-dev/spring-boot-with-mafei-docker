package com.example.userservice.repo;

import com.example.userservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * @param username the username
     * @return {@link UserEntity}
     */
    Optional<UserEntity> findByUsername(String username);

}

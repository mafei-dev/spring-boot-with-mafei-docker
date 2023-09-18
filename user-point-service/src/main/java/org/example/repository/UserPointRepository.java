package org.example.repository;

import org.example.entity.UserPointEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPointRepository extends CrudRepository<UserPointEntity, Long> {
    //update the existing point amount.
    @Modifying
    @Query("update UserPointEntity userPoint set userPoint.points = ?1 where userPoint.username = ?2")
    int updateUserPoint(int updatedBy, String username);

    Optional<UserPointEntity> findByUsername(String username);

}

package org.example.repository;

import org.example.entity.UserPointLogDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPointLogDetailRepository extends CrudRepository<UserPointLogDetailEntity, Long> {
}

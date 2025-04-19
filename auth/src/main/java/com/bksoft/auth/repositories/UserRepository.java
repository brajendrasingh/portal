package com.bksoft.auth.repositories;

import com.bksoft.auth.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.email = :email WHERE u.id = :id")
    int updateEmailByUserId(@Param("id") Long id, @Param("email") String email);
}
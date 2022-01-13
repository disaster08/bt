package com.yu.bt.mybt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yu.bt.mybt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findNameById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<UserIssues> getById(Long id);

}

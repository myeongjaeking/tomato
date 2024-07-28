package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBySub(String sub);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name WHERE u.id = :userId")
    void updateUserNameById(Long userId, String name);

}
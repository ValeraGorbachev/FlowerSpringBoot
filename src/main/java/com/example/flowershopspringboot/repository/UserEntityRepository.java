package com.example.flowershopspringboot.repository;

import com.example.flowershopspringboot.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByLogin(String login);
}

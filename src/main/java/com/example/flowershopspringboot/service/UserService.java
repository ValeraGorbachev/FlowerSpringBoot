package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.entity.RoleEntity;
import com.example.flowershopspringboot.entity.UserEntity;
import com.example.flowershopspringboot.repository.RoleEntityRepository;
import com.example.flowershopspringboot.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public UserEntity findUserById(Integer id) {
        return userEntityRepository.findById(id)

                .orElseThrow(() -> new ExpressionException("error"));
    }

    public void create(UserEntity user) {
        userEntityRepository.save(user);
    }

    public List<UserEntity> readAll() {
        List<UserEntity> userList = userEntityRepository.findAll();
        return userList;
    }
}

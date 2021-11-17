package com.example.flowershopspringboot.dto;

import com.example.flowershopspringboot.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String login;
    private String password;
    private RoleEntity roleEntity;;
}

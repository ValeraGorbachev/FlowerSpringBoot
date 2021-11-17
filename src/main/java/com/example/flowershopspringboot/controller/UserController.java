package com.example.flowershopspringboot.controller;


import com.example.flowershopspringboot.dto.UserDto;
import com.example.flowershopspringboot.entity.UserEntity;
import com.example.flowershopspringboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private final ModelMapper modelMapper;


    private final UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @PostMapping(value = "/userCreate")
    public ResponseEntity<CollectionModel<UserEntity>> update(@RequestBody UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRoleEntity(userDto.getRoleEntity());
        userService.create(user);
        return new ResponseEntity(EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel()),
                HttpStatus.CREATED);
    }


    @GetMapping(value = "/userList")
    public ResponseEntity<CollectionModel<UserEntity>> getAllUsers() {
        List<UserEntity> userList = userService.readAll();
        userList.forEach(userEntity -> {
            userEntity.add(linkTo(methodOn(UserController.class).getUserById(userEntity.getId())).withSelfRel());
        });
        Link allDirectorsLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        return ok(CollectionModel.of(userList, allDirectorsLink));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserDto>> getUserById(@PathVariable(name = "id") Integer id) {
       UserEntity userEntity = userService.findUserById(id);
        UserDto userResponse = modelMapper.map(userEntity, UserDto.class);
        return new ResponseEntity<>(EntityModel.of(userResponse,
                linkTo(methodOn(UserController.class).getUserById(id)).withSelfRel()), HttpStatus.OK);
    }
}

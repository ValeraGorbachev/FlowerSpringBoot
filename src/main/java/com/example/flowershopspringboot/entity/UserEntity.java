package com.example.flowershopspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends RepresentationModel<UserEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String login;
    @Column
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

}

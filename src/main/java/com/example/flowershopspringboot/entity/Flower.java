package com.example.flowershopspringboot.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "flower")
@AllArgsConstructor
@NoArgsConstructor
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flower_id")
    private Integer flowerId;
    @Column(name = "flower_name")
    private String flowerName;
    @Column(name = "flower_price")
    private Integer flowerPrice;
    @Column(name = "flower_count")
    private Integer flowerCount;
}

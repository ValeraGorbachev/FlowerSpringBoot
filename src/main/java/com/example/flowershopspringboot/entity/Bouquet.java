package com.example.flowershopspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
@Table(name = "bouquet")
@AllArgsConstructor
@NoArgsConstructor
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bouquet_Id")
    private Integer bouquetId;
    @Column(name = "bouquet_Name")
    private String bouquetName;
    @Column(name = "bouquet_Price")
    private Integer bouquetPrice;

}


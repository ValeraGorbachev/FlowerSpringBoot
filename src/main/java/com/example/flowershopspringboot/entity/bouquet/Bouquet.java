package com.example.flowershopspringboot.entity.bouquet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "bouquet")
@AllArgsConstructor
@NoArgsConstructor
public class Bouquet extends RepresentationModel<Bouquet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bouquet_Id")
    private Integer bouquetId;
    @NotNull(message = "Name cannot be null")
    @Column(name = "bouquet_Name")
    private String bouquetName;
    @Column(name = "bouquet_Price")
    private Integer bouquetPrice;

    public Bouquet(String bouquetName, Integer bouquetPrice) {
        this.bouquetName = bouquetName;
        this.bouquetPrice = bouquetPrice;
    }
}


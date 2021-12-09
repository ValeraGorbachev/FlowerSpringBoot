package com.example.flowershopspringboot.entity.bouquet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(value = 10, message = "Price should not be less than 10")
    @Max(value = 100000, message = "Price should not be greater than 100000")
    private Integer bouquetPrice;

    public Bouquet(String bouquetName, Integer bouquetPrice) {
        this.bouquetName = bouquetName;
        this.bouquetPrice = bouquetPrice;
    }
}


package com.example.flowershopspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class FlowerDto {
    private Integer flowerId;
    private String flowerName;
    private Integer flowerPrice;
    private Integer flowerCount;
}

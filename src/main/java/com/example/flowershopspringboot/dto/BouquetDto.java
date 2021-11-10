package com.example.flowershopspringboot.dto;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BouquetDto {
    private String bouquetName;
    private Integer bouquetPrice;
}

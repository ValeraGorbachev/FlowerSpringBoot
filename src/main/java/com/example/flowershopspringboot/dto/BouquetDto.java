package com.example.flowershopspringboot.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BouquetDto {
    private Integer bouquetId;
    private String bouquetName;
    private Integer bouquetPrice;
}

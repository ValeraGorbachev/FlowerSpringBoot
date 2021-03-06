package com.example.flowershopspringboot.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BouquetDto {
    private Integer bouquetId;
    private String bouquetName;
    @Min(value = 10, message = "Price should not be less than 10")
    @Max(value = 100000, message = "Price should not be greater than 100000")
    private Integer bouquetPrice;

    }


package com.example.flowershopspringboot.entity.bouquet;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class BouquetSearchCriteria {
    private String bouquetName ="";
    @Min(value = 10, message = "Price should not be less than 10")
    @Max(value = 100000, message = "Price should not be greater than 100000")
    private Integer bouquetPrice;
}

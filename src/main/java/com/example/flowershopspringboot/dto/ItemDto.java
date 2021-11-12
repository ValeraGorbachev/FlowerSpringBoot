package com.example.flowershopspringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
    private Integer itemId;
    private String itemName;
    private Integer itemPrice;
}

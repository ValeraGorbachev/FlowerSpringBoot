package com.example.flowershopspringboot.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BouquetDto {
    private Integer bouquetId;
    private String bouquetName;
    private Integer bouquetPrice;

}

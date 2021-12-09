package com.example.flowershopspringboot.repositoryTest;

import com.example.flowershopspringboot.entity.bouquet.Bouquet;
import com.example.flowershopspringboot.repository.BouquetRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.expression.ExpressionException;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BouquetRepositoryTests {

    @Autowired
    private BouquetRepository bouquetRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void createBouquetTest() {
        Bouquet saveBouquet = bouquetRepository.save(new Bouquet("Roses", 789));

        assertThat(saveBouquet.getBouquetId()).isGreaterThan(0);
    }}

//    @Test
//    @Rollback(value = false)
//    @Order(3)
//    public void testUpdateBouquet() {
//        Bouquet bouquet = bouquetRepository.findById(35)
//                .orElseThrow(() -> new ExpressionException("error"));
//        bouquet.setBouquetName("Alisa");
//
//        bouquetRepository.save(bouquet);
//
//        Bouquet updatedBouquet = bouquetRepository.findById(35)
//                .orElseThrow(() -> new ExpressionException("error"));
//
//        assertThat(updatedBouquet.getBouquetName()).isEqualTo("Alisa");
//    }
//
//    @Test
//    @Rollback(value = false)
//    @Order(4)
//    public void testFindBouquetById() {
//        Bouquet bouquet = bouquetRepository.findById(35)
//                .orElseThrow(() -> new ExpressionException("error"));
//        assertThat(bouquet.getBouquetId()).isEqualTo(35);
//    }
//}

package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.entity.bouquet.Bouquet;
import com.example.flowershopspringboot.repository.BouquetRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class BouquetServiceImplTest {

    @Autowired
    private BouquetServiceImpl bouquetService;
    @MockBean
    private BouquetRepository bouquetRepository;

    @Test
    void create() {
        Bouquet bouquet = new Bouquet("Rose", 124);
        bouquetService.create(bouquet);

        ArgumentCaptor<Bouquet> bouquetArgumentCaptor =
                ArgumentCaptor.forClass(Bouquet.class);
        verify(bouquetRepository)
                .save(bouquetArgumentCaptor.capture());

        Bouquet captureBouquet = bouquetArgumentCaptor.getValue();
        assertThat(captureBouquet).isEqualTo(bouquet);
    }

    @Test
    void updateBouquet() {
    }

    @Test
    void delete() {

    }

    @Test
    void findBouquetById() {
    }
}
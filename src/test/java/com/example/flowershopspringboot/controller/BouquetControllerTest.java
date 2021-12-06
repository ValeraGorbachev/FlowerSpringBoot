package com.example.flowershopspringboot.controller;

import com.example.flowershopspringboot.entity.bouquet.Bouquet;
import com.example.flowershopspringboot.service.BouquetServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static java.nio.file.Files.delete;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(controllers = BouquetController.class)
@ActiveProfiles("test")
class BouquetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BouquetServiceImpl bouquetService;

    @Test
    void createBouquet()throws Exception{
    }

    @Test
    void getAllBouquets() {
    }

    @Test
    void testGetAllBouquets() {
    }

    @Test
    void updateBouquet() {
    }

    @Test
    void getBouquetById() throws Exception{
    }

    @Test
    void deleteBouquet() {
    }
}
//package com.example.flowershopspringboot.controller;
//
//import com.example.flowershopspringboot.dto.BouquetDto;
//import com.example.flowershopspringboot.entity.bouquet.Bouquet;
//import com.example.flowershopspringboot.service.BouquetServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.net.URI;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//class BouquetControllerTest {
//
//    private MockMvc mockMvc;
//    @Mock
//    BouquetServiceImpl bouquetService;
//    @InjectMocks
//    private BouquetController bouquetController;
//
//
//
//    @Before
//    public void init() {
//        mockMvc = MockMvcBuilders.standaloneSetup(bouquetController).build();
//    }
//
//    @Test
//    public void save() throws Exception {
//        Bouquet bouquet = new Bouquet("Red",1453);
//        mockMvc.perform(MockMvcRequestBuilders.post(new URI("http://localhost:3306/flower_shop"))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(bouquet))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//
//        verify(bouquetService, times(1)).create(bouquet);
//        verifyNoMoreInteractions(bouquetService);
//
//    }
//
//    static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

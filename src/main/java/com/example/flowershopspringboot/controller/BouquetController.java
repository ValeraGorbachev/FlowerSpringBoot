package com.example.flowershopspringboot.controller;

import com.example.flowershopspringboot.dto.BouquetDto;
import com.example.flowershopspringboot.entity.Bouquet;
import com.example.flowershopspringboot.repository.BouquetRepository;
import com.example.flowershopspringboot.service.BouquetServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class BouquetController {
    //
//    private final BouquetService bouquetService;
//
//    @Autowired
//    public BouquetController(BouquetService bouquetService) {
//        this.bouquetService = bouquetService;
//    }
//
    private final BouquetRepository bouquetRepository;

    @Autowired
    public BouquetController(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }


//    @PostMapping(value = "/bouquets")
//    public ResponseEntity<Bouquet> save(@RequestBody Bouquet bouquet) {
//        bouquetRepository.save(bouquet);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping(value = "/bouquetsUpdate")
    public ResponseEntity<Bouquet> update(@RequestBody BouquetDto bouquet) {
        Bouquet bouquet1= new Bouquet();
        bouquet1.setBouquetPrice(bouquet.getBouquetPrice());
        bouquet1.setBouquetName(bouquet.getBouquetName());
        bouquetRepository.saveAndFlush(bouquet1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/bouquetList")
    public ResponseEntity<List<Bouquet>> read() {
        final List<Bouquet> bouquetList = bouquetRepository.findAll();

        return bouquetList != null && !bouquetList.isEmpty()
                ? new ResponseEntity<>(bouquetList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(value = "/deleteBouquet")
    public ResponseEntity<?> delete(@RequestBody Bouquet bouquet) {
        bouquetRepository.delete(bouquet);
        final List<Bouquet> bouquetList = bouquetRepository.findAll();

        return bouquetList != null && !bouquetList.isEmpty()
                ? new ResponseEntity<>(bouquetList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

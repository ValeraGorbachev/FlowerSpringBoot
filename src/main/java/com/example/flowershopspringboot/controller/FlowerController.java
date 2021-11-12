package com.example.flowershopspringboot.controller;

import com.example.flowershopspringboot.dto.FlowerDto;
import com.example.flowershopspringboot.dto.ItemDto;
import com.example.flowershopspringboot.entity.Flower;
import com.example.flowershopspringboot.entity.Item;
import com.example.flowershopspringboot.repository.FlowerRepository;
import com.example.flowershopspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlowerController {
    private final FlowerRepository flowerRepository;

    @Autowired
    public FlowerController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @PostMapping(value = "/saveFlower")
    public ResponseEntity<Flower> update(@RequestBody FlowerDto flowerDto) {
       Flower flower = new Flower();
        flower.setFlowerId(flowerDto.getFlowerId());
        flower.setFlowerName(flowerDto.getFlowerName());
        flower.setFlowerPrice(flowerDto.getFlowerPrice());
        flower.setFlowerCount(flowerDto.getFlowerCount());
        flowerRepository.saveAndFlush(flower);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/flowerList")
    public ResponseEntity<List<Flower>> read() {
        final List<Flower> flowerList = flowerRepository.findAll();

        return flowerList != null && !flowerList.isEmpty()
                ? new ResponseEntity<>(flowerList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deleteFlower")
    public ResponseEntity<?> delete(@RequestBody  FlowerDto flowerDto) {
        Flower flower = new Flower();
        flower.setFlowerId(flowerDto.getFlowerId());
        flower.setFlowerName(flowerDto.getFlowerName());
        flower.setFlowerPrice(flowerDto.getFlowerPrice());
        flower.setFlowerCount(flowerDto.getFlowerCount());
        final List< Flower> flowerList =flowerRepository.findAll();

        return flowerList != null && !flowerList.isEmpty()
                ? new ResponseEntity<>(flowerList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

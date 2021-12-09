package com.example.flowershopspringboot.controller;

import com.example.flowershopspringboot.dto.ItemDto;
import com.example.flowershopspringboot.entity.Item;
import com.example.flowershopspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostMapping(value = "/itemSave")
    public ResponseEntity<Item> update(@RequestBody ItemDto itemDto) {
        Item item = new Item();
        item.setItemId(itemDto.getItemId());
        item.setItemName(itemDto.getItemName());
        item.setItemPrice(itemDto.getItemPrice());
        itemRepository.saveAndFlush(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/itemList")
    public ResponseEntity<List<Item>> read() {
        final List<Item> itemList = itemRepository.findAll();

        return itemList != null && !itemList.isEmpty()
                ? new ResponseEntity<>(itemList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deleteItem")
    public ResponseEntity<?> delete(@RequestBody  ItemDto itemDto) {
        Item item = new Item();
        item.setItemId(itemDto.getItemId());
        item.setItemName(itemDto.getItemName());
        item.setItemPrice(itemDto.getItemPrice());
        itemRepository.delete(item);
        final List<Item> itemList = itemRepository.findAll();

        return itemList != null && !itemList.isEmpty()
                ? new ResponseEntity<>(itemList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

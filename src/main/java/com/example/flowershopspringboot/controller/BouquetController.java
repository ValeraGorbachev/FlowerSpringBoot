package com.example.flowershopspringboot.controller;

import com.example.flowershopspringboot.dto.BouquetDto;
import com.example.flowershopspringboot.entity.bouquet.Bouquet;
import com.example.flowershopspringboot.entity.bouquet.BouquetPage;
import com.example.flowershopspringboot.entity.bouquet.BouquetSearchCriteria;
import com.example.flowershopspringboot.service.BouquetServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bouquet")
public class BouquetController {
    private static Logger logger = LoggerFactory.getLogger(Bouquet.class);

    @Autowired
    private final ModelMapper modelMapper;

    private final BouquetServiceImpl bouquetServiceImpl;

    @Autowired
    public BouquetController(ModelMapper modelMapper, BouquetServiceImpl bouquetServiceImpl) {
        this.modelMapper = modelMapper;
        this.bouquetServiceImpl = bouquetServiceImpl;
    }
//
//    @PostMapping()
//    public ResponseEntity<Bouquet> save(@RequestBody @Valid Bouquet bouquet) {
//        bouquetServiceImpl.create(bouquet);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping()
    public ResponseEntity<CollectionModel<Bouquet>> createBouquet(@Valid @RequestBody  BouquetDto bouquet) {
        logger.info("Create Bouquets");
        Bouquet bouquet1 = new Bouquet();
        bouquet1.setBouquetId(bouquet.getBouquetId());
        bouquet1.setBouquetPrice(bouquet.getBouquetPrice());
        bouquet1.setBouquetName(bouquet.getBouquetName());
        bouquetServiceImpl.create(bouquet1);
        return new ResponseEntity(EntityModel.of(bouquet1,
                linkTo(methodOn(BouquetController.class).getBouquetById(bouquet1.getBouquetId())).withSelfRel()),
                HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Page<Bouquet>> getAllBouquets(
            BouquetPage bouquetPage,
            @RequestBody BouquetSearchCriteria bouquetSearchCriteria) {
        logger.info("Show Bouquets");
        Page<Bouquet> list =  bouquetServiceImpl.getAllBouquets(bouquetPage,bouquetSearchCriteria);
        list.forEach(bouquet -> {
            bouquet.add(linkTo(methodOn(BouquetController.class).getBouquetById(bouquet.getBouquetId())).withSelfRel());
        });
        Link allDirectorsLink = linkTo(methodOn(BouquetController.class).getAllBouquets(bouquetPage,bouquetSearchCriteria)).withSelfRel();
        return new ResponseEntity<Page<Bouquet>>(list,  new HttpHeaders(),
                HttpStatus.OK);
    }
    @GetMapping("/bouquets")
    public ResponseEntity<List<Bouquet>> getAllBouquets(
            @PageableDefault(sort = {"bouquetName"}, direction = Sort.Direction.ASC) Pageable defaultPageable) {
        logger.info("Show Bouquets");
        List<Bouquet> list = (List<Bouquet>) bouquetServiceImpl.getAllBouquets(defaultPageable);
        return new ResponseEntity<List<Bouquet>>(list, new HttpHeaders(), HttpStatus.OK);
    }


//    @GetMapping("/bouquets")
//    public ResponseEntity<CollectionModel<Bouquet>> getAllBouquets(
//            BouquetPage bouquetPage,
//            @RequestBody BouquetSearchCriteria bouquetSearchCriteria) {
//        logger.info("Show Bouquets");
//        Page<Bouquet> list =  bouquetServiceImpl.getAllBouquets(bouquetPage,bouquetSearchCriteria);
//        list.forEach(bouquet -> {
//            bouquet.add(linkTo(methodOn(BouquetController.class).getBouquetById(bouquet.getBouquetId())).withSelfRel());
//        });
//        Link allDirectorsLink = linkTo(methodOn(BouquetController.class).getAllBouquets(bouquetPage,bouquetSearchCriteria)).withSelfRel();
//        return ok(CollectionModel.of(list, allDirectorsLink));
//    }


    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BouquetDto>> updateBouquet(@PathVariable(name = "id") Integer bouquetId, @RequestBody BouquetDto bouquetDto) {
        logger.info("UpdateBouquets");
        Bouquet bouquetRequest = modelMapper.map(bouquetDto, Bouquet.class);
        Bouquet bouquet = bouquetServiceImpl.updateBouquet(bouquetId, bouquetRequest);
        BouquetDto bouquetResponse = modelMapper.map(bouquet, BouquetDto.class);
        return new ResponseEntity<>(EntityModel.of(bouquetResponse,
                linkTo(methodOn(UserController.class).getUserById(bouquet.getBouquetId())).withSelfRel()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BouquetDto>> getBouquetById(@PathVariable(name = "id") Integer id) {
        logger.info("GetById Bouquets");
        Bouquet bouquet = bouquetServiceImpl.findBouquetById(id);
        BouquetDto bouquetResponse = modelMapper.map(bouquet, BouquetDto.class);
        return new ResponseEntity<>(EntityModel.of(bouquetResponse,
                linkTo(methodOn(BouquetController.class).getBouquetById(id)).withSelfRel()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBouquet(@PathVariable(name = "id") Integer id) {
        logger.info("DeleteBouquets");
        bouquetServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}



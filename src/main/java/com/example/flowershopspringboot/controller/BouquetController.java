package com.example.flowershopspringboot.controller;

import com.example.flowershopspringboot.dto.BouquetDto;
import com.example.flowershopspringboot.entity.Bouquet;
import com.example.flowershopspringboot.service.BouquetServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/bouquet")
public class BouquetController {
//
//    private final BouquetService bouquetService;
//
//    @Autowired
//    public BouquetController(BouquetService bouquetService) {
//        this.bouquetService = bouquetService;
//    }
////
//    private final BouquetRepository bouquetRepository;
//
//    @Autowired
//    public BouquetController(BouquetRepository bouquetRepository) {
//        this.bouquetRepository = bouquetRepository;
//    }

    @Autowired
   private final ModelMapper modelMapper;


    private final BouquetServiceImpl bouquetServiceImpl;

    @Autowired
    public BouquetController(ModelMapper modelMapper, BouquetServiceImpl bouquetServiceImpl) {
        this.modelMapper = modelMapper;
        this.bouquetServiceImpl = bouquetServiceImpl;
    }

//    @PostMapping(value = "/bouquets")
//    public ResponseEntity<Bouquet> save(@RequestBody Bouquet bouquet) {
//        bouquetRepository.save(bouquet);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping(value = "/bouquetsUpdate")
    public ResponseEntity<CollectionModel<Bouquet>> update(@RequestBody BouquetDto bouquet) {
        Bouquet bouquet1 = new Bouquet();
        bouquet1.setBouquetId(bouquet.getBouquetId());
        bouquet1.setBouquetPrice(bouquet.getBouquetPrice());
        bouquet1.setBouquetName(bouquet.getBouquetName());
        bouquetServiceImpl.create(bouquet1);
        return new ResponseEntity(EntityModel.of(bouquet,
                linkTo(methodOn(BouquetController.class).getBouquetById(bouquet1.getBouquetId())).withSelfRel()),
                HttpStatus.CREATED);


    }

    @GetMapping(value = "/bouquetList")
    public ResponseEntity<CollectionModel<Bouquet>> getAllBouquets() {
        List<Bouquet> bouquetList = bouquetServiceImpl.readAll();
        bouquetList.forEach(bouquet -> {
            bouquet.add(linkTo(methodOn(BouquetController.class).getBouquetById(bouquet.getBouquetId())).withSelfRel());
        });
        Link allDirectorsLink = linkTo(methodOn(BouquetController.class).getAllBouquets()).withSelfRel();
        return ok(CollectionModel.of(bouquetList, allDirectorsLink));
    }


//    @GetMapping(value = "/bouquetList")
//    public ResponseEntity<List<Bouquet>> read() {
//        final List<Bouquet> bouquetList = bouquetRepository.findAll();
//
//        return bouquetList != null && !bouquetList.isEmpty()
//                ? new ResponseEntity<>(bouquetList, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BouquetDto>> getBouquetById(@PathVariable(name = "id") Integer id) {
        Bouquet bouquet = bouquetServiceImpl.findBouquetById(id);
        BouquetDto bouquetResponse = modelMapper.map(bouquet, BouquetDto.class);
        return new ResponseEntity<>(EntityModel.of(bouquetResponse,
                linkTo(methodOn(BouquetController.class).getBouquetById(id)).withSelfRel()), HttpStatus.OK);
    }


//
//    public Bouquet getBouquetById(Integer id){
//        Bouquet bouquet1= new Bouquet();
//        bouquetServiceImpl.getBouquetById(id);
//        return bouquet1;
//    }}


    @DeleteMapping(value = "/deleteBouquet")
    public ResponseEntity<?> delete(@RequestBody BouquetDto bouquet) {
        Bouquet bouquet1= new Bouquet();
        bouquet1.setBouquetId(bouquet.getBouquetId());
        bouquet1.setBouquetPrice(bouquet.getBouquetPrice());
        bouquet1.setBouquetName(bouquet.getBouquetName());
       bouquetServiceImpl.delete(bouquet1);
        final List<Bouquet> bouquetList = bouquetServiceImpl.readAll();

        return bouquetList != null && !bouquetList.isEmpty()
                ? new ResponseEntity<>(bouquetList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



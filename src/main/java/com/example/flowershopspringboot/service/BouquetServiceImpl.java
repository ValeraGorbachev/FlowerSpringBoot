package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.controller.BouquetController;
import com.example.flowershopspringboot.entity.Bouquet;
import com.example.flowershopspringboot.repository.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.expression.ExpressionException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.ok;

@Service
public class BouquetServiceImpl implements BouquetService {

    private final BouquetRepository bouquetRepository;


    public BouquetServiceImpl(BouquetRepository bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    @Override
    public void create(Bouquet bouquet) {
        bouquetRepository.save(bouquet);
    }


    public List<Bouquet> getAllBouquets(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Bouquet> pagedResult = bouquetRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Bouquet>();
        }
    }







//    @Override
//    public List<Bouquet> readAll() {
//        List<Bouquet> bouquetList = bouquetRepository.findAll();
//        return bouquetList;
//    }


    @Override
    public Bouquet updateBouquet(Integer bouquetId, Bouquet bouquetRequest) {
        Bouquet bouquet = bouquetRepository.findById(bouquetId).orElseThrow(() -> new ExpressionException("error"));
        bouquetRequest.setBouquetId(bouquet.getBouquetId());
        if (bouquetRequest.getBouquetName() != null) {
            bouquet.setBouquetName(bouquetRequest.getBouquetName());
        }
        if (bouquetRequest.getBouquetPrice() != null) {
            bouquet.setBouquetPrice(bouquetRequest.getBouquetPrice());
        }


        return bouquetRepository.save(bouquet);
    }



    @Override
    public void delete(Integer id) {
        Bouquet bouquet= bouquetRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("error"));

        bouquetRepository.delete(bouquet);

    }

    @Override
    public Bouquet findBouquetById(Integer id) {
        return bouquetRepository.findById(id)

                .orElseThrow(() -> new ExpressionException("error"));
    }




    }


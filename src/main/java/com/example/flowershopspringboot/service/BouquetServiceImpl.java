package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.entity.Bouquet;
import com.example.flowershopspringboot.repository.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Bouquet> readAll() {
        List<Bouquet> bouquetList = bouquetRepository.findAll();
        return bouquetList;
    }


    public Bouquet updateBouquet(Integer bouquetId, Bouquet bouquetRequest) {
        Bouquet bouquet = bouquetRepository.findById(bouquetId).orElseThrow(() -> new ExpressionException("error"));
        bouquetRequest.setBouquetId(bouquet.getBouquetId());
        if(bouquetRequest.getBouquetName() != null) {
            bouquet.setBouquetName(bouquetRequest.getBouquetName());
        }
        if (bouquetRequest.getBouquetPrice() != null) {
            bouquet.setBouquetPrice(bouquetRequest.getBouquetPrice());
        }
        return bouquetRepository.save(bouquet);
    }

    @Override
    public boolean delete(Bouquet bouquet) {
        bouquet.setBouquetId(bouquet.getBouquetId());
        bouquet.setBouquetPrice(bouquet.getBouquetPrice());
        bouquet.setBouquetName(bouquet.getBouquetName());
        bouquetRepository.delete(bouquet);
       return true;
    }

//    public Bouquet getBouquetById(Integer id) {
//        Bouquet bouquet = new Bouquet();
//        bouquetRepository.findById(bouquet.getBouquetId());
//        return bouquet;
//    }

    public Bouquet findBouquetById(Integer id) {
        return bouquetRepository.findById(id)

                .orElseThrow(() -> new ExpressionException("error"));
    }

}
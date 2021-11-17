package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.entity.Bouquet;
import com.example.flowershopspringboot.repository.BouquetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BouquetService {


    void create(Bouquet bouquet);


    List<Bouquet> readAll();

    boolean update(Bouquet bouquet, int id);

    boolean delete(Bouquet bouquet);


}

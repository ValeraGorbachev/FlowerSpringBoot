package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.entity.bouquet.Bouquet;
import com.example.flowershopspringboot.entity.bouquet.BouquetPage;
import com.example.flowershopspringboot.entity.bouquet.BouquetSearchCriteria;
import org.springframework.data.domain.Page;

public interface BouquetService {

    void create(Bouquet bouquet);

    Bouquet updateBouquet(Integer id, Bouquet bouquet);

    void delete(Integer id);

    Bouquet findBouquetById(Integer id);

}

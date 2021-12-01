package com.example.flowershopspringboot.repository;

import com.example.flowershopspringboot.entity.Bouquet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetRepository extends PagingAndSortingRepository<Bouquet, Integer> {


}


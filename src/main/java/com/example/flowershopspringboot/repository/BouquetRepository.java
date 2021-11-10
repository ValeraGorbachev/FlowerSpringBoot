package com.example.flowershopspringboot.repository;

import com.example.flowershopspringboot.entity.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, Integer> {

}

package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.entity.bouquet.Bouquet;
import com.example.flowershopspringboot.entity.bouquet.BouquetPage;
import com.example.flowershopspringboot.entity.bouquet.BouquetSearchCriteria;
import com.example.flowershopspringboot.repository.BouquetCriteriaRepository;
import com.example.flowershopspringboot.repository.BouquetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BouquetServiceImpl implements BouquetService {

    private final BouquetRepository bouquetRepository;
    private final BouquetCriteriaRepository bouquetCriteriaRepository;

    public BouquetServiceImpl(BouquetRepository bouquetRepository, BouquetCriteriaRepository bouquetCriteriaRepository) {
        this.bouquetRepository = bouquetRepository;
        this.bouquetCriteriaRepository = bouquetCriteriaRepository;
    }

    public Page<Bouquet> getAllBouquets(
            BouquetPage bouquetPage,
            BouquetSearchCriteria bouquetSearchCriteria) {
        return bouquetCriteriaRepository.findAllWithFilters(bouquetPage, bouquetSearchCriteria);
    }

    @Override
    public void create(Bouquet bouquet) {
        bouquetRepository.save(bouquet);
    }

    public List<Bouquet> getAllBouquets(@PageableDefault(sort = {"bouquetName"}, direction = Sort.Direction.ASC) Pageable defaultPageable) {
        Page<Bouquet> pagedResult = bouquetRepository.findAll(defaultPageable);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Bouquet>();
        }
    }

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
        Bouquet bouquet = bouquetRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("error"));
        bouquetRepository.delete(bouquet);

    }

    @Override
    public Bouquet findBouquetById(Integer id) {
        return bouquetRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("error"));
    }

}


package com.example.flowershopspringboot.service;

import com.example.flowershopspringboot.entity.Bouquet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BouquetServiceImpl implements BouquetService{

    private static final Map<Integer, Bouquet> BOUQUET_REPOSITORY_MAP = new HashMap<>();
    @Override
    public void create(Bouquet bouquet) {
        BOUQUET_REPOSITORY_MAP.put(bouquet.getBouquetId(), bouquet);
    }

    @Override
    public List<Bouquet> readAll() {
        return new ArrayList<>(BOUQUET_REPOSITORY_MAP.values());
    }

    @Override

    public boolean update(Bouquet bouquet, int id) {
        if(BOUQUET_REPOSITORY_MAP.containsKey(id)){
            bouquet.setBouquetId(id);
            BOUQUET_REPOSITORY_MAP.put(bouquet.getBouquetId(), bouquet);
        return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return BOUQUET_REPOSITORY_MAP.remove(id) !=null;
    }
}

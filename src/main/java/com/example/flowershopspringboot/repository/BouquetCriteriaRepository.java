package com.example.flowershopspringboot.repository;

import com.example.flowershopspringboot.entity.bouquet.Bouquet;
import com.example.flowershopspringboot.entity.bouquet.BouquetPage;
import com.example.flowershopspringboot.entity.bouquet.BouquetSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BouquetCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;


    public BouquetCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Bouquet> findAllWithFilters(BouquetPage bouquetPage,
                                             BouquetSearchCriteria bouquetSearchCriteria){
        CriteriaQuery<Bouquet> criteriaQuery = criteriaBuilder.createQuery(Bouquet.class);
        Root<Bouquet> bouquetRoot = criteriaQuery.from(Bouquet.class);
        Predicate predicate = getPredicate(bouquetSearchCriteria, bouquetRoot);
        criteriaQuery.where(predicate);
        setOrder(bouquetPage, criteriaQuery, bouquetRoot);

        TypedQuery<Bouquet> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(bouquetPage.getPageNumber() * bouquetPage.getPageSize());
        typedQuery.setMaxResults(bouquetPage.getPageSize());

        Pageable pageable = getPageable(bouquetPage);

        long bouquetsCount = getBouquetsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, bouquetsCount);
    }

    private Predicate getPredicate(BouquetSearchCriteria bouquetSearchCriteria,
                                   Root<Bouquet> bouquetRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(bouquetSearchCriteria.getBouquetName())){
            predicates.add(
                    criteriaBuilder.like(bouquetRoot.get("bouquetName"),
                            "%" + bouquetSearchCriteria.getBouquetName() + "%")
            );
        }
        if(Objects.nonNull(bouquetSearchCriteria.getBouquetPrice())){
            predicates.add(
                    criteriaBuilder.equal(bouquetRoot.get("bouquetPrice"),
                             + bouquetSearchCriteria.getBouquetPrice() )
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(BouquetPage bouquetPage,
                          CriteriaQuery<Bouquet> criteriaQuery,
                          Root<Bouquet> bouquetRoot) {
        if (bouquetPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(bouquetRoot.get(bouquetPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(bouquetRoot.get(bouquetPage.getSortBy())));
        }
    }

    private Pageable getPageable(BouquetPage bouquetPage) {
        Sort sort = Sort.by(bouquetPage.getSortDirection(), bouquetPage.getSortBy());
        return PageRequest.of(bouquetPage.getPageNumber(), bouquetPage.getPageSize(), sort);
    }

    private long getBouquetsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Bouquet> countRoot = countQuery.from(Bouquet.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}


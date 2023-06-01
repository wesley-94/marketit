package com.marketit.orders.repository;

import com.marketit.orders.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

}

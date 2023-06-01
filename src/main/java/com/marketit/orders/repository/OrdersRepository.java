package com.marketit.orders.repository;


import com.marketit.orders.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersRepository {

    private final EntityManager em;

    public void save(Orders orders) {
        em.persist(orders);
    }

    public Orders findOne(Long id) {
        return em.find(Orders.class, id);
    }

    public List<Orders> findAll() {
        // JPQL
        String jpql = "select o from Orders o join o.member m";

        TypedQuery<Orders> query = em.createQuery(jpql, Orders.class);

        return query.getResultList();
    }

}

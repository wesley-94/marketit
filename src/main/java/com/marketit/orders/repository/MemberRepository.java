package com.marketit.orders.repository;

import com.marketit.orders.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

}

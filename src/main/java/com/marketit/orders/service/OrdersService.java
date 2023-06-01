package com.marketit.orders.service;

import com.marketit.orders.domain.*;
import com.marketit.orders.repository.ItemRepository;
import com.marketit.orders.repository.MemberRepository;
import com.marketit.orders.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrdersRepository ordersRepository;

    /**
     * 주문 접수
     */
    @Transactional
    public Long receive(Long memberId, Long itemId, int count) {
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 주문 상품 생성
        OrdersItem ordersItem = OrdersItem.createOrdersItem(item, item.getPrice(), count);

        // 주문 생성
        Orders orders = Orders.createOrder(member, ordersItem);

        // 주문 저장
        ordersRepository.save(orders);
        return orders.getId();
    }

    @Transactional
    public Long complete(Long ordersId) {
        // 엔티티 조회
        Orders orders = ordersRepository.findOne(ordersId);
        orders.setStatus(OrdersStatus.COMPLETED);

        // 저장
        ordersRepository.save(orders);
        return orders.getId();
    }

    public Orders findOne(Long ordersId) {
        return ordersRepository.findOne(ordersId);
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

}

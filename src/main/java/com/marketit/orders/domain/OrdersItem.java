package com.marketit.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders_item")
@Getter @Setter
public class OrdersItem {

    @Id @GeneratedValue
    @Column(name = "orders_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item; // 주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    @JsonIgnore
    private Orders orders;

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    // == 생성 메서드 == //
    public static OrdersItem createOrdersItem(Item item, int orderPrice, int count) {
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setItem(item);
        ordersItem.setOrderPrice(orderPrice);
        ordersItem.setCount(count);

        return ordersItem;
    }

}

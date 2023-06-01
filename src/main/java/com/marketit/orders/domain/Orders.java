package com.marketit.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Orders {

    // 주문 번호
    @Id @GeneratedValue
    @Column(name = "orders_id")
    private Long id;

    // 주문 시간
    private LocalDateTime orderDate;

    // 주문자 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    // 주문 상태 [RECEIVED, COMPLETED]
    @Enumerated(EnumType.STRING)
    private OrdersStatus status;

    // 주문 상품
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrdersItem> ordersItems = new ArrayList<>();

    // == 연관 관계 메서드 == //
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrdersItem(OrdersItem ordersItem) {
        ordersItems.add(ordersItem);
        ordersItem.setOrders(this);
    }

    // == 생성 메서드 == //
    public static Orders createOrder(Member member, OrdersItem... ordersItems) {
        Orders orders = new Orders();
        orders.setMember(member);
        for (OrdersItem ordersItem : ordersItems) {
            orders.addOrdersItem(ordersItem);
        }
        orders.setStatus(OrdersStatus.RECEIVED);
        orders.setOrderDate(LocalDateTime.now());
        return orders;
    }


}

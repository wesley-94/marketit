package com.marketit.orders.web;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrdersForm {
    // 회원 ID
    private Long memberId;

    // 상품 ID
    private Long itemId;

    // 주문 가격
    private int count;
}

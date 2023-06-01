package com.marketit.orders.web;

import com.marketit.orders.domain.Orders;
import com.marketit.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    /**
     * 1. 주문 접수 처리
     * (주문 저장 / 주문 상태 - "접수" 변경 / 회원 테이블의 데이터만 등록)
     */
    @PostMapping(value = "/receive")
    public void receive(@RequestBody OrdersForm form) {
        // parameter 값 추출
        Long memberId = form.getMemberId();
        Long itemId = form.getItemId();
        int count = form.getCount();

        ordersService.receive(memberId, itemId, count);
    }

    /**
     * 2. 주문 완료 처리
     * (주문 저장 / 주문 상태 - "완료" 변경 / 주문 테이블의 데이터도 등록)
     */
    @PostMapping(value = "/complete/{ordersId}")
    public void complete(@PathVariable Long ordersId) {
        ordersService.complete(ordersId);
    }

    /**
     * 3. 단일 주문 조회
     * (findOne)
     */
    @PostMapping("/orders/{ordersId}")
    public Orders selectOneOrder(@PathVariable Long ordersId) {
        return ordersService.findOne(ordersId);
    }

    /**
     * 4. 주문 목록 조회
     * (findAll)
     */
    @PostMapping("/orders/")
    public List<Orders> selectOrders() {
        return ordersService.findAll();
    }
}

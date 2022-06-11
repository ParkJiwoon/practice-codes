package com.example.bookobject.ch01;

/**
 * 소극장 구현 클래스
 * - ticketSeller: 소극장에는 판매원이 있음
 */
public class Theater {
    private final TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    /* 관람객 맞이 */
    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}

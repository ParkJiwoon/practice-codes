package com.example.bookobject.ch01;

/**
 * 매표소에서 초대장을 티켓으로 교환해주거나 티켓을 판매하는 판매원
 * - ticketOffice: 자기가 일하는 매표소
 */
public class TicketSeller {
    private final TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(Audience audience) {
        this.ticketOffice.sellTicketTo(audience);
    }
}

package com.example.bookobject.ch01;

/**
 * 소극장 구현 클래스
 * - ticketSeller: 소극장에는 판매원이 있음
 */
public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    /* 관람객 맞이 */
    public void enter(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            // 초대장이 있으면 티켓으로 교환
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            // 초대장이 없으면 현금으로 구매
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());

            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}

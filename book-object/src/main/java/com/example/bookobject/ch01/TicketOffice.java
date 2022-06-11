package com.example.bookobject.ch01;

import java.util.ArrayList;
import java.util.List;

/**
 * 매표소
 * - amount: 총 판매금액
 * - tickets: 관람객에게 판매할 티켓
 */
public class TicketOffice {
    private Long amount;
    private final List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, List<Ticket> tickets) {
        this.amount = amount;
        this.tickets.addAll(tickets);
    }

    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buy(getTicket()));
    }

    /* 편의상 그냥 첫번째 티켓 반환 */
    private Ticket getTicket() {
        return this.tickets.get(0);
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    private void plusAmount(Long amount) {
        this.amount += amount;
    }
}

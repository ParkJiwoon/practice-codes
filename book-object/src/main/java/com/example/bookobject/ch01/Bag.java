package com.example.bookobject.ch01;

/**
 * 관람객의 소지품
 * - amount: 현금 (티켓 구매 용도)
 * - invitation: 초대장
 * - ticket: 초대장 또는 현금으로 구한 티켓
 */
public class Bag {
    private Long amount;
    private Invitation invitation;
    private Ticket ticket;

    /* 초대장 없이 현금만 존재 */
    public Bag(Long amount) {
        this.amount = amount;
    }

    /* 초대장과 현금 존재 */
    public Bag(Long amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public Long hold(Ticket ticket) {
        if (hasInvitation()) {
            setTicket(ticket);
            return 0L;
        } else {
            setTicket(ticket);
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }

    private boolean hasInvitation() {
        return this.invitation != null;
    }

    public boolean hasTicket() {
        return this.ticket != null;
    }

    private void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    private void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }
}

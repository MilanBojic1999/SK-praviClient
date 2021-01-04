package sk.micorservise.kilejnt.model;

import java.util.ArrayList;
import java.util.List;

public class TicketList {

    private List<Ticket> list;

    public TicketList(List<Ticket> list) {
        this.list = list;
    }

    public TicketList() {
        list = new ArrayList<>();
    }

    public List<Ticket> getList() {
        return list;
    }

    public void setList(List<Ticket> list) {
        this.list = list;
    }
}

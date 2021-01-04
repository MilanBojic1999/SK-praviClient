package sk.micorservise.kilejnt.model;

import java.sql.Date;

public class Ticket {

    private long id;

    private long userId;

    private long flightId;

    private long cardNum;

    private double cena;

    private boolean permitted;

    private Date date;

    public Ticket(long id, long userId, long flightId, long cardNum, double cena, boolean permitted, Date date) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.cardNum = cardNum;
        this.cena = cena;
        this.permitted = permitted;
        this.date = date;
    }

    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getCardNum() {
        return cardNum;
    }

    public void setCardNum(long cardNum) {
        this.cardNum = cardNum;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public boolean isPermitted() {
        return permitted;
    }

    public void setPermitted(boolean permitted) {
        this.permitted = permitted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

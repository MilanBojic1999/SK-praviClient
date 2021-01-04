package sk.micorservise.kilejnt.model;

public class BuyingForm {

    private long flightId;
    private long cardId;

    public BuyingForm(long flightId, long cardId) {
        this.flightId = flightId;
        this.cardId = cardId;
    }

    public BuyingForm() {
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }
}

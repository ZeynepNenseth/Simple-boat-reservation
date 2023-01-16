package boatsale.no;

import java.util.UUID;

public class Boat {
    enum BoatType {
        DECK BOAT,
        CENTRE CONSOLE BOAT,
        CUDY BOAT,
        CATAMARAN;
    }

    UUID boatId = UUID.randomUUID();
    public int numberSeats;
    public int price;
    public BoatType boatType;
    public Boat (UUID boatId, int numberSeats, BoatType boatType, int price) {
        this.boatId = boatId;
        this.numberSeats = numberSeats;
        this.boatType = boatType;
        this.price = price;
    }
}

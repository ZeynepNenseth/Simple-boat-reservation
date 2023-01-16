package boatsale.no;

import java.time.LocalDate;

public class Reservation {
    public int numberPeople;
    public Boat boat;
    public LocalDate date;

    public Reservation (Boat boat, int numberPeople, LocalDate date) {
        this.boat = boat;
        this.numberPeople = numberPeople;
        this.date = date;
    }
}

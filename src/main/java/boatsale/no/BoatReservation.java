package boatsale.no;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.UUID;

public class BoatReservation {
    public ArrayList<Boat> boatList = new ArrayList<>();
    public ArrayList<Reservation> reservationList = new ArrayList<>();

    public void registerBoat (int numberSeats, Boat.BoatType boatType, int price) {
        Boat newBoat = new Boat (UUID.randomUUID(), numberSeats, boatType, price);
        boatList.add(newBoat);
    }

    public void registerReservation (UUID boatId, int numberPeople, LocalDate bookingDate) {
        Boat foundBoat = null;
        for (Boat boat : boatList) {
            if (boat.boatId.equals(boatId)) {
                foundBoat = boat;
            }
        }
        Reservation newReservation = new Reservation (foundBoat, numberPeople, bookingDate);
        reservationList.add(newReservation);
    }

    public int calculateProfitBoat (UUID boatId) {
        int profit = 0;
        for (Reservation reservations : reservationList) {
            if (reservations.boat.boatId == boatId) {
                profit += reservations.boat.price;
            }
        }
        return profit;
    }

    public ArrayList<Boat> sortBoatAfterProfit () {
        ArrayList<Boat> sortetBoatProfitList = boatList;
        Collections.sort(sortetBoatProfitList, new Comparator<Boat>() {
            @Override
            public int compare(Boat o1, Boat o2) {
                return calculateProfitBoat(o2.boatId) - calculateProfitBoat(o1.boatId); // default er ascending order, dette blir descending
            }
        });
        return sortetBoatProfitList;
    }

    public int calculateMaxNrUninterruptedReservation (UUID boatId) {
        int maxNumberDays = 0;
        for (int i = 0; i < reservationList.size(); i++) {
         //   if (reservationList. == boatId[i+])
        }
        return maxNumberDays;
    }

    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}

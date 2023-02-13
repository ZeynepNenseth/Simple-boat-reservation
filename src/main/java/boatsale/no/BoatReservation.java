package boatsale.no;

import java.time.Period;
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

    public boolean isAvailableByDate (UUID boatId, LocalDate reservationDate) {
        for (Reservation reservations : reservationList) {
            if (reservations.boat.boatId.equals(boatId) && reservations.date != reservationDate) {
                return true;
            }
        }
        return false;
    }

    public boolean isAvailableBySize (UUID boatId, int numberPeople) {
        for (Reservation reservations : reservationList) {
            if (reservations.boat.boatId.equals(boatId) && reservations.boat.numberSeats >= numberPeople) {
                return true;
            }
        }
        return false;
    }
    public void registerReservation (UUID boatId, int numberPeople, LocalDate reservationDate) throws Exception {
        Boat foundBoat = null;
        for (Boat boat : boatList) {
            if (boat.boatId.equals(boatId) && isAvailableByDate(boatId, reservationDate) && isAvailableBySize(boatId, numberPeople)) {
                foundBoat = boat;
            } else {
                throw new Exception ("This reservation is not valid! Please write correct information");
            }
        }
        Reservation newReservation = new Reservation (foundBoat, numberPeople, reservationDate);
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
            public int compare(Boat b1, Boat b2) {
                return calculateProfitBoat(b2.boatId) - calculateProfitBoat(b1.boatId); // default er ascending order, dette blir descending
            }
        });
        return sortetBoatProfitList;
    }

    public ArrayList<Reservation> sortReservationAfterDate (ArrayList<Reservation> sortedBookingDateList) {
        Collections.sort(sortedBookingDateList, new Comparator<Reservation>() {
            @Override
            public int compare(Reservation r1, Reservation r2) {
                return r1.date.compareTo(r2.date);
            }
        });
        return sortedBookingDateList;
    }
    static long getDayDiff (Period period) {
        long years = period.getYears();
        long months = period.getMonths();
        long days = period.getDays();

        return (years * 365) + (months * 30) + days;
    }

    public int calculateMaxUninterruptedReservationDays (UUID boatId) {
        ArrayList<Reservation> reservationsForBoats = new ArrayList<>();
        ArrayList<Integer> numberDays = new ArrayList<>();

        int maxNumberDays = 0;

        for (Reservation r : reservationList) {
            if (r.boat.boatId == boatId) {
                reservationsForBoats.add(r);
            }
        }

        reservationsForBoats = sortReservationAfterDate(reservationsForBoats);

        for (int i = 0; i < reservationsForBoats.size(); i++) {
            Period p = Period.between(reservationsForBoats.get(i).date, reservationsForBoats.get(i + 1).date);
            if (getDayDiff(p) == 1) {
                maxNumberDays++;
            } else {
                numberDays.add(maxNumberDays);
                maxNumberDays = 0;
            }
        }
        Collections.sort(numberDays);

        maxNumberDays = numberDays.get(numberDays.size() - 1);
        return maxNumberDays;
    }

    @Override
    public String toString() {
        return boatList.toString();
    }

    public static void main(String[] args) throws Exception {
        BoatReservation boatReservation = new BoatReservation();
        boatReservation.registerBoat(5, Boat.BoatType.CUDY_BOAT, 1_000);
        boatReservation.registerBoat(10, Boat.BoatType.CATAMARAN, 4_500);
        boatReservation.registerBoat(3, Boat.BoatType.DECK_BOAT, 800);
        boatReservation.registerReservation(UUID.randomUUID(), 3, LocalDate.of(2021,10,9));
        boatReservation.registerReservation(UUID.randomUUID(), 8, LocalDate.of(2021,10,12));
        boatReservation.registerReservation(UUID.randomUUID(), 5, LocalDate.of(2021,8,15));
    }
}

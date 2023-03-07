package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Statistics {
    private int currentIncome = 0;
    private int numberOfAvailableSeats = 81;
    private int numberOfPurchasedTickets = 0;

    public void purchase(Seat seat) {
        currentIncome += seat.getPrice();
        numberOfAvailableSeats--;
        numberOfPurchasedTickets++;
    }

    public void refund(Seat seat) {
        currentIncome -= seat.getPrice();
        numberOfAvailableSeats++;
        numberOfPurchasedTickets--;
    }

    @JsonProperty("current_income")
    public int getCurrentIncome() {
        return currentIncome;
    }

    @JsonProperty("number_of_available_seats")
    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    @JsonProperty("number_of_purchased_tickets")
    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

}

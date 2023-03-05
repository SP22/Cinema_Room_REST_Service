package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketDTO {
    private String token;
    private Seat seat;

    public TicketDTO(String token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public String getToken() {
        return token;
    }

    @JsonProperty("ticket")
    public Seat getSeat() {
        return seat;
    }
}

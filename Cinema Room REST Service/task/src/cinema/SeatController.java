package cinema;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {
    private SeatInfo theater = new SeatInfo(9, 9);

    @GetMapping("/seats")
    public SeatInfo getSeats() {
        return theater;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> bookTicket(@RequestBody SeatRequestBody seatRequestBody) {
        try {
            int row = seatRequestBody.getRow();
            int column = seatRequestBody.getColumn();
            if (theater.isAvailable(row, column)) {
                Seat seat = theater.getSeat(row, column);
                seat.setAvailable(false);
                return ResponseEntity.ok(seat);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error","The ticket has been already purchased!"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return ResponseEntity.badRequest().body(Map.of("error",
                    "The number of a row or a column is out of bounds!"));
        }
    }
}

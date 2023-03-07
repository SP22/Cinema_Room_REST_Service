package cinema;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {
    private SeatInfo theater = new SeatInfo(9, 9);
    @Autowired
    private Statistics statistics;

    @GetMapping("/seats")
    public SeatInfo getSeats() {
        return theater;
    }

    @PostMapping("/purchase")
    public ResponseEntity bookTicket(@RequestBody SeatRequestBody seatRequestBody) {
        try {
            int row = seatRequestBody.getRow();
            int column = seatRequestBody.getColumn();
            Map<String, Seat> reserved = theater.getReservedSeats();
            if (theater.isAvailable(row, column)) {
                Seat seat = theater.getSeat(row, column);
                seat.setAvailable(false);
                String token = UUID.randomUUID().toString();
                reserved.put(token, seat);
                TicketDTO ticket = new TicketDTO(token, seat);
                statistics.purchase(seat);
                return ResponseEntity.ok(ticket);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error","The ticket has been already purchased!"));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return ResponseEntity.badRequest().body(Map.of("error",
                    "The number of a row or a column is out of bounds!"));
        }
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody Token token) {
        String strToken = token.getToken().toString();
        if (!theater.getReservedSeats().containsKey(strToken)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Wrong token!"));
        }
        Seat seat = (Seat) theater.getReservedSeats().get(strToken);
        seat.setAvailable(true);
        theater.getReservedSeats().remove(strToken);
        statistics.refund(seat);
        return ResponseEntity.ok(Map.of("returned_ticket", seat));
    }

    @PostMapping("/stats")
    public ResponseEntity stats(@RequestParam(required = false) String password) {
        if ("super_secret".equals(password)) {
            return ResponseEntity.ok(statistics);
        } else {
            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }
}

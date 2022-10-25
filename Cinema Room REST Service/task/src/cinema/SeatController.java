package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {
    @GetMapping("/seats")
    public SeatInfo getSeats() {
        SeatInfo theater = new SeatInfo(9, 9);
        return theater;
    }
}

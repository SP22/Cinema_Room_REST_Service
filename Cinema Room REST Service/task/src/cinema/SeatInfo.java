package cinema;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SeatInfo {
    private int totalRows;
    private int totalColumns;
    private Seat[][] availableSeats;
    @JsonIgnore
    private Map<String, Seat> reservedSeats;

    public SeatInfo(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new Seat[totalRows][totalColumns];
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                int price = i <= 4 ? 10 : 8;
                availableSeats[i][j] = new Seat(i + 1, j + 1, price);
            }
        }
        this.reservedSeats = new HashMap<>();
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonProperty("available_seats")
    public Seat[] getAvailableSeats() {
        return Arrays.stream(availableSeats)
                .flatMap(Arrays::stream)
                .toList().toArray(Seat[]::new);
    }

    public boolean isAvailable(int row, int column) {
        return availableSeats[(row - 1)][column - 1].isAvailable();
    }

    public Seat getSeat(int row, int column) {
        return availableSeats[(row - 1)][column - 1];
    }

    public Map<String, Seat> getReservedSeats() {
        return reservedSeats;
    }
}

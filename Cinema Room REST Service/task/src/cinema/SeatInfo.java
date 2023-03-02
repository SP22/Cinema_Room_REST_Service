package cinema;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SeatInfo {
    private int total_rows;
    private int total_columns;
    private Seat[][] available_seats;

    public SeatInfo(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new Seat[total_rows][total_columns];
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                int price = i <= 4 ? 10 : 8;
                available_seats[i][j] = new Seat(i + 1, j + 1, price);
            }
        }
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public Seat[] getAvailable_seats() {
        return Arrays.stream(available_seats)
                .flatMap(s -> Arrays.stream(s))
                .toList().toArray(Seat[]::new);
    }

    public boolean isAvailable(int row, int column) {
        return available_seats[(row - 1)][column - 1].isAvailable();
    }

    public Seat getSeat(int row, int column) {
        return available_seats[(row - 1)][column - 1];
    }
}

package cinema;

public class SeatInfo {
    private int total_rows;
    private int total_columns;
    private Seat[] available_seats;

    public SeatInfo(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new Seat[total_rows * total_columns];
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                available_seats[i * total_rows + j] = new Seat(i + 1, j + 1);
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
        return available_seats;
    }

}

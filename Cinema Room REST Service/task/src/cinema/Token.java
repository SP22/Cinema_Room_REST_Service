package cinema;

import java.util.UUID;

public class Token {
    private UUID name;

    public Token() {
        this.name = UUID.randomUUID();
    }

    public UUID getToken() {
        return name;
    }

    public void setToken(UUID token) {
        this.name = token;
    }

}

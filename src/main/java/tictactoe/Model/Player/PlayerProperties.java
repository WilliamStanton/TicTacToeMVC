package tictactoe.Model.Player;

/**
 * Defines updated player options from Game Properties
 */
public class PlayerProperties {
    private final String symbol;
    private final String color;

    // No-arg Constructor
    public PlayerProperties() {
        symbol = "";
        color = "";
    }

    // Initialized Object
    public PlayerProperties(String symbol, String color) {
        this.symbol = symbol;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }
}

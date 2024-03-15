package tictactoe.Model.Player;

/**
 * Defines updated player options from Game Properties
 */
public class PlayerProperties {
    private final String symbol;
    private final int color;

    // No-arg Constructor
    public PlayerProperties() {
        symbol = "";
        color = 0;
    }

    // Initialized Object
    public PlayerProperties(String symbol, int color) {
        this.symbol = symbol;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getColor() {
        return color;
    }
}

package tictactoe.Model.Player;

/**
 * Defines a player
 */
public class Player {
    private String name;
    private String symbol;
    private String color;
    private boolean isTurn;
    private boolean configured;

    // Initialization Constructor
    public Player(String name, String symbol, String color, boolean isTurn) {
        this.isTurn = isTurn;
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.configured = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isConfigured() {
        return configured;
    }

    public void setConfigured(boolean configured) {
        this.configured = configured;
    }

    @Override
    public String toString() {
        return name;
    }
}

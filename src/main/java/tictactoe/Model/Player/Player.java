package tictactoe.Model.Player;

/**
 * Defines a player
 */
public class Player {
    private final String name;
    private String symbol;
    private String color;
    private boolean isTurn;
    private boolean configured;

    // Initialization Constructor
    public Player(String name, String symbol, boolean isTurn) {
        this.isTurn = isTurn;
        this.name = name;
        this.symbol = symbol;
        this.configured = false;
    }

    // Configured Player Constructor
    public Player(Player player, String symbol, String color) {
        this.name = player.getName();
        this.symbol = symbol;
        this.color = color;
        this.isTurn = player.isTurn();
        this.configured = true;
    }

    public String getName() {
        return name;
    }

    public String getNameNormalized() {
        return name.replaceAll(" ", "").toLowerCase();
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

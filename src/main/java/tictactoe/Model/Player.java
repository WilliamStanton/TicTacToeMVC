package tictactoe.Model;

/**
 * Defines a player
 */
public class Player {
    private final String name;
    private final String symbol;
    private boolean isTurn;

    public Player(String name, String symbol, boolean isTurn) {
        this.isTurn = isTurn;
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    @Override
    public String toString() {
        return name;
    }
}

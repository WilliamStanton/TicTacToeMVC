package tictactoe.Model.Player;

import lombok.Getter;
import lombok.Setter;

/**
 * Defines a player
 */
@Getter
@Setter
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

    @Override
    public String toString() {
        return name;
    }
}

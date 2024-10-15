package tictactoe.Model.Board;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tictactoe.Model.Player.Player;

/**
 * Defines a board spot
 */
@Getter
@Setter
public class BoardSpot {
    private final int id;
    private boolean winningSpot;
    private Player player;

    public BoardSpot(int id) {
        // Initialize player for spot
        this.id = id;
        this.winningSpot = false;
        this.player = new Player(null, null, "", false);
    }

    public boolean isTaken() {
        return player.getName() != null;
    }
}


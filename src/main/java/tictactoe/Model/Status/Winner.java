package tictactoe.Model.Status;

import lombok.Getter;
import tictactoe.Model.Board.BoardSpot;
import tictactoe.Model.Player.Player;

import java.util.ArrayList;

/**
 * Defines the winner
 */
@Getter
public class Winner {
    private Player player;
    private final boolean won;

    // Winner Found Constructor
    public Winner(ArrayList<BoardSpot> boardSpots, Player player) {
        // Dump winning spots into the board spot
        for (int i = 0; i < boardSpots.size(); i++) {
            boardSpots.get(i).setWinningSpot(true);
        }
        this.player = player;
        this.won = true;
    }

    // Winner not found constructor
    public Winner() {
        this.won = false;
    }
}

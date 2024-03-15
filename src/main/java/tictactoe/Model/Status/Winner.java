package tictactoe.Model.Status;

import tictactoe.Model.Board.BoardSpot;
import tictactoe.Model.Player.Player;

import java.util.ArrayList;

/**
 * Defines the winner
 */
public class Winner {
    private ArrayList<BoardSpot> boardSpots;
    private Player player;
    private final boolean won;

    // Winner Found Constructor
    public Winner(ArrayList<BoardSpot> boardSpots, Player player) {
        this.boardSpots = boardSpots;
        this.player = player;
        this.won = true;
    }

    // Winner not found constructor
    public Winner() {
        this.won = false;
    }

    public boolean isWon() {
        return won;
    }

    public ArrayList<BoardSpot> getBoardSpots() {
        return boardSpots;
    }

    public Player getPlayer() {
        return player;
    }
}

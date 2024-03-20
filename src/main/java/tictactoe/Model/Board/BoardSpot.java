package tictactoe.Model.Board;

import tictactoe.Model.Player.Player;

/**
 * Defines a board spot
 */
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

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isWinningSpot() {
        return winningSpot;
    }

    public void setWinningSpot(boolean winningSpot) {
        this.winningSpot = winningSpot;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}


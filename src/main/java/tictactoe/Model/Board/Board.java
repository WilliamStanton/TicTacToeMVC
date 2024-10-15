package tictactoe.Model.Board;

import lombok.Getter;

/**
 * Defines the board
 */
@Getter
public class Board {
    private final BoardSpot[][] boardSpots;

    // Initialize all board spots
    public Board() {
        this.boardSpots = new BoardSpot[3][3];
        initializeSpots();
    }

    /**
     * Initializes all board spots
     */
    public void initializeSpots() {
        int id = 1;
        for (int i = 0; i < boardSpots.length; i++) {
            for (int j = 0; j < boardSpots[0].length; j++) {
                boardSpots[i][j] = new BoardSpot(id);
                id++; // increment id
            }
        }
    }
}


package tictactoe.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import tictactoe.Exception.GameUpdateException;
import tictactoe.Model.Board.Board;
import tictactoe.Model.Board.BoardSpot;
import tictactoe.Model.Game;

/**
 * Creates a board and methods for updating the board
 */
@Service
@RequestScope
public class BoardService {
    private final Board board;
    private final PlayerService playerService;

    public BoardService(Board board, PlayerService playerService) {
        this.board = board;
        this.playerService = playerService;
    }

    /**
     * Updates the game by setting spot and setting next player
     * @param id the spot id
     */
    public void updateBoard(int id) throws GameUpdateException {
        try {
            // Get board spot
            var spot = getBoardSpot(id);

            // Check if spot already is taken
            if (spot.getPlayer().getName() != null) {
                throw new GameUpdateException("The chosen spot is already taken");
            }

            // Update board spot for current player
            spot.setPlayer(playerService.getNextPlayer());

            // Update next player
            playerService.nextTurn();
        } catch (Exception e) {
            throw new GameUpdateException("The chosen spot doesn't exist");
        }
    }

    /**
     * Gets the board spot object from the spot id
     * @param id the spot id
     * @return board spot object
     */
    public BoardSpot getBoardSpot(int id) {
        var bs = board.getBoardSpots();
        for (int i = 0; i < bs.length; i++) {
            for (int j = 0; j < bs[0].length; j++) {
                if (bs[i][j].getId() == id)
                    return bs[i][j];
            }
        }

        // No board spot found
        return null;
    }
}

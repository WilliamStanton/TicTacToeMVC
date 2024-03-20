package tictactoe.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import tictactoe.Model.Board.Board;
import tictactoe.Model.Board.BoardSpot;
import tictactoe.Model.Game;
import tictactoe.Model.Status.GameStatus;
import tictactoe.Model.Status.Winner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Provides methods for checking the game status
 */
@Service
@RequestScope
public class StatusService {
    private final Board board;
    private final Game game;

    public StatusService(Board board, Game game) {
        this.board = board;
        this.game = game;
    }

    /**
     * Checks if the game is still active (no winner/tie)
     * @return game status
     */
    public boolean isActive() {
        return !isWon() && !isTie();
    }

    /**
     * Checks if there has been a winner
     * @return true/false
     */
    public boolean isWon() {
        return gameStatus().getWinner().isWon();
    }

    /**
     * Checks if there has been a tie
     * @return true/false
     */
    public boolean isTie() {
        return gameStatus().isTie();
    }

    public void setCompleted(boolean completed) {
        game.setCompleted(completed);
    }

    public boolean isCompleted() {
        return game.isCompleted();
    }
    /**
     * Checks if there is a winner, tie, or no
     * @return true/false
     */
    public GameStatus gameStatus() {
        // Get board spots
        var bs = board.getBoardSpots();
        ArrayList<BoardSpot> winningBoardSpots = new ArrayList<>();

        // Check Vertical (each column)
        for (int row = 0; row < bs[0].length; row++) {
            boolean colFlag = true;
            var p = bs[0][row];
            for (int col = 0; col < bs.length; col++) {
                // Get first index in column to compare against
                if (!bs[col][row].getPlayer().equals(p.getPlayer())) {
                    colFlag = false;
                    winningBoardSpots = new ArrayList<>();
                    break; // terminate loop
                } else {
                    winningBoardSpots.add(bs[col][row]);
                }
            }
            if (colFlag) {
                winningBoardSpots.add(p);
                return new GameStatus(new Winner(winningBoardSpots, p.getPlayer()));
            }
        }

        // Check Horizontal (each row)
        for (int col = 0; col < bs.length; col++) {
            boolean colFlag = true;
            var p = bs[col][0];
            for (int row = 0; row < bs[0].length; row++) {
                // Get first index in row to compare against
                if (!bs[col][row].getPlayer().equals(p.getPlayer())) {
                    colFlag = false;
                    winningBoardSpots = new ArrayList<>();
                    break; // terminate loop
                } else {
                    winningBoardSpots.add(bs[col][row]);
                }
            }
            if (colFlag) {
                winningBoardSpots.add(p);
                return new GameStatus(new Winner(winningBoardSpots, p.getPlayer()));
            }
        }

        // Check vertical
        if (bs[0][0].getPlayer().equals(bs[1][1].getPlayer())
                && bs[1][1].getPlayer().equals(bs[2][2].getPlayer())) {
            return new GameStatus(new Winner(new ArrayList<BoardSpot> (Arrays.asList(bs[0][0], bs[1][1], bs[2][2])), bs[0][0].getPlayer()));
        }
        if (bs[2][0].getPlayer().equals(bs[1][1].getPlayer())
                && bs[1][1].getPlayer().equals(bs[0][2].getPlayer())) {
            return new GameStatus(new Winner(new ArrayList<BoardSpot>(Arrays.asList(bs[2][0], bs[1][1], bs[0][2])), bs[2][0].getPlayer()));
        }

        // Check tie
        boolean tie = true;
        for (int i = 0; i < bs.length; i++) {
            for (int j = 0; j < bs[0].length; j++) {
                // Check if any element is available
                if (bs[i][j].getPlayer().getName() == null) {
                    tie = false;
                    break;
                }
            }
        }

        // Return tie status
        if (tie)
            return new GameStatus(true);
        else
            return new GameStatus(false);
    }
}

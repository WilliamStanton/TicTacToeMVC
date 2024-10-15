package tictactoe.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import tictactoe.Model.Board.Board;
import tictactoe.Model.Player.Player;

/**
 * Contains methods for processing game requests
 */
@Component
@SessionScope
public class Game {
    private final Board board;
    private final Player p1;
    private final Player p2;
    @Getter @Setter
    private boolean computer;
    @Getter @Setter
    private boolean completed;

    // Initialize board
    public Game() {
        board = new Board();
        p1 = new Player("", "❌", "none", true);
        p2 = new Player("", "⭕", "none", false);
        computer = false;
    }

    /**
     * Resets game (players and board)
     */
    public void reset() {
        p1.setTurn(true);
        p2.setTurn(false);
        board.initializeSpots();
    }

    @Bean("board")
    @SessionScope
    public Board getBoard() {
        return board;
    }

    @Bean("p1")
    @SessionScope
    public Player getP1() {
        return p1;
    }

    @Bean("p2")
    @SessionScope
    public Player getP2() {
        return p2;
    }
}


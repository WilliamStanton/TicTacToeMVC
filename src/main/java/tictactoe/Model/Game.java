package tictactoe.Model;

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
    private boolean completed;

    // Initialize board
    public Game() {
        board = new Board();
        p1 = new Player("Player 1", "❌", "none", true);
        p2 = new Player("Player 2", "⭕", "none", false);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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


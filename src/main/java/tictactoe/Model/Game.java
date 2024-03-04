package tictactoe.Model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import tictactoe.Model.Board.Board;

@Component
@SessionScope
/**
 * Contains methods for processing game requests
 */
public class Game {
    private Board board;
    private Player p1;
    private Player p2;

    // Initialize board
    public Game() {
        board = new Board();
        p1 = new Player("Player 1", "O", true);
        p2 = new Player("Player 2", "X", false);
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


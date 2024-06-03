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
    private boolean computer;
    private boolean completed;

    // Initialize board
    public Game() {
        board = new Board();
        p1 = new Player("", "❌", "none", true);
        p2 = new Player("", "⭕", "none", false);
        computer = false;
    }

    public boolean isComputer() {
        return computer;
    }

    /**
     * Updates computer & changes name / symbol if true
     * @param computer computer status
     */
    public void setComputer(boolean computer) {
        this.computer = computer;
        if (computer) {
            p2.setName("Computer");
            p2.setSymbol("\uD83E\uDD16");
        }
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


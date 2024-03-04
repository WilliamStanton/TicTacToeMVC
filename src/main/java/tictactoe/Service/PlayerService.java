package tictactoe.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import tictactoe.Model.Player;

@Service
@RequestScope
public class PlayerService {
    private final Player p1;
    private final Player p2;

    public PlayerService(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Sets the player for the next turn
     */
    public void nextTurn() {
        // If P1 just went, make P2 next turn
        if (p1.isTurn()) {
            p1.setTurn(false);
            p2.setTurn(true);
        }
        // If P2 just went, make P1 next turn
        else {
            p1.setTurn(true);
            p2.setTurn(false);
        }
    }

    /**
     * Gets the player for the next turn
     * @return player for next turn
     */
    public Player getNextPlayer() {
        if (p1.isTurn()) {
            return p1;
        }
        else {
            return p2;
        }
    }
}

package tictactoe.Model.Status;

/**
 * Defines the game status
 */
public class GameStatus {
    private final Winner winner;
    private final boolean tie;

    // Winner found constructor
    public GameStatus(Winner winner) {
        this.winner = winner;
        this.tie = false;
    }

    // Tie Found Constructor
    public GameStatus(boolean tie) {
        this.winner = new Winner();
        this.tie = tie;
    }

    public Winner getWinner() {
        return winner;
    }

    public boolean isTie() {
        return tie;
    }
}

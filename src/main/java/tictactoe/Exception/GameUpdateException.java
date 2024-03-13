package tictactoe.Exception;

/**
 * Exception for game update errors
 */
public class GameUpdateException extends Throwable {
    public GameUpdateException(String message) {
        super(message);
    }
}

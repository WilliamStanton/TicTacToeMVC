package tictactoe.ExceptionController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tictactoe.Exception.GameUpdateException;
import tictactoe.Exception.PlayerException;

/**
 * Handles Game Exceptions
 */
@ControllerAdvice
public class GameExceptionHandler {
    /**
     * Handles Game Update Exceptions
     * @param gameUpdateException occurs when a game fails to update
     * @return the game update error
     */
    @ExceptionHandler(GameUpdateException.class)
    public String gameUpdateException(Model model, GameUpdateException gameUpdateException) {
        model.addAttribute("error", gameUpdateException.getMessage());

        return "error.html";
    }

    /**
     * Handles Player Exceptions
     * @param playerException occurs when there's a player error
     * @return the player error
     */
    @ExceptionHandler(PlayerException.class)
    public String playerException(Model model, PlayerException playerException) {
        model.addAttribute("error", playerException.getMessage());

        return "error.html";
    }
}

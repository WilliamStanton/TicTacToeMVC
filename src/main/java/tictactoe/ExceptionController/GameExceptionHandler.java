package tictactoe.ExceptionController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tictactoe.Exception.GameUpdateException;

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
}

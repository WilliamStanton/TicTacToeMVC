package tictactoe.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tictactoe.Exception.GameUpdateException;
import tictactoe.Model.Board.Board;
import tictactoe.Service.BoardService;
import tictactoe.Service.PlayerService;
import tictactoe.Service.StatusService;

/**
 * Controls the Game via MVC
 */
@Controller
public class GameController {
    private final BoardService boardService;
    private final Board board;
    private final PlayerService playerService;
    private final StatusService statusService;

    public GameController(BoardService boardService, Board board, PlayerService playerService, StatusService statusService) {
        this.boardService = boardService;
        this.board = board;
        this.playerService = playerService;
        this.statusService = statusService;
    }

    /**
     * Loads the game
     * @return game page
     * @throws GameUpdateException error updating game
     */
    @GetMapping("")
    public String home(Model model) throws GameUpdateException {
        // Add status
        model.addAttribute("player", playerService);
        model.addAttribute("status", statusService);
        model.addAttribute("board", board.getBoardSpots());

        // Load game
        return "game.html";
    }

    /**
     * Update the board
     * @param id board spot id
     * @return back to game page
     * @throws GameUpdateException error updating game
     */
    @PostMapping("")
    public String updateBoard(@RequestParam int id) throws GameUpdateException {
        // Ensure game is active
        if (statusService.isActive())
            boardService.updateBoard(id); // Update Board

        // Return back to game page
        return "redirect:/";
    }

    /**
     * Restarts the game
     * @param request HTTP Request
     * @param session HTTP Session
     * @return back to game page
     */
    @PostMapping("/restart")
    public String restartGame(HttpServletRequest request, HttpSession session) {
        // Invalidate Session
        session.invalidate();

        // Create new Session
        var newSession = request.getSession();

        // Return back to game page
        return "redirect:/";
    }
}

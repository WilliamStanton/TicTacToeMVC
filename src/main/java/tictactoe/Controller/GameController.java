package tictactoe.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tictactoe.Exception.GameUpdateException;
import tictactoe.Exception.PlayerException;
import tictactoe.Model.Board.Board;
import tictactoe.Model.Player.Player;
import tictactoe.Model.Player.PlayerProperties;
import tictactoe.Service.BoardService;
import tictactoe.Service.PlayerService;
import tictactoe.Service.StatusService;

/**
 * Controls the Game via MVC
 */
@Controller
public class GameController {
    private final Board board;
    private final BoardService boardService;
    private final PlayerService playerService;
    private final StatusService statusService;
    private PlayerProperties pp1;
    private PlayerProperties pp2;

    public GameController(Board board, BoardService boardService, PlayerService playerService, StatusService statusService, PlayerProperties pp1, PlayerProperties pp2) {
        this.board = board;
        this.boardService = boardService;
        this.playerService = playerService;
        this.statusService = statusService;
        this.pp1 = pp1;
        this.pp2 = pp2;
    }

    /**
     * Loads the game or game properties
     * @return game page
     * @throws GameUpdateException error updating game
     */
    @GetMapping("")
    public String game(Model model) throws GameUpdateException {
        // If game has been configured, start game
        if (playerService.playersConfigured()) {
            // Add services and board spots
            model.addAttribute("player", playerService);
            model.addAttribute("status", statusService);
            model.addAttribute("board", board.getBoardSpots());

            // Return game
            return "game.html";
        }

        // Else redirect user to configure player properties
        else {
            return "redirect:/config";
        }
    }

    /**
     * Sets the game properties
     * @return redirect back to game
     * @throws PlayerException error info
     */
    @PostMapping("/init")
    public String gameProperties(@RequestParam String symbol1, @RequestParam String symbol2, @RequestParam String color1, @RequestParam String color2) throws PlayerException {
        // Save Player Properties
        pp1 = new PlayerProperties(symbol1, color1);
        pp2 = new PlayerProperties(symbol2, color2);

        // Configure players
        playerService.configurePlayers(pp1, pp2);

        // Redirect to game
        return "redirect:/";
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
     * Configure Game Properties
     * @return game property configuration page
     */
    @GetMapping("/config")
    public String configGame(Model model) {
        // Add attributes
        model.addAttribute("player", playerService); // player service

        // Return game properties
        return "gameproperties.html";
    }

    /**
     * Restarts the game
     * @param request HTTP Request
     * @param session HTTP Session
     * @return back to game page
     */
    @PostMapping("/restart")
    public String restartGame(HttpServletRequest request, HttpSession session) throws PlayerException {
        // Save current session Player Properties
        PlayerProperties pp1t = new PlayerProperties(pp1.getSymbol(), pp1.getColor());
        PlayerProperties pp2t = new PlayerProperties(pp2.getSymbol(), pp2.getColor());

        // Invalidate Current Session
        session.invalidate();

        // Create new Session
        var newSession = request.getSession();

        // Configure players from prior session
        playerService.configurePlayers(pp1t, pp2t);

        // Send user to game
        return "redirect:/";
    }
}

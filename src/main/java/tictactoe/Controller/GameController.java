package tictactoe.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final Player p1;
    private final Player p2;

    public GameController(Board board, BoardService boardService, PlayerService playerService, StatusService statusService, Player p1, Player p2) {
        this.board = board;
        this.boardService = boardService;
        this.playerService = playerService;
        this.statusService = statusService;
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Loads the game or game properties
     * @return game page
     * @throws GameUpdateException error updating game
     */
    @GetMapping("")
    public String game(Model model) throws GameUpdateException {
        // Return game if players are configured
        if (playerService.playersConfigured()) {
            // Add services and board spots
            model.addAttribute("player", playerService);
            model.addAttribute("status", statusService);
            model.addAttribute("board", board.getBoardSpots());

            // Return game
            return "game.html";
        }

        // Else return game properties to configure players
        else {
            // Add attributes
            model.addAttribute("player", playerService); // player service

            // Return game properties
            return "gameproperties.html";
        }
    }

    /**
     * Sets the game properties
     * @return redirect back to game
     * @throws PlayerException error info
     */
    @PostMapping("/init")
    public String gameProperties(@RequestParam String symbol1, @RequestParam String symbol2, @RequestParam String color1, @RequestParam String color2) throws PlayerException {
        // Configure players
        playerService.configurePlayers(new PlayerProperties(symbol1, color1), new PlayerProperties(symbol2, color2));

        // Redirect to game
        return "redirect:/";
    }

    /**
     * Update the board
     * @param id board spot id
     * @return back to game page
     * @throws GameUpdateException error updating game
     */
    @PostMapping(path = "", params = {"id"})
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

    @GetMapping("test")
    public String test() {
        return "gameproperties.html";
    }
}

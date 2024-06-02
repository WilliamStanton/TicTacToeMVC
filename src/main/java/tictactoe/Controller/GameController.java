package tictactoe.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tictactoe.Exception.GameUpdateException;
import tictactoe.Exception.PlayerException;
import tictactoe.Model.Board.Board;
import tictactoe.Model.Player.Player;
import tictactoe.Model.Player.PlayerProperties;
import tictactoe.Repository.LeaderboardRepository;
import tictactoe.Service.BoardService;
import tictactoe.Service.LeaderboardService;
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
    private final LeaderboardService leaderboardService;
    private final LeaderboardRepository leaderboardRepository;
    private final Player p1;
    private final Player p2;

    public GameController(Board board, BoardService boardService, PlayerService playerService, StatusService statusService, LeaderboardService leaderboardService, LeaderboardRepository leaderboardRepository, Player p1, Player p2) {
        this.board = board;
        this.boardService = boardService;
        this.playerService = playerService;
        this.statusService = statusService;
        this.leaderboardService = leaderboardService;
        this.leaderboardRepository = leaderboardRepository;
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
        // If game has been configured, start game
        if (playerService.playersConfigured()) {
            // Add services and board spots
            model.addAttribute("player", playerService);
            model.addAttribute("status", statusService);
            model.addAttribute("board", board.getBoardSpots());

            // Check if won, and if so, has game not been completed? If not, update leaderboard
            if (statusService.isWon() && !statusService.isCompleted()) {
                // Get winner & handle win and loss
                var winningPlayer = statusService.gameStatus().getWinner().getPlayer();
                leaderboardRepository.addWin(statusService.gameStatus().getWinner().getPlayer()); // add win
                leaderboardRepository.addLoss(winningPlayer.equals(p1) ? p2 : p1); // add loss

                // Mark game completed
                statusService.setCompleted(true);
            }

            // Return game
            return "game.html";
        }

        // Else redirect user to configure player properties
        else {
            return "redirect:/settings";
        }
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
    @GetMapping("/settings")
    public String configGame(Model model) {
        // Add attributes
        model.addAttribute("player", playerService); // player service
        model.addAttribute("p1", p1);
        model.addAttribute("p2", p2);

        // Add Symbol List for players
        model.addAttribute("s1", playerService.symbolList(p1, new String[]{"❌", "❤️", "\uD83C\uDF89"})); // Player 1
        model.addAttribute("s2", playerService.symbolList(p2, new String[]{"⭕", "\uD83D\uDC80", "\uD83D\uDC51"})); // Player 2

        // Add Color List for players
        model.addAttribute("c1", playerService.colorList(p1, new String[]{"none", "blue", "yellow", "tan"})); // Player 1
        model.addAttribute("c2", playerService.colorList(p2, new String[]{"none", "purple", "green", "brown"})); // Player 2

        // Return game properties
        return "gameproperties.html";
    }

    /**
     * Displays the leaderboard
     * @return the leaderboard
     */
    @GetMapping("/leaderboard")
    public String test(Model model) {
        // Add attributes
        model.addAttribute("leaderboard", leaderboardService.getLeaderboard());

        // Send user to leaderboard
        return "leaderboard.html";
    }

    /**
     * Sets the game properties
     * @return redirect back to game
     * @throws PlayerException error info
     */
    @PostMapping("/settings")
    public String gameProperties(@RequestParam String name1, @RequestParam String name2, @RequestParam String symbol1, @RequestParam String symbol2, @RequestParam String color1, @RequestParam String color2) throws PlayerException {
        // Configure players
        playerService.configurePlayers(new PlayerProperties(name1, symbol1, color1), new PlayerProperties(name2, symbol2, color2));

        // Redirect to game
        return "redirect:/";
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
        var pp1t = new PlayerProperties(p1.getName(), p1.getSymbol(), p1.getColor());
        var pp2t = new PlayerProperties(p2.getName(), p2.getSymbol(), p2.getColor());

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

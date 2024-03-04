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

    @GetMapping("")
    public String home(Model model) throws GameUpdateException {
        // Add status
        model.addAttribute("player", playerService);
        model.addAttribute("status", statusService);
        model.addAttribute("board", board.getBoardSpots());
        return "game.html";
    }

    @PostMapping("/")
    public String updateBoard(@RequestParam int id) throws GameUpdateException {
        boardService.updateBoard(id);

        return "redirect:/";
    }

    @PostMapping("/restart")
    public String restartGame(HttpServletRequest request, HttpSession session) {
        // Invalidate Session
        session.invalidate();

        // Create new Session
        var newSession = request.getSession(); // create session

        // Return back to game
        return "redirect:/";
    }
}

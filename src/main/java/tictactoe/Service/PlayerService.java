package tictactoe.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import tictactoe.Exception.PlayerException;
import tictactoe.Model.Player.Player;
import tictactoe.Model.Player.PlayerProperties;

import java.util.ArrayList;

/**
 * Contains two players and methods for alternating turns
 */
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

    /**
     * Updates the players based off of the game properties configuration
     * @param pp1 Player Properties for p1
     * @param pp2 Player Properties for p2
     */
    public void configurePlayers(PlayerProperties pp1, PlayerProperties pp2) throws PlayerException {
        // Ensure name isn't over 25 chars
        if (pp1.getName().length() > 20)
            throw new PlayerException("Player name is too long (" + pp1.getName().length() + "/" + "20 characters)");
        else if (pp2.getName().length() > 20)
            throw new PlayerException("Player name is too long (" + pp2.getName().length() + "/" + "20 characters)");

        // Configure Player 1
        p1.setName(pp1.getName());
        p1.setColor(pp1.getColor());
        p1.setSymbol(pp1.getSymbol());
        p1.setConfigured(true);

        // Configure Player 2
        p2.setName(pp2.getName());
        p2.setColor(pp2.getColor());
        p2.setSymbol(pp2.getSymbol());
        p2.setConfigured(true);
    }

    /**
     * Builds the symbol list in the proper order (selected first, then the rest)
     * @param player player to build symbol list for
     * @param symbolList the symbol list
     * @return symbol list in proper order
     */
    public ArrayList<String> symbolList(Player player, String[] symbolList) {
        // Initialize symbol list
        ArrayList<String> list = new ArrayList<>();

        // Add selected symbol, then add all others
        list.add(player.getSymbol());
        for (int i = 0; i < symbolList.length; i++) {
            if (!symbolList[i].equals(player.getSymbol()))
                list.add(symbolList[i]);
        }

        // Return built symbol list
        return list;
    }

    /**
     * Builds the color list in the proper order (selected first, then the rest)
     * @param player player to build color list for
     * @param colorList the color list
     * @return color list in proper order
     */
    public ArrayList<String> colorList(Player player, String[] colorList) {
        // Initialize symbol list
        ArrayList<String> list = new ArrayList<>();

        // Add selected symbol, then add all others
        list.add(player.getColor());
        for (int i = 0; i < colorList.length; i++) {
            if (!colorList[i].equals(player.getColor()))
                list.add(colorList[i]);
        }

        // Return built symbol list
        return list;
    }

    /**
     * Checks to ensure both players are updated
     * @return true/false
     */
    public boolean playersConfigured() {
        return p1.isConfigured() && p2.isConfigured();
    }
}

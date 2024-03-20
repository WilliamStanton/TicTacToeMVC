package tictactoe.Repository;

import org.springframework.stereotype.Repository;
import tictactoe.Model.Player.Player;
import tictactoe.Model.Player.Statistics;

import java.util.ArrayList;

/**
 * Stores and updates the leaderboard
 */
@Repository
public class LeaderboardRepository {
    private static ArrayList<Statistics> statistics = new ArrayList<>();

    /**
     * Adds a player and returns the index
     * @param player the player
     * @return index if added, else -1 if already exists
     */
    public int addPlayer(Player player) {
        // Add player if doesn't already exist
        if (findPlayer(player.getName()) == -1) {
            statistics.add(new Statistics(player.getName()));
            return statistics.size()-1;
        } // Else return -1 if already exists
        else
            return -1;
    }

    /**
     * Adds a win for player
     * @param player the player
     */
    public void addWin(Player player) {
        var i = findPlayer(player.getName());

        // Add player if doesn't yet exist
        if (i == -1)
            i = addPlayer(player); // add player

        // Increment wins
        statistics.get(i).setWins(statistics.get(i).getWins() + 1);
    }

    /**
     * Adds a loss for player
     * @param player the player
     */
    public void addLoss(Player player) {
        var i = findPlayer(player.getName());

        // Add player if doesn't yet exist
        if (i == -1)
            i = addPlayer(player); // add player

        // Increment losses
        statistics.get(i).setLosses(statistics.get(i).getLosses() + 1);
    }

    /**
     * Finds player by name and returns the index
     * @param name player name
     * @return index if found, else -1 if not found
     */
    public int findPlayer(String name) {
        // Check if name already exists
        for (int i = 0; i < statistics.size(); i++) {
            if (statistics.get(i).getName().equalsIgnoreCase(name))
                return i;
        }

        // Else return false
        return -1;
    }

    /**
     * Return statistics
     * @return statistics
     */
    public ArrayList<Statistics> getStatistics() {
        return statistics;
    }
}

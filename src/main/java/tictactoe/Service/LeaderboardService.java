package tictactoe.Service;

import org.springframework.dao.IncorrectUpdateSemanticsDataAccessException;
import org.springframework.stereotype.Service;
import tictactoe.Model.Player.Player;
import tictactoe.Model.Player.Statistics;
import tictactoe.Repository.LeaderboardRepository;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Provides methods for manipulating the leaderboard
 */
@Service
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    /**
     * Returns leaderboard sorted by wins
     * @return leaderboard sorted by wins
     */
    public ArrayList<Statistics> getLeaderboard() {
        ArrayList<Statistics> lb = new ArrayList<>(leaderboardRepository.findAll());
        lb.sort(Comparator.comparingInt(Statistics::getWins).reversed()); // Sort array by wins
        // Return sorted leaderboard
        return lb;
    }

    /**
     * Adds a win to a player
     * @param player winner
     */
    public void addWin(Player player) {
        // Check if player exists, if not, add them
        if (leaderboardRepository.findStatisticsByName(player.getName()) == null)
            addPlayer(player.getName());

        // Set wins
        var statistic = leaderboardRepository.findStatisticsByName(player.getName());
        statistic.setWins(statistic.getWins()+1);
        leaderboardRepository.save(statistic);
    }

    /**
     * Adds a loss to a player
     * @param player loser
     */
    public void addLoss(Player player) {
        // Check if player exists, if not, add them
        if (leaderboardRepository.findStatisticsByName(player.getName()) == null)
            addPlayer(player.getName());

        // Set losses
        var statistic = leaderboardRepository.findStatisticsByName(player.getName());
        statistic.setLosses(statistic.getLosses()+1);
        leaderboardRepository.save(statistic);
    }

    /**
     * Adds a player
     * @param name player
     */
    private void addPlayer(String name) {
        leaderboardRepository.save(new Statistics(name));
    }
}

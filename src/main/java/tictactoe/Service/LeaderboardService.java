package tictactoe.Service;

import org.springframework.stereotype.Service;
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
        // Sort array by wins
        var lb = new ArrayList<>(leaderboardRepository.getStatistics());
        lb.sort(Comparator.comparingInt(Statistics::getWins).reversed());

        // Return sorted leaderboard
        return lb;
    }
}

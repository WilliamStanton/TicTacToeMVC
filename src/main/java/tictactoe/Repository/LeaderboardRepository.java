package tictactoe.Repository;

import org.springframework.data.repository.ListCrudRepository;
import tictactoe.Model.Player.Statistics;


/**
 * Stores and updates the leaderboard
 */
public interface LeaderboardRepository extends ListCrudRepository<Statistics, String> {
    Statistics findStatisticsByName(String name);
}

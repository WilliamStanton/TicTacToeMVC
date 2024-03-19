package tictactoe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tictactoe.Model.Player.PlayerProperties;

@Configuration
public class Config {

    /**
     * Player 1 Properties bean
     * @return p1 properties
     */
    @Bean
    public PlayerProperties pp1() {
        return new PlayerProperties();
    }

    /**
     * Player 2 Properties bean
     * @return p2 properties
     */
    @Bean
    public PlayerProperties pp2() {
        return new PlayerProperties();
    }
}

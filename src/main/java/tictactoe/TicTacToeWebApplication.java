package tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tictactoe.Model.Player.PlayerProperties;

@SpringBootApplication
public class TicTacToeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicTacToeWebApplication.class, args);

        // Create Context
        var context = new AnnotationConfigApplicationContext(Config.class);
    }
}

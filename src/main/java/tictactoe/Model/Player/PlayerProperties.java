package tictactoe.Model.Player;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Defines updated player options from Game Properties
 */
@Component
@SessionScope
@Getter
@Setter
public class PlayerProperties {
    private String name;
    private String symbol;
    private String color;

    // No-arg Constructor
    public PlayerProperties() {
        name = "";
        symbol = "";
        color = "";
    }

    // Initialized Object
    public PlayerProperties(String name, String symbol, String color) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
    }
}

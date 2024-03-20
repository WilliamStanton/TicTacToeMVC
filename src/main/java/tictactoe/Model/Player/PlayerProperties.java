package tictactoe.Model.Player;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Defines updated player options from Game Properties
 */
@Component
@SessionScope
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

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package tictactoe.Model.Player;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents player statistics
 */
@Table("statistics")
@Data
public class Statistics {
    @Id
    private int id;
    @Column("name")
    private String name;
    @Column("wins")
    private int wins;
    @Column("losses")
    private int losses;

    public Statistics(String name) {
        this.name = name;
        this.wins = 0;
        this.losses = 0;
    }
}
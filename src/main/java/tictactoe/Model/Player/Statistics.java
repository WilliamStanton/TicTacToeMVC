package tictactoe.Model.Player;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents player statistics
 */
@Table("statistics")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
package entity;

import javax.persistence.*;

/**
 * Clasa entitate care reprezinta tabela move din baza de date
 * Are o singura cheie straina: player_id care reprezinta playerul care face miscarea
 */
@Entity
@Table(name = "MOVE", schema = "STUDENT", catalog = "")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    int id;

    @OneToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID", nullable = false)
    private Player player_id;

    @Column(name = "line")
    private int line;

    @Column(name = "col")
    private int col;


    public Move() {}

    public Player getPlayer() {
        return player_id;
    }

    public void setPlayer(Player player) {
        this.player_id = player;
    }

    public Move(Player player, int line, int col) {
        this.setPlayer(player);
        this.setLine(line);
        this.setCol(col);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

}

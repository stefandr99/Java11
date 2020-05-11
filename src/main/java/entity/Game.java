package entity;

import javax.persistence.*;

/**
 * Clasa entity care reprezinta tabela game din baza de date
 * Are doua chei straine: player1_id, player2_id care reprezinta cei doi player-i din joc
 */
@Entity
@Table(name = "GAME", schema = "STUDENT", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Game.findAll",
                query = "SELECT g FROM Game g")
})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    int id;

    @OneToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "PLAYER1_ID", referencedColumnName = "ID", nullable = false)
    private Player player1;

    @OneToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "PLAYER2_ID", referencedColumnName = "ID", nullable = false)
    private Player player2;

    public Game() {}

    public Game(int id) {
        this.id = id;
    }

    public Game(Player player1, Player player2) {
        this.setPlayer1(player1);
        this.setPlayer2(player2);
    }

    public Game(int id, Player player1, Player player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayers(Player player1, Player player2) {
        this.setPlayer1(player1);
        this.setPlayer2(player2);
    }
}

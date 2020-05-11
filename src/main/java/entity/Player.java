package entity;

import javax.persistence.*;

/**
 * Clasa entity care reprezinta tabela player din baza de date
 */
@Entity
@Table(name = "PLAYER", schema = "STUDENT", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Player.findAll",
                query = "SELECT p FROM Player p")
})
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    public Player() {}

    public Player(String name) {
        this.setName(name);
    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
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
}

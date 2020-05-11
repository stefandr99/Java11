package repo;

import entity.Move;
import entity.Player;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.Java11.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class PlayerRepository {
    private final EntityManager entityManager;

    public PlayerRepository() {
        entityManager = PersistenceUtil.getFactory().createEntityManager();
    }

    /**
     * Inserarea unui player in baza de date
     * @param player - Obiectul player care trebuie introdus in baza de date
     */
    public void create(Player player) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(player);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Functie ce extrage toti player-ii din baza de date
     * @return - Lista cu toti player-ii din baza de date
     * @throws NotFoundException - Exceptie personalizata
     */
    public List<Player> findAll() throws NotFoundException {
        List<Player> playerList = entityManager.createNamedQuery("Player.findAll", Player.class)
                .getResultList();
        if(playerList.isEmpty())
            throw new NotFoundException("STEFAN'S ERROR - No players found !");
        return playerList;
    }

    /**
     * Functie ce cata un player dupa id in baza de date
     * @param id - Id ul jucatorului cautat
     * @return - Jucatorul daca este gasit, exceptie altfel
     * @throws NotFoundException - Exceptie personalizata
     */
    public Player findById(int id) throws NotFoundException {
        Player p = entityManager.find(Player.class, id);
        if(p == null)
            throw new NotFoundException("STEFAN'S ERROR - Not found player with id " + id);
        return p;
    }

    /**
     * Functie ce updateaza numele unui player in baza de date
     * @param id - Id-ul playerului a carui nume trebuie updaatat
     * @param name - Numele nou al playerului
     * @return - Mesaj de succes
     * @throws NotFoundException - Exceptie personalizata
     */
    public ResponseEntity<String> updatePlayer(int id, String name) throws NotFoundException {
        Player player = entityManager.find(Player.class, id);
        if(player != null) {
            player.setName(name);
            entityManager.merge(player);
            return new ResponseEntity<>(
                    "Player updated successfully", HttpStatus.OK);
        }
        else {
            throw new NotFoundException("STEFAN'S ERROR - Not found player with id " + id);
        }
    }

    /**
     * Functie ce sterge un player din baza de date dupa id
     * @param id - Id ul playerului care trebuie sters din baza de date
     * @return - Mesaj de succes
     * @throws NotFoundException - Exceptie personalizata
     */
    public ResponseEntity<String> removePlayer(int id) throws NotFoundException {
        Player player = entityManager.find(Player.class, id);
        if(player != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(player);
            entityManager.getTransaction().commit();
            return new ResponseEntity<>("Player deleted", HttpStatus.OK);
        }
        else {
            throw new NotFoundException("STEFAN'S ERROR - Not found player with id " + id);
        }

    }

    /**
     * Plasarea unei piese pe tabla
     * @param playerId - Id ul playerului care face miscarea
     * @param line - Linia pe tabla
     * @param column - Coloana pe tabla
     * @throws NotFoundException - Exceptie personalizata
     */
    public void placePiece(int playerId, int line, int column) throws NotFoundException {
        try {
            Player player = entityManager.find(Player.class, playerId);
            Move move = new Move(player, line, column);
            entityManager.getTransaction().begin();
            entityManager.persist(move);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

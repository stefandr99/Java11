package repo;

import entity.Game;
import com.example.Java11.PersistenceUtil;
import entity.Player;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class GameRepository {
    private final EntityManager entityManager;

    public GameRepository() {
        entityManager = PersistenceUtil.getFactory().createEntityManager();
    }

    /**
     * Inserarea unui joc nou in baza de date
     * @param game - Game ul care trebuie inserat in baza de date
     */
    public void create(Game game) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(game);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Functie ce extrage din baza de date toate jocurile din baza de date
     * @return - Lista de jocuri disponibila
     * @throws NotFoundException - Exceptie personalizata
     */
    public List<Game> findAll() throws NotFoundException {
        List<Game> gameList = entityManager.createNamedQuery("Game.findAll", Game.class)
                .getResultList();
        if(gameList.isEmpty())
            throw new NotFoundException("STEFAN'S ERROR - No games found !");
        return gameList;
    }

    /**
     * Functie ce sterge un joc din baza de date dupa un id
     * @param id - Id ul jocului care trebuie sters din baza de date
     * @return - Mesaj de succes in urma stergerii
     * @throws NotFoundException - Exceptie personalizata
     */
    public ResponseEntity<String> removeGame(int id) throws NotFoundException {
        Game game = entityManager.find(Game.class, id);
        if(game != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(game);
            entityManager.getTransaction().commit();
            return new ResponseEntity<>("Game deleted", HttpStatus.OK);
        }
        else
            throw new NotFoundException("STEFAN'S ERROR - Not found game with id " + id);

    }
}

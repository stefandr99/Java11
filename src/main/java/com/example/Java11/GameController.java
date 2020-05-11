package com.example.Java11;

import entity.Game;
import entity.Player;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.GameRepository;
import repo.PlayerRepository;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    public GameRepository gameRepository;
    public PlayerRepository playerRepository;

    GameController() {
        this.gameRepository = new GameRepository();
        this.playerRepository = new PlayerRepository();
    }

    /**
     * Afisarea tuturor jocurilor
     * @return - Lista cu toate jocurile din baza de date
     * @throws NotFoundException - Exceptie personalizata
     */
    @GetMapping
    public List<Game> getGames() throws NotFoundException {
        return gameRepository.findAll();
    }

    /**
     * Inserarea unui joc in baza de date
     * @param player1Id - Id-ul primului jucator
     * @param player2Id - Id-ul celui de-al doilea jucator
     * @return - Mesaj de succes
     * @throws NotFoundException - Exceptie personalizata
     */
    @PostMapping
    public ResponseEntity<String> createGame(@RequestParam int player1Id, @RequestParam int player2Id) throws NotFoundException {
        Player player1 = playerRepository.findById(player1Id);
        Player player2 = playerRepository.findById(player2Id);
        gameRepository.create(new Game(player1, player2));
        return new ResponseEntity<>(
                "Game inserted successfully", HttpStatus.CREATED);
    }

    /**
     * Stergerea unui joc
     * @param id - id ul jocului ce trebuie sters
     * @return - Mesaj de succes
     * @throws NotFoundException - Exceptie personalizata
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable int id) throws NotFoundException {
        return gameRepository.removeGame(id);
    }
}

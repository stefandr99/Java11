package com.example.Java11;

import entity.Player;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.PlayerRepository;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    public PlayerRepository playerRepository;

    PlayerController() {
        this.playerRepository = new PlayerRepository();
    }

    /**
     * Afisarea tuturor jucatorilor din baza de date
     * @return - Lista cu toti jucatorii din baza de date
     * @throws NotFoundException - Exceptie personalizata
     */
    @GetMapping
    public List<Player> getPlayers() throws NotFoundException {
        return playerRepository.findAll();
    }

    /**
     * Inserarea unui jucator in baza de date
     * @param name - Numele noului jucator
     * @return - Mesaj de succes
     */
    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestParam String name) {
        playerRepository.create(new Player(name));
        return new ResponseEntity<>(
                "Player inserted successfully", HttpStatus.CREATED);
    }

    /**
     * Realizarea unei miscari
     * @param player - jucatorul care realizeaza miscarea
     * @param line - linia unde se pune piesa
     * @param column - coloana unde se pune piesa
     * @return - Mesaj de succes
     * @throws NotFoundException - Exceptie personalizata
     */
    @PostMapping("/move")
    public ResponseEntity<String> move(@RequestParam int player, int line, int column) throws NotFoundException {
        playerRepository.placePiece(player, line, column);
        return new ResponseEntity<>(
                "Move made successfully", HttpStatus.CREATED);
    }

    /**
     * Updatarea numelui unui jucator
     * @param id - id ul jucatorului a carui nume trebuie updatat
     * @param name - numele nou
     * @return - Mesaj de succes
     * @throws NotFoundException - Exceptie personalizata
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updatePlayer(
            @PathVariable int id, @RequestParam String name) throws NotFoundException {
        return playerRepository.updatePlayer(id, name);
    }

    /**
     * Stergerea unui jucator din baza de date
     * @param id - Id ul jucatorului ce trebuie sters
     * @return - Mesaj de succes
     * @throws NotFoundException - Exceptie personalizata
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) throws NotFoundException {
        return playerRepository.removePlayer(id);
    }
}

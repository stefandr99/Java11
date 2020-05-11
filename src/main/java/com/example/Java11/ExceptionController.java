package com.example.Java11;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {
    /**
     * Functie ce returneaza un mesaj de eroare in cazul unei excepti
     * @param ex - Eroarea primita
     * @return - Obiect de tipul ResponseErr reprezentand mesajul exceptiei
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseErr handleNotFoundException(NotFoundException ex) {
        return new ResponseErr(ex.getMessage());
    }
}

package com.euris.esame_finale.utils.exceptions;

public class FilmVietatoAiMinoriException extends Exception {

    public FilmVietatoAiMinoriException(String film) {
        super(film + " è vietato ai minori");
    }
}

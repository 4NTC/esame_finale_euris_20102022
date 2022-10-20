package com.euris.esame_finale.utils.exceptions;

public class FilmVietatoAiMinoriException extends Exception {

    public FilmVietatoAiMinoriException(Long idFilm) {
        super("Film " + idFilm + " vietato ai minori");
    }
}

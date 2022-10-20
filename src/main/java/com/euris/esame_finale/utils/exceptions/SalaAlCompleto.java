package com.euris.esame_finale.utils.exceptions;

public class SalaAlCompleto extends Exception {

    public SalaAlCompleto(Long idSala) {
        super("Sala " + idSala + " al completo!");
    }
}

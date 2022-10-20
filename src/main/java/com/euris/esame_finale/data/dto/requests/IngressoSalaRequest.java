package com.euris.esame_finale.data.dto.requests;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IngressoSalaRequest {
    private Long idSala;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private Long idBiglietto;
}

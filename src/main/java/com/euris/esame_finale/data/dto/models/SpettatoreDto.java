package com.euris.esame_finale.data.dto.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.archetypes.Model;
import com.euris.esame_finale.data.models.Biglietto;
import com.euris.esame_finale.data.models.Sala;
import com.euris.esame_finale.data.models.Spettatore;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SpettatoreDto implements Dto {

    private Long id;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private Long idBiglietto;
    private Long idSala;

    @Override
    public Spettatore toModel() {
        Biglietto biglietto = Biglietto.builder().id(idBiglietto).build();
        Sala sala = Sala.builder().id(idSala).build();

        return Spettatore.builder()
                .id(id)
                .nome(nome)
                .cognome(cognome)
                .dataNascita(dataNascita)
                .biglietto(biglietto)
                .sala(sala)
                .build();
    }
}

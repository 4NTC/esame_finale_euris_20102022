package com.euris.esame_finale.data.dto.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.archetypes.Model;
import com.euris.esame_finale.data.models.Biglietto;
import com.euris.esame_finale.data.models.Spettatore;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BigliettoDto implements Dto {

    private Long id;
    private Long idSpettatore;
    private Integer posizione;
    private Double prezzo;

    @Override
    public Biglietto toModel() {
        Spettatore spettatore = Spettatore.builder().id(idSpettatore).build();
        return Biglietto.builder()
                .id(id)
                .spettatore(spettatore)
                .posizione(posizione)
                .prezzo(prezzo)
                .build();
    }
}

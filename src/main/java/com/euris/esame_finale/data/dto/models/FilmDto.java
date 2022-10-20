package com.euris.esame_finale.data.dto.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.models.Film;
import com.euris.esame_finale.data.models.Sala;
import com.euris.esame_finale.utils.GenereFilm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FilmDto implements Dto {

    private Long id;
    private String titolo;
    private String autore;
    private String produttore;
    private GenereFilm genereFilm;
    private Integer etaMinima;
    private Integer durata;
    private Long idSala;
    @Override
    public Film toModel() {
        Sala sala = Sala.builder().id(idSala).build();

        return Film.builder()
                .id(id)
                .titolo(titolo)
                .autore(autore)
                .produttore(produttore)
                .genereFilm(genereFilm)
                .etaMinima(etaMinima)
                .durata(durata)
                .sala(sala)
                .build();
    }
}

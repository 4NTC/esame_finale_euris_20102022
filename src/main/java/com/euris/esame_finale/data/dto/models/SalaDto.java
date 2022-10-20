package com.euris.esame_finale.data.dto.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.models.Cinema;
import com.euris.esame_finale.data.models.Film;
import com.euris.esame_finale.data.models.Sala;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SalaDto implements Dto {

    private Long id;
    private Integer posti;
    private Long idFilm;
    private Long idCinema;
    @Override
    public Sala toModel() {
        Cinema cinema = Cinema.builder().id(idCinema).build();
        Film film = Film.builder().id(idFilm).build();
        return Sala.builder()
                .id(id)
                .posti(posti)
                .film(film)
                .cinema(cinema)
                .build();
    }
}

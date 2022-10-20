package com.euris.esame_finale.data.models;

import com.euris.esame_finale.data.archetypes.Model;
import com.euris.esame_finale.data.dto.models.SalaDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "sala")
public class Sala implements Model {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_posti")
    private Integer posti;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sala")
    private List<Spettatore> spettatori;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;

    @Override
    public SalaDto toDto() {
        return SalaDto.builder()
                .id(id)
                .posti(posti)
                .idFilm(film.getId())
                .idCinema(cinema.getId())
                .build();
    }
}

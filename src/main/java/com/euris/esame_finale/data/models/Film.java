package com.euris.esame_finale.data.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.archetypes.Model;
import com.euris.esame_finale.data.dto.models.FilmDto;
import com.euris.esame_finale.utils.GenereFilm;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "film")
public class Film implements Model {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "autore")
    private String autore;

    @Column(name = "produttore")
    private String produttore;

    @Enumerated(EnumType.STRING)
    @Column(name = "genereFilm")
    private GenereFilm genereFilm;

    @Column(name = "etaMinima")
    private Integer etaMinima;

    @Column(name = "durata")
    private Integer durata;

    @OneToOne(mappedBy = "film")
    private Sala sala;

    @Override
    public FilmDto toDto() {
        return FilmDto.builder()
                .id(id)
                .titolo(titolo)
                .autore(autore)
                .produttore(produttore)
                .genereFilm(genereFilm)
                .etaMinima(etaMinima)
                .durata(durata)
                .idSala(sala.getId())
                .build();
    }
}

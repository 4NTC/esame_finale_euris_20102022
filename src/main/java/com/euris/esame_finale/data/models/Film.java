package com.euris.esame_finale.data.models;

import com.euris.esame_finale.utils.Genere;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "film")
public class Film {

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
    @Column(name = "genere")
    private Genere genere;

    @Column(name = "etaMinima")
    private int etaMinima;

    @Column(name = "durata")
    private int durata;

    @OneToOne(mappedBy = "film")
    private SalaCinematografica sala;

}

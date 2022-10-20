package com.euris.esame_finale.data.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "salaCinematografica")
public class SalaCinematografica {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero_posti")
    private int posti;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sala")
    private List<Spettatore> spettatori;

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema;
}

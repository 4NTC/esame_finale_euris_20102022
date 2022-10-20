package com.euris.esame_finale.data.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "biglietto")
public class Biglietto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "biglietto")
    private Spettatore spettatore;

    @Column(name = "posizione")
    private int posizione;

    @Column(name = "prezzo")
    private double prezzo;
}

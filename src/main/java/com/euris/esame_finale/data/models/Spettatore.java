package com.euris.esame_finale.data.models;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "spettatore")
public class Spettatore {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private String cognome;

    @Column
    private LocalDate dataNascita;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name =  "biglietto_id", referencedColumnName = "id")
    private Biglietto biglietto;

    @ManyToOne
    @JoinColumn(name = "salaCinematografica_id", nullable = false)
    private SalaCinematografica sala;
}

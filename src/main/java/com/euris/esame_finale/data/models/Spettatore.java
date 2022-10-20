package com.euris.esame_finale.data.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.archetypes.Model;
import com.euris.esame_finale.data.dto.models.SpettatoreDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "spettatore")
public class Spettatore implements Model {

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
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @Override
    public SpettatoreDto toDto() {
        return SpettatoreDto.builder()
                .id(id)
                .nome(nome)
                .cognome(cognome)
                .dataNascita(dataNascita)
                .idBiglietto(biglietto.getId())
                .build();
    }
}

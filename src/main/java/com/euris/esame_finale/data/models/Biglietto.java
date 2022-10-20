package com.euris.esame_finale.data.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.archetypes.Model;
import com.euris.esame_finale.data.dto.models.BigliettoDto;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "biglietto")
public class Biglietto implements Dto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "biglietto")
    private Spettatore spettatore;

    @Column(name = "posizione")
    private Integer posizione;

    @Column(name = "prezzo")
    private Double prezzo;

    @Override
    public BigliettoDto toModel() {
        return BigliettoDto.builder()
                .id(id)
                .idSpettatore(spettatore.getId())
                .posizione(posizione)
                .prezzo(prezzo)
                .build();
    }
}

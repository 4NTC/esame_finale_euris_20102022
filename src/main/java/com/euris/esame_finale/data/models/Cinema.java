package com.euris.esame_finale.data.models;

import com.euris.esame_finale.data.archetypes.Model;
import com.euris.esame_finale.data.dto.models.CinemaDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cinema")
public class Cinema implements Model {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cinema")
    private List<Sala> sale;

    @Override
    public CinemaDto toDto() {
        return CinemaDto.builder()
                .id(id)
                .sale(sale)
                .build();
    }
}

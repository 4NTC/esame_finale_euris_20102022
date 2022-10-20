package com.euris.esame_finale.data.dto.models;

import com.euris.esame_finale.data.archetypes.Dto;
import com.euris.esame_finale.data.models.Cinema;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CinemaDto implements Dto {

    private Long id;
    private List<SalaDto> sale;
    @Override
    public Cinema toModel() {
        return Cinema.builder()
                .id(id)
                .sale(sale.stream().map(SalaDto::toModel).toList())
                .build();
    }
}

package com.euris.esame_finale.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncassoResponse {

    private Long idCinema;
    private double incasso;
}

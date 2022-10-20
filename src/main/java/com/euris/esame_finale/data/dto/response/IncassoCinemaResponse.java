package com.euris.esame_finale.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncassoCinemaResponse {

    private Long idCinema;
    private double incasso;
}

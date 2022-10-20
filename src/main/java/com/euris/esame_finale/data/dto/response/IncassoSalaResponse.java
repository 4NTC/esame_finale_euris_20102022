package com.euris.esame_finale.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IncassoSalaResponse {

    private Long idSala;
    private double incasso;
}

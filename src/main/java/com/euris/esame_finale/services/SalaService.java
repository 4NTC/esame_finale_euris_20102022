package com.euris.esame_finale.services;


import com.euris.esame_finale.data.dto.IncassoSalaResponse;
import com.euris.esame_finale.data.dto.ResponseDto;
import com.euris.esame_finale.data.dto.models.SalaDto;
import com.euris.esame_finale.data.models.Sala;
import com.euris.esame_finale.utils.exceptions.FilmVietatoAiMinoriException;
import com.euris.esame_finale.utils.exceptions.SalaAlCompleto;

import java.util.List;

public interface SalaService {

    ResponseDto<SalaDto> insertSala(SalaDto salaDto);
    ResponseDto<SalaDto> updateSala(SalaDto salaDto);
    ResponseDto<SalaDto> removeById(Long idSala);
    ResponseDto<List<SalaDto>> getAll();
    ResponseDto<SalaDto> getById(Long idSala);

    ResponseDto<Sala> svuotaSala(Long idSala);
    ResponseDto<Sala> consentiIngressoSpettatore(Long idSpettatore) throws SalaAlCompleto, FilmVietatoAiMinoriException;
    IncassoSalaResponse calcolaIncasso(Long idSala);
}

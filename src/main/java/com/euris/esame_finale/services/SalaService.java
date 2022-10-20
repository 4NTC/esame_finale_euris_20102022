package com.euris.esame_finale.services;


import com.euris.esame_finale.data.dto.requests.IngressoSalaRequest;
import com.euris.esame_finale.data.dto.response.IncassoSalaResponse;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.SalaDto;
import com.euris.esame_finale.utils.exceptions.FilmVietatoAiMinoriException;
import com.euris.esame_finale.utils.exceptions.SalaAlCompleto;

import java.util.List;

public interface SalaService {

    ResponseDto<SalaDto> insert(SalaDto salaDto);
    ResponseDto<SalaDto> update(SalaDto salaDto);
    ResponseDto<SalaDto> deleteById(Long idSala);
    ResponseDto<List<SalaDto>> getAll();
    ResponseDto<SalaDto> getById(Long idSala);

    ResponseDto<SalaDto> svuotaSala(Long idSala);
    ResponseDto<SalaDto> consentiIngressoSpettatore(IngressoSalaRequest ingressoSalaRequest) throws SalaAlCompleto, FilmVietatoAiMinoriException;
    IncassoSalaResponse calcolaIncasso(Long idSala);
}

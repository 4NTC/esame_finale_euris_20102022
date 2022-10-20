package com.euris.esame_finale.services;

import com.euris.esame_finale.data.dto.response.IncassoCinemaResponse;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.CinemaDto;

import java.util.List;

public interface CinemaService {

    ResponseDto<CinemaDto> insert();
    // Cinema non contiene campi da aggiornare
    // ResponseDto<CinemaDto> updateCinema(CinemaDto cinemaDto);
    ResponseDto<CinemaDto> deleteById(Long idCinema);
    ResponseDto<List<CinemaDto>> getAll();
    ResponseDto<CinemaDto> getById(Long idCinema);

    IncassoCinemaResponse calcolaIncasso(Long idCinema);
}

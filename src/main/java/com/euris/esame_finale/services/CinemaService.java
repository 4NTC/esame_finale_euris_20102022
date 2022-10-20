package com.euris.esame_finale.services;

import com.euris.esame_finale.data.dto.IncassoCinemaResponse;
import com.euris.esame_finale.data.dto.ResponseDto;
import com.euris.esame_finale.data.dto.models.CinemaDto;

import java.util.List;

public interface CinemaService {

    ResponseDto<CinemaDto> insertCinema(CinemaDto cinemaDto);
    ResponseDto<CinemaDto> updateCinema(CinemaDto cinemaDto);
    ResponseDto<CinemaDto> removeById(Long idCinema);
    ResponseDto<List<CinemaDto>> getAll();
    ResponseDto<CinemaDto> getById(Long idCinema);

    IncassoCinemaResponse calcolaIncasso(Long idCinema);
}

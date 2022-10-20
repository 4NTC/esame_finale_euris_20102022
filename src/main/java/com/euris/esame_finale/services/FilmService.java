package com.euris.esame_finale.services;

import com.euris.esame_finale.data.dto.ResponseDto;
import com.euris.esame_finale.data.dto.models.FilmDto;

import java.util.List;

public interface FilmService {

    ResponseDto<FilmDto> insertCinema(FilmDto filmDto);
    ResponseDto<FilmDto> updateCinema(FilmDto filmDto);
    ResponseDto<FilmDto> removeById(Long idFilm);
    ResponseDto<List<FilmDto>> getAll();
    ResponseDto<FilmDto> getById(Long idFilm);
}

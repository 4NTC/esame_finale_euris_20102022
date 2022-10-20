package com.euris.esame_finale.services;

import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.FilmDto;

import java.util.List;

public interface FilmService {

    ResponseDto<FilmDto> insertFilm(FilmDto filmDto);
    ResponseDto<FilmDto> updateFilm(FilmDto filmDto);
    ResponseDto<FilmDto> removeById(Long idFilm);
    ResponseDto<List<FilmDto>> getAll();
    ResponseDto<FilmDto> getById(Long idFilm);
}

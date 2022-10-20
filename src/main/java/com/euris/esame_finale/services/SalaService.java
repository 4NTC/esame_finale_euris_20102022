package com.euris.esame_finale.services;


import com.euris.esame_finale.data.dto.ResponseDto;
import com.euris.esame_finale.data.dto.models.SalaDto;
import com.euris.esame_finale.data.models.SalaCinematografica;

import java.util.List;

public interface SalaService {

    ResponseDto<SalaDto> insertCinema(SalaDto salaDto);
    ResponseDto<SalaDto> updateCinema(SalaDto salaDto);
    ResponseDto<SalaDto> removeById(Long idSala);
    ResponseDto<List<SalaDto>> getAll();
    ResponseDto<SalaDto> getById(Long idSala);

    ResponseDto<SalaCinematografica> svuotaSala(Long idSala);
}

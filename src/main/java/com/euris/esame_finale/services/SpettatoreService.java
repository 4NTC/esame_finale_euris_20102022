package com.euris.esame_finale.services;

import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.SpettatoreDto;

import java.util.List;

public interface SpettatoreService {

    ResponseDto<SpettatoreDto> insertSpettatore(SpettatoreDto spettatoreDto);
    ResponseDto<SpettatoreDto> updateSpettatore(SpettatoreDto spettatoreDto);
    ResponseDto<SpettatoreDto> removeById(Long idSpettatore);
    ResponseDto<List<SpettatoreDto>> getAll();
    ResponseDto<SpettatoreDto> getById(Long idSpettatore);

    Integer getEtaSpettatore(Long idSpettatore);
}

package com.euris.esame_finale.services;

import com.euris.esame_finale.data.dto.ResponseDto;
import com.euris.esame_finale.data.dto.models.BigliettoDto;

import java.util.List;

public interface BigliettoService {

    ResponseDto<BigliettoDto> insertCinema(BigliettoDto bigliettoDto);
    ResponseDto<BigliettoDto> updateCinema(BigliettoDto bigliettoDto);
    ResponseDto<BigliettoDto> removeById(Long idBiglietto);
    ResponseDto<List<BigliettoDto>> getAll();
    ResponseDto<BigliettoDto> getById(Long idBiglietto);

    // Sconto 10% se età > 70 | Sconto 50% se età < 5;
    ResponseDto<BigliettoDto> applicaRiduzionePrezzo(Long idBiglietto);
}

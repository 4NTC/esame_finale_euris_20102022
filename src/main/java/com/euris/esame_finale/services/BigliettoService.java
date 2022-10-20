package com.euris.esame_finale.services;

import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.BigliettoDto;

import java.util.List;

public interface BigliettoService {

    ResponseDto<BigliettoDto> insert(BigliettoDto bigliettoDto);
    ResponseDto<BigliettoDto> update(BigliettoDto bigliettoDto);
    ResponseDto<BigliettoDto> deleteById(Long idBiglietto);
    ResponseDto<List<BigliettoDto>> getAll();
    ResponseDto<BigliettoDto> getById(Long idBiglietto);

    // Sconto 10% se età > 70 | Sconto 50% se età < 5;
    ResponseDto<BigliettoDto> applicaRiduzionePrezzo(Long idBiglietto);
}

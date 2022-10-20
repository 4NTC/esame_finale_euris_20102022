package com.euris.esame_finale.services.impls;

import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.BigliettoDto;
import com.euris.esame_finale.data.models.Biglietto;
import com.euris.esame_finale.data.models.Spettatore;
import com.euris.esame_finale.repository.BigliettoRepository;
import com.euris.esame_finale.repository.SpettatoreRepository;
import com.euris.esame_finale.services.BigliettoService;
import com.euris.esame_finale.utils.HttpRequestType;
import com.euris.esame_finale.utils.HttpResponseType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class BigliettoServiceImpl implements BigliettoService {

    private final BigliettoRepository bigliettoRepository;

    private final SpettatoreRepository spettatoreRepository;

    public BigliettoServiceImpl(BigliettoRepository bigliettoRepository, SpettatoreRepository spettatoreRepository) {
        this.bigliettoRepository = bigliettoRepository;
        this.spettatoreRepository = spettatoreRepository;
    }

    @Override
    public ResponseDto<BigliettoDto> insert(BigliettoDto bigliettoDto) {
        ResponseDto<BigliettoDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);

        Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(bigliettoDto.getId());

        if (optionalBiglietto.isEmpty()) {
            Optional<Spettatore> optionalSpettatore = spettatoreRepository.findById(bigliettoDto.getId());

            if (optionalSpettatore.isPresent()) {
                Biglietto biglietto = bigliettoDto.toModel();
                biglietto.setSpettatore(optionalSpettatore.get());
                bigliettoRepository.save(biglietto);
                response.setHttpResponse(HttpResponseType.CREATED);
                response.setCode(HttpResponseType.CREATED.getCode());
                response.setDesc(HttpResponseType.CREATED.getDesc());
                response.setBody(bigliettoDto);
            } else {
                response.setHttpResponse(HttpResponseType.NOT_FOUND);
                response.setCode(HttpResponseType.NOT_FOUND.getCode());
                response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
            }

        } else {
            response.setHttpResponse(HttpResponseType.NOT_CREATED);
            response.setCode(HttpResponseType.NOT_CREATED.getCode());
            response.setDesc(HttpResponseType.NOT_CREATED.getDesc());
        }

        return response;
    }

    @Override
    public ResponseDto<BigliettoDto> update(BigliettoDto bigliettoDto) {

        ResponseDto<BigliettoDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.PUT);

        Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(bigliettoDto.getId());

        if (optionalBiglietto.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            Optional<Spettatore> optionalSpettatore = spettatoreRepository.findById(bigliettoDto.getId());

            if (optionalSpettatore.isPresent()) {
                Biglietto biglietto = bigliettoDto.toModel();
                biglietto.setSpettatore(optionalSpettatore.get());
                bigliettoRepository.save(biglietto);

                response.setHttpResponse(HttpResponseType.UPDATED);
                response.setCode(HttpResponseType.UPDATED.getCode());
                response.setDesc(HttpResponseType.UPDATED.getDesc());
                response.setBody(bigliettoDto);
            } else {
                response.setHttpResponse(HttpResponseType.NOT_UPDATED);
                response.setCode(HttpResponseType.NOT_UPDATED.getCode());
                response.setDesc(HttpResponseType.NOT_UPDATED.getDesc());
            }
        }

        return response;
    }

    @Override
    public ResponseDto<BigliettoDto> deleteById(Long idBiglietto) {
        ResponseDto<BigliettoDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.DELETE);

        Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(idBiglietto);

        if (optionalBiglietto.isPresent()) {
            bigliettoRepository.deleteById(idBiglietto);
            response.setHttpResponse(HttpResponseType.DELETED);
            response.setCode(HttpResponseType.DELETED.getCode());
            response.setDesc(HttpResponseType.DELETED.getDesc());
            response.setBody(optionalBiglietto.get().toDto());
        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }

        return response;
    }

    @Override
    public ResponseDto<List<BigliettoDto>> getAll() {

        ResponseDto<List<BigliettoDto>> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        List<Biglietto> biglietti = bigliettoRepository.findAll();

        if (biglietti.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(biglietti.stream().map(Biglietto::toDto).toList());
        }

        return response;
    }

    @Override
    public ResponseDto<BigliettoDto> getById(Long idBiglietto) {

        ResponseDto<BigliettoDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(idBiglietto);

        if (optionalBiglietto.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(optionalBiglietto.get().toDto());
        }
        return response;
    }

    @Override
    public ResponseDto<BigliettoDto> applicaRiduzionePrezzo(Long idBiglietto) {

        ResponseDto<BigliettoDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.PUT);

        Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(idBiglietto);

        if (optionalBiglietto.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            Biglietto biglietto = optionalBiglietto.get();
            Period period = Period.between(LocalDate.now(), biglietto.getSpettatore().getDataNascita());

            if (period.getYears() > 70) {
                biglietto.setPrezzo(biglietto.getPrezzo() - biglietto.getPrezzo() * 10 / 100);
            } else if (period.getYears() < 5) {
                biglietto.setPrezzo(biglietto.getPrezzo() - biglietto.getPrezzo() * 50 / 100);
            }

            bigliettoRepository.save(biglietto);
            response.setHttpResponse(HttpResponseType.UPDATED);
            response.setCode(HttpResponseType.UPDATED.getCode());
            response.setDesc(HttpResponseType.UPDATED.getDesc());
            response.setBody(biglietto.toDto());
        }

        return response;
    }
}

package com.euris.esame_finale.services.impls;

import com.euris.esame_finale.data.dto.models.SpettatoreDto;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.models.Biglietto;
import com.euris.esame_finale.data.models.Sala;
import com.euris.esame_finale.data.models.Spettatore;
import com.euris.esame_finale.repository.BigliettoRepository;
import com.euris.esame_finale.repository.SalaRepository;
import com.euris.esame_finale.repository.SpettatoreRepository;
import com.euris.esame_finale.services.SpettatoreService;
import com.euris.esame_finale.utils.HttpRequestType;
import com.euris.esame_finale.utils.HttpResponseType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class SpettatoreServiceImpl implements SpettatoreService {
    private final SpettatoreRepository spettatoreRepository;
    private final BigliettoRepository bigliettoRepository;
    private final SalaRepository salaRepository;


    public SpettatoreServiceImpl(SpettatoreRepository spettatoreRepository, BigliettoRepository bigliettoRepository, SalaRepository salaRepository) {
        this.spettatoreRepository = spettatoreRepository;
        this.bigliettoRepository = bigliettoRepository;
        this.salaRepository = salaRepository;
    }

    @Override
    public ResponseDto<SpettatoreDto> insert(SpettatoreDto spettatoreDto) {

        ResponseDto<SpettatoreDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);
        Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(spettatoreDto.getIdBiglietto());

        if (optionalBiglietto.isPresent()) {
            Optional<Sala> optionalSala = salaRepository.findById(spettatoreDto.getIdSala());

            if (optionalSala.isPresent()) {
                Spettatore spettatore = Spettatore.builder()
                        .nome(spettatoreDto.getNome())
                        .cognome(spettatoreDto.getCognome())
                        .dataNascita(spettatoreDto.getDataNascita())
                        .biglietto(optionalBiglietto.get())
                        .sala(optionalSala.get())
                        .build();
                spettatoreRepository.save(spettatore);
                response.setHttpResponse(HttpResponseType.CREATED);
                response.setCode(HttpResponseType.CREATED.getCode());
                response.setDesc(HttpResponseType.CREATED.getDesc());
                response.setBody(spettatoreDto);

            } else {
                response.setHttpResponse(HttpResponseType.NOT_FOUND);
                response.setCode(HttpResponseType.NOT_FOUND.getCode());
                response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
            }

        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }

        return response;
    }

    @Override
    public ResponseDto<SpettatoreDto> update(SpettatoreDto spettatoreDto) {
        ResponseDto<SpettatoreDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.PUT);
        Optional<Spettatore> optionalSpettatore = spettatoreRepository.findById(spettatoreDto.getIdBiglietto());

        if (optionalSpettatore.isPresent()) {

            Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(spettatoreDto.getIdBiglietto());

            if (optionalBiglietto.isPresent()) {

                Optional<Sala> optionalSala = salaRepository.findById(spettatoreDto.getIdSala());

                if (optionalSala.isPresent()) {
                    Spettatore spettatore = Spettatore.builder()
                            .nome(spettatoreDto.getNome())
                            .cognome(spettatoreDto.getCognome())
                            .dataNascita(spettatoreDto.getDataNascita())
                            .biglietto(optionalBiglietto.get())
                            .sala(optionalSala.get())
                            .build();
                    spettatoreRepository.save(spettatore);
                    response.setHttpResponse(HttpResponseType.UPDATED);
                    response.setCode(HttpResponseType.UPDATED.getCode());
                    response.setDesc(HttpResponseType.UPDATED.getDesc());
                    response.setBody(spettatoreDto);

                } else {
                    response.setHttpResponse(HttpResponseType.NOT_FOUND);
                    response.setCode(HttpResponseType.NOT_FOUND.getCode());
                    response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
                }

            } else {
                response.setHttpResponse(HttpResponseType.NOT_FOUND);
                response.setCode(HttpResponseType.NOT_FOUND.getCode());
                response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
            }

        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }
        return response;
    }

    @Override
    public ResponseDto<SpettatoreDto> deleteById(Long idSpettatore) {
        ResponseDto<SpettatoreDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.DELETE);

        Optional<Spettatore> optionalSpettatore = spettatoreRepository.findById(idSpettatore);

        if (optionalSpettatore.isPresent()) {
            spettatoreRepository.deleteById(idSpettatore);
            response.setHttpResponse(HttpResponseType.DELETED);
            response.setCode(HttpResponseType.DELETED.getCode());
            response.setDesc(HttpResponseType.DELETED.getDesc());
            response.setBody(optionalSpettatore.get().toDto());
        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }

        return response;
    }

    @Override
    public ResponseDto<List<SpettatoreDto>> getAll() {
        ResponseDto<List<SpettatoreDto>> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        List<Spettatore> spettatori = spettatoreRepository.findAll();

        if (spettatori.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(spettatori.stream().map(Spettatore::toDto).toList());
        }

        return response;
    }

    @Override
    public ResponseDto<SpettatoreDto> getById(Long idSpettatore) {
        ResponseDto<SpettatoreDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        Optional<Spettatore> optionalSpettatore = spettatoreRepository.findById(idSpettatore);

        if (optionalSpettatore.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(optionalSpettatore.get().toDto());
        }
        return response;
    }

    @Override
    public Integer getEtaSpettatore(Long idSpettatore) {
        int eta = 0;
        Optional<Spettatore> optionalSpettatore = spettatoreRepository.findById(idSpettatore);
        if (optionalSpettatore.isPresent()) {
            Spettatore spettatore = optionalSpettatore.get();
            Period period = Period.between(LocalDate.now(), spettatore.getDataNascita());
            eta = period.getYears();
        }
        return eta;
    }
}

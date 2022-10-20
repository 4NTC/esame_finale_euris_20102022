package com.euris.esame_finale.services.impls;

import com.euris.esame_finale.data.dto.response.IncassoCinemaResponse;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.CinemaDto;
import com.euris.esame_finale.data.models.Biglietto;
import com.euris.esame_finale.data.models.Cinema;
import com.euris.esame_finale.repository.BigliettoRepository;
import com.euris.esame_finale.repository.CinemaRepository;
import com.euris.esame_finale.services.CinemaService;
import com.euris.esame_finale.utils.HttpRequestType;
import com.euris.esame_finale.utils.HttpResponseType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final BigliettoRepository bigliettoRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository, BigliettoRepository bigliettoRepository) {
        this.cinemaRepository = cinemaRepository;
        this.bigliettoRepository = bigliettoRepository;
    }

    @Override
    public ResponseDto<CinemaDto> insertCinema() {
        ResponseDto<CinemaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);
        Cinema cinema = Cinema.builder().sale(new ArrayList<>()).build();
        cinemaRepository.save(cinema);
        response.setHttpResponse(HttpResponseType.CREATED);
        response.setCode(HttpResponseType.CREATED.getCode());
        response.setDesc(HttpResponseType.CREATED.getDesc());
        response.setBody(cinema.toDto());
        return response;
    }


    @Override
    public ResponseDto<CinemaDto> removeById(Long idCinema) {
        ResponseDto<CinemaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.DELETE);

        Optional<Cinema> optionalCinema = cinemaRepository.findById(idCinema);

        if (optionalCinema.isPresent()) {
            bigliettoRepository.deleteById(idCinema);
            response.setHttpResponse(HttpResponseType.DELETED);
            response.setCode(HttpResponseType.DELETED.getCode());
            response.setDesc(HttpResponseType.DELETED.getDesc());
            response.setBody(optionalCinema.get().toDto());
        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }

        return response;
    }

    @Override
    public ResponseDto<List<CinemaDto>> getAll() {
        ResponseDto<List<CinemaDto>> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        List<Cinema> cinema = cinemaRepository.findAll();

        if (cinema.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(cinema.stream().map(Cinema::toDto).toList());
        }

        return response;
    }

    @Override
    public ResponseDto<CinemaDto> getById(Long idCinema) {
        ResponseDto<CinemaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        Optional<Cinema> optionalCinema = cinemaRepository.findById(idCinema);

        if (optionalCinema.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(optionalCinema.get().toDto());
        }
        return response;
    }

    @Override
    public IncassoCinemaResponse calcolaIncasso(Long idCinema) {
        IncassoCinemaResponse response = new IncassoCinemaResponse();
        response.setIdCinema(idCinema);
        response.setIncasso(0.0);

        Optional<Cinema> optionalCinema = cinemaRepository.findById(idCinema);

        if (optionalCinema.isPresent()) {
            List<Biglietto> biglietti = bigliettoRepository.findAll();
            biglietti.forEach(biglietto -> response.setIncasso(response.getIncasso() + biglietto.getPrezzo()));
        }
        return response;
    }
}

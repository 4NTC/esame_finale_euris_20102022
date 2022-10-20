package com.euris.esame_finale.services.impls;

import com.euris.esame_finale.data.dto.requests.IngressoSalaRequest;
import com.euris.esame_finale.data.dto.response.IncassoSalaResponse;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.SalaDto;
import com.euris.esame_finale.data.models.*;
import com.euris.esame_finale.repository.*;
import com.euris.esame_finale.services.SalaService;
import com.euris.esame_finale.utils.GenereFilm;
import com.euris.esame_finale.utils.HttpRequestType;
import com.euris.esame_finale.utils.HttpResponseType;
import com.euris.esame_finale.utils.exceptions.FilmVietatoAiMinoriException;
import com.euris.esame_finale.utils.exceptions.SalaAlCompleto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaServiceImpl implements SalaService {

    private final SalaRepository salaRepository;
    private final SpettatoreRepository spettatoreRepository;
    private final BigliettoRepository bigliettoRepository;
    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;

    public SalaServiceImpl(SalaRepository salaRepository, SpettatoreRepository spettatoreRepository, BigliettoRepository bigliettoRepository, FilmRepository filmRepository, CinemaRepository cinemaRepository) {
        this.salaRepository = salaRepository;
        this.spettatoreRepository = spettatoreRepository;
        this.bigliettoRepository = bigliettoRepository;
        this.filmRepository = filmRepository;
        this.cinemaRepository = cinemaRepository;
    }


    @Override
    public ResponseDto<SalaDto> insert(SalaDto salaDto) {
        ResponseDto<SalaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);

        Optional<Sala> optionalSala = salaRepository.findById(salaDto.getId());

        if (optionalSala.isEmpty()) {
            Optional<Film> optionalFilm = filmRepository.findById(salaDto.getIdFilm());
            if (optionalFilm.isPresent()) {
                Optional<Cinema> optionalCinema = cinemaRepository.findById(salaDto.getIdCinema());
                if (optionalCinema.isPresent()) {
                    Sala sala = Sala.builder()
                            .posti(salaDto.getPosti())
                            .film(optionalFilm.get())
                            .spettatori(new ArrayList<>())
                            .cinema(optionalCinema.get())
                            .build();
                    salaRepository.save(sala);
                    response.setHttpResponse(HttpResponseType.CREATED);
                    response.setCode(HttpResponseType.CREATED.getCode());
                    response.setDesc(HttpResponseType.CREATED.getDesc());
                    response.setBody(salaDto);

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
        }
        return response;
    }

    @Override
    public ResponseDto<SalaDto> update(SalaDto salaDto) {
        ResponseDto<SalaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);

        Optional<Sala> optionalSala = salaRepository.findById(salaDto.getId());

        if (optionalSala.isPresent()) {
            Optional<Film> optionalFilm = filmRepository.findById(salaDto.getIdFilm());
            if (optionalFilm.isPresent()) {
                Optional<Cinema> optionalCinema = cinemaRepository.findById(salaDto.getIdCinema());
                if (optionalCinema.isPresent()) {
                    Sala sala = Sala.builder()
                            .id(optionalSala.get().getId())
                            .posti(salaDto.getPosti())
                            .film(optionalFilm.get())
                            .spettatori(new ArrayList<>())
                            .cinema(optionalCinema.get())
                            .build();
                    salaRepository.save(sala);
                    response.setHttpResponse(HttpResponseType.UPDATED);
                    response.setCode(HttpResponseType.UPDATED.getCode());
                    response.setDesc(HttpResponseType.UPDATED.getDesc());
                    response.setBody(salaDto);

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
    public ResponseDto<SalaDto> deleteById(Long idSala) {
        ResponseDto<SalaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.DELETE);

        Optional<Sala> optionalSala = salaRepository.findById(idSala);

        if (optionalSala.isPresent()) {
            bigliettoRepository.deleteById(idSala);
            response.setHttpResponse(HttpResponseType.DELETED);
            response.setCode(HttpResponseType.DELETED.getCode());
            response.setDesc(HttpResponseType.DELETED.getDesc());
            response.setBody(optionalSala.get().toDto());
        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }

        return response;
    }

    @Override
    public ResponseDto<List<SalaDto>> getAll() {
        ResponseDto<List<SalaDto>> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        List<Sala> sale = salaRepository.findAll();

        if (sale.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(sale.stream().map(Sala::toDto).toList());
        }

        return response;
    }

    @Override
    public ResponseDto<SalaDto> getById(Long idSala) {
        ResponseDto<SalaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        Optional<Sala> optionalSala = salaRepository.findById(idSala);

        if (optionalSala.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(optionalSala.get().toDto());
        }
        return response;
    }

    @Override
    public ResponseDto<SalaDto> svuotaSala(Long idSala) {
        ResponseDto<SalaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.DELETE);

        Optional<Sala> optionalSala = salaRepository.findById(idSala);

        if (optionalSala.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            Sala sala = optionalSala.get();
            Sala newEmptySala = Sala.builder()
                    .id(sala.getId())
                    .posti(sala.getPosti())
                    .film(sala.getFilm())
                    .spettatori(new ArrayList<>())
                    .cinema(sala.getCinema())
                    .build();
            salaRepository.deleteById(idSala);
            salaRepository.save(newEmptySala);
            response.setHttpResponse(HttpResponseType.DELETED);
            response.setCode(HttpResponseType.DELETED.getCode());
            response.setDesc(HttpResponseType.DELETED.getDesc());
            response.setBody(newEmptySala.toDto());
        }
        return response;
    }

    @Override
    public ResponseDto<SalaDto> consentiIngressoSpettatore(IngressoSalaRequest ingressoSalaRequest) throws SalaAlCompleto, FilmVietatoAiMinoriException {
        ResponseDto<SalaDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);
        Long idSala = ingressoSalaRequest.getIdSala();

        Optional<Sala> optionalSala = salaRepository.findById(idSala);
        if (optionalSala.isPresent()) {
            Sala sala = optionalSala.get();

            if (sala.getSpettatori().size() < sala.getPosti()) {
                Period period = Period.between(LocalDate.now(), ingressoSalaRequest.getDataNascita());

                if (sala.getFilm().getGenereFilm().equals(GenereFilm.HORROR) && period.getYears() < 18) {
                    Optional<Biglietto> optionalBiglietto = bigliettoRepository.findById(ingressoSalaRequest.getIdBiglietto());

                    if (optionalBiglietto.isPresent()) {
                        Biglietto biglietto = optionalBiglietto.get();
                        Spettatore spettatore = Spettatore.builder()
                                .nome(ingressoSalaRequest.getNome())
                                .cognome(ingressoSalaRequest.getCognome())
                                .dataNascita(ingressoSalaRequest.getDataNascita())
                                .biglietto(biglietto)
                                .sala(sala)
                                .build();
                        spettatoreRepository.save(spettatore);
                        response.setHttpResponse(HttpResponseType.CREATED);
                        response.setCode(HttpResponseType.CREATED.getCode());
                        response.setDesc(HttpResponseType.CREATED.getDesc());
                        response.setBody(sala.toDto());
                    } else {
                        response.setHttpResponse(HttpResponseType.NOT_CREATED);
                        response.setCode(HttpResponseType.NOT_CREATED.getCode());
                        response.setDesc(HttpResponseType.NOT_CREATED.getDesc());
                    }

                } else {
                    throw new FilmVietatoAiMinoriException(sala.getFilm().getTitolo());
                }
            } else {
                throw new SalaAlCompleto(idSala);
            }

        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }
        return response;
    }

    @Override
    public IncassoSalaResponse calcolaIncasso(Long idSala) {
        IncassoSalaResponse response = new IncassoSalaResponse();
        response.setIdSala(idSala);
        response.setIncasso(0.0);
        Optional<Sala> optionalSala = salaRepository.findById(idSala);
        if (optionalSala.isPresent()) {
            List<Spettatore> spettatori = optionalSala.get().getSpettatori();
            spettatori.forEach(spettatore -> response.setIncasso(response.getIncasso() + spettatore.getBiglietto().getPrezzo()));
        }
        return response;
    }
}

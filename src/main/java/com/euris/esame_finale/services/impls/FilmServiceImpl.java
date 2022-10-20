package com.euris.esame_finale.services.impls;

import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.data.dto.models.FilmDto;
import com.euris.esame_finale.data.models.Film;
import com.euris.esame_finale.data.models.Sala;
import com.euris.esame_finale.repository.FilmRepository;
import com.euris.esame_finale.repository.SalaRepository;
import com.euris.esame_finale.services.FilmService;
import com.euris.esame_finale.utils.HttpRequestType;
import com.euris.esame_finale.utils.HttpResponseType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final SalaRepository salaRepository;

    public FilmServiceImpl(FilmRepository filmRepository, SalaRepository salaRepository) {
        this.filmRepository = filmRepository;
        this.salaRepository = salaRepository;
    }


    @Override
    public ResponseDto<FilmDto> insert(FilmDto filmDto) {
        ResponseDto<FilmDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);

        Optional<Film> optionalFilm = filmRepository.findById(filmDto.getId());
        if (optionalFilm.isEmpty()) {
            Optional<Sala> optionalSala = salaRepository.findById(filmDto.getIdSala());

            if (optionalSala.isPresent()) {
                Film film = filmDto.toModel();
                film.setSala(optionalSala.get());
                filmRepository.save(film);
                response.setHttpResponse(HttpResponseType.CREATED);
                response.setCode(HttpResponseType.CREATED.getCode());
                response.setDesc(HttpResponseType.CREATED.getDesc());
                response.setBody(filmDto);

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
    public ResponseDto<FilmDto> update(FilmDto filmDto) {
        ResponseDto<FilmDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.POST);

        Optional<Film> optionalFilm = filmRepository.findById(filmDto.getId());
        if (optionalFilm.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());

        } else {
            Optional<Sala> optionalSala = salaRepository.findById(filmDto.getIdSala());

            if (optionalSala.isPresent()) {
                Film film = filmDto.toModel();
                film.setSala(optionalSala.get());
                filmRepository.save(film);
                response.setHttpResponse(HttpResponseType.UPDATED);
                response.setCode(HttpResponseType.UPDATED.getCode());
                response.setDesc(HttpResponseType.UPDATED.getDesc());
                response.setBody(filmDto);
            }
        }
        return response;
    }

    @Override
    public ResponseDto<FilmDto> deleteById(Long idFilm) {
        ResponseDto<FilmDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.DELETE);

        Optional<Film> optionalFilm = filmRepository.findById(idFilm);

        if (optionalFilm.isPresent()) {
            filmRepository.deleteById(idFilm);
            response.setHttpResponse(HttpResponseType.DELETED);
            response.setCode(HttpResponseType.DELETED.getCode());
            response.setDesc(HttpResponseType.DELETED.getDesc());
            response.setBody(optionalFilm.get().toDto());
        } else {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        }

        return response;
    }

    @Override
    public ResponseDto<List<FilmDto>> getAll() {
        ResponseDto<List<FilmDto>> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        List<Film> filmList = filmRepository.findAll();

        if (filmList.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(filmList.stream().map(Film::toDto).toList());
        }

        return response;
    }

    @Override
    public ResponseDto<FilmDto> getById(Long idFilm) {
        ResponseDto<FilmDto> response = new ResponseDto<>();
        response.setHttpRequest(HttpRequestType.GET);

        Optional<Film> optionalFilm = filmRepository.findById(idFilm);

        if (optionalFilm.isEmpty()) {
            response.setHttpResponse(HttpResponseType.NOT_FOUND);
            response.setCode(HttpResponseType.NOT_FOUND.getCode());
            response.setDesc(HttpResponseType.NOT_FOUND.getDesc());
        } else {
            response.setHttpResponse(HttpResponseType.FOUND);
            response.setCode(HttpResponseType.FOUND.getCode());
            response.setDesc(HttpResponseType.FOUND.getDesc());
            response.setBody(optionalFilm.get().toDto());
        }
        return response;
    }
}

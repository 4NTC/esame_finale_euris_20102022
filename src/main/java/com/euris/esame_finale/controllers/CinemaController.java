package com.euris.esame_finale.controllers;

import com.euris.esame_finale.data.dto.models.CinemaDto;
import com.euris.esame_finale.data.dto.response.IncassoCinemaResponse;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.services.CinemaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping
    public ResponseDto<CinemaDto> insert() {
        return cinemaService.insertCinema();
    }

    @DeleteMapping("/{id}")
    public ResponseDto<CinemaDto> deleteById(@PathVariable Long idCinema) {
        return cinemaService.removeById(idCinema);
    }

    @GetMapping
    public ResponseDto<List<CinemaDto>> getAll() {
        return cinemaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseDto<CinemaDto> getById(@PathVariable Long idCinema) {
        return cinemaService.getById(idCinema);
    }

    @GetMapping("/incasso/{id}")
    public IncassoCinemaResponse calcolaIncasso(@PathVariable Long idCinema) {
        return cinemaService.calcolaIncasso(idCinema);
    }

}

package com.euris.esame_finale.controllers;

import com.euris.esame_finale.data.dto.models.FilmDto;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.services.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseDto<FilmDto> insert(@RequestBody FilmDto filmDto) {
        return filmService.insert(filmDto);
    }

    @PutMapping
    public ResponseDto<FilmDto> update(@RequestBody FilmDto filmDto) {
        return filmService.update(filmDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<FilmDto> deleteById(@PathVariable Long idFilm) {
        return filmService.deleteById(idFilm);
    }

    @GetMapping
    public ResponseDto<List<FilmDto>> getAll() {
        return filmService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseDto<FilmDto> getById(@PathVariable Long idFilm) {
        return filmService.getById(idFilm);
    }
}

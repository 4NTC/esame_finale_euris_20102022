package com.euris.esame_finale.controllers;

import com.euris.esame_finale.data.dto.models.SpettatoreDto;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.services.SpettatoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spettatore")
public class SpettatoreController {

    private final SpettatoreService spettatoreService;

    public SpettatoreController(SpettatoreService spettatoreService) {
        this.spettatoreService = spettatoreService;
    }

    @PostMapping
    ResponseDto<SpettatoreDto> insert(@RequestBody SpettatoreDto spettatoreDto) {
        return spettatoreService.insertSpettatore(spettatoreDto);
    }

    @PutMapping
    ResponseDto<SpettatoreDto> update(@RequestBody SpettatoreDto spettatoreDto) {
        return spettatoreService.updateSpettatore(spettatoreDto);
    }

    @DeleteMapping
    ResponseDto<SpettatoreDto> deleteById(@PathVariable Long idSpettatore) {
        return spettatoreService.removeById(idSpettatore);
    }

    @GetMapping
    ResponseDto<List<SpettatoreDto>> getAll() {
        return spettatoreService.getAll();
    }

    @GetMapping("/{id}")
    ResponseDto<SpettatoreDto> getById(@PathVariable Long idSpettatore) {
        return spettatoreService.getById(idSpettatore);
    }

    @GetMapping("/Eta/{id}")
    Integer getEtaSpettatore(@PathVariable Long idSpettatore) {
        return spettatoreService.getEtaSpettatore(idSpettatore);
    }
}

package com.euris.esame_finale.controllers;

import com.euris.esame_finale.data.dto.models.BigliettoDto;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.services.BigliettoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biglietto")
public class BigliettoController {

    private final BigliettoService bigliettoService;

    public BigliettoController(BigliettoService bigliettoService) {
        this.bigliettoService = bigliettoService;
    }

    @PostMapping
    public ResponseDto<BigliettoDto> insert(@RequestBody BigliettoDto bigliettoDto) {
        return bigliettoService.insert(bigliettoDto);
    }

    @PutMapping
    public ResponseDto<BigliettoDto> update(@RequestBody BigliettoDto bigliettoDto) {
        return bigliettoService.update(bigliettoDto);
    }

    @PutMapping("/riduzionePrezzo/{id}")
    public ResponseDto<BigliettoDto> applicaRiduzionePrezzo(@PathVariable Long idBiglietto) {
        return bigliettoService.applicaRiduzionePrezzo(idBiglietto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<BigliettoDto> deleteById(@PathVariable Long idBiglietto) {
        return bigliettoService.deleteById(idBiglietto);
    }

    @GetMapping
    public ResponseDto<List<BigliettoDto>> getAll() {
        return bigliettoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseDto<BigliettoDto> getById(@PathVariable Long idBiglietto) {
        return bigliettoService.getById(idBiglietto);
    }


}

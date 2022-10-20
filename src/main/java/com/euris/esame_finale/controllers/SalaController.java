package com.euris.esame_finale.controllers;

import com.euris.esame_finale.data.dto.models.SalaDto;
import com.euris.esame_finale.data.dto.requests.IngressoSalaRequest;
import com.euris.esame_finale.data.dto.response.IncassoSalaResponse;
import com.euris.esame_finale.data.dto.response.ResponseDto;
import com.euris.esame_finale.services.SalaService;
import com.euris.esame_finale.utils.exceptions.FilmVietatoAiMinoriException;
import com.euris.esame_finale.utils.exceptions.SalaAlCompleto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sala")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseDto<SalaDto> insert(@RequestBody SalaDto salaDto) {
        return salaService.insertSala(salaDto);
    }

    @PostMapping("/{id}")
    public ResponseDto<SalaDto> svuotaSala(@PathVariable Long idSala) {
       return salaService.svuotaSala(idSala);
    }

    @PostMapping("/consentiIngresso")
    public ResponseDto<SalaDto> consentiIngressoSpettatore(@RequestBody IngressoSalaRequest ingressoSalaRequest) {
        try {
            return salaService.consentiIngressoSpettatore(ingressoSalaRequest);
        } catch (SalaAlCompleto | FilmVietatoAiMinoriException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseDto<>();
    }

    @PutMapping
    public ResponseDto<SalaDto> update(@RequestBody SalaDto salaDto) {
        return salaService.updateSala(salaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<SalaDto> deleteById(@PathVariable Long idSala) {
        return salaService.removeById(idSala);
    }

    @GetMapping
    public ResponseDto<List<SalaDto>> getAll() {
        return salaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseDto<SalaDto> getById(@PathVariable Long idSala) {
        return salaService.getById(idSala);
    }

    @GetMapping("/incasso/{id}")
    public IncassoSalaResponse calcolaIncasso(@PathVariable Long idSala) {
        return salaService.calcolaIncasso(idSala);
    }
}

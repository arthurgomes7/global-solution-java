package com.global.orbit_bridge.controller;

import com.global.orbit_bridge.dto.SolucaoDto;
import com.global.orbit_bridge.model.SolucaoEspacial;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import com.global.orbit_bridge.service.SolucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solucoes")
public class SolucaoController {

    @Autowired
    private SolucaoService solucaoService;

    @GetMapping
    public List<SolucaoEspacial> listarSolucoes(){
        return solucaoService.listarSolucoes();
    }

    @GetMapping("/{ods}")
    public Optional<List<SolucaoEspacial>> buscarPorOds(@RequestParam Integer ods){
        return solucaoService.solucoesPorOds(ods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolucaoEspacial> buscarPorId(@PathVariable Long id) {
        return solucaoService.solucaoPorId(id);
    }

    @GetMapping("/{area}")
    public List<SolucaoEspacial> buscarPorArea(String area){
        return solucaoService.solucoesPorArea(area);
    }

    @GetMapping("/resumo")
    public String resumoSolucoes(){
        return solucaoService.resumoSolucoes();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SolucaoDto> cadastrarSolucao(@RequestBody SolucaoDto solucaoDto){
        solucaoService.cadastrarSolucao(solucaoDto);
        return ResponseEntity.ok(solucaoDto);
    }

    @PutMapping
    public ResponseEntity<SolucaoDto> atualizarSolucao(@RequestParam Long id, @RequestBody SolucaoDto solucaoDto){
        return solucaoService.atualizarSolucao(id, solucaoDto);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<SolucaoEspacial> alterarStatus(
            @PathVariable Long id,
            @RequestParam StatusSolucao status) {

        return solucaoService.alterarStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SolucaoDto> deletarSolucao(@RequestParam Long id){
        return solucaoService.deletarSolucao(id);
    }
}

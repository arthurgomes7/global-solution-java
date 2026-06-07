package com.global.orbit_bridge.controller;

import com.global.orbit_bridge.dto.SolucaoDto;
import com.global.orbit_bridge.model.SolucaoEspacial;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import com.global.orbit_bridge.service.SolucaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solucoes")
public class SolucaoController {

    @Autowired
    private SolucaoService solucaoService;

    @GetMapping
    public List<SolucaoEspacial> listarSolucoes(){
        return solucaoService.listarSolucoes();
    }

    @GetMapping("/ods/{ods}")
    public List<SolucaoEspacial> buscarPorOds(
            @PathVariable Integer ods) {
        return solucaoService.solucoesPorOds(ods);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SolucaoEspacial> buscarPorId(@PathVariable Long id) {
        return solucaoService.solucaoPorId(id);
    }

    @GetMapping("/area/{area}")
    public List<SolucaoEspacial> buscarPorArea(@PathVariable String area){
        return solucaoService.solucoesPorArea(area);
    }

    @GetMapping("/resumo")
    public String resumoSolucoes(){
        return solucaoService.resumoSolucoes();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SolucaoDto> cadastrarSolucao(@RequestBody @Valid SolucaoDto solucaoDto){
        solucaoService.cadastrarSolucao(solucaoDto);
        return ResponseEntity.ok(solucaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolucaoEspacial> atualizarSolucao(@PathVariable Long id, @RequestBody @Valid SolucaoDto solucaoDto){
        return solucaoService.atualizarSolucao(id, solucaoDto);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<SolucaoEspacial> alterarStatus(
            @PathVariable Long id,
            @RequestParam StatusSolucao status) {

        return solucaoService.alterarStatus(id, status);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<SolucaoDto> deletarSolucao(@PathVariable Long id){
        return solucaoService.deletarSolucao(id);
    }
}

package com.global.orbit_bridge.controller;

import com.global.orbit_bridge.dto.OrganizacaoDto;
import com.global.orbit_bridge.model.Organizacao;
import com.global.orbit_bridge.service.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizacoes")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService organizacaoService;

    @GetMapping
    public List<Organizacao> listarOrganizacoes(){
        return organizacaoService.listarOrganizacoes();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<OrganizacaoDto> salvarOrganizacao(@RequestBody OrganizacaoDto organizacaoDto){
        organizacaoService.salvarOrganizacao(organizacaoDto);

        return ResponseEntity.ok(organizacaoDto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<OrganizacaoDto> excluirOrganizacao(@RequestParam Long id){
        organizacaoService.excluirOrganizacao(id);
        return ResponseEntity.noContent().build();
    }
}
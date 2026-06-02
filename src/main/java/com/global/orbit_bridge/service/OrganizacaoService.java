package com.global.orbit_bridge.service;

import com.global.orbit_bridge.dto.OrganizacaoDto;
import com.global.orbit_bridge.model.Organizacao;
import com.global.orbit_bridge.repository.OrganizacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacaoService {
    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @Transactional
    public ResponseEntity<Organizacao> salvarOrganizacao(OrganizacaoDto organizacaoDto) {
        Organizacao organizacao = Organizacao.builder()
                .nome(organizacaoDto.getNome())
                .descricao(organizacaoDto.getDescricao())
                .build();

        organizacaoRepository.save(organizacao);
        return ResponseEntity.ok(organizacao);
    }

    @Transactional
    public ResponseEntity<Organizacao> excluirOrganizacao(Long idOrganizacao) {
        if (!organizacaoRepository.existsById(idOrganizacao)){
            return ResponseEntity.badRequest().build();
        }

        organizacaoRepository.deleteById(idOrganizacao);
        return ResponseEntity.noContent().build();
    }

    public List<Organizacao> listarOrganizacoes() {
        return organizacaoRepository.findAll();
    }
}

package com.global.orbit_bridge.service;

import com.global.orbit_bridge.dto.OrganizacaoDto;
import com.global.orbit_bridge.model.Organizacao;
import com.global.orbit_bridge.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacaoService {
    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    public void salvarOrganizacao(OrganizacaoDto organizacaoDto) {
        Organizacao organizacao = Organizacao.builder()
                .nome(organizacaoDto.getNome())
                .descricao(organizacaoDto.getDescricao())
                .build();

        organizacaoRepository.save(organizacao);
    }

    public void excluirOrganizacao(Long idOrganizacao) {
        organizacaoRepository.deleteById(idOrganizacao);
    }

    public List<Organizacao> listarOrganizacoes() {
        return organizacaoRepository.findAll();
    }
}

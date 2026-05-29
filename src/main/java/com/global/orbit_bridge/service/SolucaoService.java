package com.global.orbit_bridge.service;

import com.global.orbit_bridge.dto.SolucaoDto;
import com.global.orbit_bridge.model.SolucaoEspacial;
import com.global.orbit_bridge.model.enums.Prioridade;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import com.global.orbit_bridge.repository.SolucaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolucaoService {
    @Autowired
    private SolucaoRepository solucaoRepository;

    public void cadastrarSolucao(SolucaoDto solucaoDto) {
        SolucaoEspacial solucaoEspacial = SolucaoEspacial.builder()
                .nome(solucaoDto.getNome())
                .descricao(solucaoDto.getDescricao())
                .areaAtuacao(solucaoDto.getAreaAtuacao())
                .status(solucaoDto.getStatus())
                .prioridade(solucaoDto.getPrioridade())
                .ods(solucaoDto.getOds())
                .idOrganizacaoResponsavel(solucaoDto.getIdOrganizacaoResponsavel())
                .build();

        solucaoRepository.save(solucaoEspacial);
    }

    public List<SolucaoEspacial> listarSolucoes() {
        return solucaoRepository.findAll();
    }

    public ResponseEntity<SolucaoEspacial> solucaoPorId(Long id) {
        return ResponseEntity.ok(solucaoRepository.getReferenceById(id));
    }

    public Optional<List<SolucaoEspacial>> solucoesPorArea(String areaAtuacao) {
        return solucaoRepository.getAllByAreaAtuacao(areaAtuacao);
    }

    @Transactional
    public ResponseEntity<SolucaoEspacial> atualizarSolucao(Long id, SolucaoDto solucaoDto) {
        if (solucaoRepository.existsById(id)) {
            SolucaoEspacial solucaoEspacial = solucaoRepository.getReferenceById(id);

            solucaoEspacial.setNome(solucaoDto.getNome());
            solucaoEspacial.setDescricao(solucaoDto.getDescricao());
            solucaoEspacial.setAreaAtuacao(solucaoDto.getAreaAtuacao());
            solucaoEspacial.setStatus(solucaoDto.getStatus());
            solucaoEspacial.setPrioridade(solucaoDto.getPrioridade());
            solucaoEspacial.setOds(solucaoDto.getOds());
            solucaoEspacial.setIdOrganizacaoResponsavel(solucaoDto.getIdOrganizacaoResponsavel());

            solucaoRepository.save(solucaoEspacial);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<SolucaoEspacial> alterarStatus(Long id, StatusSolucao status) {
        if (!solucaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        SolucaoEspacial solucaoEspacial = solucaoRepository.getReferenceById(id);
        solucaoEspacial.setStatus(status);

        return ResponseEntity.ok(solucaoEspacial);
    }

    public ResponseEntity<SolucaoEspacial> deletarSolucao(Long id) {
        if (solucaoRepository.existsById(id)) {
            solucaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Optional<List<SolucaoEspacial>> solucoesPorOds(Integer ods) {
        return solucaoRepository.getAllByOds(ods);
    }

    public String resumoSolucoes() {

        Map<StatusSolucao, Long> quantidadePorStatus =
                solucaoRepository.findAll()
                        .stream()
                        .collect(Collectors.groupingBy(
                                SolucaoEspacial::getStatus,
                                Collectors.counting()
                        ));

        Map<String, Long> quantidadePorArea =
                solucaoRepository.findAll()
                        .stream()
                        .collect(Collectors.groupingBy(
                                SolucaoEspacial::getAreaAtuacao,
                                Collectors.counting()
                        ));

        Map<Prioridade, Long> quantidadePorPrioridade =
                solucaoRepository.findAll()
                        .stream()
                        .collect(Collectors.groupingBy(
                                SolucaoEspacial::getPrioridade,
                                Collectors.counting()
                        ));

        return "" +
                "Soluções cadastradas: " + solucaoRepository.count() +
                "Quantidade por Status: " +
                "   Em analise : " + quantidadePorStatus.getOrDefault(StatusSolucao.EM_ANALISE, 0L) +
                "   Em desenvolvimento : " + quantidadePorStatus.getOrDefault(StatusSolucao.EM_DESENVOLVIMENTO, 0L) +
                "   Implementada: " + quantidadePorStatus.getOrDefault(StatusSolucao.IMPLEMENTADA, 0L) +
                "   Pausada: " + quantidadePorStatus.getOrDefault(StatusSolucao.PAUSADA, 0L) +
                "   Inativa: " + quantidadePorStatus.getOrDefault(StatusSolucao.INATIVA, 0L) +
                "Quantidade por Área:" +
                "" +
                "Quantidade por Prioridade Alta: " + solucaoRepository.findAllByPrioridadeAlta();
    }
}
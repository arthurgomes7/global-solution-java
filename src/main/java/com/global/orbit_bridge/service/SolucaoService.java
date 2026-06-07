package com.global.orbit_bridge.service;

import com.global.orbit_bridge.dto.SolucaoDto;
import com.global.orbit_bridge.exceptions.SolucaoException;
import com.global.orbit_bridge.model.SolucaoEspacial;
import com.global.orbit_bridge.model.enums.Prioridade;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import com.global.orbit_bridge.repository.SolucaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SolucaoService {
    @Autowired
    private SolucaoRepository solucaoRepository;

    @Transactional
    public ResponseEntity<SolucaoDto> cadastrarSolucao(SolucaoDto solucaoDto) { //Valid
        SolucaoEspacial solucaoEspacial = SolucaoEspacial.builder()
                .nome(solucaoDto.getNome())
                .descricao(solucaoDto.getDescricao())
                .areaAtuacao(solucaoDto.getAreaAtuacao())
                .status(solucaoDto.getStatus())
                .prioridade(solucaoDto.getPrioridade())
                .ods(solucaoDto.getOds())
                .idOrganizacaoResponsavel(solucaoDto.getIdOrganizacaoResponsavel())
                .dataCriacao(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .build();

        solucaoRepository.save(solucaoEspacial);
        return ResponseEntity.ok(solucaoDto);
    }

    public List<SolucaoEspacial> listarSolucoes() {
        return solucaoRepository.findAll();
    }

    public ResponseEntity<SolucaoEspacial> solucaoPorId(Long id) {
        if (solucaoRepository.existsById(id)) {
            return ResponseEntity.ok(solucaoRepository.getReferenceById(id));
        }
        return ResponseEntity.notFound().build();
    }

    public List<SolucaoEspacial> solucoesPorArea(String areaAtuacao) {
        return solucaoRepository.findAllByAreaAtuacao(areaAtuacao).orElseThrow(() -> new SolucaoException("Erro ao consultar Área de Atuação"));
    }

    @Transactional
    public ResponseEntity<SolucaoEspacial> atualizarSolucao(Long id, SolucaoDto solucaoDto) {
        if (solucaoRepository.existsById(id)) {
            SolucaoEspacial solucaoEspacial = solucaoRepository.getReferenceById(id);
            if (solucaoEspacial.getStatus() != StatusSolucao.INATIVA || solucaoEspacial.getStatus() != StatusSolucao.PAUSADA) {

                solucaoEspacial.setNome(solucaoDto.getNome());
                solucaoEspacial.setDescricao(solucaoDto.getDescricao());
                solucaoEspacial.setAreaAtuacao(solucaoDto.getAreaAtuacao());
                solucaoEspacial.setStatus(solucaoDto.getStatus());
                solucaoEspacial.setPrioridade(solucaoDto.getPrioridade());
                solucaoEspacial.setOds(solucaoDto.getOds());
                solucaoEspacial.setDataAtualizacao(LocalDateTime.now());

                solucaoRepository.save(solucaoEspacial);
                return ResponseEntity.ok(solucaoEspacial);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<SolucaoEspacial> alterarStatus(Long id, StatusSolucao status) {
        if (!solucaoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        SolucaoEspacial solucaoEspacial = solucaoRepository.getReferenceById(id);

        if (solucaoEspacial.getStatus() == StatusSolucao.INATIVA || solucaoEspacial.getStatus() == StatusSolucao.PAUSADA) {
            return ResponseEntity.badRequest().build();
        }
        solucaoEspacial.setStatus(status);
        solucaoEspacial.setDataAtualizacao(LocalDateTime.now());
        return ResponseEntity.ok(solucaoEspacial);
    }

    @Transactional
    public ResponseEntity<SolucaoDto> deletarSolucao(Long id) {
        if (solucaoRepository.existsById(id)) {
            SolucaoEspacial s = solucaoRepository.getReferenceById(id);
            s.setStatus(StatusSolucao.INATIVA);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public List<SolucaoEspacial> solucoesPorOds(Integer ods) {
        return solucaoRepository.findByOdsContaining(ods);
    }

    @Transactional
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
                "\nQuantidade por Status: " +
                "   \n-> Em analise : " + quantidadePorStatus.getOrDefault(StatusSolucao.EM_ANALISE, 0L) +
                "   \n-> Em desenvolvimento : " + quantidadePorStatus.getOrDefault(StatusSolucao.EM_DESENVOLVIMENTO, 0L) +
                "   \n-> Implementada: " + quantidadePorStatus.getOrDefault(StatusSolucao.IMPLEMENTADA, 0L) +
                "   \n-> Pausada: " + quantidadePorStatus.getOrDefault(StatusSolucao.PAUSADA, 0L) +
                "   \n-> Inativa: " + quantidadePorStatus.getOrDefault(StatusSolucao.INATIVA, 0L) +
                "\nQuantidade por Área: " + solucaoRepository.count() +
                "" +
                "\nQuantidade por Prioridade Alta: " + solucaoRepository.findAllByPrioridade(Prioridade.ALTA).stream().count();
    }
}
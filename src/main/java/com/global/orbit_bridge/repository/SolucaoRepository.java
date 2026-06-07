package com.global.orbit_bridge.repository;

import com.global.orbit_bridge.model.SolucaoEspacial;
import com.global.orbit_bridge.model.enums.Prioridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolucaoRepository extends JpaRepository<SolucaoEspacial, Long> {
    Optional<List<SolucaoEspacial>> findAllByAreaAtuacao(String areaAtuacao);

    List<SolucaoEspacial> findByOdsContaining(Integer ods);

    List<SolucaoEspacial> findAllByPrioridade(Prioridade prioridade);
}

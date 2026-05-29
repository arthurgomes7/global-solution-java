package com.global.orbit_bridge.repository;

import com.global.orbit_bridge.model.SolucaoEspacial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolucaoRepository extends JpaRepository<SolucaoEspacial, Long> {
    Optional<List<SolucaoEspacial>> getAllByAreaAtuacao(String areaAtuacao);

    Optional<List<SolucaoEspacial>> getAllByOds(Integer ods);

    Integer findAllByPrioridadeAlta();
}

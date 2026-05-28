package com.global.orbit_bridge.repository;

import com.global.orbit_bridge.model.SolucaoEspacial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolucaoRepository extends JpaRepository<SolucaoEspacial, Long> {
}

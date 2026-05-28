package com.global.orbit_bridge.repository;

import com.global.orbit_bridge.model.Organizacao;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
}

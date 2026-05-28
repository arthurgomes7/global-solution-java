package com.global.orbit_bridge.dto;

import com.global.orbit_bridge.model.SolucaoEspacial;
import lombok.Data;

import java.util.Set;
@Data
public class OrganizacaoDto {
    private String nome;
    private String descricao;
    private Set<SolucaoEspacial> solucoes;
}

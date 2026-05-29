package com.global.orbit_bridge.dto;

import com.global.orbit_bridge.model.SolucaoEspacial;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;
@Data
public class OrganizacaoDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    private Set<SolucaoEspacial> solucoes;
}
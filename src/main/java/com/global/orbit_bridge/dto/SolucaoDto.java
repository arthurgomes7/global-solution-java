package com.global.orbit_bridge.dto;

import com.global.orbit_bridge.model.Organizacao;
import com.global.orbit_bridge.model.enums.Prioridade;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SolucaoDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private String areaAtuacao;
    private StatusSolucao status;
    private Prioridade prioridade;
    private List<Integer> ods;
    private Organizacao idOrganizacaoResponsavel;
}

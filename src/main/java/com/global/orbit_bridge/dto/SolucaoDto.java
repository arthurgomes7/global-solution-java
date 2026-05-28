package com.global.orbit_bridge.dto;

import com.global.orbit_bridge.model.Organizacao;
import com.global.orbit_bridge.model.enums.Prioridade;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import lombok.Data;

import java.util.List;

@Data
public class SolucaoDto {
    private String nome;
    private String descricao;
    private String areaAtuacao;
    private StatusSolucao status;
    private Prioridade prioridade;
    private List<String> ods;
    private Organizacao idOrganizacaoResponsavel;
}

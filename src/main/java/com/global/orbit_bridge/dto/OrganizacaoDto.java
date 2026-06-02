package com.global.orbit_bridge.dto;

import com.global.orbit_bridge.model.SolucaoEspacial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;
@Data
public class OrganizacaoDto {
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 30, message = "O nome não pode ter mais do que 30 caracteres")
    private String nome;
    private String descricao;
    private Set<SolucaoEspacial> solucoes;
}
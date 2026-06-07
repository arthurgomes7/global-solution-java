package com.global.orbit_bridge.dto;

import com.global.orbit_bridge.model.Organizacao;
import com.global.orbit_bridge.model.enums.Prioridade;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class SolucaoDto {
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 30, message = "O nome não pode ter mais que 30 caracteres")
    @Column(unique = true)
    private String nome;
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;
    @NotBlank
    @Size(max = 30, message = "A área de atuação não pode ter mais que 30 caracteres")
    private String areaAtuacao;
    private StatusSolucao status;
    private Prioridade prioridade;
    private List<Integer> ods;
    @ManyToOne
    private Organizacao idOrganizacaoResponsavel;
}

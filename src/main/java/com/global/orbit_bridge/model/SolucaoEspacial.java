package com.global.orbit_bridge.model;

import com.global.orbit_bridge.model.enums.Prioridade;
import com.global.orbit_bridge.model.enums.StatusSolucao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolucaoEspacial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String descricao;
    private String areaAtuacao;
    private StatusSolucao status;
    private Prioridade prioridade;
    private List<Integer> ods;
    @ManyToOne
    private Organizacao idOrganizacaoResponsavel;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

package com.global.orbit_bridge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class OrganizacaoDto {
    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 30, message = "O nome não pode ter mais do que 30 caracteres")
    @Column(unique = true)
    private String nome;
    private String descricao;
    @JsonIgnore
    private List<Long> idSolucoes;
}
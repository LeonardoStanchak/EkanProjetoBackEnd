package br.com.backend.ekan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "beneficiario_model")
public class BeneficiarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @NotNull
    @OneToOne(mappedBy = "beneficiario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private DocumentoModel dto;
    @NotNull
    private Date dataNascimento;
    @NotNull
    private Date dataInclusao;
    @NotNull
    private Date dataAtualizacao;



}

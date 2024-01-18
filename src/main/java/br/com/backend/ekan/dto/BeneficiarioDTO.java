package br.com.backend.ekan.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioDTO {

    private String nome;

    private String telefone;

    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;

}

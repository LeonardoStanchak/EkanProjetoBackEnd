package br.com.backend.ekan.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoucmentoDTO {
    private String tipoDeDocumento;

    private String descricao;

    private Date dataInclusao;
    private Date dataAtualizacao;
}

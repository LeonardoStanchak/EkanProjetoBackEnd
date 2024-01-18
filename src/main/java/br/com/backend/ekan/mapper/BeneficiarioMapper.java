package br.com.backend.ekan.mapper;

import br.com.backend.ekan.dto.BeneficiarioDTO;
import br.com.backend.ekan.model.BeneficiarioModel;
import org.springframework.stereotype.Component;

@Component
public class BeneficiarioMapper {

    public BeneficiarioDTO lsitaBeneficiarios(BeneficiarioModel beneficiarioModel) {

        return BeneficiarioDTO.builder()
                .nome(beneficiarioModel.getNome())
                .telefone(beneficiarioModel.getTelefone())
                .dataNascimento(beneficiarioModel.getDataNascimento())
                .dataInclusao(beneficiarioModel.getDataInclusao())
                .dataAtualizacao(beneficiarioModel.getDataAtualizacao())
                .build();

    }
}

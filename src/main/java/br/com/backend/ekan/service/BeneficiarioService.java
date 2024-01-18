package br.com.backend.ekan.service;

import br.com.backend.ekan.dto.BeneficiarioDTO;
import br.com.backend.ekan.model.BeneficiarioModel;
import br.com.backend.ekan.model.DocumentoModel;

import java.util.List;
import java.util.Optional;

public interface BeneficiarioService {
    BeneficiarioModel novoBeneficiario(BeneficiarioModel beneficiarioModel);
    List<BeneficiarioDTO> beneficiarios();
    List<DocumentoModel> listarDocumentosDoBeneficiario(Long id);
    BeneficiarioModel  atualizarDadosCadastrais(Long id, BeneficiarioModel novosDados);

    void removerBeneficiario(Long id);
}

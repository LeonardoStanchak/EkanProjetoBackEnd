package br.com.backend.ekan.service.impl;

import br.com.backend.ekan.dto.BeneficiarioDTO;
import br.com.backend.ekan.mapper.BeneficiarioMapper;
import br.com.backend.ekan.model.BeneficiarioModel;
import br.com.backend.ekan.model.DocumentoModel;
import br.com.backend.ekan.repository.BeneficiarioRepository;
import br.com.backend.ekan.repository.DocumentoRepository;
import br.com.backend.ekan.service.BeneficiarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BeneficiarioImpl implements BeneficiarioService {

    private BeneficiarioRepository beneficiarioRepository;

    private DocumentoRepository documentoRepository;
    private BeneficiarioMapper mapper;

    @Autowired
    public BeneficiarioImpl(BeneficiarioRepository beneficiarioRepository, BeneficiarioMapper mapper, DocumentoRepository documentoRepository){
        this.beneficiarioRepository = beneficiarioRepository;
        this.mapper = mapper;
        this.documentoRepository = documentoRepository;
    }

    @Override
    public BeneficiarioModel novoBeneficiario(BeneficiarioModel beneficiarioModel) {
        return beneficiarioRepository.save(beneficiarioModel);
    }

    @Override
    public List<BeneficiarioDTO> beneficiarios() {
        List<BeneficiarioModel> beneficiarioDTOS = beneficiarioRepository.findAll();
        return beneficiarioDTOS.stream().map(x -> mapper.lsitaBeneficiarios(x)).collect(Collectors.toList());
    }

    public List<DocumentoModel> listarDocumentosDoBeneficiario(Long id) {
        Optional<BeneficiarioModel> beneficiarioOptional = beneficiarioRepository.findById(id);
        return documentoRepository.findByBeneficiarioId(beneficiarioOptional.get().getId());
    }

    public BeneficiarioModel atualizarDadosCadastrais(Long id, BeneficiarioModel novosDados) {
        Optional<BeneficiarioModel> beneficiarioOptional = beneficiarioRepository.findById(id);

        if (beneficiarioOptional.isPresent()) {
            BeneficiarioModel beneficiario = beneficiarioOptional.get();
            beneficiario.setTelefone(novosDados.getTelefone());
            beneficiario.setDataAtualizacao(novosDados.getDataAtualizacao());
            beneficiario.getDto().setDataAtualizacao(novosDados.getDataAtualizacao());
            return beneficiarioRepository.save(beneficiario);
        } else {
            throw new IllegalArgumentException("Beneficiário não encontrado com o ID: " + id);
        }
    }

    public void removerBeneficiario(Long id) {
        Optional<BeneficiarioModel> beneficiarioOptional = beneficiarioRepository.findById(id);

        if (beneficiarioOptional.isPresent()) {
            beneficiarioRepository.deleteById(id);
            documentoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Beneficiário não encontrado com o ID: " + id);
        }
    }
}

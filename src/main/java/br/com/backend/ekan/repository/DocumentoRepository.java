package br.com.backend.ekan.repository;

import br.com.backend.ekan.model.DocumentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DocumentoRepository  extends JpaRepository<DocumentoModel, Long> {
    List<DocumentoModel> findByBeneficiarioId(Long id);
}

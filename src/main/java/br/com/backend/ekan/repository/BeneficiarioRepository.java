package br.com.backend.ekan.repository;

import br.com.backend.ekan.model.BeneficiarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiarioRepository extends JpaRepository<BeneficiarioModel, Long> {
}

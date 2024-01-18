package br.com.backend.ekan.dto;

import br.com.backend.ekan.util.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String email, @NotNull String password, @NotNull UserRole role ) {

}
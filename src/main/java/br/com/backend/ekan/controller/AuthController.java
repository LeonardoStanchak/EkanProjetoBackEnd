package br.com.backend.ekan.controller;

import br.com.backend.ekan.dto.AuthetinticationDto;
import br.com.backend.ekan.dto.RegisterDTO;
import br.com.backend.ekan.service.impl.AuthorizationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "Ekan-Segurança")
public class AuthController {

    @Autowired
    private AuthorizationServiceImpl authorizationService;

    @Operation(summary = "Login do usuario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salva um novo beneficiario"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar login de usuario"),
    })
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthetinticationDto authetinticationDto){
        return authorizationService.login(authetinticationDto);
    }

    @Operation(summary = "Registra um novo usuario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registra um novo usuario"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro de usuario"),
    })
    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
        return authorizationService.register(registerDto);
    }
}

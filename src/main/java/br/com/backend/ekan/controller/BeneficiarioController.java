package br.com.backend.ekan.controller;

import br.com.backend.ekan.dto.BeneficiarioDTO;
import br.com.backend.ekan.model.BeneficiarioModel;
import br.com.backend.ekan.model.DocumentoModel;
import br.com.backend.ekan.service.BeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/ekan", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "Ekan-Beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService service;

    @Operation(summary = "Registra um novo beneficiario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registra um novo beneficiario"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar registro de um benificiario"),
    })
    @PostMapping("/novo_beneficiario")
    public ResponseEntity<BeneficiarioModel> beneficiarioModelResponseEntity(@RequestBody BeneficiarioModel model){
        model = service.novoBeneficiario(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @Operation(summary = "Busca todos os beneficiarios", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca todos os beneficiarios"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de beneficiarios"),
    })
    @GetMapping("/beneficiarios")
    public ResponseEntity<List<BeneficiarioDTO>> listaBeneficiairos(){
        List<BeneficiarioDTO> beneficiairos = service.beneficiarios();
        return ResponseEntity.ok().body(beneficiairos);
    }


    @Operation(summary = "Busca beneficiario por id", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca beneficiario por id"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de beneficiarios por id"),
    })
    @GetMapping("/{id}/documentos")
    public ResponseEntity<List<DocumentoModel>> listarDocumentosDoBeneficiario(@PathVariable Long id) {
        List<DocumentoModel> documentos = service.listarDocumentosDoBeneficiario(id);
        return ResponseEntity.ok(documentos);
    }


    @Operation(summary = "Atualiza um beneficiairo por id", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualiza um beneficiairo por id"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar um beneficiario")})
    @PutMapping("atualizando_benificiario/{id}")
    public ResponseEntity<BeneficiarioModel> atualizarDadosCadastrais(
            @PathVariable Long id,
            @RequestBody BeneficiarioModel novosDados) {
        BeneficiarioModel beneficiarioAtualizado = service.atualizarDadosCadastrais(id, novosDados);
        return ResponseEntity.ok(beneficiarioAtualizado);
    }

    @Operation(summary = "Remove um beneficiario da base de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remove um beneficiario da base de dados"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar remoção de um usuario")})
    @DeleteMapping("/{beneficiarioId}")
    public ResponseEntity<Void> removerBeneficiario(@PathVariable Long beneficiarioId) {
        service.removerBeneficiario(beneficiarioId);
        return ResponseEntity.noContent().build();
    }

}

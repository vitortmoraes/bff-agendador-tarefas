package com.vitormoraes.bffagendadortarefas.controller;

import com.vitormoraes.bffagendadortarefas.business.UsuarioService;
import com.vitormoraes.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.vitormoraes.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.vitormoraes.bffagendadortarefas.infrastructure.client.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários.")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuários.", description = "Cria um novo usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso.")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")

    public ResponseEntity<UsuarioDTOResponse> salvausuario(@RequestBody UsuarioDTORequest usuarioDTORequest){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Login usuários.", description = "Login de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Credenciais inválidas.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuários por email.", description = "Buscar dados do usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(value = "Authorization",
                                                                   required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token)); //vai converter para uma resposta http
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuários por id.", description = "Deleta usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário deletado.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(value = "Authorization",
                                                              required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuários.", description = "Atualizar dados de usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<UsuarioDTORequest> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(value = "Authorization",
                                                                   required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereço de usuários.", description = "Atualiza endereço de usuários.")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<EnderecoDTORequest> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                               @RequestParam("id") Long id,
                                                               @RequestHeader(value = "Authorization",
                                                                required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone de usuários.", description = "Atualiza telefone de usuários.")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TelefoneDTORequest> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                               @RequestParam("id") Long id,
                                                               @RequestHeader(value = "Authorization",
                                                                required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereço de usuários.", description = "Salva endereço de usuários.")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<EnderecoDTORequest> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                               @RequestHeader(value = "Authorization",
                                                                required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuários.", description = "Salva telefone de usuários.")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<TelefoneDTORequest> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                               @RequestHeader(value = "Authorization",
                                                                required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }
}

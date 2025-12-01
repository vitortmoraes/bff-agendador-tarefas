package com.vitormoraes.bffagendadortarefas.infrastructure.client;

import com.vitormoraes.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.vitormoraes.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")

public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                           @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);

    @DeleteMapping("/{email}")
    void deleteUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTORequest atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTORequest atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                        @RequestParam("id") Long id,
                                        @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTORequest atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                        @RequestParam("id") Long id,
                                        @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTORequest cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                        @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTORequest cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                        @RequestHeader("Authorization") String token);
}

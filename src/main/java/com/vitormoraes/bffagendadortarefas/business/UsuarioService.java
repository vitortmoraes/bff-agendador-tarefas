package com.vitormoraes.bffagendadortarefas.business;

import com.vitormoraes.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.vitormoraes.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.vitormoraes.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        client.deleteUsuarioPorEmail(email, token);
    }

    public UsuarioDTORequest atualizaDadosUsuario (String token, UsuarioDTORequest dto){
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTORequest atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTORequest atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token){
        return client.atualizaTelefone(dto, idTelefone, token);
    }

    public EnderecoDTORequest cadastraEndereco(String token, EnderecoDTORequest dto){
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTORequest cadastraTelefone(String token, TelefoneDTORequest dto){
        return client.cadastraTelefone(dto, token);
    }
}
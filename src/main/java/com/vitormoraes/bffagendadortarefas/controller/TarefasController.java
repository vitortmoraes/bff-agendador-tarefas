package com.vitormoraes.bffagendadortarefas.controller;

import com.vitormoraes.bffagendadortarefas.business.TarefasService;
import com.vitormoraes.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.vitormoraes.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.vitormoraes.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.vitormoraes.bffagendadortarefas.infrastructure.client.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários.")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)


public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas de usuários.", description = "Cria uma nova tarefa.")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")

    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por período.", description = "Busca tarefas cadastradas por período.")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")

    public ResponseEntity<List<TarefasDTOResponse>> bucasListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista de tarefas por email.",
            description = "Busca tarefas cadastradas por email de usuário.")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")

    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(value = "Authorization",
            required = false) String token){
        List<TarefasDTOResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por id.", description = "Deleta tarefas cadastradas por id.")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")

    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam("id") String id,
                                                   @RequestHeader(value = "Authorization", required = false) String token){
        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefa.", description = "Altera status de tarefas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Status da Tarefa alterado com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")

    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefa.", description = "Altera dados de tarefas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")

    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}

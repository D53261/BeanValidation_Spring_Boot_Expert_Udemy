package com.example.cursoudemy.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {  // Representa uma resposta de erro padrao da API
    public static ErroResposta respostaPadrao(String mensagem) {  // Cria uma resposta de erro padrao com status 400 Bad Request
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());  // Retorna uma nova instancia de ErroResposta com status 400, mensagem fornecida e lista vazia de erros
    }

    public static ErroResposta conflito(String mensagem) {  // Cria uma resposta de erro de conflito com status 409 Conflict
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());  // Retorna uma nova instancia de ErroResposta com status 409, mensagem fornecida e lista vazia de erros
    }
}

package com.example.cursoudemy.libraryapi.controller;

import com.example.cursoudemy.libraryapi.controller.dto.AutorDTO;
import com.example.cursoudemy.libraryapi.models.Autor;
import com.example.cursoudemy.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/autores")
// Tera a URL base http://localhost:8080/autores
// Controlador REST para gerenciar recursos de Autor
public class AutorController {
    // Injeta o AutorService via construtor
    private final AutorService service;

    // Construtor para injeção de dependência
    public AutorController(AutorService service) {
        // Atribui o serviço injetado ao campo da classe
        this.service = service;
    }

    @PostMapping // Mapeia requisicoes HTTP POST para este metodo, pode ser posto uma URL mapeada dentro dos parenteses
    // Pode ser colocado tambem o @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {
        Autor autorEntity = autor.mapearParaAutor();
        service.salvar(autorEntity);

        // Construindo a URI do recurso criado para retornar no cabecalho da resposta
        // Isto serve para seguir o padrao REST de retornar a localizacao do recurso criado na resposta
        URI location = ServletUriComponentsBuilder // Classe utilitaria para construir URIs com base na requisicao atual
                .fromCurrentRequest() // Pega a URI da requisicao atual (http://localhost:8080/autores)
                .path("/{id}") // Adiciona o caminho /{id} a URI
                .buildAndExpand(autorEntity.getId()) // Substitui {id} pelo ID do autor criado
                .toUri(); // Converte para o tipo URI

        return ResponseEntity.created(location).build(); // Retorna status 201 Created com o cabecalho Location
    }
}
